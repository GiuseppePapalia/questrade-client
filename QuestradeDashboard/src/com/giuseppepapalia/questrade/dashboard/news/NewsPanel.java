package com.giuseppepapalia.questrade.dashboard.news;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.giuseppepapalia.questrade.dashboard.util.Settings;

public class NewsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2914966505322525363L;

	public NewsPanel(Settings settings) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		RSSFeedGUI newsFeed = new RSSFeedGUI(RSSFeed.NEWS, "Breaking News", settings);
		RSSFeedGUI financeFeed = new RSSFeedGUI(RSSFeed.FINANCE, "Finance News", settings);
		add(newsFeed.getPane());
		add(financeFeed.getPane());
		add(Box.createVerticalStrut(10));
		add(new JSeparator(0));
	}
}
