package com.bakulin.spotify.utils;

import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Screen;

import com.bakulin.spotify.LoginPage;

/**
 * Implementation of system-level actions for Spotify Client test automation framework. 
 */
public class SystemActions {
	
	private final static String BUNDLE_PATH = "src/main/resources";
	private final static String PROPERTIES_FILE = "src/main/resources/testdata.properties";
	private final static String APP_PATH_WIN = TestDataProvider.readTestData("win_app_location", PROPERTIES_FILE);
	private final static String APP_PATH_MAC = TestDataProvider.readTestData("mac_app_location", PROPERTIES_FILE);
	private final static int TIMEOUT_CLIENT_LAUNCH = 10;
	
	/**
	 * Selects all text in active input and clears it.
	 * @param s Screen
	 */
	public static void selectAllAndClear(Screen s) {
		if (Settings.isWindows()) {
			s.type("a", KeyModifier.CTRL);
		} else if (Settings.isMac()) {
			s.type("a", KeyModifier.CMD);
		}
		s.type(Key.DELETE);
	}

	/**
	 * Launches Spotify client.
	 * @return {@link App} instance of launched client
	 */
	public static App launchClient() {
		System.out.println("Launching client.");
		String appPath = "";
		if (Settings.isWindows()) {
			appPath = (APP_PATH_WIN != "" && APP_PATH_WIN != null) ? APP_PATH_WIN : buildDefaultWinAppPath();
		} else if (Settings.isMac()) {
			appPath = APP_PATH_MAC;
		}
		App app = new App(appPath);
		app.open(TIMEOUT_CLIENT_LAUNCH);
		return app;
	}
	
	/**
	 * Builds String with path to Spotify app location on Windows using default location.
	 * Format: $USER.HOME\\AppData\\Roaming\\Spotify\\Spotify.exe
	 * This method is called of no value provided for win_app_location in properties file.
	 * @return String value of path
	 */
	private static String buildDefaultWinAppPath () {
		StringBuilder sb = new StringBuilder();
		sb
			.append(System.getProperty("user.home"))
			.append("\\AppData\\Roaming\\Spotify\\Spotify.exe");
		return sb.toString();
	}

	/**
	 * Logs system info: OS name and version.
	 */
	public static void logSystemInfo() {
		int OS = Settings.getOS();
		switch (OS) {
			case 0: System.out.println(("Running test on Windows " + Settings.getOSVersion()));
					break;
			case 1: System.out.println(("Running test on Mac " + Settings.getOSVersion()));
					break;
		}
	}

	/**
	 * Applies Settings before test execution.
	 */
	public static void updateSettings() {
		Settings.AutoWaitTimeout = 10f;
		Settings.setShowActions(true);
//		Settings.Highlight = true;
		Settings.ActionLogs = false;
		ImagePath.setBundlePath(BUNDLE_PATH);
	}

	/**
	 * Closes Spotify client.
	 * @param s Screen
	 */
	public static void closeClient(App app) {
		System.out.println("Closing client.");
		app.close();
	}
	
	/**
	 * Checks if log out if necessary (i.e. not Login screen is displayed. Logs out.
	 * @param s Screen
	 * @return {@link LoginPage}
	 */
	public static LoginPage logOutIfNeccessary(Screen s) {
		System.out.println("Checking if log out is neccessary.");
		if (!LoginPage.isDisplayed(s)){
			System.out.println("Logging out.");
			logoutKeysCombination(s);
		} else {
			System.out.println("Already logged out");
		}
		return new LoginPage(s);
	}

	/**
	 * Performs hotkey combination for logout.
	 * @param s Screen
	 */
	public static void logoutKeysCombination(Screen s) {
		if (Settings.isWindows()) {
			s.type("w", KeyModifier.CTRL + KeyModifier.SHIFT);
		} else if (Settings.isMac()) {
			s.type("w", KeyModifier.CMD + KeyModifier.SHIFT);
		}
	}

}
