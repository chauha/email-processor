package com.parse.info;

import junit.framework.TestCase;

public class EmailBodyParserTest extends TestCase {

	EmailBodyParser emailParser;

	protected void setUp() throws Exception {
		super.setUp();
		emailParser = new EmailBodyParser();
	}

	
	public void testFindRelevantEntries() {
		
		String body = "Random string with ip 10.30.122.100 and https://sample.com as a url";
		String results = emailParser.findRelevantEntries(body);
		assert (!results.isEmpty());
	}

	
	public void negativeFindRelevantEntries() {
		
		String body = "Random string with ip  a url";
		String results = emailParser.findRelevantEntries(body);
		assert (results.isEmpty());
	}
	
}
