package com.excilys.patricksbank.test.controller;

import org.junit.*;

import com.excilys.patricksbank.controller.AdminController;

import static org.junit.Assert.*;


public class AdminControllerTest {
	private AdminController adminController;
	
	@Before
	public void setup(){
		adminController = new AdminController();
	}
	
	@Test
	public void testHandleHome(){
		assertEquals("admin", adminController.handleHome().getViewName());
	}
}
