package com.bakulin.spotify.client.testing;

import org.sikuli.script.Screen;

public abstract class Page {
	
	protected static Screen s;
	
	public Page(Screen s) {
		this.s = s;
		waitForPageToBeDisplayed();
	}
	
	/** Checks if Page is loaded. Must be overriden and implemented on page level. */
	abstract void waitForPageToBeDisplayed();

}
