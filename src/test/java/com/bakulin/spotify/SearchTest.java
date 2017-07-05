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

	/*
	 * We assume Artist Pattern to be visible on first screen as we search by
	 * Artist name. Search by less relevant request should be a separate case.
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
