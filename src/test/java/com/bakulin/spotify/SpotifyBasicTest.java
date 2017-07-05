package com.bakulin.spotify;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.sikuli.script.App;
import org.sikuli.script.Screen;

import com.bakulin.spotify.utils.SystemActions;
import com.bakulin.spotify.utils.TestDataProvider;

/**
 * Spotify Basic Test Class handles setup and teardown of client.
 */
public abstract class SpotifyBasicTest {
	
	protected final String username = readTestData("username");
	protected final String password = readTestData("password");
	
	private final String propertiesFileName = "src/main/resources/testdata.properties";
	
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
	 */
	@Before
	public void launchClient() {
		s = new Screen();
		app = SystemActions.launchClient();
	}
	
	/**
	 * Performs Log out action, closes the client.
	 */
	@After
	public void teardown() {
		SystemActions.logOutIfNeccessary(s);
		SystemActions.closeClient(app);
	}
	
	protected String readTestData(String key) {
		return TestDataProvider.readTestData(key, propertiesFileName);
	}
	
}
