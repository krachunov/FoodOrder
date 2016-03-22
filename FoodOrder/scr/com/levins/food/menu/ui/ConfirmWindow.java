package com.levins.food.menu.ui;

import javax.swing.JOptionPane;

public class ConfirmWindow {
	private boolean state;

	public ConfirmWindow() {
		int response = JOptionPane.showConfirmDialog(null,
				"Do you want to continue?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.NO_OPTION) {
			this.state = false;
		} else if (response == JOptionPane.YES_OPTION) {
			this.state = true;
		} else if (response == JOptionPane.CLOSED_OPTION) {
			this.state = false;
		}
	}

	public boolean isState() {
		return state;
	}

}