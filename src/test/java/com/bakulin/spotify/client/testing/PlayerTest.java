package com.bakulin.spotify.client.testing;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import static org.junit.Assert.*;

public class PlayerTest {

	// TODO: implement un/pw retrieval through Test Args / JSON
	private final static String USERNAME = "Californiaby";
	private final static String PASSWORD = "password";

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
	public void testCanPlayPromoPlaylist() throws FindFailed {
		Boolean isPlayerPlaying = new LoginPage(s)
			.loginAsValidUser(USERNAME, PASSWORD)
			.clickGreenPlayButton()			
			.isPlaying();
		assertTrue(isPlayerPlaying);
	}
	
	@After
	public void teardown() throws FindFailed {
		new HomePage(s).logOut();
		new SystemActions().closeClient(app);
	}

}
