package com.corso.policysystem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationTest {

    private static final String TITLE       = "TestTitle";
    private static final String CONTENT     = "TestContent";
    private static final String COMPANYNAME = "Test Company";

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void standardPrepareTest() {
        Notification n = new Notification();
        Notification nTest = Notification.prepare();

        assertEquals(n.getSender(), nTest.getSender());
        assertEquals(n.getTitle(), nTest.getTitle());
        assertEquals(n.getContent(), nTest.getContent());
    }

    @Test
    public void standardPrepareSettersTest(){
        User u = new User();
        Notification n = Notification.prepare().setSender(u).setTitle(TITLE).setContent(CONTENT);

        assertEquals(u, n.getSender());
        assertEquals(TITLE, n.getTitle());
        assertEquals(CONTENT, n.getContent());
    }

    /*
    Tests whether the notification that a user receives contains the right information
     */
    @Test
    public void standardSendTo() {
        Company company = new Company(COMPANYNAME);
        User user = new User();
        Notification.prepare().setSender(company).setTitle(TITLE).setContent(CONTENT).sendTo(user);
        Notification notification = user.getNotification();

        assertEquals(company, notification.getSender());
        assertEquals(TITLE, notification.getTitle());
        assertEquals(CONTENT, notification.getContent());
    }
}