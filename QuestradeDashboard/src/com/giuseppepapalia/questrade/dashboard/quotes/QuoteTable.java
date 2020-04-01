package com.giuseppepapalia.questrade.dashboard.quotes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import com.giuseppepapalia.questrade.dashboard.util.SwingUtils;
import com.giuseppepapalia.questrade.util.DollarValue;

public class QuoteTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -955505591109849092L;

	public QuoteTable(QuoteTableModel model) {
		super(model);
		setSize(750, 500);
		setPreferredSize(new Dimension(750, 500));
		renderPLColors();

	}

	private void renderPLColors() {
		getColumnModel().getColumn(QuoteTableModel.PL_COL).setCellRenderer(new DefaultTableCellRenderer() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5666445418757148496L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
				if (column == QuoteTableModel.PL_COL) {
					if (value != null) {
						c.setForeground(((DollarValue) value).getValue() >= 0 ? Color.GREEN : Color.RED);
					}
				}
				return c;
			}
		});
	}

	public void refreshFromModel() {
		SwingUtils.resizeTable(this);
		repaint();
	}

}