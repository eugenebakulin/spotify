package com.bakulin.spotify.client.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

/**
 * Spotify Test Class handles setup and teardown of client.
 */
public abstract class SpotifyBasicTest {
	
	// TODO: implement un/pw retrieval through Test Args / JSON
	protected final static String USERNAME = "Californiaby";
	protected final static String PASSWORD = "password";
	protected final static String INVALID_PASSWORD = "this password is so wrong";
	
	protected Screen s;
	protected App app;
	
	/**
	 * Logs OS information to console, applies Sikuli Settings. 
	 */
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
	}
	
	/**
	 * Performs Log out action, closes the client.
	 * @throws FindFailed
	 */
	@After
	public void teardown() throws FindFailed {
		//TODO: check if not LoginPage is displayed > then
		new HomePage(s).logOut();
		SystemActions.closeClient(app);
	}
	
}
