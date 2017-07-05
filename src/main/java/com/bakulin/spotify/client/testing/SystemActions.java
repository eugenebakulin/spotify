package com.bakulin.spotify.client.testing;

import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
public class SystemActions {
	
	private final static String BUNDLE_PATH = "src/main/resources";
	private final static String APP_PATH_WIN = "C:\\Users\\Eugene_Bakulin\\AppData\\Roaming\\Spotify\\Spotify.exe";
	private final static int TIMEOUT_CLIENT_LAUNCH = 10;
	
	public SystemActions() {};
	
	/**
	 * Selects all text in input and clears it.
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
		//TODO: implement launch for Mac
		App app = new App(APP_PATH_WIN);
		app.open(TIMEOUT_CLIENT_LAUNCH);
		return app;
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
	 * Updates Settings before test execution.
	 */
	public static void updateSettings() {
		Settings.AutoWaitTimeout = 10f;
		Settings.setShowActions(true);
//		Settings.Highlight = true;
		Settings.ActionLogs = true;
		ImagePath.setBundlePath(BUNDLE_PATH);
	}

	/**
	 * Closes Spotify client.
	 * @param s
	 */
	public static void closeClient(App app) {
		app.close();
	}
	
	public static LoginPage logOut(Screen s) {
		logoutKeysCombination(s);
		return new LoginPage(s);
	}

	public static void logoutKeysCombination(Screen s) {
		if (Settings.isWindows()) {
			s.type("w", KeyModifier.CTRL + KeyModifier.SHIFT);
		} else if (Settings.isMac()) {
			s.type("w", KeyModifier.CMD + KeyModifier.SHIFT);
		}
	}

}
