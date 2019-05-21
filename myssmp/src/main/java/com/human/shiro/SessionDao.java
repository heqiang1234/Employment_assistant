package com.human.shiro;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.human.util.SerializableUtils;
import com.human.dao.SessionMapper;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * 创建日期:2017/12/21<br/>
 * 创建时间:8:31:04<br/>
 * 创建用户:yellowcong<br/>
 * 机能概要:用于Session的保存
 */
public class SessionDao extends EnterpriseCacheSessionDAO {

    @Autowired
    private SessionMapper sessionMapper;

    public void delete(Session session) {
        //删除session
        System.out.println("delete");
        this.sessionMapper.delete(session.getId().toString());
    }

    public void update(Session session) throws UnknownSessionException {
        //当是ValidatingSession 无效的情况下，直接退出
        System.out.println("update");
        if(session instanceof ValidatingSession &&
                !((ValidatingSession)session).isValid() ) {
            return ;
        }

        //检索到用户名
        String username = String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));

        //序列化Session

        this.sessionMapper.update(session.getId().toString(), SerializableUtils.serializ(session),username);
    }

    @Override
    protected Serializable doCreate(Session session) {
        //生成session的id
        System.out.println("docreate");
        Serializable sessionId = generateSessionId(session);
        //给Session设定id
        assignSessionId(session, sessionId);


        //插入session 到数据库
        this.sessionMapper.insert(session.getId().toString(), SerializableUtils.serializ(session));

        return sessionId;
    }

    /**
     * 创建日期:2017/12/21<br/>
     * 创建时间:13:56:15<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:通过名称来获取用户 Session
     * @param username
     * @return
     */
    public List<Session> loadByUserName(String username) {
        //获取session的字符串
        System.out.println("loadByUserName");
        List<com.human.model.Session> dbSessions = this.sessionMapper.loadByUserName(username);

        //判断是否存在用户的情况
        if(dbSessions == null || dbSessions.size() == 0) {
            return null;
        }

        List<Session> result = new ArrayList<Session>();
        for(com.human.model.Session session:dbSessions) {
            //加载session数据
            String sessionStr = session.getSession();

            //将Session的数据串，转化为对象
            result.add(SerializableUtils.deserializ(sessionStr));
        }

        return result;
    }


    @Override
    protected Session doReadSession(Serializable sessionId) {
        //获取session的字符串
        com.human.model.Session dbSession = this.sessionMapper.load(sessionId.toString());
        if(dbSession == null) {
            return null;
        }

        //加载session数据
        String sessionStr = dbSession.getSession();
        return SerializableUtils.deserializ(sessionStr);
    }

}