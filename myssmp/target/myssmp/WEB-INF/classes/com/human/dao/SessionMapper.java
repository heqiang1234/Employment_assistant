package com.human.dao;

import com.human.model.Session;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 创建日期:2017/12/21<br/>
 * 创建时间:8:44:22<br/>
 * 创建用户:yellowcong<br/>
 * 机能概要:
 */
public interface SessionMapper {

    /**
     * 创建日期:2017/12/21<br/>
     * 创建时间:8:44:54<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:插入session
     *
     * @param session
     */
    public int insert(@Param("id") String id,@Param("session") String session);

    /**
     * 创建日期:2017/12/21<br/>
     * 创建时间:8:48:06<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:删除session
     *
     * @param sessionid
     * @return
     */
    public int delete(String  sessionid);

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
    public int update(@Param("id") String id,@Param("session") String session,@Param("username") String username);

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

    /**
     * 创建日期:2017/12/21<br/>
     * 创建时间:11:52:02<br/>
     * 创建用户:yellowcong<br/>
     * 机能概要:根据用户名获取sesssion
     * @param username
     * @return
     */
    public List<Session> loadByUserName(@Param("username") String username);
}