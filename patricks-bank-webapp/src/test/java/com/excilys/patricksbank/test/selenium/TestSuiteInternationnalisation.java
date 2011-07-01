package com.excilys.patricksbank.test.selenium;

import java.util.ResourceBundle;

import com.thoughtworks.selenium.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

public class TestSuiteInternationnalisation extends SeleneseTestCase {

	private final String context_path = "/patricks-bank";
	private final String host = "localhost:8080";

	ResourceBundle messages_fr = ResourceBundle.getBundle("messages_fr");
	ResourceBundle messages_en = ResourceBundle.getBundle("messages_en");

	@BeforeClass
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://" + host + context_path);
		selenium.setSpeed("100");
		selenium.start();
	}

	@Test
	public void testPresenceDrapeaux() {
		selenium.open(context_path);
		verifyTrue(selenium.isElementPresent("//img[@alt='fr']"));
		verifyTrue(selenium.isElementPresent("//img[@alt='en']"));
		selenium.click("//img[@alt='en']");
		selenium.click("//img[@alt='fr']");
	}

	@Test
	public void testChangementLangueLoginPage() {
		selenium.open(context_path);
		selenium.click("//img[@alt='en']");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent(messages_en.getString("login.title")));
		selenium.click("//img[@alt='fr']");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent(messages_fr.getString("login.title")));
		}

	@Test
	public void testRememberLangueUtilisateur() {
		selenium.open(context_path + "/?locale=en");
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@value='Connection']");
		selenium.waitForPageToLoad("30000");
		selenium.click("//img[@alt='en']");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Disconnect");
		selenium.waitForPageToLoad("30000");
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@value='Connexion']");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent(messages_en.getString("account.list.title")));
		selenium.click("//img[@alt='fr']");
	}

	@Test
	public void testVerifyLangueParDefaut() {
		selenium.open(context_path);
		verifyTrue(selenium.isTextPresent(messages_fr.getString("login.title")));
	}
	
	@Test
	public void testLangueFrancaisApresDepart(){
		selenium.open(context_path);
		selenium.click("//img[@alt='en']");
		selenium.waitForPageToLoad("30000");
		selenium.open(context_path);
		verifyTrue(selenium.isTextPresent(messages_en.getString("login.title")));
	}

	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();
	}

}
