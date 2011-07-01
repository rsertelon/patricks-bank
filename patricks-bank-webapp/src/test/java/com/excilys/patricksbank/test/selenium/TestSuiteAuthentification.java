package com.excilys.patricksbank.test.selenium;

import java.util.ResourceBundle;

import com.thoughtworks.selenium.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSuiteAuthentification extends SeleneseTestCase {

	private final String context_path = "/patricks-bank";
	private final String host = "localhost:8080";

	ResourceBundle messages_fr = ResourceBundle.getBundle("messages_fr");
	ResourceBundle messages_en = ResourceBundle.getBundle("messages_en");

	@BeforeClass
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://" + host + context_path);
		selenium.setSpeed("100");
		selenium.start();

		selenium.open(context_path + "/login.html");
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		selenium.click("//img[@alt='fr']");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=" + messages_fr.getString("login.logout"));
		selenium.waitForPageToLoad("30000");
		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		selenium.click("//img[@alt='en']");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=" + messages_en.getString("login.logout"));
	}
	
	@Test
	public void testConnexionEnGet() throws Exception {
		selenium.open(context_path + "/login.html?j_username=user&j_password=user");
		assertEquals("http://" + host + context_path + "/login.html?j_username=user&j_password=user", selenium.getLocation());
	}

	@Test
	public void testAuthentificationUser() throws Exception {
		selenium.open(context_path + "/login.html");
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/user/home.html", selenium.getLocation());
		verifyTrue(selenium.isTextPresent(messages_fr.getString("usernav.hello")));
		verifyTrue(selenium.isTextPresent("Stephane Landelle"));
		verifyTrue(selenium.isTextPresent(messages_fr.getString("account.list.title")));
		selenium.click("link=" + messages_fr.getString("login.logout"));
	}

	@Test
	public void testMessageErreurMauvaiseAuth() throws Exception {
		selenium.open(context_path);
		selenium.type("j_username", "unknown");
		selenium.type("j_password", "unknown");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent(messages_fr.getString("login.error.credentials")));
	}

	@Test
	public void testInjectionSQL() throws Exception {
		selenium.open(context_path + "/login.html");
		selenium.type("j_username", "admin' --");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent(messages_fr.getString("login.error.credentials")));
		assertEquals("http://" + host + context_path + "/login.html", selenium.getLocation());
	}

	@Test
	public void testAuthentificationAdmin() throws Exception {
		selenium.open(context_path + "/login.html");
		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/admin/home.html", selenium.getLocation());
		verifyTrue(selenium.isTextPresent(messages_fr.getString("webapp.workinprogress")));
		selenium.click("link=" + messages_en.getString("login.logout"));
	}

	@Test
	public void testErrorPage404() throws Exception {
		selenium.open(context_path + "/login.html");
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/user/home.html", selenium.getLocation());
		verifyTrue(selenium.isTextPresent(messages_fr.getString("usernav.hello")));
		verifyTrue(selenium.isTextPresent("Stephane Landelle"));
		verifyTrue(selenium.isTextPresent(messages_fr.getString("account.list.title")));

		try {
			selenium.open(context_path + "/unknown_page");
		} catch (Exception e) {
			verifyTrue(selenium.isTextPresent(messages_fr.getString("404.title")));
			verifyTrue(selenium.isTextPresent(messages_fr.getString("404.message")));
		}
		selenium.click("link=" + messages_fr.getString("404.lien"));
		selenium.click("link=" + messages_fr.getString("login.logout"));
	}

	@Test
	public void testDeconnexionAdmin() throws Exception {
		selenium.open(context_path + "/login.html");
		selenium.type("j_username", "admin");
		selenium.type("j_password", "admin");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=" + messages_en.getString("login.logout"));
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/login.html", selenium.getLocation());
	}

	@Test
	public void testDeconnexionUser() throws Exception {
		selenium.open(context_path + "/login.html");
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=" + messages_fr.getString("login.logout"));
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/login.html", selenium.getLocation());
	}

	@Test
	public void testRedirectionApresLogin() throws Exception {
		selenium.open(context_path + "/user/compte/1/detail.html");
		assertEquals("http://" + host + context_path + "/login.html*", selenium.getLocation());
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@type='submit']");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/user/home.html", selenium.getLocation());
		selenium.click("link=" + messages_fr.getString("login.logout"));
	}

	@Test
	public void testAccesPageProtegeeSansEtreLogge() throws Exception {
		selenium.open(context_path + "/user/home.html");
		selenium.waitForPageToLoad("");
		assertEquals("http://" + host + context_path + "/login.html*", selenium.getLocation());
	}

	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
