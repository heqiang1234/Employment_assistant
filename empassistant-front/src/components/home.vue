<template>
  <div class="wrap" v-loading.fullscreen.lock="fullscreenLoading">
    <Header ref="header"></Header>
    <div class="top-bar">
      <div class="container">
        <div class="left-bar">
          <div class="user-count">{{totalJobNum}}</div>
          <div class="user-count-num">共计{{totalJobNum}}个热门职业等着你</div>
        </div>
        <div class="right-bar">
          <div class="form">
            <el-input
              placeholder="请输入内容"
              v-model="searchInfo.searchContent"
              class="input-with-select"
            >
              <el-select
                style="width:120px;"
                v-model="searchInfo.searchPlace"
                placeholder="选择地区"
                slot="prepend"
              >
                <el-option label="全国" value="1"></el-option>
                <el-option label="北京" value="2"></el-option>
              </el-select>
              <el-select
                style="width:120px;margin-left:15px;"
                placeholder="选择信息"
                slot="prepend"
                v-model="searchInfo.searchType"
              >
                <el-option label="搜职业" value="1"></el-option>
                <el-option label="搜公司" value="2"></el-option>
              </el-select>
              <el-button style="width:130px;" slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </div>
          <div class="form-bar">
            热门搜索：
            <a href="#">项目</a>
            <a href="#">前端</a>
            <a href="#">财会</a>
            <a href="#">市场</a>
            <a href="#">行政</a>
            <a href="#">广告</a>
          </div>
        </div>
      </div>
    </div>
    <div class="mid-bar">
      <div class="container">
        <div class="list">
          <div class="list-title">全部分类</div>
          <div
            v-for="(classfy,index) in jobClassfy"
            :key="index"
            class="type-item"
            @click="searchJob"
          >
            <a v-for="(item,index) in classfy.ovallClsf" :key="index" href="#">{{item}}</a>
            <div class="item-infor">
              <div v-for="(detail,index) in classfy.detailClsf" :key="index" class="item-list">
                <a v-for="(item,index) in detail" :key="index" href="#">{{item}}</a>
              </div>
            </div>
          </div>
        </div>
        <div class="swiper">
          <el-carousel height="288px">
            <el-carousel-item v-for="item in 4" :key="item">
              <h3>{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
          <div class="ad-bar">
            <div class="ad-container"> 
              <div @click="linkTo({name:'post',params:{id:item.employment_id}})" v-for="(item,index) in adImgList" :key="index" class="vip-companys">
                <img :src="item.logo" title="公司" alt="广告招聘">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="company-tab">
      <div class="container-tab">
        <ul class="tab-list">
          <li
            @click="chageListType(index,item.value)"
            :data-index="index"
            v-for="(item,index) in [{label:'热门职业',value:'Hot'},{label:'最新职业',value:'New'},{label:'急招职业',value:'Quick'}]"
            :key="index"
            :class="curJobIndex===index?'select':''"
          >{{item.label}}</li>
        </ul>
        <ul class="tab-content">
          <div v-for="(item,index) in jobList" :key="index" class="post">
            <!-- 职位盒子 -->
            <div class="post-title">
              <div class="post-head">
                <div
                  @click="linkTo({name:'post',params:{id:item.positionID}})"
                  :data-job="item"
                  class="post-name"
                >{{item.position_name}}</div>
                <div class="post-pay">{{item.positionWage}}</div>
              </div>
              <div class="post-body">
                <div v-if="item.workPlace" class="post-others">
                  <i class="el-icon-location-outline"></i>
                  <span class="post-other">{{item.workPlace}}</span>
                </div>
                <div class="post-others">
                  <i class="el-icon-view"></i>
                  <span class="post-other">{{item.num}}</span>
                </div>
              </div>
            </div>
            <div class="post-sepLine"></div>
            <div class="post-company">
              <div class="company-logo">
                <img :src="item.company.company_logo" alt>
              </div>
              <div class="company-infor">
                <div class="company-name">
                  <a href="#">{{item.company.company_name}}</a>
                </div>
                <div
                  class="company-info"
                >{{item.company.companyType}} | {{item.company.company_size}}</div>
              </div>
            </div>
          </div>
        </ul>
        <div class="jobs-pagetab">
          <el-pagination
            @current-change="handleCurrentChange"
            :current-page.sync="pagination.curJobPage"
            :page-size="pagination.pageSize"
            layout="prev, pager, next, jumper"
            :page-count="pagination.totalPage"
          ></el-pagination>
        </div>
      </div>
    </div>
    <Footsy></Footsy>
    <a class="goto-top" href="#">
      <img src="../assets/right-hn.png" alt>
      回到顶部
    </a>
    <div v-if="newUser" class="self-mesg-input">
      <div class="fundmation-mesg">
        <el-alert
          :closable="false"
          v-if="userInfoErr"
          show-icon
          :title="userInfoErrMsg"
          type="error"
        ></el-alert>
        <div class="mesg-title">
          <i class="el-icon-discount"></i>
          <span>基本信息</span>
        </div>
        <div class="head-pic">
          <el-upload
            class="avatar-uploader"
            :action="API.UPLOAD.UPIMG"
            name="UserImg"
            list-type="picture"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
          >
            <img v-if="userInfo.avtUrl" :src="userInfo.avtUrl" class="avatar">
            <i v-else class="el-icon-camera-solid avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <div class="mesg-form">
          <div class="line-form">
            <span class="line-item">姓名</span>
            <span class="line-input">
              <el-input v-model="userInfo.name" placeholder="请输入内容"></el-input>
            </span>
          </div>
          <div class="line-form">
            <span class="line-item">性别</span>
            <span class="line-ele">
              <el-select v-model="userInfo.sex" placeholder="请选择">
                <el-option label="男" value="male"></el-option>
                <el-option label="女" value="female"></el-option>
              </el-select>
            </span>
          </div>
          <!-- <div class="line-form">
            <span class="line-item">生日</span>
            <span class="line-ele">
              <el-date-picker v-model="value3" type="year" placeholder="选择年"></el-date-picker>
              <el-date-picker v-model="value2" type="month" placeholder="选择月"></el-date-picker>
            </span>
          </div>-->
          <div class="line-form">
            <span class="line-item">所在城市</span>
            <span class="line-ele">
              <el-select @change="changeProvince" v-model="userInfo.province" placeholder="请选择省">
                <el-option
                  v-for="item in allCity"
                  :key="item.province"
                  :label="item.province"
                  :value="item.province"
                ></el-option>
              </el-select>
              <el-select v-model="userInfo.city" placeholder="请选择市">
                <el-option v-for="item in curCity" :key="item" :label="item" :value="item"></el-option>
              </el-select>
            </span>
          </div>
          <div class="line-form">
            <span class="line-item-phone">所在学校</span>
            <span class="line-element">
              <el-input v-model="userInfo.school" placeholder="请输入内容"></el-input>
            </span>
          </div>
          <div class="line-form">
            <span class="line-item-phone">所学专业</span>
            <span class="line-element">
              <el-input v-model="userInfo.major" placeholder="请输入内容"></el-input>
            </span>
          </div>
          <div class="line-form">
            <span class="line-item">求职意向</span>
            <span class="line-input">
              <el-input v-model="userInfo.want" placeholder="请输入内容"></el-input>
            </span>
          </div>
          <div class="line-form">
            <span class="line-item-phone">常用邮箱</span>
            <span class="line-element">
              <el-input v-model="userInfo.email" placeholder="请输入内容"></el-input>
            </span>
          </div>
          <el-checkbox
            v-if="userInfo.email"
            style="margin-left:120px;"
            v-model="userInfo.getPush"
          >是否接受推送</el-checkbox>
        </div>
        <div class="mesg-form-bot">
          <el-button @click="postUser" type="primary" round>保存</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Header from "./common/header.vue";
import Footsy from "./common/footsy.vue";
export default {
  name: "home",
  components: { Header,Footsy },
  created() {
    if (this.$route.params.newUser) {
      this.newUser = this.$route.params.newUser;
      console.log(this.newUser);
    }

    this.getJobList();
    this.getAdImg();
  },
  methods: {
    getJobList() {
      //获取职位列表
      let that = this;
      if (!that.pagination.nextPage) return;
      that
        .axios({
          url: this.API.JOBS.GETJOBLIST,
          methods: "GET",
          params: {
            PageSize: that.pagination.pageSize,
            CurrentPage: that.pagination.curJobPage,
            S_ID: this.jobListType
          }
        })
        .then(res => {
          console.log(res);
          let totalJob = "" + res.data.extendInfo.pageBean_List.totalCount;
          // for (let i = 0; i < 8 - totalJob.length; i++) {
          //   totalJob = "0" + totalJob;
          // }
          that.totalJobNum = totalJob;
          that.jobList = res.data.extendInfo.pageBean_List.lists;
          if (
            res.data.extendInfo.pageBean_List.totalPage >
            that.pagination.curJobPage + 9
          ) {
            that.pagination.totalPage = that.pagination.curJobPage + 9;
          } else {
            that.pagination.totalPage =
              res.data.extendInfo.pageBean_List.totalPage;
          }
        });
    },
    getAdImg() {
      //获取广告图片
      this.axios({
        url: this.API.OTHER.GETADIMG
      }).then(res => {
        console.log(res);
        this.adImgList = res.data.extendInfo.list;
      });
    },
    chageListType(index, value) {
      //切换职位信息列表
      this.pagination.curPage = 1;
      this.curJobIndex = index;
      this.jobListType = value;
      this.getJobList();
    },
    UploadAvt() {},
    searchJob(e) {
      //搜索相关职位
      let that = this;
      let key = e.target.innerText;
      console.log(key);
      that
        .axios({
          url: that.API.JOBS.SEARCHJOBS,
          methods: "GET",
          params: {
            CurrentPage: 1,
            PageSize: that.pagination.pageSize,
            Search_Id: "position_name",
            Search_Name: key
          }
        })
        .then(res => {
          console.log(res);
          if (res.data.code != "200") {
            this.$message({
              showClose: true,
              message: res.data.extendInfo.login_error,
              type: "error"
            });
            return;
          }
          let initInfo = res.data.extendInfo.pagebean_position_name;
          initInfo["searchKey"] = key;
          this.linkTo({ name: "jobs", params: initInfo });
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleCurrentChange(val) {
      //跳转页数
      this.pagination.curJobPage = val;
      this.getJobList();
    },
    handleAvatarSuccess(res, file) {
      //上传头像更改
      console.log(res);
      console.log(file);
      this.userInfo.avtUrl = res.extendInfo.success;
    },
    changeProvince(val) {
      let that = this;
      let province = that.allCity;
      for (let i = 0; i < province.length; i++) {
        if (province[i].province === val) {
          that.curCity = province[i].children;
          that.userInfo.city = province[i].children[0];
        }
      }
    },
    onUploadAvt() {
      this.onUploadImg = true;
    },
    postUser() {
      //上传个人信息
      let userInfo = this.userInfo;
      let that = this;
      var pattern = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      console.log(userInfo);
      if (userInfo.avtUrl == "") {
        that.showUserInfoErr("请上传个人头像");
        return;
      }
      for (let item in userInfo) {
        if (userInfo[item] === "") {
          that.showUserInfoErr("请完整填写个人信息");
          return;
        }
      }
      if (!pattern.test(userInfo.email)) {
        that.showUserInfoErr("请填写正确的邮箱地址");
        return;
      }
      console.log(
        userInfo.name +
          "\n" +
          userInfo.sex +
          "\n" +
          userInfo.school +
          "\n" +
          userInfo.major +
          "\n" +
          userInfo.want +
          "\n" +
          userInfo.city +
          "\n" +
          userInfo.email +
          "\n" +
          userInfo.avtUrl
      );
      this.axios({
        url: this.API.USER.UPDATE,
        params: {
          UserRealName: userInfo.name,
          UserSex: userInfo.sex,
          UserSchool: userInfo.school,
          UserMajor: userInfo.major,
          UserIntentionalPost: userInfo.want,
          UserProvince:userInfo.province,
          UserCity: userInfo.city,
          UserMail: userInfo.email,
          UserImg: userInfo.avtUrl,
          UserStatus: userInfo.getPush
        }
      }).then(res => {
        console.log(res);
        this.newUser = false;
        this.USERSTATUS.userInfo = res.data.extendInfo.person;
        console.log(this.USERSTATUS.userInfo);
        this.$refs.header.syncUserStatus();
      });
    },
    showUserInfoErr(msg) {
      this.userInfoErrMsg = msg;
      this.userInfoErr = true;
      console.log(this.userInfoErr);
    },
    beforeAvatarUpload(file){
      const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
    }
  },
  data() {
    return {
      totalJobNum: false, //总岗位数量
      curJobIndex: 0, //当前职位列表类型
      jobList: [], //职位列表
      fullscreenLoading: false, //加载框状态
      jobListType: "Hot", //职位列表类型
      pagination: {
        //分页相关
        curJobPage: 1, //当前职位列表页数
        pageSize: 12, //分页容量
        nextPage: true, //是否有下一页
        totalPage: 1 //职位总页数
      },
      searchInfo: {
        //搜索相关
        searchContent: "", //搜索框内容
        searchPlace: "", //搜索地区范围
        searchType: "" //搜索内容类型
      },
      userInfo: {
        //用户信息
        avtUrl: "", //用户信息头像链接
        name: "", //用户姓名
        sex: "", //用户性别
        province: "", //用户所在省份
        city: "", //用户所在城市
        school: "", //学校
        major: "", //所学专业
        want: "", //求职意向
        getPush: true //是否接受推送
      },
      userInfoErr: false, //用户信息填写不完整
      newUser: false, //用户未完善资料
      userInfoErrMsg: "", //用户资料报错提示信息,
      onUploadImg: false,
      adImgList: [], //广告图片列表
      jobClassfy: [
        //职位分类
        {
          ovallClsf: [
            //总体分类
            "IT互联网",
            "软件",
            "运营",
            "硬件"
          ],
          detailClsf: [
            //具体分类
            ["软件", "数据库", "C#/.NET", "Android", "算法", "运维", "前端"],
            ["运营", "新媒体", "内容运营", "编辑", "SEO", "产品运营"],
            ["硬件", "嵌入式", "集成电路"],
            ["设计", "Flash", "UI/UE", "特效", "网页/美工", "2D/3D"],
            ["通信", "物联网", "通信", "射频"],
            ["产品", "用户研究", "产品助理"]
          ]
        },
        {
          ovallClsf: ["市场商务", "商务", "销售", "公关"],
          detailClsf: [
            ["商务", "商务", "招投标"],
            ["销售", "销售", "推广"],
            ["公关", "公关", "媒介"],
            ["客服", "客户服务", "销售支持"],
            ["市场", "渠道", "分析/调研", "策划", "品牌", "市场"]
          ]
        },
        {
          ovallClsf: ["电子电器", "电子", "电气"],
          detailClsf: [
            ["电子", "光电", "半导体/芯片", "电子工程"],
            ["电气", "电子设计", "电气工程"]
          ]
        },
        {
          ovallClsf: ["其它类型", "体育快消", "机械"],
          detailClsf: [
            ["体育快消", "快消", "体育"],
            ["机械制造", "质量", "机械设计", "生产", "安全", "设备", "自动化"],
            ["物流采购", "采购", "供应链", "物流"],
            [
              "建筑/房产",
              "城规/市规",
              "工程造价",
              "建筑",
              "土木",
              "园林",
              "土地开发",
              "房产销售",
              "排水",
              "物业管理"
            ],
            ["生物医疗", "医生", "医药", "生物", "护理"],
            ["能源环保", "矿产", "能源", "环保"],
            ["食品材料", "材料", "食品"],
            ["NGO公益", "志愿者"]
          ]
        },
        {
          ovallClsf: ["人力资源", "猎头", "行政"],
          detailClsf: [
            ["人力资源", "人事/HR", "招聘", "企业文化"],
            ["猎头", "猎头"],
            ["物流采购", "采购", "供应链", "物流"],
            ["行政", "行政", "前台", "助理"]
          ]
        },
        {
          ovallClsf: ["外语外贸", "外语", "外贸"],
          detailClsf: [
            ["外语", "英语", "日语", "翻译"],
            ["外贸", "报关员", "外贸专员"]
          ]
        },
        {
          ovallClsf: ["金融法务", "金融", "投资", "法务"],
          detailClsf: [
            ["金融", "基金", "证券", "风控", "金融"],
            ["投资", "分析师", "投资"],
            ["法务", "合规", "律师", "法务"],
            ["银行", "客户经理", "部门经理", "贷款", "大堂经理"],
            ["保险", "业务", "保单"],
            ["财会", "审计", "税务", "财务", "会计/出纳"]
          ]
        },
        {
          ovallClsf: ["教育咨询", "教育", "咨询"],
          detailClsf: [
            ["教育", "教务", "教师", "幼教", "培训", "课程"],
            ["咨询", "咨询/顾问"]
          ]
        },
        {
          ovallClsf: ["媒体设计", "广告", "编辑", "设计"],
          detailClsf: [
            ["广告", "创意", "策划", "AE"],
            ["编辑", "编辑/采编", "校对/排版"],
            ["设计", "美术设计", "工业设计", "平面设计", "视觉设计"],
            ["艺术", "记者", "支持/播音", "编导"],
            ["艺术", "演艺", "摄影"]
          ]
        }
      ],
      allCity: [
        //全国省市区
        {
          province: "北京",
          children: [
            "东城区",
            "西城区",
            "崇文区",
            "宣武区",
            "朝阳区",
            "海淀区",
            "丰台区",
            "石景山区",
            "房山区",
            "通州区",
            "顺义区",
            "门头沟区",
            "昌平区",
            "大兴区",
            "怀柔区",
            "平谷区",
            "密云县",
            "延庆县"
          ]
        },
        {
          province: "上海",
          children: [
            "黄浦区",
            "卢湾区",
            "徐汇区",
            "长宁区",
            "静安区",
            "普陀区",
            "闸北区",
            "虹口区",
            "杨浦区",
            "宝山区",
            "闵行区",
            "嘉定区",
            "浦东新区",
            "金山区",
            "松江区",
            "青浦区",
            "南汇区",
            "奉贤区",
            "崇明县"
          ]
        },
        {
          province: "天津",
          children: [
            "和平区",
            "河东区",
            "河西区",
            "南开区",
            "河北区",
            "红桥区",
            "塘沽区",
            "汉沽区",
            "大港区",
            "东丽区",
            "西青区",
            "津南区",
            "北辰区",
            "武清区",
            "宝坻区",
            "宁河县",
            "静海县",
            "蓟县"
          ]
        },
        {
          province: "重庆",
          children: [
            "渝中区",
            "大渡口区",
            "江北区",
            "沙坪坝区",
            "九龙坡区",
            "南岸区",
            "北碚区",
            "万盛区",
            "双桥区",
            "渝北区",
            "巴南区",
            "万县区",
            "涪陵区",
            "永川",
            "合川",
            "江津",
            "南川",
            "长寿县",
            "綦江县",
            "潼南县",
            "荣昌县",
            "壁山县",
            "大足县",
            "铜梁县",
            "梁平县",
            "城口县",
            "垫江县",
            "武隆县",
            "丰都县",
            "忠 县",
            "开 县",
            "云阳县",
            "青龙镇青龙嘴",
            "奉节县",
            "巫山县",
            "巫溪县",
            "南宾镇",
            "中和镇",
            "钟多镇",
            "联合镇",
            "汉葭镇"
          ]
        },
        {
          province: "河北",
          children: [
            "石家庄",
            "唐山",
            "秦皇岛",
            "邯郸",
            "邢台",
            "保定",
            "张家口",
            "承德",
            "沧州",
            "廊坊",
            "衡水"
          ]
        },
        {
          province: "山西",
          children: [
            "太原",
            "大同",
            "阳泉",
            "长治",
            "晋城",
            "朔州",
            "晋中",
            "运城",
            "忻州",
            "临汾",
            "吕梁"
          ]
        },
        {
          province: "辽宁",
          children: [
            "沈阳",
            "大连",
            "鞍山",
            "抚顺",
            "本溪",
            "丹东",
            "锦州",
            "营口",
            "阜新",
            "辽阳",
            "盘锦",
            "铁岭",
            "朝阳",
            "葫芦岛"
          ]
        },
        {
          province: "吉林",
          children: [
            "长春",
            "吉林",
            "四平",
            "辽源",
            "通化",
            "白山",
            "松原",
            "白城",
            "延边朝鲜族自治州"
          ]
        },
        {
          province: "河南",
          children: [
            "郑州",
            "开封",
            "洛阳",
            "平顶山",
            "安阳",
            "鹤壁",
            "新乡",
            "焦作",
            "濮阳",
            "许昌",
            "漯河",
            "三门峡",
            "南阳",
            "商丘",
            "信阳",
            "周口",
            "驻马店",
            "济源"
          ]
        },
        {
          province: "江苏",
          children: [
            "南京",
            "无锡",
            "徐州",
            "常州",
            "苏州",
            "南通",
            "连云港",
            "淮安",
            "盐城",
            "扬州",
            "镇江",
            "泰州",
            "宿迁"
          ]
        },
        {
          province: "浙江",
          children: [
            "杭州",
            "宁波",
            "温州",
            "嘉兴",
            "湖州",
            "绍兴",
            "金华",
            "衢州",
            "舟山",
            "台州",
            "丽水"
          ]
        },
        {
          province: "安徽",
          children: [
            "合肥",
            "芜湖",
            "蚌埠",
            "淮南",
            "马鞍山",
            "淮北",
            "铜陵",
            "安庆",
            "黄山",
            "滁州",
            "阜阳",
            "宿州",
            "巢湖",
            "六安",
            "亳州",
            "池州",
            "宣城"
          ]
        },
        {
          province: "福建",
          children: [
            "福州",
            "厦门",
            "莆田",
            "三明",
            "泉州",
            "漳州",
            "南平",
            "龙岩",
            "宁德"
          ]
        },
        {
          province: "江西",
          children: [
            "南昌",
            "景德镇",
            "萍乡",
            "九江",
            "新余",
            "鹰潭",
            "赣州",
            "吉安",
            "宜春",
            "抚州",
            "上饶"
          ]
        },
        {
          province: "山东",
          children: [
            "济南",
            "青岛",
            "淄博",
            "枣庄",
            "东营",
            "烟台",
            "潍坊",
            "威海",
            "济宁",
            "泰安",
            "日照",
            "莱芜",
            "临沂",
            "德州",
            "聊城",
            "滨州",
            "菏泽"
          ]
        },
        {
          province: "湖北",
          children: [
            "武汉",
            "黄石",
            "襄樊",
            "十堰",
            "荆州",
            "宜昌",
            "荆门",
            "鄂州",
            "孝感",
            "黄冈",
            "咸宁",
            "随州",
            "恩施州",
            "仙桃",
            "潜江",
            "天门",
            "神农架林区"
          ]
        },
        {
          province: "湖南",
          children: [
            "长沙",
            "株洲",
            "湘潭",
            "衡阳",
            "邵阳",
            "岳阳",
            "常德",
            "张家界",
            "益阳",
            "郴州",
            "永州",
            "怀化",
            "娄底",
            "湘西州"
          ]
        },
        {
          province: "广东",
          children: [
            "广州",
            "深圳",
            "珠海",
            "汕头",
            "韶关",
            "佛山",
            "江门",
            "湛江",
            "茂名",
            "肇庆",
            "惠州",
            "梅州",
            "汕尾",
            "河源",
            "阳江",
            "清远",
            "东莞",
            "中山",
            "潮州",
            "揭阳",
            "云浮"
          ]
        },
        {
          province: "海南",
          children: ["海口", "龙华区", "秀英区", "琼山区", "美兰区", "三亚"]
        },
        {
          province: "四川",
          children: [
            "成都",
            "自贡",
            "攀枝花",
            "泸州",
            "德阳",
            "绵阳",
            "广元",
            "遂宁",
            "内江",
            "乐山",
            "南充",
            "宜宾",
            "广安",
            "达州",
            "眉山",
            "雅安",
            "巴中",
            "资阳",
            "阿坝州",
            "甘孜州",
            "凉山州"
          ]
        },
        {
          province: "贵州",
          children: [
            "贵阳",
            "六盘水",
            "遵义",
            "安顺",
            "铜仁地区",
            "毕节地区",
            "黔西南州",
            "黔东南州",
            "黔南州"
          ]
        },
        {
          province: "云南",
          children: [
            "昆明",
            "大理",
            "曲靖",
            "玉溪",
            "昭通",
            "楚雄",
            "红河",
            "文山",
            "思茅",
            "西双版纳",
            "保山",
            "德宏",
            "丽江",
            "怒江",
            "迪庆",
            "临沧"
          ]
        },
        {
          province: "陕西",
          children: [
            "西安",
            "铜川",
            "宝鸡",
            "咸阳",
            "渭南",
            "延安",
            "汉中",
            "榆林",
            "安康",
            "商洛"
          ]
        },
        {
          province: "甘肃",
          children: [
            "兰州",
            "嘉峪关",
            "金昌",
            "白银",
            "天水",
            "武威",
            "张掖",
            "平凉",
            "酒泉",
            "庆阳",
            "定西",
            "陇南",
            "临夏州",
            "甘南州"
          ]
        },
        {
          province: "青海",
          children: [
            "西宁",
            "海东地区",
            "海北州",
            "黄南州",
            "海南州",
            "果洛州",
            "玉树州",
            "海西州"
          ]
        },
        {
          province: "黑龙江",
          children: [
            "哈尔滨",
            "齐齐哈尔",
            "鸡西",
            "鹤岗",
            "双鸭山",
            "大庆",
            "伊春",
            "佳木斯",
            "七台河",
            "牡丹江",
            "黑河",
            "绥化",
            "大兴安岭地区"
          ]
        },
        {
          province: "内蒙古自治区",
          children: [
            "呼和浩特",
            "包头",
            "乌海",
            "赤峰",
            "通辽",
            "鄂尔多斯",
            "呼伦贝尔",
            "巴彦淖尔",
            "乌兰察布",
            "兴安盟",
            "锡林郭勒盟",
            "阿拉善盟"
          ]
        },
        {
          province: "广西",
          children: [
            "南宁",
            "柳州",
            "桂林",
            "梧州",
            "北海",
            "防城港",
            "钦州",
            "贵港",
            "玉林",
            "百色",
            "贺州",
            "河池",
            "来宾",
            "崇左"
          ]
        },
        {
          province: "西藏自治区",
          children: [
            "拉萨",
            "昌都地区",
            "山南地区",
            "日喀则地区",
            "那曲地区",
            "阿里地区",
            "林芝地区"
          ]
        },
        {
          province: "宁夏自治区",
          children: ["银川", "石嘴山", "吴忠", "固原", "中卫"]
        },
        {
          province: "新疆维吾尔自治区",
          children: [
            "乌鲁木齐",
            "克拉玛依",
            "吐鲁番地区",
            "哈密地区",
            "和田地区",
            "阿克苏地区",
            "喀什地区",
            "克孜勒苏柯尔克孜自治州",
            "巴音郭楞蒙古自治州",
            "昌吉回族自治州",
            "博尔塔拉蒙古自治州",
            "伊犁哈萨克自治州",
            "塔城地区",
            "阿勒泰地区",
            "石河子",
            "阿拉尔",
            "图木舒克",
            "五家渠"
          ]
        },
        {
          province: "台湾",
          children: [
            "台北",
            "高雄",
            "基隆",
            "台中",
            "台南",
            "新竹",
            "嘉义",
            "台北县",
            "宜兰县",
            "桃园县",
            "新竹县",
            "苗栗县",
            "台中县",
            "彰化县",
            "南投县",
            "云林县",
            "嘉义县",
            "台南县",
            "高雄县",
            "屏东县",
            "澎湖县",
            "台东县",
            "花莲县"
          ]
        },
        {
          province: "香港",
          childrem: [
            "中西区",
            "东区",
            "九龙城区",
            "观塘区",
            "南区",
            "深水埗区",
            "黄大仙区",
            "湾仔区",
            "油尖旺区",
            "离岛区",
            "葵青区",
            "北区",
            "西贡区",
            "沙田区",
            "屯门区",
            "大埔区",
            "荃湾区",
            "元朗区"
          ]
        },
        {
          province: "澳门地区",
          children: ["澳门"]
        },
        {
          province: "其它地区",
          children: ["其他地区"]
        }
      ],
      curCity: ["请选择"] //当前选中的省的市级列表
    };
  }
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 288px;
  margin: 0;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}
.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.wrap {
  width: 100%;
  min-width: 1080px;
}

.top-header {
  width: 100%;
  height: 172px;
  background: #67b0e7;
}
.container {
  justify-content: space-between;
  display: flex;
  height: 100%;
  max-width: 90%;
  margin: 0px auto;
}
.leftImg {
  width: 23%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
}
.top-bar {
  margin-top: 20px;
  width: 100%;
  height: 120px;
  padding-top: 20px;
  box-sizing: border-box;
}
.left-bar {
  width: 23%;
  height: 100px;
  padding-top: 20px;
  background: #fafafa;
  display: inline-block;
  box-sizing: border-box;
}
.user-count {
  font-weight: 400;
  letter-spacing: 5px;
  width: 95%;
  height: 50px;
  color: #0287ee;
  font-size: 30px;
  text-align: center;
  line-height: 30px;
  padding: 10px 0px;
  margin: 0px auto;
  background: #fff;
  box-sizing: border-box;
}
.loadingUp {
  position: absolute;
  left: 50%;
  top: 50%;
}
.user-count-num {
  line-height: 24px;
  font-size: 12px;
  text-align: center;
  height: 24px;
  color: #333;
}
.right-bar {
  width: 75%;
  height: 100px;
  padding-top: 20px;
  display: inline-block;
  box-sizing: border-box;
}
.form {
  width: 100%;
  height: 50px;
}
.form-bar {
  color: #6aa2e4;
  line-height: 28px;
  font-size: 14px;
}
.form-bar > a {
  text-decoration: none;
  color: #6aa2e4;
  padding-right: 15px;
}
.form-bar > a:hover {
  text-decoration: underline;
}
.mid-bar {
  width: 100%;
  padding: 20px 0px 20px 0px;
  box-sizing: border-box;
}
.list {
  width: 23%;
  height: 408px;
  font-size: 14px;
  background: #fafafa;
  display: inline-block;
  z-index: 9;
  box-sizing: border-box;
}
.list-title {
  height: 40px;
  line-height: 40px;
  padding: 0 15px;
  box-sizing: border-box;
  border: solid 1px transparent;
  border-bottom: solid 1px #eee;
}
.type-item {
  height: 40px;
  line-height: 40px;
  padding-left: 15px;
  box-sizing: border-box;
  border: solid 1px transparent;
  border-bottom: solid 1px #eee;
  position: relative;
}

.type-item:hover {
  background-color: #fff;
  border: solid 1px #f5f5f5;
  border-right: none;
  border-left: none;
  z-index: 999;
}
.type-item:hover .item-infor {
  display: block;
}
.type-item :nth-child(1) {
  color: #000;
  font-weight: 500;
  font-size: 15px;
}
.type-item > a {
  text-decoration: none;
  color: #555;
  padding-right: 10px;
  cursor: pointer;
}
.type-item > a:hover {
  cursor: pointer;
  text-decoration: underline;
}
.item-infor {
  position: absolute;
  left: 100%;
  top: -10px;
  width: 250%;
  background-color: #fff;
  border: solid 1px #d5d5d5;
  z-index: 99;
  display: none;
}
.item-list {
  padding-right: 20px;
}
.item-list > a {
  text-decoration: none;
  color: #555;
  padding-left: 15px;
}
.item-list > a:hover {
  text-decoration: underline;
}

.item-list :nth-child(1) {
  color: #000;
  font-size: 15px;
  font-weight: 500;
}
.swiper {
  width: 75%;
  height: 408px;
}
.ad-bar {
  width: 100%;
  height: 120px;
  padding-bottom: 20px;
  box-sizing: border-box;
}
.ad-container {
  margin-top: 20px;
  display: flex;
  justify-content: space-between;
}
.vip-companys {
  width: 100px;
  height: 100px;
  display: flex;
  align-items: center;
  border: 1px solid #eee;
  cursor: pointer;
}
.vip-companys > img {
  width: 100%;
}
.company-tab {
  width: 100%;
}
.container-tab {
  height: 100%;
  max-width: 90%;
  min-width: 900px;
  margin: 0px auto;
}
.tab-list {
  height: 40px;
  width: 100%;
  font-size: 14px;
  text-align: center;
  line-height: 40px;
  font-family: "Microsoft Yahei";
  background: #f3f3f3;
}
.tab-list li {
  float: left;
  width: 100px;
  height: 100%;
  cursor: pointer;
  list-style: none;
  position: relative;
}
.tab-list .select {
  background: #fff;
  color: #0287ee;
}
.tab-list .select::before {
  width: 100%;
  content: "";
  height: 4px;
  background: #0287ee;
  position: absolute;
  top: 0;
  left: 0;
}

.tab-list li:hover {
  color: #0287ee;
}
.tab-content {
  display: block;
  margin-top: 10px;
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-bottom: 50px;
}
.tab-content .el-carousel {
  width: 100%;
}
.post {
  width: 380px;
  height: 195px;
  margin: 5px 0px;
  padding: 0px 10px;
  box-sizing: border-box;
  border: 1px solid #dadada;
  background: #fff;
}
.post-title {
  height: 100px;
  text-align: center;
  padding: 20px 0px;
  box-sizing: border-box;
}
.post-head {
  width: 100%;
  height: 30px;
  display: flex;
  justify-content: space-between;
}
.post-name {
  cursor: pointer;
  max-width: 59%;
  overflow: hidden;
  line-height: 30px;
  height: 30px;
  font-family: "微软雅黑";
  font-size: 18px;
  text-align: left;
  font-weight: bolder;
  color: rgba(0, 0, 0, 0.7);
}
.post-sepLine {
  height: 1px;
  width: 100%;
  background: #dadada;
}
.post-pay {
  font-size: 14px;
  width: 40%;
  height: 30px;
  line-height: 30px;
  text-align: right;
  color: #fd8150;
}
.post-body {
  width: 80%;
  height: 30px;
  display: flex;
  justify-content: flex-start;
}
.post-others {
  min-width: 30%;
  height: 100%;
  line-height: 30px;
  padding-left: 0px;
  text-align: left;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.post-other {
  color: #666;
  font-size: 14px;
  margin-left: -2px;
}
.post-company {
  height: 90px;
  width: 100%;
  padding-top: 15px;
  box-sizing: border-box;
  line-height: 90px;
  display: flex;
  flex-direction: row;
}
.company-logo {
  width: 60px;
  height: 60px;
  overflow: hidden;
}
.company-logo > img {
  width: 100%;
}
.company-infor {
  width: 70%;
  height: 50px;
  padding-left: 20px;
}
.company-name {
  height: 30px;
  line-height: 30px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  color: #0287ee;
}
.company-name > a {
  color: #0287ee;
  text-decoration: none;
}
.company-info {
  height: 30px;
  color: #aaa;
  line-height: 30px;
}
.goto-top {
  width: 50px;
  height: 43px;
  font-size: 12px;
  color: #999;
  display: block;
  text-decoration: none;
  text-align: center;
  right: 20px;
  bottom: 100px;
  z-index: 98;
  position: fixed;
}
.goto-top:hover {
  color: #0287ee;
}
.el-input .el-input__inner {
  width: 400px;
}
.mesg-form {
  box-sizing: border-box;
  width: 100%;
  padding: 10px 50px;
  margin: 20px 0px;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.1);
}
.line-item {
  width: 120px;
  display: inline-block;
  height: 40px;
  line-height: 40px;
  text-align: right;
  padding-right: 15px;
  color: #333;
  font-family: "微软雅黑";
  box-sizing: border-box;
}
.line-item-phone {
  display: inline-block;
  height: 40px;
  width: 120px;
  line-height: 40px;
  text-align: right;
  padding: 0px 15px;
  color: #333;
  font-family: "微软雅黑";
  box-sizing: border-box;
}
.line-input {
  width: 350px;
}
.line-ele {
}
.line-form {
  height: 40px;
  padding: 5px 0px;
  display: flex;
  justify-content: start;
}
.line-ele .el-date-editor {
  width: 197px;
}
.line-ele .el-select {
  width: 197px;
}
.line-element .el-input {
  width: 350px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 106px;
  height: 106px;
  line-height: 106px;
  text-align: center;
}
.avatar {
  width: 106px;
  height: 106px;
  display: block;
  border-radius:50%;
}
.self-mesg-input {
  position: fixed;
  top: 0px;
  width: 100%;
  height: 100vh;
  overflow: hidden;
  z-index: 99;
  background: rgba(0, 0, 0, 0.3);
}
.fundmation-mesg {
  width: 700px;
  margin: 60px auto;
  background: #fff;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.1);
  padding-bottom: 20px;
}
.mesg-title {
  width: 200px;
  height: 50px;
  padding: 20px 0px 0px 30px;
  font-size: 20px;
  box-sizing: border-box;
  font-family: "黑体";
  line-height: 30px;
  color: #0287ee;
}
.mesg-title > span {
  padding-left: 10px;
  color: #000;
}
.head-pic {
  position: relative;
  margin: 5px auto 10px;
  width: 106px;
  height: 106px;
  background-image: url("../assets/default_headpic.png");
  background-size: 106px;
  border-radius: 100%;
  box-shadow: 0 0 1px rgba(0, 0, 0, 0.1);
}
.mesg-form-bot {
  width: 100px;
  margin: 0px auto;
}
.mesg-form-bot .el-button.is-round {
  width: 100px;
}
.jobs-pagetab {
  text-align: center;
}
</style>