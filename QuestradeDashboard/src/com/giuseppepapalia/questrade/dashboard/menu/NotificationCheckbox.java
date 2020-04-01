package com.giuseppepapalia.questrade.dashboard.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

import com.giuseppepapalia.questrade.dashboard.util.IconFactory;
import com.giuseppepapalia.questrade.dashboard.util.Settings;

public class NotificationCheckbox extends JCheckBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3049131322589641464L;
	private static final IconFactory iconFactory = new IconFactory();
	private static final ImageIcon ENABLED = iconFactory.createImageIcon("resources/volume_enabled.png");
	private static final ImageIcon DISABLED = iconFactory.createImageIcon("resources/volume_disabled.png");
	private Settings settings;

	public NotificationCheckbox(Settings settings) {
		super(ENABLED, settings.isNotifyEnabled());
		this.settings = settings;
		addListener();
	}

	private void addListener() {
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				if (isSelected()) {
					setIcon(ENABLED);

				} else {
					setIcon(DISABLED);
				}
				settings.setNotifyEnabled(isSelected());
			}

		});
	}
}
