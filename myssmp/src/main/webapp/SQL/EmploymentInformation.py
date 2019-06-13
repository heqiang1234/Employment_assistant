#!/usr/bin/python3
#encoding=utf-8
import json
from bs4 import BeautifulSoup
import requests

import mysql.connector
import datetime
class Database:
	#初始化数据库连接信息
	def __init__(self,host,user,password,database):
		self.host = host
		self.user = user
		self.password = password
		self.database = database
	def initconn(self):
		try:
			self.conn = mysql.connector.connect(host=self.host,user=self.user, password=self.password, database=self.database)
			return 1
		except Exception as e:
			print(e)
			return None
	def insert(self,sql,data):
		cursor = self.conn.cursor()
		try:
			cursor.execute(sql,data)
			self.conn.commit()
		except Exception as e:
			print(sql)
			print(e)
			self.conn.rollback()
		cursor.close()
	def update(self,sql,data):
		cursor = self.conn.cursor()
		try:
			cursor.execute(sql,data)
			self.conn.commit()
		except Exception as e:
			print(sql)
			print(e)
			self.conn.rollback()
		cursor.close()
	def select(self,sql,data):
		cursor = self.conn.cursor()
		try:
			cursor.execute(sql,data)
			returndata = cursor.fetchall()
			cursor.close()
			return returndata
		except Exception as e:
			print(sql)
			print(e)
			self.conn.rollback()
			cursor.close()
			return None
	def close(self):
		self.conn.close()

