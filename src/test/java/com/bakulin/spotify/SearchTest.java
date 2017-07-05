package com.bakulin.spotify;

import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import com.bakulin.spotify.LoginPage;

import static org.junit.Assert.*;

/**
 * Tests for search functionality of Spotify client.
 */
public class SearchTest extends SpotifyBasicTest {
	
	private final String searchRequest = readTestData("artist_search_request");
	private Pattern artistPattern = new Pattern(readTestData("artist_pattern"));

	/**
	 * Logs in, searches for artist, verifies if artist is found.
	 * It's expected that exact artist name will be used for search, so artist would be visible on first screen.
	 * @throws FindFailed
	 */
	@Test
	public void testSearchByValidArtistName() throws FindFailed {
		Boolean isArtistFound = new LoginPage(s)
				.loginAsValidUser(username, password)
				.search(searchRequest)
				.isArtistFound(artistPattern);
		assertTrue(isArtistFound);
	}
	
}
