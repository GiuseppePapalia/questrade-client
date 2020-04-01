package com.giuseppepapalia.questrade.dashboard.quotes;

import java.awt.Color;
import java.util.List;

import javax.swing.JTextField;

import com.giuseppepapalia.questrade.QuestradeClient;
import com.giuseppepapalia.questrade.data.Position;
import com.giuseppepapalia.questrade.data.Quote;
import com.giuseppepapalia.questrade.util.DollarValue;

public class QuoteWatcher extends Thread {

	private QuestradeClient client;
	private QuoteTableModel model;
	private JTextField txtPL;
	private QuoteTable table;

	public QuoteWatcher(QuestradeClient client, QuoteTableModel model, QuoteTable table, JTextField txtPL) {
		this.client = client;
		this.model = model;
		this.txtPL = txtPL;
		this.table = table;

		for (Position pos : client.getPositions(client.getAccount())) {
			Object[] o = { pos };
			model.addRow(o);
		}

	}

	@Override
	public void run() {
		while (true) {

			List<Position> newPos = client.getPositions(client.getAccount());
			if (newPos.size() != model.getRowCount()) { // New Positions - Rebuild the table model
				for (int i = 0; i < model.getRowCount(); i++) {
					model.removeRow(i);
				}
				table.refreshFromModel();
				for (int i = 0; i < newPos.size(); i++) {
					Position pos = newPos.get(i);
					Object[] o = { pos };
					model.addRow(o);

					Quote quote = client.getQuote(pos.getID());
					model.setValueAt(new DollarValue(computeAvgPrice(quote)), i, 1);
					model.setValueAt(new DollarValue((computeAvgPrice(quote) - pos.getAvgEntryPrice()) * 100 * pos.getOpenQuantity()), i, 2);
					model.setValueAt(new DollarValue(quote.getBidPrice()) + " / " + new DollarValue(quote.getAskPrice()), i, 3);
					model.setValueAt(quote.getBidSize() + " / " + quote.getAskSize(), i, 4);
					model.setValueAt(quote.getVolume(), i, 5);
				}

			} else {
				for (int i = 0; i < model.getRowCount(); i++) {
					Position pos = (Position) model.getValueAt(i, 0);
					Quote quote = client.getQuote(pos.getID());
					model.setValueAt(new DollarValue(computeAvgPrice(quote)), i, 1);
					model.setValueAt(new DollarValue((computeAvgPrice(quote) - pos.getAvgEntryPrice()) * 100 * pos.getOpenQuantity()), i, 2);
					model.setValueAt(new DollarValue(quote.getBidPrice()) + " / " + new DollarValue(quote.getAskPrice()), i, 3);
					model.setValueAt(quote.getBidSize() + " / " + quote.getAskSize(), i, 4);
					model.setValueAt(quote.getVolume(), i, 5);
				}
			}

			int j = QuoteTableModel.PL_COL;
			double sum = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				sum += ((DollarValue) model.getValueAt(i, j)).getValue();
			}
			txtPL.setText(new DollarValue(sum).toString());
			txtPL.setForeground(sum >= 0 ? Color.GREEN : Color.RED);

			table.refreshFromModel();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private Double computeAvgPrice(Quote quote) {
		return (quote.getAskPrice() + quote.getBidPrice()) / 2;
	}
}
