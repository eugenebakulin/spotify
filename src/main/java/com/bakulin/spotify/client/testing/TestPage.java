package com.bakulin.spotify.client.testing;

import org.sikuli.script.Screen;

public class TestPage extends Page {

	public TestPage(Screen s) {
		super(s);
	}

	@Override
	void waitForPageToBeDisplayed() {
		
		System.out.println("Hello Worlds");
		
	}

}
