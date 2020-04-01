package com.giuseppepapalia.questrade.dashboard.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.giuseppepapalia.questrade.QuestradeAPI;
import com.giuseppepapalia.questrade.dashboard.util.IconFactory;
import com.giuseppepapalia.questrade.exception.InvalidTokenException;

public class SplashScreen extends JFrame {

	private static final long serialVersionUID = -2383405811428507600L;

	private Dashboard mainFrame;
	private JTextField enterField;

	public SplashScreen(Dashboard frame) {
		this.mainFrame = frame;
		populate();
	}

	public void populate() {
		setSize(100, 1000);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new IconFactory().createImageIcon("resources/questrade_options_dashboard_icon.png").getImage());
		setLocationRelativeTo(null);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel("Welcome to Questrade Dashboard", SwingConstants.CENTER), BorderLayout.NORTH);
		panel.add(new JLabel("Enter your Token:"), BorderLayout.WEST);
		this.enterField = new JTextField(19);
		enterField.setEditable(true);
		enterField.setEnabled(true);
		enterField.addActionListener(action);
		panel.add(enterField, BorderLayout.CENTER);
		JButton submit = new JButton("Submit");
		submit.addActionListener(action);
		panel.add(submit, BorderLayout.EAST);
		getContentPane().add(panel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private ActionListener action = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg) {
			try {
				QuestradeAPI api = new QuestradeAPI(enterField.getText());
				mainFrame.enableClient(api);
				setVisible(false);
				dispose();
				mainFrame.populate();
			} catch (InvalidTokenException e) {
				enterField.setText("");
				JOptionPane.showMessageDialog(mainFrame, e.getMessage(), "Token error", JOptionPane.ERROR_MESSAGE);
			}
		}

	};

}