package com.bakulin.spotify;

import org.junit.Test;
import org.sikuli.script.FindFailed;

import com.bakulin.spotify.LoginPage;

import static org.junit.Assert.*;

/**
 * Tests for authorization functionality of Spotify client.
 */
public class LoginTest extends SpotifyBasicTest {
	
	protected final String invalidPassword = readTestData("invalid_password");
	
	@Test
	public void testLogInAsValidUser() throws FindFailed {
		Boolean isHomePageDisplayed = new LoginPage(s)
			.loginAsValidUser(username, password)
			.isHomePageDisplayed();
		assertTrue(isHomePageDisplayed);
	}
	
	@Test
	public void testLogInAsInvalidUser() throws FindFailed {
		Boolean isLoginErrorDisplayed = new LoginPage(s)
			.loginAsInvalidUser(username, invalidPassword)
			.isLoginErrorDisplayed();
		assertTrue(isLoginErrorDisplayed);
	}
	
	@Test
	public void testUncheckedRememberMe() throws FindFailed {
		Boolean isEmptyUsername = new LoginPage(s)
			.loginAsValidUser(username, password, false)
			.logOut()
			.isEmptyUsernameInput();
		assertTrue(isEmptyUsername);
	}
	
}
