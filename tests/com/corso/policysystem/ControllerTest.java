package com.corso.policysystem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    private Database     mockDatabase;
    private Company      mockCompany;
    private User         mockUser;
    private Policy       mockPolicy;
    private Controller   controller;

    private final ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
    private final ArgumentCaptor<Double> doubleCaptor = ArgumentCaptor.forClass(Double.class);

    //User constants
    private static final String EMAILINDATABASE     = "test@email.com";
    private static final String EMAILNOTINDATABASE  = "test_not@email.com";
    private static final String PASSWORD            = "test_password";

    //Policy constants
    private static final Integer ID          = 1;
    private static final String  DESCRIPTION = "test_policy";
    private static final Double  COST        = 10D;

    @Before
    public void setUp() throws Exception {
        mockCompany     = mock(Company.class);
        mockDatabase    = mock(Database.class);
        mockUser        = mock(User.class);
        mockPolicy      = mock(Policy.class);
        controller      = new Controller(mockCompany, mockDatabase);

        //mockDatabase behaviour
        when(mockDatabase.getUserByEmail(EMAILINDATABASE)).thenReturn(mockUser);
        when(mockDatabase.saveUser(EMAILNOTINDATABASE, PASSWORD)).thenReturn(mockUser);
        when(mockDatabase.getPolicyById(ID)).thenReturn(mockPolicy);

        //mockUser behaviour
        when(mockUser.getEmail()).thenReturn(EMAILINDATABASE);
    }

    /*
    Tests the registerUser method with a parameter that represents an email already present in the database
    In particular it tests whether the currentlyLoggedUser attribute is correctly set
    and makes sure that the saveUser method is not getting
     */
    @Test
    public void standardRegisterExistentUserTest() {
        controller.registerUser(EMAILINDATABASE, PASSWORD);

        assertEquals(null, controller.getCurrentlyLoggedUser());
        verify(mockDatabase, times(0)).saveUser(isA(String.class), isA(String.class));
    }

    /*
    Tests the registerUser method with a parameter that represents an email that is not already present in the database
    In particular it tests whether saveUser is called and and whether it sets the currently logged user correctly
     */
    @Test
    public void standardRegisterNewUserTest(){
        controller.registerUser(EMAILNOTINDATABASE, PASSWORD);

        verify(mockDatabase).saveUser(EMAILNOTINDATABASE, PASSWORD);
        assertEquals(mockUser, controller.getCurrentlyLoggedUser());
    }

    /*
    Tests the insertPolicy method
    In particular tests that it calls the savePolicy method and it controls whether it passes the right arguments
     */
    @Test
    public void standardInsertPolicyArgumentsTest(){
        controller.insertPolicy(DESCRIPTION, COST);

        verify(mockDatabase).savePolicy(stringCaptor.capture(), doubleCaptor.capture());
        assertEquals(DESCRIPTION, stringCaptor.getValue());
        assertEquals(COST, doubleCaptor.getValue());
    }

    /*
    Test the getPolicyById method
    In particular tests that it returns the right policy
     */
    @Test
    public void standardGetPolicyTest(){
        assertEquals(mockPolicy, controller.getPolicyById(ID));
    }

    /*
    Tests the buyPolicy method
    In particular verifies that the update user policy is called with the user and the policy specified
    and that the company receives a notification
     */
    @Test
    public void standardBuyPolicyTest(){
        controller.setCurrentlyLoggedUser(mockUser);
        controller.buyPolicy(mockPolicy);

        verify(mockDatabase).updateUserPolicy(mockUser.getEmail(), mockPolicy);
        verify(mockCompany).receiveNotification(isA(Notification.class));
    }
}