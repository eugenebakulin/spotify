package com.bakulin.spotify.client.testing;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import static org.junit.Assert.*;

public class SearchTest {

	// TODO: implement un/pw retrieval through Test Args / JSON
	private final static String USERNAME = "Californiaby";
	private final static String PASSWORD = "password";
	private final static String SEARCH_REQUEST_AUTHOR = "MC Hammer";
	private final static Pattern AUTHOR_PATTERN = new Pattern("src/main/resources/author_mc_hammer.png");
	// TODO: get variables from file

	static Screen s;
	App app;

	@BeforeClass
	public static void setupSystem() {
		SystemActions.logSystemInfo();
		SystemActions.updateSettings();
	}

	/**
	 * Launches client, prepares for test.
	 * 
	 * @throws FindFailed
	 */
	@Before
	public void launchClient() throws FindFailed {
		s = new Screen();
		app = SystemActions.launchClient();
		// Check if already logged in. Log out of neccessary.
	}

	@Test
	public void testSearchByValidArtistName() throws FindFailed {
		Boolean isArtistFound = new LoginPage(s)
				.loginAsValidUser(USERNAME, PASSWORD)
				.search(SEARCH_REQUEST_AUTHOR)
				.isArtistFound(AUTHOR_PATTERN);
		assertTrue(isArtistFound);
	}

	@After
	public void teardown() throws FindFailed {
		new HomePage(s).logOut();
		new SystemActions().closeClient(app);
		// TODO: log out
		// TODO: close client
	}

}
