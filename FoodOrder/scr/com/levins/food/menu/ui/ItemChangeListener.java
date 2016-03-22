package com.levins.food.menu.ui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

class ItemChangeListener implements ItemListener {
	List<JComboBox> box;

	public ItemChangeListener() {
		this.box = new ArrayList<JComboBox>();
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		if (event.getStateChange() == ItemEvent.SELECTED) {
			Object item = event.getItem();
			for (JComboBox jComboBox : box) {
				jComboBox.revalidate();
				jComboBox.repaint();
				System.out.println("ddddd");
			}
		}
	}

	public void addBox(JComboBox box) {
		this.box.add(box);
	}

}
