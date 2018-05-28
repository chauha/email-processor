package com.parse.info;

import java.util.ArrayList;

import org.apache.commons.validator.routines.DomainValidator;
import org.apache.commons.validator.routines.UrlValidator;

import com.google.common.net.InetAddresses;

public class EmailBodyParser implements ParseInformation {
	private static UrlValidator urlValidator = UrlValidator.getInstance();
	private static DomainValidator domainValidator = DomainValidator.getInstance();

	public String findRelevantEntries(String _bodyText) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		ArrayList<String> ipAddrEntries = new ArrayList<String>();
		ArrayList<String> urlEntries = new ArrayList<String>();
		ArrayList<String> domainEntries = new ArrayList<String>();

		String[] lines = _bodyText.split("\\n");
		for (String line : lines) {
			line = line.replaceAll("(\\r|\\n)", "");
			String[] words = line.split(" ");
			for (String word : words) {
				if (InetAddresses.isInetAddress(word)) {
					ipAddrEntries.add(word);
				}
				if (urlValidator.isValid(word)) {
					urlEntries.add(word);
				}
				if (domainValidator.isValid(word)) {
					domainEntries.add(word);
				}

			}
		}
		if (!ipAddrEntries.isEmpty()) {
			result.add(ipAddrEntries);

		}
		if (!urlEntries.isEmpty()) {
			result.add(urlEntries);
		}
		if (!domainEntries.isEmpty()) {
			result.add(domainEntries);

		}

		return result.isEmpty() ? "" : result.toString();
	}

}
