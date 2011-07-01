package com.excilys.patricksbank.test.controller;

import org.junit.*;

import com.excilys.patricksbank.controller.DefaultController;

import static org.junit.Assert.*;
//import static org.easymock.EasyMock.*;

public class DefaultControllerTest {
	private DefaultController defaultController;
	
	@Before
	public void setup(){
		defaultController = new DefaultController();
	}
	
	@Test
	public void testHandleLogin(){
		// On veut afficher la vue qui correspond au login
		assertEquals("login", defaultController.handleLogin().getViewName());
	}
	
	@Test
	public void testHandleDefault(){
		// On veut afficher la vue qui correspond au login
		assertEquals("login", defaultController.handleDefault().getViewName());
	}
}
