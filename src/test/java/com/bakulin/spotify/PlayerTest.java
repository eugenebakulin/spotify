package com.bakulin.spotify;

import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;

import com.bakulin.spotify.LoginPage;

import static org.junit.Assert.*;

/**
 * Tests for player functionality of Spotify client.
 */
public class PlayerTest extends SpotifyBasicTest {
	
	private final String searchRequest = readTestData("artist_search_request_playertest");
	private Pattern artistPattern = new Pattern(readTestData("artist_pattern_playertest"));
	
	@Test
	public void testCanPlay() throws FindFailed {
		Boolean isPlayerPlaying = 
				new LoginPage(s)
				.loginAsValidUser(username, password)
				.search(searchRequest)
				.selectArtist(artistPattern)
				.isPlaying();
		assertTrue(isPlayerPlaying);
	}

}