#"""江西财经大学就业信息网数据"""
class Information:
	#初始化
	def __init__(self):
		self.num = 0#记录数据条数
		self.data = 0#记录当前获取的全部数据条数
		self.nowdatanum = 0
		self.outtimenum = 0#统计超时次数
		self.outtime = 300
		self.outtimemaxnum = 5
		self.session_requests = requests.session()
		self.error_num = 0
		self.error_string = ''
		self.islogin = None
		self.login_data = {}
	def setouttime(self,outtime):
		self.outtime = outtime
	def setouttimemaxnum(self,outtimemaxnum):
		self.outtimemaxnum = outtimemaxnum
	#获得大学就业网信息
	def initdata(self,career_address,school_id,school_name,num_url,careers_url,career_url,job_url,database):
		self.outtimenum = 0
		print('数据数目：'+str(self.getnum(num_url))+'条')
		for value in range(1,self.num):
			#print(self.data,value)
			#if self.data>=100 or self.data>=self.num:
			if self.data>=self.num:
				break
			self.outtimenum = 0
			print('\r [{},{},{}] downloads...'.format(school_name,self.data,value),end='')
			data = self.getdata(100,value,careers_url,school_name)
			print('\r',end='')
			#IO操作
			#with open('D:/jxufe.json','a+',encoding='utf-8', errors='ignore') as f:
				#f.write(str(str1))
			#self.database(data,'jxufe')
			#self.data = self.data+len(data)
			if data:
				self.database(data,career_address,school_id,school_name,career_url,job_url,database)
				self.data = self.data+len(data)
			else:
				#print('data',data)
				self.data = self.data+100
				self.nowdatanum = self.data
	#获取信息的条数
	def getnum(self,url):
		self.outtimenum = self.outtimenum+1
		#req = request.Request('http://career.jxufe.edu.cn/module/getcareers?is_total=1&start=0&count=0&k=&type=inner&day=')
		#req = self.session_requests.Request(url)
		try:
			response = self.session_requests.post(url,timeout=self.outtime)#获取信息，设置超时时间为
			html = html = response.text
			soup = BeautifulSoup(html, 'lxml')
			self.num = int(soup.text)
			return self.num
		except Exception as e:#超时后执行
			if self.outtimenum>=self.outtimemaxnum:
				print(e)
				print('getnum content outtime!')
				return None
			else:
				self.outtimenum = self.outtimenum+1
				return self.getnum(url)
	#批量获取信息
	def getdata(self,count,value,url,school_name):
		self.outtimenum = self.outtimenum+1
		#url = 'http://career.jxufe.edu.cn/module/getcareers?start_page=1&k=&type=inner&day=&count={0}&start={1}&_=1557199180406'.format(count,value)
		#req = self.session_requests.Request(url.format(count,value))
		try:
			response = self.session_requests.post(url.format(count,value),timeout=self.outtime)#获取信息，设置超时时间为
			html = html = response.text
			soup = BeautifulSoup(html, 'lxml')
			tempdata = json.loads(soup.text)
			return tempdata.get("data")
		except Exception as e:#超时后执行
			if self.outtimenum>=self.outtimemaxnum:
				#print(e)
				#print('getdata content outtime!')
				self.error_num = self.error_num + 1
				self.error_string = self.error_string + '\n school:{},Nonedata_index:{} \n'.format(school_name,value) + str(e) + '\n' + url.format(count,value) + '\n getdata content outtime!'
				return None
			else:
				self.outtimenum = self.outtimenum+1
				return self.getdata(count,value,url,school_name)
				
				
	def setcompanyProfile(self,div,returnData,carerr_id,url,job_url):
		returnData['companyProfile'] = div.find('div',attrs={'class':'dm-cont'}).text
	def setrecruitPost(self,div,returnData,carerr_id,url,job_url):
		temp = str(div.find('div',attrs={'class':'dm-cont'}).prettify())
		if len(temp)>6000:
			#returnData['recruitPost'] = 'http://career.jxufe.edu.cn/detail/career?id={}'.format(carerr_id)
			returnData['recruitPost'] = url.format(carerr_id)
		else:
			returnData['recruitPost'] = temp
	def getposition(self,position,job_id,job_url):
		chioce = {
			'职位诱惑':'positionTemptation',
			'薪酬福利':'salaryWelfare',
			'职位描述':'jobDescription',
		}
		self.outtimenum = self.outtimenum+1
		#req = self.session_requests.Request(job_url.format(job_id))
		try:
			response = self.session_requests.post(job_url.format(job_id),timeout=self.outtime)#获取信息，设置超时时间为
			html = html = response.text
			soup = BeautifulSoup(html, 'lxml')
			divs = soup.find_all('p',attrs={'class':'job-welfare'})
			for div in divs:
				if div.text:
					text = div.text.strip()
					if chioce.get(text[:4]):
						position[chioce.get(text[:4])] = text[5:]
			divs = soup.find_all('div',attrs={'class':'detail-module'})
			for div in divs:
				if div.find(attrs={'class':'dm-tit'}):
					if chioce.get(str(div.find(attrs={'class':'dm-tit'}).text.strip())):
						temp = str(div.find('div',attrs={'class':'dm-cont'}).text.strip())
						if len(temp)>6000:
							position[chioce.get(str(div.find(attrs={'class':'dm-tit'}).text.strip()))] = job_url.format(job_id)
						else:
							position[chioce.get(str(div.find(attrs={'class':'dm-tit'}).text.strip()))] = temp
		except Exception as e:#超时后执行
			if self.outtimenum>=self.outtimemaxnum:
				print('getposition content outtime!')
			else:
				self.outtimenum = self.outtimenum+1
				self.getposition(position,job_id,job_url)
	def setposition(self,div,returnData,carerr_id,url,job_url):
		position = []
		div_position = div.find('div',attrs={'class':'dm-cont'}).find_all(attrs={'style':'border-bottom:1px dashed #ddd;padding:10px 0;line-height:2;'})
		for value in div_position:
			href = value.find('a')['href']
			job_id = href[href.index('=')+1:]
			temp = {}
			temp['name'] = value.find(attrs={'class':'item-link'}).text.strip()
			temp['professionals'] = value.find_all('p')[1]['title']
			temp['positionWage'] = value.find(attrs={'style':'float:left;color:#ff9900;'}).text.strip()
			temp['num'] = value.find(attrs={'style':'float:right;'}).find_all('p')[0].text.strip()
			temp['academicRequirements'] = value.find(attrs={'style':'float:right;'}).find_all('p')[1].text.strip()
			self.getposition(temp,job_id,job_url)
			position.extend([temp])
		returnData['position'] = position
	def getCareer(self,carerr_id,school_name,url,job_url):
		returnData = {}
		chioce = {
			'单位简介':self.setcompanyProfile,
			'招聘简章':self.setrecruitPost,
			'招聘职位':self.setposition,
			'规模':'company_size',
			'地址':'company_address',
		}
		#req = request.Request('http://career.jxufe.edu.cn/detail/career?id={}'.format(carerr_id))
		#req = self.session_requests.Request(url.format(carerr_id))
		try:
			#self.header['Referer'] = url.format(carerr_id)
			response = self.session_requests.post(url.format(carerr_id),data=self.login_data,timeout=self.outtime)
			html = html = response.text
			soup = BeautifulSoup(html, 'html.parser')
			#print(soup.text)
			divs = soup.find_all('div',attrs={'class':'detail-module'})
			#print('divs',divs)
			if divs:
				for div in divs:
					div1 = div.find(attrs={'class':'dm-tit'})
					if div1:
						if chioce.get(str(div1.text.strip())):
							chioce.get(str(div1.text.strip()))(div,returnData,carerr_id,url,job_url)
			company = soup.find('div',attrs={'class':'side'})
			#print('company',company)
			if company:
				for div in company.find_all(attrs={'class':'ci-text'}):
					if div.text:
						text = div.text.strip()
						if chioce.get(text[:2]):
							returnData[chioce.get(text[:2])] = text[3:]
			temp = soup.find_all('div',attrs={'class':'side-inner'})
			mmap = None
			if len(temp)>=1:
				mmap = temp[1]
			#print('mmap',mmap)
			if mmap:
				if mmap.find('a'):
					returnData['map_link'] = mmap.find('a')['href']
					returnData['image_link'] = mmap.find('img')['src']
					#print(map_image.find('img')['src'])
			return returnData
		except Exception as e:#超时后执行
			if self.outtimenum>=self.outtimemaxnum:
				if self.islogin==None or self.islogin>=len(ncu.login_url):
					#print(e)
					#print(url.format(carerr_id))
					#print('getCareer content outtime!')
					self.error_num = self.error_num + 1
					self.error_string = self.error_string + '\n school:{},carerr_id:{} \n'.format(school_name,career_id) + str(e) + '\n' + url.format(carerr_id) + '\n getCareer content outtime!'
					return None
				else:
					self.outtimenum = 0
					self.islogin = self.islogin+1
					self.login_ncu()
					return self.getCareer(carerr_id,school_name,url,job_url)
			else:
				self.outtimenum = self.outtimenum+1
				return self.getCareer(carerr_id,school_name,url,job_url)
				
				
	def database(self,datas,career_address,school_id,school_name,career_url,job_url,database):
		# 注意把password设为你的root口令:
		#database = Database('120.79.15.183','root','123456','work_assa')
		#database = Database('127.0.0.1','root','!testing','test')
		#database.initconn()
		for data in datas:
			# 插入记录，注意MySQL的占位符是%s:
			#if not database.select('select school_id, career_talk_id, company_id from employment where school_id = %s and career_talk_id = %s',[school_id, data.get('career_talk_id')]):
			if datetime.datetime.now().strftime('%Y-%m-%d')<data.get("meet_day") and not database.select('select school_id, career_talk_id, company_id from employment where school_id = %s and career_talk_id = %s',[school_id, data.get('career_talk_id')]):
				#进入宣讲会详细信息获取数据
				self.outtimenum = 0
				careerdata = self.getCareer(data.get("career_talk_id"),school_name,career_url,job_url)
				#如果当前网页没有爬取出数据则跳过当前循环步
				if careerdata==None:
					self.error_num = self.error_num + 1
					self.error_string = self.error_string + '\n error:update[{},{}]'.format(school_name,data.get("career_talk_id"))
					continue
				#插入数据到宣讲会表
				#print(data)
				#print(careerdata)
				#print(data.get("career_talk_id"),career_url,job_url,careerdata)
				sql1 = 'insert into employment (school_id, school_name, career_address, career_talk_id, company_id, company_name, logo, hitCount, recruitPost, professionals, meet_name, meet_time, meet_day, address, presentLink) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
				#presentLink = 'http://career.jxufe.edu.cn/detail/career?id={}'.format(data.get("career_talk_id"))
				presentLink = career_url.format(data.get("career_talk_id"))
				value1 = list([school_id, school_name, career_address, data.get("career_talk_id"), data.get("company_id"), data.get("company_name"), data.get("logo"), data.get("view_count"), careerdata.get('recruitPost'), data.get("professionals"), data.get("meet_name"), data.get("meet_time"), data.get("meet_day"), data.get("address"), presentLink])
				database.insert(sql1,value1)
				#插入数据到公司表
				sql2 = 'insert into company (school_id, career_talk_id, company_id, company_name, company_logo, company_size, company_address, map_link, image_link, companyType, companyTrade, companyProfile) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
				value2 = list([school_id, data.get("career_talk_id"), data.get("company_id"), data.get("company_name"), data.get("logo"), careerdata.get('company_size'), careerdata.get('company_address'), careerdata.get('map_link'), careerdata.get('image_link'), data.get("company_property"), data.get("industry_category"), careerdata.get('companyProfile')])
				database.insert(sql2,value2)
				#插入职位表
				if careerdata.get('position'):
					for position in careerdata.get('position'):
						sql3 = 'insert into position (school_id, career_talk_id, company_id, position_name, professionals, jobDescription, positionWage, company_logo, companyType, company_size, num, workPlace, positionTemptation, salaryWelfare) values (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)'
						value3 = list([school_id, data.get("career_talk_id"), data.get("company_id"), position.get("name"), position.get("professionals"), position.get("jobDescription"), position.get("positionWage"), data.get("logo"), data.get("company_property"), careerdata.get('company_size'), position.get("num"), data.get('city_name'), position.get("positionTemptation"), position.get("salaryWelfare")])
						database.insert(sql3,value3)
			else:
				#更新宣讲会信息
				database.update('Update employment Set hitCount = %s Where school_id = %s and career_talk_id = %s',[data.get("view_count"), school_id, data.get('career_talk_id')])
			self.nowdatanum = self.nowdatanum + 1
			print('\r updata school:{}  [{:.2f}%]'.format(school_name,(self.nowdatanum/self.num)*100),end='')
		#database.close()
	def login_ncu(self):
		self.outtimenum = self.outtimenum+1
		#req = request.Request(login_url,data=login_data,headers = dict(referer=login_url))
		try:
			#print('islogin',self.islogin)
			response = self.session_requests.post(self.login_url[str(self.islogin)].get('url1'),data=self.login_data,timeout=self.outtime)#获取信息，设置超时时间
			response1 = self.session_requests.post(self.login_url[str(self.islogin)].get('url2'),timeout=self.outtime)#获取信息，设置超时时间
			response2 = self.session_requests.post(self.login_url[str(self.islogin)].get('url3'),timeout=self.outtime)#获取信息，设置超时时间
		except Exception as e:#超时后执行
			if self.outtimenum>=self.outtimemaxnum:
				print(e)
				print('login content outtime!')
			else:
				self.outtimenum = self.outtimenum+1
				return self.login()

