package com.giuseppepapalia.questrade.dashboard.util;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;

public class Notify {

	public static void notifyDesktop(String msg, boolean playSound) {
		// Obtain only one instance of the SystemTray object
		SystemTray tray = SystemTray.getSystemTray();

		// If the icon is a file
		// Alternative (if the icon is on the classpath):
		// Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));
		IconFactory f = new IconFactory();
		Image image = f.createImageIcon("resources/questrade_options_dashboard_icon.png").getImage();
		TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
		// Let the system resize the image if needed
		trayIcon.setImageAutoSize(true);
		// Set tooltip text for the tray icon
		trayIcon.setToolTip("System tray icon demo");
		try {
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
	}

}
