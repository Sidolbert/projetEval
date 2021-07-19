package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Promotion;

public class PromoWindow extends JFrame {

	public PromoWindow(Promotion p) {
		this.setSize(600, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		JLabel test = new JLabel(p.getName());
		this.getContentPane().add(test);
		
		this.setVisible(true);
	}



}
