package com.bakulin.spotify.client.testing;

import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;

public class HomePage {
	// TODO: implement parent class Page, extend

	private static final Pattern PLAYER_MENU_PATTERN = new Pattern("player_menu.png");
	private static final Pattern PLAYER_MENU_ACTIVE_PATTERN = new Pattern("player_menu_active.png");
	private static final Pattern ARTISTS_HEADER_PATTERN = new Pattern("artists_header.png");
	private static final Pattern ARTISTS_IMAGE_PATTERN = new Pattern("author_mc_hammer.png");
	private static final Pattern SEARCH_INPUT_PATTERN = new Pattern("search_input.png");
	private static final Pattern OVERVIEW_MENU_PATTERN = new Pattern("overview_menu.png");
	private static final Pattern GREEN_PLAY_BUTTON_PATTERN = new Pattern("green_play_button.png");
	private static final int TIMEOUT_HOME_PAGE_DISPLAY = 20; 

	Screen s;

	public HomePage(Screen s) {
		this.s = s;
		waitForPageToBeDisplayed();
	}
	
	/** Checks if Login page is displayed (i.e. logo is visible). */
	private void waitForPageToBeDisplayed() {
		try {
			s.wait(PLAYER_MENU_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}

	/*
	 * Checks if Home page is displayed after log.
	 */
	public Boolean isHomePageDisplayed() throws FindFailed {
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
		s.wait(OVERVIEW_MENU_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		s.click(SEARCH_INPUT_PATTERN);
		SystemActions.selectAllAndClear(s);
		s.type(searchRequest);
		s.type(SEARCH_INPUT_PATTERN, Key.ENTER);
		return this;
	}

	public Boolean isArtistFound(Pattern pattern) throws FindFailed {
		/*
		 * We assume Artist Pattern to be visible on first screen as we search by
		 * Artist name. Search by less relevant request should be a separate case.
		 */
		s.wait(ARTISTS_HEADER_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		Region artist = s.find(ARTISTS_IMAGE_PATTERN);
		if (artist != null) {
			artist.highlight(1);
			return true;
		}
		return false;
	}

	public HomePage clickGreenPlayButton() throws FindFailed {
		s.wait(GREEN_PLAY_BUTTON_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
		s.click(GREEN_PLAY_BUTTON_PATTERN);
		return this;
	}

	public Boolean isPlaying() {
		try {
			Match activePlayer = s.wait(PLAYER_MENU_ACTIVE_PATTERN, TIMEOUT_HOME_PAGE_DISPLAY);
			activePlayer.highlight(1);
			return true;
		} catch (FindFailed e) {
			System.out.println("Was not able to find pattern " + PLAYER_MENU_ACTIVE_PATTERN);
			return false;
		}
	}

	// TODO: handle advertisement popup
	// TODO: try/catch where necessary
	// TODO: prontf where necessary

}
