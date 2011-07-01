package com.excilys.patricksbank.test.selenium;

import java.util.ResourceBundle;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

public class TestSuiteAfficherLeDetailCompte extends SeleneseTestCase{
	
	ResourceBundle messages_fr = ResourceBundle.getBundle("messages_fr");
	ResourceBundle messages_en = ResourceBundle.getBundle("messages_en");
	
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
		selenium.type("j_username", "user");
		selenium.type("j_password", "user");
		selenium.click("//input[@type='submit']");
		selenium.open(context_path + "/user/home.html");
	}
	
	@Test
	public void testUtilisateurAuthentifieAccedeListeOperationPourUnCompte() {
		selenium.click("//ul[@id='comptes']/li[1]/a/strong");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("Detail : Compte courant (n° 993 045 71 789)"));
		verifyTrue(selenium.isTextPresent("Date"));
		verifyTrue(selenium.isTextPresent("Type"));
		verifyTrue(selenium.isTextPresent("Détail"));
		verifyTrue(selenium.isTextPresent("Débit"));
		verifyTrue(selenium.isTextPresent("Crédit"));
		verifyTrue(selenium.isTextPresent("2011-08-12 00:00:00.0"));
		verifyTrue(selenium.isTextPresent("PAIEMENT_CARTE"));
		verifyTrue(selenium.isTextPresent("Casino"));
		verifyTrue(selenium.isTextPresent("-15.0 €"));
		verifyTrue(selenium.isTextPresent("2011-01-13 00:00:00.0"));
		verifyTrue(selenium.isTextPresent("VIREMENT_EMIS"));
		verifyTrue(selenium.isTextPresent("Franprix"));
		verifyTrue(selenium.isTextPresent("-152.0 €"));
		verifyTrue(selenium.isTextPresent("2011-11-14 00:00:00.0"));
		verifyTrue(selenium.isTextPresent("CHEQUE"));
		verifyTrue(selenium.isTextPresent("Mammuth"));
		verifyTrue(selenium.isTextPresent("-45.0 €"));
		verifyTrue(selenium.isTextPresent("2011-10-15 00:00:00.0"));
		verifyTrue(selenium.isTextPresent("PAIEMENT_CARTE"));
		verifyTrue(selenium.isTextPresent("Auchan"));
		verifyTrue(selenium.isTextPresent("-52.0 €"));
		verifyTrue(selenium.isTextPresent("2011-12-16 00:00:00.0"));
		verifyTrue(selenium.isTextPresent("REMISE_CHEQUE"));
		verifyTrue(selenium.isTextPresent("PatricksBank"));
		verifyTrue(selenium.isTextPresent("400.0 €"));
		verifyTrue(selenium.isTextPresent("Retour à la liste des comptes"));
		selenium.click("back_link");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/user/home.html", selenium.getLocation());
	}
	
	@Test
	public void testUtilisateurAuthentifieEssayeAccesAUnCompteInexistant() throws Exception {
		selenium.click("//ul[@id='comptes']/li[1]/a/strong");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/user/compte/1/detail.html", selenium.getLocation());
		selenium.open("http://" + host + context_path + "/user/compte/9abc/detail.html");
		assertEquals("http://" + host + context_path + "/user/home.html", selenium.getLocation());
	}
	
	@Test
	public void testUtilisateurAuthentifieEssayeAccesAUnCompteQuiNeLuiAppartientPas() throws Exception {
		selenium.click("//ul[@id='comptes']/li[1]/a/strong");
		selenium.waitForPageToLoad("30000");
		assertEquals("http://" + host + context_path + "/user/compte/1/detail.html", selenium.getLocation());
		selenium.open("http://" + host + context_path + "/user/compte/4/detail.html");
		assertEquals("http://" + host + context_path + "/user/home.html", selenium.getLocation());
	}
	
	@Test
	public void testUtilisateurAuthentifieChoisiUneDateGraceAuSelecteurs() throws Exception {
		selenium.click("//ul[@id='comptes']/li[1]/a/strong");
		selenium.waitForPageToLoad("30000");
		selenium.select("id=mois", "label=Janvier");
		selenium.click("//input[@value='Afficher']");
		selenium.waitForPageToLoad("30000");
		verifyTrue(selenium.isTextPresent("12/01/2011"));
		verifyTrue(selenium.isTextPresent("PAIEMENT_CARTE"));
		verifyTrue(selenium.isTextPresent("Casino"));
		verifyTrue(selenium.isTextPresent("-15.0 €"));
		verifyTrue(selenium.isTextPresent("05/01/2011"));
		verifyTrue(selenium.isTextPresent("RETRAIT"));
		verifyTrue(selenium.isTextPresent("Casino"));
		verifyTrue(selenium.isTextPresent("-30.0 €"));
		verifyTrue(selenium.isTextPresent("05/01/2011"));
		verifyTrue(selenium.isTextPresent("PAIEMENT_CARTE"));
		verifyTrue(selenium.isTextPresent("Casino"));
		verifyTrue(selenium.isTextPresent("-15.0 €"));
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
