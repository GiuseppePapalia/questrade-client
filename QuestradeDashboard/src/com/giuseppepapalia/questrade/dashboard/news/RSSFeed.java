package com.giuseppepapalia.questrade.dashboard.news;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class RSSFeed {

	public static final String NEWS = "https://www.cnbc.com/id/100003114/device/rss/rss.html";
	public static final String FINANCE = "https://www.cnbc.com/id/10000664/device/rss/rss.html";

	private String url;
	List<RSSEntry> entries = new ArrayList<RSSEntry>();

	public RSSFeed(String url) {
		this.url = url;
		refresh();
	}

	public void refresh() {
		try {
			URL rssURL = new URL(url);
			BufferedReader input = new BufferedReader(new InputStreamReader(rssURL.openStream(), StandardCharsets.UTF_8), 8192);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			InputSource is = new InputSource(input);

			Document doc = dBuilder.parse(is);
			NodeList nodes = doc.getElementsByTagName("item");

			entries.clear();
			for (int i = 0; i < nodes.getLength(); i++) {
				entries.add(new RSSEntry(nodes.item(i)));
			}
			input.close();
			Collections.sort(entries);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<RSSEntry> getEntries() {
		return entries;
	}

	public static void main(String args[]) {
		RSSFeed rss = new RSSFeed("https://www.cnbc.com/id/10000664/device/rss/rss.html");
		System.out.println(rss.entries);
	}

}
