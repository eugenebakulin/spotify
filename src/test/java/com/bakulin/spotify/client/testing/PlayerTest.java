package com.bakulin.spotify.client.testing;

import org.junit.Test;
import org.sikuli.script.FindFailed;
import static org.junit.Assert.*;

public class PlayerTest extends SpotifyBasicTest {

	@Test
	public void testCanPlayPromoPlaylist() throws FindFailed {
		Boolean isPlayerPlaying = new LoginPage(s)
			.loginAsValidUser(USERNAME, PASSWORD)
			.clickGreenPlayButton()			
			.isPlaying();
		assertTrue(isPlayerPlaying);
	}

}
