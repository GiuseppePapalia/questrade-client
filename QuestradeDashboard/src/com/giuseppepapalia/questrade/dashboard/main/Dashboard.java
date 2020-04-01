package com.giuseppepapalia.questrade.dashboard.main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.giuseppepapalia.questrade.QuestradeAPI;
import com.giuseppepapalia.questrade.QuestradeClient;
import com.giuseppepapalia.questrade.dashboard.menu.MainMenu;
import com.giuseppepapalia.questrade.dashboard.news.NewsPanel;
import com.giuseppepapalia.questrade.dashboard.quotes.QuotePanel;
import com.giuseppepapalia.questrade.dashboard.util.IconFactory;
import com.giuseppepapalia.questrade.dashboard.util.Settings;

public class Dashboard extends JFrame {

	private JPanel mainPane;
	private QuestradeClient client;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3774801634601775931L;

	public Dashboard() {
		new SplashScreen(this);
	}

	public void populate() {
		setTitle("Questrade Dashboard");
		setResizable(false);
		Settings settings = new Settings();
		setJMenuBar(new MainMenu(settings));
		mainPane = new JPanel();
		getContentPane().add(mainPane);
		NewsPanel newsPanel = new NewsPanel(settings);
		mainPane.add(newsPanel);
		QuotePanel optionPanel = new QuotePanel(client, settings);
		mainPane.add(optionPanel);
		setSize(900, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		IconFactory f = new IconFactory();
		setIconImage(f.createImageIcon("resources/questrade_options_dashboard_icon.png").getImage());
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void enableClient(QuestradeAPI api) {
		client = new QuestradeClient(api);
	}

}
