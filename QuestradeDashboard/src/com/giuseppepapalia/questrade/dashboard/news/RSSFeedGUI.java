package com.giuseppepapalia.questrade.dashboard.news;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import com.giuseppepapalia.questrade.dashboard.util.AudioPlayer;
import com.giuseppepapalia.questrade.dashboard.util.Settings;

public class RSSFeedGUI {

	@SuppressWarnings("rawtypes")
	private JList list;
	private RSSFeed feed;
	private List<RSSEntry> prevEntries;
	private JScrollPane pane;
	private Settings settings;

	/**
	 * 
	 */

	public JScrollPane getPane() {
		return pane;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RSSFeedGUI(String url, String title, Settings settings) {
		this.settings = settings;
		feed = new RSSFeed(url);
		prevEntries = new ArrayList<RSSEntry>(feed.getEntries());
		list = new JList(feed.getEntries().toArray());
		initListeners();
		pane = new JScrollPane(list);
		pane.setBorder(BorderFactory.createTitledBorder(title));
		pane.setSize(1000, 250);
		Refresher r = new Refresher();
		r.start();
	}

	private void initListeners() {
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				@SuppressWarnings("rawtypes")
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {

					// Double-click detected
					int index = list.locationToIndex(evt.getPoint());
					URI url = ((RSSEntry) list.getModel().getElementAt(index)).getURL();
					try {
						Desktop.getDesktop().browse(url);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	class Refresher extends Thread {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				feed.refresh();
				List<RSSEntry> refreshedEntries = feed.getEntries();
				if (!prevEntries.containsAll(refreshedEntries)) {
					updateAndNotify(refreshedEntries);
				}
				list.validate();
			}
		}

		@SuppressWarnings("unchecked")
		private void updateAndNotify(List<RSSEntry> refreshedEntries) {
			DefaultListModel<RSSEntry> model = new DefaultListModel<RSSEntry>();
			for (RSSEntry e : refreshedEntries) {
				model.addElement(e);
			}
			list.setModel(model);

			prevEntries = new ArrayList<RSSEntry>(refreshedEntries);

			if (settings.isNotifyEnabled()) {
				AudioPlayer audioPlayer = new AudioPlayer();
				audioPlayer.playNotify();
			}

		}

	}

}
