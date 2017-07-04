package com.bakulin.spotify.client.testing;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import com.bakulin.spotify.client.testing.SystemActions;

/**
 * POM class of Login Page for Spotify desktop client.
 */
public class LoginPage {

	Screen s;

	/*
	 * Login input can contain previously entered username, so easiest way to
	 * locate - find password input and use negative vertical offset
	 */
	private static final Pattern USERNAME_PATTERN = new Pattern("password_input.png").targetOffset(0, -44);
	private static final Pattern PASSWORD_PATTERN = new Pattern("password_input.png");
	private static final Pattern USERNAME_EMPTY_PATTERN = new Pattern("username_input.png");
	private static final Pattern REMEMBER_ME_CHECKED_PATTERN = new Pattern("remember_me_checked.png");
	private static final Pattern REMEMBER_ME_UNCHECKED_PATTERN = new Pattern("remember_me_unchecked.png");
	private static final Pattern LOGIN_BUTTON_PATTERN = new Pattern("login_button.png");
	private static final Pattern APP_LOGO_PATTERN = new Pattern("logo.png");
	private static final Pattern LOGIN_ERROR_PATTERN = new Pattern("login_error.png");
	private static final int TIMEOUT_LOGIN_PAGE_DISPLAY = 10; 

	public LoginPage(Screen s) {
		this.s = s;
		waitForPageToBeDisplayed();
	}

	/** Checks if Login page is displayed (i.e. logo is visible). */
	private void waitForPageToBeDisplayed() {
		try {
			s.wait(APP_LOGO_PATTERN, TIMEOUT_LOGIN_PAGE_DISPLAY);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	/**
	 * Logs in Spotify desktop client with given valid username / password.
	 * 
	 * @param username
	 * @param password
	 * @return HomePage new page instance for next screen
	 * @throws FindFailed
	 *           if pattern is not found
	 */
	public HomePage loginAsValidUser(String username, String password) throws FindFailed {
		provideCredentials(username, password);
		s.click(LOGIN_BUTTON_PATTERN);
		return new HomePage(s);
	}
	
	/**
	 * Logs in Spotify desktop client with given valid username / password.
	 * 
	 * @param username
	 * @param password
	 * @param rememberMe flag for "remember me" button
	 * @return HomePage new page instance for next screen
	 * @throws FindFailed
	 *           if pattern is not found
	 */
	public HomePage loginAsValidUser(String username, String password, boolean rememberMe) throws FindFailed {
		provideCredentials(username, password);
		setRememberMe(rememberMe);
		s.click(LOGIN_BUTTON_PATTERN);
		return new HomePage(s);
	}

	/**
	 * Attempts to log in with invalid username / password.
	 * 
	 * @param username
	 * @param password
	 * @throws FindFailed
	 *           if pattern is not found
	 */
	public LoginPage loginAsInvalidUser(String username, String password) throws FindFailed {
		provideCredentials(username, password);
		s.click(LOGIN_BUTTON_PATTERN);
		return this;
	}

	/**
	 * Provides username, password, and unchecks "remember me" if necessary.
	 * 
	 * @param username
	 * @param password
	 * @param rememberMe
	 *          false if script should uncheck option
	 * @throws FindFailed
	 *           if pattern is not found
	 */
	private void provideCredentials(String username, String password) throws FindFailed {
		s.click(USERNAME_PATTERN);
		SystemActions.selectAllAndClear(s);
		s.type(username);
		s.click(PASSWORD_PATTERN);
		SystemActions.selectAllAndClear(s);
		s.type(password);
	}

	/**
	 * Sets "remember me" flag during sign in. This method checks if Pattern
	 * matching conditions opposite to expected is visible, and clicks the button
	 * if it is. I.e. if we want flag ot by unchecked, method checks if there's
	 * "checked" pattern and clicks accordingly.
	 * 
	 * @param rememberMe
	 *          true if flag should be checked, false otherwise
	 */
	private void setRememberMe(Boolean rememberMe) {
		if (!rememberMe) {
			try {
				if (s.find(REMEMBER_ME_CHECKED_PATTERN.exact()) != null) {
					s.click(REMEMBER_ME_CHECKED_PATTERN.exact().targetOffset(130, 0));
				}
			} catch (FindFailed e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (s.find(REMEMBER_ME_UNCHECKED_PATTERN.exact()) != null) {
					s.click(REMEMBER_ME_UNCHECKED_PATTERN.exact().targetOffset(130, 0));
				}
			} catch (FindFailed e) {
				e.printStackTrace();
			}
		}
	}

	public Boolean isLoginErrorDisplayed() throws FindFailed {
		Match loginError = s.wait(LOGIN_ERROR_PATTERN, TIMEOUT_LOGIN_PAGE_DISPLAY);
		if (loginError != null) {
			System.out.println("Login error displayed!");
			loginError.highlight(1);
			return true;
		} else {
			return false;
		}
	}

	public Boolean isEmptyUsernameInput() {
		try {
			Match emptyUsernameInput = s.wait(USERNAME_EMPTY_PATTERN.exact(), TIMEOUT_LOGIN_PAGE_DISPLAY);
			emptyUsernameInput.highlight(1);
			System.out.println("Found pattern " + USERNAME_EMPTY_PATTERN);
			return true;
		} catch (FindFailed e) {
			System.out.println("Was not able to find pattern " + USERNAME_EMPTY_PATTERN);
			return false;
		}
	}

}
