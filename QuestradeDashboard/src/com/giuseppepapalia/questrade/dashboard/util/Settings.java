package com.giuseppepapalia.questrade.dashboard.util;

public class Settings {

	Boolean notifyEnabled;
	Double optionPriceSensitivity;

	public Settings() {
		notifyEnabled = true;
		optionPriceSensitivity = 10.00;

	}

	public Boolean isNotifyEnabled() {
		return notifyEnabled;
	}

	public void setNotifyEnabled(boolean b) {
		notifyEnabled = b;
	}

	public void setOptionPriceSensitivity(Double d) {
		optionPriceSensitivity = d;
	}

	public Double getOptionPriceSensitivity() {
		return optionPriceSensitivity;
	}

}
