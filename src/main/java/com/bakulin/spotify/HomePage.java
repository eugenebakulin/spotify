package com.bakulin.spotify;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

import com.bakulin.spotify.utils.SystemActions;

/**
 * Page-Object Model representation of Home Page for Spotify desktop client.
 */
public class HomePage extends Page {
	
	private static final Pattern PLAYER_MENU_PATTERN = new Pattern("player_menu.png");
	private static final Pattern PLAYER_MENU_ACTIVE_PATTERN = new Pattern("player_menu_active.png");
	private static final Pattern ARTISTS_HEADER_PATTERN = new Pattern("artists_header.png");
	private static final Pattern SEARCH_INPUT_PATTERN = new Pattern("search_input.png");
	private static final Pattern OVERVIEW_MENU_PATTERN = new Pattern("overview_menu.png");
	private static final int TIMEOUT_HOME_PAGE_DISPLAY = 20;

	public HomePage(Screen s) {
		super(s);
	}
	
	/** Checks if Home Screen is displayed (i.e. player is visible). */
	@Override
	void waitForPageToBeDisplayed() {
		System.out.println("Loading Home Screen POM.");
		try {
			s.wait(PLAYER_MENU_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
			System.out.printf("Successfully found %s, Home Screen is visible.\n", PLAYER_MENU_PATTERN);
		} catch (FindFailed e) {
			System.out.printf("Was not able to find pattern %s, Login Home is not visible.\n", PLAYER_MENU_PATTERN);
		}
	}

	/**
	 * Checks if Home screen is displayed after log in.
	 * @return true if successfully displayed, false otherwise
	 */
	public Boolean isHomePageDisplayed() {
		System.out.println("Checking if Home Page is displayed.");
		Match playerMenu;
		try {
			playerMenu = s.wait(PLAYER_MENU_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
			System.out.println("Found player menu!");
			playerMenu.highlight(1);
			return true;
		} catch (FindFailed e) {
			System.out.println("Player menu was not found!");
			return false;
		}
	}

	/**
	 * Logs out user from Home screen.
	 * @return
	 */
	public LoginPage logOut() {
		SystemActions.logoutKeysCombination(s);
		return new LoginPage(s);
	}

	/**
	 * Performs search with given request.
	 * @param searchRequest request value
	 * @return {@link HomePage} same page
	 * @throws FindFailed if pattern is not found
	 */
	public HomePage search(String searchRequest) throws FindFailed {
		System.out.printf("Searching for %s.\n", searchRequest);
		s.wait(OVERVIEW_MENU_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		s.click(SEARCH_INPUT_PATTERN);
		SystemActions.selectAllAndClear(s);
		s.type(searchRequest);
		s.type(SEARCH_INPUT_PATTERN, Key.ENTER);
		return this;
	}

	/**
	 * Checks if expected Artist pattern is visible.
	 * @param artistPattern used for search
	 * @return true if visible, false otherwise
	 * @throws FindFailed if pattern is not found
	 */
	public Boolean isArtistFound(Pattern artistPattern) throws FindFailed {
		System.out.println("Checking if Artist is displayed.");
		s.wait(ARTISTS_HEADER_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		Region artist = s.find(artistPattern);
		if (artist != null) {
			artist.highlight(1);
			System.out.println("Artist is found!");
			return true;
		}
		System.out.println("Artist was not found.");
		return false;
	}
	
	/**
	 * Clicks on artist pattern.
	 * @param artistPattern
	 * @return {@link HomePage} same page
	 * @throws FindFailed if pattern is not found
	 */
	public HomePage selectArtist(Pattern artistPattern) throws FindFailed {
		s.wait(ARTISTS_HEADER_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		Region artist = s.find(artistPattern);
		if (artist != null) {
			s.click(artistPattern);
		}
		return this;
}

	/**
	 * Checks if player is currently playing.
	 * @return true if playing, false otherwise
	 */
	public Boolean isPlaying() {
		System.out.println("Checking if player is active (playing).");
		try {
			Match activePlayer = s.wait(PLAYER_MENU_ACTIVE_PATTERN.exact(), TIMEOUT_HOME_PAGE_DISPLAY);
			System.out.println("Player is active.");
			activePlayer.highlight(1);
			return true;
		} catch (FindFailed e) {
			System.out.println("Was not able to find pattern " + PLAYER_MENU_ACTIVE_PATTERN);
			return false;
		}
	}
	
	// TODO: handle advertisement popup

}
