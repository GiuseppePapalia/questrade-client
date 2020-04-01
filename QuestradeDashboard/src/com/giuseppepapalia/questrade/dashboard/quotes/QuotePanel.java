package com.giuseppepapalia.questrade.dashboard.quotes;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.giuseppepapalia.questrade.QuestradeClient;
import com.giuseppepapalia.questrade.dashboard.util.Settings;
import com.giuseppepapalia.questrade.util.DollarValue;

public class QuotePanel extends JPanel {

	private static final long serialVersionUID = -3101206056606595465L;
	private JScrollPane pane;
	private JLabel lblPL;
	private JTextField txtPL;

	public QuotePanel(QuestradeClient client, Settings settings) {
		setSize(750, 600);
		setPreferredSize(new Dimension(750, 600));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		QuoteTableModel tableModel = new QuoteTableModel();
		QuoteTable table = new QuoteTable(tableModel);
		pane = new JScrollPane(table);
		pane.setSize(750, 500);
		pane.setPreferredSize(new Dimension(750, 500));
		add(pane);

		JPanel panelPL = new JPanel();
		panelPL.setSize(750, 100);
		panelPL.setPreferredSize(new Dimension(750, 100));
		lblPL = new JLabel("Open P / L");
		lblPL.setPreferredSize(new Dimension(100, 50));
		txtPL = new JTextField(new DollarValue(0).toString());
		txtPL.setPreferredSize(new Dimension(100, 50));
		txtPL.setEditable(false);
		txtPL.setHorizontalAlignment(JTextField.CENTER);

		panelPL.add(lblPL, BorderLayout.WEST);
		panelPL.add(txtPL, BorderLayout.CENTER);

		add(panelPL);

		QuoteWatcher watcher = new QuoteWatcher(client, tableModel, table, txtPL);
		watcher.start();
	}

}
