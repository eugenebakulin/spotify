package com.bakulin.spotify.client.testing;

import org.junit.Test;
import org.sikuli.script.FindFailed;
import static org.junit.Assert.*;

public class LoginTest extends SpotifyBasicTest {
	
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
	
}
