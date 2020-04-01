package com.giuseppepapalia.questrade.dashboard.quotes;

import javax.swing.table.DefaultTableModel;

public class QuoteTableModel extends DefaultTableModel {

	public static final int PL_COL = 2;

	private static final String[] COLUMN_NAMES = { "Position", "Price", "Unrealized P / L", "Bid / Ask Price", "Bid / Ask Size", "Volume" };

	/**
	 * 
	 */
	private static final long serialVersionUID = 593189513946341401L;

	public QuoteTableModel() {
		super(COLUMN_NAMES, 0);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
