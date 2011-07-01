package com.excilys.patricksbank.test.selenium;

import java.util.ResourceBundle;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class TestSuiteListeCompteUtilisateur extends SeleneseTestCase {

	ResourceBundle messages_fr = ResourceBundle.getBundle("messages_fr");

	private final String context_path = "/patricks-bank";
	private final String host = "localhost:8080";

	@BeforeClass
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://" + host + context_path);
		selenium.setSpeed("100");
		selenium.start();
		selenium.open(context_path + "/login.html");
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@type='submit']");
	}

	@Test
	public void testUtilisateurAuthentifieAccedeListeComptes() {
		selenium.open(context_path + "/user/home.html");
		verifyTrue(selenium.isTextPresent("Compte Courant (n° 993 045 71 789)"));
		verifyTrue(selenium.isTextPresent("623.56€"));
		verifyTrue(selenium.isTextPresent("Livret Jeune (n° 993 045 71 759)"));
		verifyTrue(selenium.isTextPresent("-63.2€"));
		verifyTrue(selenium.isTextPresent("Livret A (n° 993 545 71 789)"));
		verifyTrue(selenium.isTextPresent("1560€"));
	}

	@Test
	public void testClicSurLienCompteEnvoieVersDetailCompte() {
		selenium.click("//ul[@id='comptes']/li[1]/a[1]");
		assertEquals("http://" + host + context_path + "/user/compte/1/detail.html", selenium.getLocation());
	}

	@After
	public void logoutAfterTest() {
		selenium.click("link=" + messages_fr.getString("login.logout"));
	}

	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();
	}

}
