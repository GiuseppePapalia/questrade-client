package com.giuseppepapalia.questrade.dashboard;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.giuseppepapalia.questrade.dashboard.main.Dashboard;

public class Main {

	public static void main(String args[]) {
		com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Green", "", "");

		// select the Look and Feel
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Dashboard();
	}

}