database = Database('kangnan15.yicp.top','root','!testing','work_assa')		
#database = Database('120.79.15.183','root','123456','work_assa')
#database = Database('127.0.0.1','root','!testing','test')
#database = Database('kangnan15.yicp.top','root','!testing','workdatabase')
error_num = 0
error_string = ''
if database.initconn():
	print('jxufe')
	num_url = 'http://career.jxufe.edu.cn/module/getcareers?is_total=1&start=0&count=0&k=&type=inner&day='
	careers_url = 'http://career.jxufe.edu.cn/module/getcareers?start_page=1&k=&type=inner&day=&count={0}&start={1}&_=1557199180406'
	career_url = 'http://career.jxufe.edu.cn/detail/career?id={}'
	job_url = 'http://career.jxufe.edu.cn/detail/job?id={}'
	jxufe = Information()
	jxufe.initdata('江西省南昌市江西财经大学','jxufe','江西财经大学',num_url,careers_url,career_url,job_url,database)
	error_num = error_num + jxufe.error_num
	error_string = error_string + jxufe.error_string
	print('')
	
	
	print('ecjtu')
	ecnum_url = 'http://zjc.ecjtu.edu.cn/module/getcareers?is_total=1&start=0&count=0&keyword=&type=inner&day='
	eccareers_url = 'http://zjc.ecjtu.edu.cn/module/getcareers?start_page=1&keyword=&type=inner&day=&count={0}&start={1}&_=1557987344244'
	eccareer_url = 'http://zjc.ecjtu.edu.cn/detail/career?id={}'
	ecjob_url = 'http://zjc.ecjtu.edu.cn/detail/job?id={}'
	ecjtu = Information()
	ecjtu.initdata('江西省南昌市华东交通大学','ecjtu','华东交通大学',ecnum_url,eccareers_url,eccareer_url,ecjob_url,database)
	error_num = error_num + ecjtu.error_num
	error_string = error_string + ecjtu.error_string
	print('')


	#print('jxny')
	#jxnynum_url = 'http://jxny.bibibi.net/module/getcareers?is_total=1&start=0&count=0&keyword=&type=inner&day='
	#jxnycareers_url = 'http://jxny.bibibi.net/module/getcareers?start_page=1&keyword=&type=inner&day=&count={0}&start={1}&_=1557987344244'
	#jxnycareer_url = 'http://jxny.bibibi.net/detail/career?id={}'
	#jxnyjob_url = 'http://jxny.bibibi.net/detail/job?id={}'
	#jxny = Information()
	#jxny.initdata('江西省南昌市江西农业大学','jxny',jxnynum_url,jxnycareers_url,jxnycareer_url,jxnyjob_url,database)
	#print(jxny.num)
	

	print('nchu')
	nchunum_url = 'http://nchu.bysjy.com.cn/module/getcareers?is_total=1&start=0&count=0&keyword=&type=inner&day='
	nchucareers_url = 'http://nchu.bysjy.com.cn/module/getcareers?start_page=1&keyword=&type=inner&day=&count={0}&start={1}&_=1557987344244'
	nchucareer_url = 'http://nchu.bysjy.com.cn/detail/career?id={}'
	nchujob_url = 'http://nchu.bysjy.com.cn/detail/job?id={}'
	nchu = Information()
	nchu.initdata('江西省南昌市南昌航空大学','nchu','南昌航空大学',nchunum_url,nchucareers_url,nchucareer_url,nchujob_url,database)
	error_num = error_num + nchu.error_num
	error_string = error_string + nchu.error_string
	print('')
	

	print('jxkjsf')
	jxkjnum_url = 'http://jxkjsf.bysjy.com.cn/module/getcareers?is_total=1&start=0&count=0&keyword=&type=inner&day='
	jxkjcareers_url = 'http://jxkjsf.bysjy.com.cn/module/getcareers?start_page=1&keyword=&type=inner&day=&count={0}&start={1}&_=1557987344244'
	jxkjcareer_url = 'http://jxkjsf.bysjy.com.cn/detail/career?id={}'
	jxkjjob_url = 'http://jxkjsf.bysjy.com.cn/detail/job?id={}'
	jxkj = Information()
	jxkj.initdata('江西省南昌市江西科技师范大学','jxkjsf','江西科技师范大学',jxkjnum_url,jxkjcareers_url,jxkjcareer_url,jxkjjob_url,database)
	error_num = error_num + jxkj.error_num
	error_string = error_string + jxkj.error_string
	print('')
	

	print('asc')
	ascnum_url = 'http://asc.bysjy.com.cn/module/getcareers?is_total=1&start=0&count=0&keyword=&type=inner&day='
	asccareers_url = 'http://asc.bysjy.com.cn/module/getcareers?start_page=1&keyword=&type=inner&day=&count={0}&start={1}&_=1557987344244'
	asccareer_url = 'http://asc.bysjy.com.cn/detail/career?id={}'
	ascjob_url = 'http://asc.bysjy.com.cn/detail/job?id={}'
	asc = Information()
	asc.initdata('江西省南昌市江西理工大学','asc','江西理工大学',ascnum_url,asccareers_url,asccareer_url,ascjob_url,database)
	error_num = error_num + asc.error_num
	error_string = error_string + asc.error_string
	print('')
	

	print('jxutcm')
	jxutnum_url = 'http://zsjyc.jxutcm.edu.cn/module/getcareers?is_total=1&start=0&count=0&keyword=&type=inner&day='
	jxutcareers_url = 'http://zsjyc.jxutcm.edu.cn/module/getcareers?start_page=1&keyword=&type=inner&day=&count={0}&start={1}&_=1557987344244'
	jxutcareer_url = 'http://zsjyc.jxutcm.edu.cn/detail/career?id={}'
	jxutjob_url = 'http://zsjyc.jxutcm.edu.cn/detail/job?id={}'
	jxut = Information()
	jxut.initdata('江西省南昌市江西中医药大学','jxutcm','江西中医药大学',jxutnum_url,jxutcareers_url,jxutcareer_url,jxutjob_url,database)
	error_num = error_num + jxut.error_num
	error_string = error_string + jxut.error_string
	print('')
	


	print('ncu')
	ncu = Information()
	#ncu.login_url = 'https://cas.ncu.edu.cn:8443/cas/login;jsessionid=C42DC0EBC2F400EA9412AF5D40B1C213'
	#ncu.login_url1 = 'https://cas.ncu.edu.cn:8443/cas/login;jsessionid=3DA2B762D183ADEC2C4E775041687CE8?service=http%3A%2F%2Fjy.ncu.edu.cn%2Fcas%2Fncu%2Fget.php%3Furl%3D%252Fdetail%252Fcareer%253Fid%253D226356'
	#ncu.login_url1 = ''
	#ncu.login_url2 = ''
	#ncu.login_url3 = ''
	ncu.login_url = {
		'1':{
			'url1':'https://cas.ncu.edu.cn:8443/cas/login;jsessionid=71947C9E3B32A54426EE1CA58219A591?service=http%3A%2F%2Fjy.ncu.edu.cn%2Fcas%2Fncu%2Fget.php%3Furl%3D%252Fdetail%252Fcareer%253Fid%253D226356',
			'url2':'http://jy.ncu.edu.cn/cas/ncu/get.php?url=%2Fdetail%2Fcareer%3Fid%3D226356&ticket=ST-14733-lSqohUdVbOiyLa2V9YQH-cas',
			'url3':'http://jy.ncu.edu.cn/sso/ncu?tokens=eyJ1c2VyIjoiODAwMTcxNjE4MiIsInBhc3MiOiIyMDE5MDYwNSN6amMiLCJjYXMiOjF9&url=%2Fdetail%2Fcareer%3Fid%3D226356',
		},
		'2':{
			'url1':'https://cas.ncu.edu.cn:8443/cas/login?service=http%3A%2F%2Fjy.ncu.edu.cn%2Fcas%2Fncu%2Fget.php%3Furl%3D%252Fdetail%252Fcareer%253Fid%253D226356',
			'url2':'http://jy.ncu.edu.cn/cas/ncu/get.php?url=%2Fdetail%2Fcareer%3Fid%3D226356&ticket=ST-16306-ZKGzcXbDcihRuwrdPJMS-cas',
			'url3':'http://jy.ncu.edu.cn/sso/ncu?tokens=eyJ1c2VyIjoiODAwMTcxNjE4MiIsInBhc3MiOiIyMDE5MDYwNiN6amMiLCJjYXMiOjF9&url=%2Fdetail%2Fcareer%3Fid%3D226356',
		},
	}
	ncu.islogin = 1
	ncu.login_data = {
			'username': '8001716182',
			'password': '1252312194',
			'errors': '0',
			'imageCodeName': '',
			'lt':'_cE918B71F-29E3-2801-F412-74FC4F278A16_kC0294A47-65AA-7902-22D2-34ED7F65CFC4',
			'_eventId':' submit',
		}
	ncunum_url = 'http://jy.ncu.edu.cn/module/get_jx_careers?is_total=1&start=0&count=0&keyword=&xz=&type=inner&day='
	ncucareers_url = 'http://jy.ncu.edu.cn/module/get_jx_careers?start_page=1&keyword=&type=inner&day=&count={0}&start={1}&_=1557987344244'
	ncucareer_url = 'http://jy.ncu.edu.cn/detail/career?id={}'
	ncujob_url = 'http://jy.ncu.edu.cn/detail/job?id={}'
	ncu.login_ncu()
	ncu.initdata('江西省南昌市南昌大学','ncu','南昌大学',ncunum_url,ncucareers_url,ncucareer_url,ncujob_url,database)
	error_num = error_num + ncu.error_num
	error_string = error_string + ncu.error_string
	print('')
	
print('error_num:',error_num)
print(error_string)
database.close()
