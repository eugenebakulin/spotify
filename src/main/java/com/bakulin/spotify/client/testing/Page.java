package com.bakulin.spotify.client.testing;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public abstract class Page {
	
	Screen s;
	
	public Page(Screen s) {
		this.s = s;
		waitForPageToBeDisplayed();
	}
	
	/** Checks if Page is loaded (i.e. logo is visible). */
	abstract void waitForPageToBeDisplayed();

}
