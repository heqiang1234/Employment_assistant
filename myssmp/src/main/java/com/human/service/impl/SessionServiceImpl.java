package com.human.service.impl;

import com.human.dao.SessionMapper;
import com.human.model.Session;
import com.human.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 创建日期:2017/12/21<br/>
 * 创建时间:8:56:09<br/>
 * 创建用户:yellowcong<br/>
 * 机能概要:
 */
@Service("sessionService")
public class SessionServiceImpl implements SessionService {
	
	@Autowired
	private SessionMapper sessionMapper ;
	
	public int insert(Session session) {
		return this.sessionMapper.insert(session.getId(),session.getSession());
	}

	public int delete(String sessionid) {
		return this.sessionMapper.delete(sessionid);
	}

	public int update(Session session) {
		return this.sessionMapper.update(session.getId(),session.getSession(),session.getUsername());
	}

	public Session load(String sessionid) {
		return this.sessionMapper.load(sessionid);
	}

}
