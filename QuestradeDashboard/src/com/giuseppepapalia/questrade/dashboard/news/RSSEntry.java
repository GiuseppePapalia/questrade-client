package com.giuseppepapalia.questrade.dashboard.news;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSSEntry implements Comparable<RSSEntry> {

	private URI url;
	private String title;
	private Date pubDate;

	public RSSEntry(Node itemNode) {
		stripData(itemNode);
	}

	private void stripData(Node itemNode) {
		NodeList nodes = itemNode.getChildNodes();

		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeName().equals("title")) {
				title = node.getTextContent();
				continue;
			}

			if (node.getNodeName().equals("pubDate")) {
				String text = node.getTextContent();

				int firstChar = 0;
				for (int j = 0; j < text.length(); j++) {
					if (Character.isDigit(text.charAt(j))) {
						firstChar = j;
						break;
					}
				}
				text = text.substring(firstChar);

				SimpleDateFormat f = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
				try {
					pubDate = f.parse(text);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				continue;
			}

			if (node.getNodeName().equals("link")) {

				try {
					url = new URI(node.getTextContent());
				} catch (DOMException | URISyntaxException e) {
					e.printStackTrace();
				}

				continue;
			}
		}
	}

	@Override
	public int compareTo(RSSEntry rss) {
		return rss.pubDate.compareTo(pubDate);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else if (o instanceof RSSEntry) {
			RSSEntry entry = (RSSEntry) o;
			return entry.url.equals(url) && entry.title.equals(title) && entry.pubDate.equals(pubDate);

		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		long HOUR = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);
		long DAY = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
		long duration = (new Date()).getTime() - pubDate.getTime();

		if (duration <= HOUR) {
			long minutesAgo = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS);
			if (minutesAgo == 0) {
				return title + " - just now";
			} else {
				String s = " minutes ago";
				if (minutesAgo == 1) {
					s = " minute ago";
				}
				return title + " - " + minutesAgo + s;
			}
		} else if (duration <= DAY) {
			long hoursAgo = TimeUnit.HOURS.convert(duration, TimeUnit.MILLISECONDS);
			String s = " hours ago";
			if (hoursAgo == 1) {
				s = " hour ago";
			}
			return title + " - " + hoursAgo + s;
		} else {
			long daysAgo = TimeUnit.DAYS.convert(duration, TimeUnit.MILLISECONDS);
			String s = " days ago";
			if (daysAgo == 1) {
				s = " day ago";
			}
			return title + " - " + daysAgo + s;
		}
	}

	public URI getURL() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public Date getPubDate() {
		return pubDate;
	}

}
