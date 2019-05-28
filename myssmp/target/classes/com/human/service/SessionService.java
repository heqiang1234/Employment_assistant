package com.human.service;

import com.human.model.Session;

/**
 * 创建日期:2017/12/21<br/>
 * 创建时间:8:55:24<br/>
 * 创建用户:yellowcong<br/>
 * 机能概要:
 */
public interface SessionService {

	/**
	 * 创建日期:2017/12/21<br/>
	 * 创建时间:8:44:54<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:插入session
	 * 
	 * @param session
	 */
	public int insert(Session session);

	/**
	 * 创建日期:2017/12/21<br/>
	 * 创建时间:8:48:06<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:删除session
	 * 
	 * @param sessionid
	 * @return
	 */
	public int delete(String sessionid);

	/**
	 * 
	 * 创建日期:2017/12/21<br/>
	 * 创建时间:8:48:23<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:删除session
	 * 
	 * @param session
	 * @return
	 */
	public int update(Session session);

	/**
	 * 创建日期:2017/12/21<br/>
	 * 创建时间:8:49:13<br/>
	 * 创建用户:yellowcong<br/>
	 * 机能概要:通过sessionid来获取session数据
	 * 
	 * @param sessionid
	 * @return
	 */
	public Session load(String sessionid);
	
}
