package com.bakulin.spotify.client.testing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import static org.junit.Assert.*;

public class LoginTest {

	// TODO: implement un/pw retrieval through Test Args / JSON
	private final static String USERNAME = "Californiaby";
	private final static String PASSWORD = "password";
	private final static String INVALID_PASSWORD = "this password is so wrong";

	static Screen s;
	App app;

	@BeforeClass
	public static void setupSystem() {
		SystemActions.logSystemInfo();
		SystemActions.updateSettings();
	}

	/**
	 * Launches client, prepares for test.
	 * @throws FindFailed 
	 */
	@Before
	public void launchClient() throws FindFailed {
		s = new Screen();
		app = SystemActions.launchClient();
		// Check if already logged in. Log out of neccessary.
	}

	@Test
	public void testLogInAsValidUser() throws FindFailed {
		Boolean isHomePageDisplayed = new LoginPage(s)
			.loginAsValidUser(USERNAME, PASSWORD)
			.isHomePageDisplayed();
		assertTrue(isHomePageDisplayed);
	}
	
	@Test
	public void testLogInAsInvalidUser() throws FindFailed {
		Boolean isLoginErrorDisplayed = new LoginPage(s)
			.loginAsInvalidUser(USERNAME, INVALID_PASSWORD)
			.isLoginErrorDisplayed();
		assertTrue(isLoginErrorDisplayed);
	}
	
	@Test
	public void testUncheckedRememberMe() throws FindFailed {
		Boolean isEmptyUsername = new LoginPage(s)
			.loginAsValidUser(USERNAME, PASSWORD, false)
			.logOut()
			.isEmptyUsernameInput();
		assertTrue(isEmptyUsername);
	}
	
	@Test
	public void testAbstract() {
		new TestPage(s);
	}
	
	@After
	public void teardown() throws FindFailed {
		new HomePage(s).logOut();
		new SystemActions().closeClient(app);
	}

}
