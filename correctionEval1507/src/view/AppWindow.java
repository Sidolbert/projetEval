package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import model.*;

public class AppWindow extends JFrame {

	public AppWindow(ArrayList<Promotion> listePromo) {
		//on construit la fenêtre et on la configure
		this.setSize(600, 300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel label = new JLabel("Bienvenue sur GestionPromo !");
		label.setFont(new Font("Calibri", Font.BOLD, 35));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(label, BorderLayout.NORTH); //ne pas oublier d'ajouter les éléments créés !
		
		JPanel panel = new JPanel(new GridLayout(2, 2));
		JComboBox promoBox = new JComboBox(new Vector(listePromo));
		panel.add(promoBox);
		JButton promoButton = new JButton("Afficher promotion");
		panel.add(promoButton);
		
		//listener : quand on clique sur le bouton, on récupère la promotion selectionnée dans la combobox et on va l'afficher dans une nouvelle fenêtre
		promoButton.addActionListener((e)->{new PromoWindow((Promotion)promoBox.getSelectedItem());});
		
		Vector<Learner> learnerList = new Vector<Learner>();
		//pour chaque promotion : on ajoute les apprenants de sa liste au vecteur learnerList
		for (Promotion p : listePromo) {
			learnerList.addAll(p.getListLearner());
		}
		JComboBox learnerBox = new JComboBox(learnerList);
		panel.add(learnerBox);
		JButton learnerButton = new JButton("Afficher apprenant");
		panel.add(learnerButton);
		
		this.getContentPane().add(panel);
	}

}
