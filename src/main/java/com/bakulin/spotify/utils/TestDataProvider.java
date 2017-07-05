package com.bakulin.spotify.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestDataProvider {
	
	/**
	 * Reads test data from properties file.
	 * @param key of the property that should be read
	 * @param fileName full path with name of properties file
	 * @return {@link String} value of property
	 */
	public static String readTestData(String key, String fileName) {
		String value = "";
		try {
			Properties properties = new Properties();
			File file = new File(fileName);
			if (file.exists()) {
				properties.load(new FileInputStream(file));
				value = properties.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

}
