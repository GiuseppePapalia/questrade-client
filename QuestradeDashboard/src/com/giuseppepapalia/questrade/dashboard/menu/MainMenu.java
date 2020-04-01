package com.giuseppepapalia.questrade.dashboard.menu;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import com.giuseppepapalia.questrade.dashboard.util.IconFactory;
import com.giuseppepapalia.questrade.dashboard.util.Settings;

public class MainMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7486665047058598737L;

	public MainMenu(Settings settings) {
		setBorderPainted(true);
		setBorder(BorderFactory.createBevelBorder(500));
		add(new NotificationCheckbox(settings));
		IconFactory f = new IconFactory();
		JMenu notifSettings = new JMenu("Notification Settings");
		notifSettings.setIcon(f.createImageIcon("resources/settings.png"));
		add(notifSettings);
		JMenu researchTools = new JMenu("Algo Tools");
		researchTools.setIcon(f.createImageIcon("resources/research_tools.png"));
		add(researchTools);
	}

}
