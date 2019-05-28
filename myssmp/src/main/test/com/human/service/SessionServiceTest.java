package com.human.service;

import com.human.model.Session;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class SessionServiceTest {
    @Autowired
    SessionService sessionService;

    @Test
    public void testInsert() {

        Session session = new Session();
        session.setId("1231313");
        session.setSession("doubi3");
        session.setUsername("doubi");
        int id = this.sessionService.insert(session);
        System.out.println(id);
    }

    @Test
    public void testLoad() {
        Session session = this.sessionService.load("1231313");
        System.out.println(session.getSession());
    }

    @Test
    public void testUpdate() {
        String updateSession = "testtestses2";
        String id = "1231313";
        Session session = this.sessionService.load(id);
        session.setUsername("doubi");
        session.setSession(updateSession);
        this.sessionService.update(session);

        Session session2 = this.sessionService.load(id);
        Assert.assertEquals(session2.getSession(), updateSession);
    }

    @Test
    public void testDelete() {
        String sessionId = "14";
        int cnt = this.sessionService.delete(sessionId);

        Session se = this.sessionService.load(sessionId);
        Assert.assertNull(se);
    }
}