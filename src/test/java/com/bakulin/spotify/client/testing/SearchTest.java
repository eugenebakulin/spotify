package com.bakulin.spotify.client.testing;

import org.junit.Test;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import static org.junit.Assert.*;

public class SearchTest extends SpotifyBasicTest {

	private final static String SEARCH_REQUEST_AUTHOR = "MC Hammer";
	private final static Pattern AUTHOR_PATTERN = new Pattern("src/main/resources/author_mc_hammer.png");

	@Test
	public void testSearchByValidArtistName() throws FindFailed {
		Boolean isArtistFound = new LoginPage(s)
				.loginAsValidUser(USERNAME, PASSWORD)
				.search(SEARCH_REQUEST_AUTHOR)
				.isArtistFound(AUTHOR_PATTERN);
		assertTrue(isArtistFound);
	}
	
}
