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
	
	/**
	 * Logs in with valid username / password, verifies that Home screen is displayed.
	 * @throws FindFailed
	 */
	@Test
	public void testLogInAsValidUser() throws FindFailed {
		Boolean isHomePageDisplayed = new LoginPage(s)
			.loginAsValidUser(username, password)
			.isHomePageDisplayed();
		assertTrue(isHomePageDisplayed);
	}
	
	/**
	 * Attempts to log is with invalid username / password, verifies that error message is displayed.
	 * @throws FindFailed
	 */
	@Test
	public void testLogInAsInvalidUser() throws FindFailed {
		Boolean isLoginErrorDisplayed = new LoginPage(s)
			.loginAsInvalidUser(username, invalidPassword)
			.isLoginErrorDisplayed();
		assertTrue(isLoginErrorDisplayed);
	}
	
	/**
	 * Logs in with valid username / password, unchecks "remember me" option, logs out, verifies that "username input" 
	 * is empty.
	 * @throws FindFailed
	 */
	@Test
	public void testUncheckedRememberMe() throws FindFailed {
		Boolean isEmptyUsername = new LoginPage(s)
			.loginAsValidUser(username, password, false)
			.logOut()
			.isEmptyUsernameInput();
		assertTrue(isEmptyUsername);
	}
	
}
