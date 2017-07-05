package com.bakulin.spotify;

import org.sikuli.script.Button;
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
	private static final Pattern GREEN_PLAY_BUTTON_PATTERN = new Pattern("green_play_button.png");
	private static final int TIMEOUT_HOME_PAGE_DISPLAY = 20; 

	public HomePage(Screen s) {
		super(s);
	}
	
	/** Checks if Login page is displayed (i.e. logo is visible). */
	@Override
	void waitForPageToBeDisplayed() {
		System.out.println("Checking of Home page is displayed. Looking for Player menu.");
		try {
			s.wait(PLAYER_MENU_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
			System.out.println("Player menu is displayed.");
		} catch (FindFailed e) {
			System.out.println("Player menu is not displayed.");
		}
	}

	/*
	 * Checks if Home page is displayed after log.
	 */
	public Boolean isHomePageDisplayed() throws FindFailed {
		System.out.println("Checking if Home Page is displayed.");
		Match playerMenu = s.wait(PLAYER_MENU_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		if (playerMenu != null) {
			System.out.println("Found player menu!");
			playerMenu.highlight(1);
			return true;
		}
		System.out.println("Player menu was not found!");
		return false;
	}

	public LoginPage logOut() {
		SystemActions.logoutKeysCombination(s);
		return new LoginPage(s);
	}

	public HomePage search(String searchRequest) throws FindFailed {
		s.wait(OVERVIEW_MENU_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY); // TODO: check if there's better wait
		s.click(SEARCH_INPUT_PATTERN);
		SystemActions.selectAllAndClear(s);
		s.type(searchRequest);
		s.type(SEARCH_INPUT_PATTERN, Key.ENTER);
		return this;
	}

	public Boolean isArtistFound(Pattern artistPattern) throws FindFailed {
		s.wait(ARTISTS_HEADER_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		Region artist = s.find(artistPattern);
		if (artist != null) {
			artist.highlight(1);
			return true;
		}
		return false;
	}
	
	public HomePage selectArtist(Pattern artistPattern) throws FindFailed {
		s.wait(ARTISTS_HEADER_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		Region artist = s.find(artistPattern);
		if (artist != null) {
			s.click(artistPattern);
		}
		return this;
}

	public HomePage clickGreenPlayButton() throws FindFailed {
		s.wait(GREEN_PLAY_BUTTON_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		s.click(GREEN_PLAY_BUTTON_PATTERN);
		return this;
	}

	public Boolean isPlaying() {
		try {
			Match activePlayer = s.wait(PLAYER_MENU_ACTIVE_PATTERN.exact(), TIMEOUT_HOME_PAGE_DISPLAY);
			activePlayer.highlight(1);
			return true;
		} catch (FindFailed e) {
			System.out.println("Was not able to find pattern " + PLAYER_MENU_ACTIVE_PATTERN);
			return false;
		}
	}

//	public HomePage scrollToSongAndDoubleClick() {
//		try {
//			Region resultsHeader = s.find("_nope_search_results.PNG");
//			s.click(resultsHeader);
//			int scrollAttempts = 0;
//			Region firstSong = s.find("_nope_never_gonna.PNG");
//			while (firstSong == null || scrollAttempts < 20) {
//				System.out.println("looking for song");
//				s.type(Key.DOWN);
//				System.out.println("scrolling down, attempt " + scrollAttempts);
//				scrollAttempts++;
//				firstSong = s.find("_nope_never_gonna.PNG");
//			}
//			s.click("_nope_never_gonna.PNG");
//		} catch (FindFailed e) {
//			e.printStackTrace();
//		}
//		return this;
//	}

	// TODO: handle advertisement popup
	// TODO: try/catch where necessary
	// TODO: prontf where necessary

}
