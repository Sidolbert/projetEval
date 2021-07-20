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
		this.setSize(1500, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Welcome to GestionPromo");
		//on crée les différents panels avec lesquels on va travailler
		JPanel checkPanel = new JPanel();
		
		JPanel promoPanel = new JPanel(new GridLayout(4, 1));
		//on remplit promoPanel
		//la combobox permet de choisir une promotion parmi celles enregistrées
		JComboBox<Promotion> promoBox = new JComboBox<Promotion>(new Vector<Promotion>(listePromo));
		promoPanel.add(promoBox);
		JPanel promoInfo = new JPanel(new GridLayout(4, 2));
		JLabel nameL = new JLabel("Nom de la promotion");
		promoInfo.add(nameL);
		//on récupère la Promotion sélectionnée dans la combobox et on affiche son nom dans le label
		//le cast en Promotion de promoBox.getSelectedItem() retourné en Object nous permet d'avoir accès aux fonctions de l'objet Promotion
		Promotion p = (Promotion)promoBox.getSelectedItem();
		JLabel nameData = new JLabel(p.getName());
		promoInfo.add(nameData);
		JLabel totalDurationL = new JLabel("Durée totale en jours");
		promoInfo.add(totalDurationL);
		JLabel totalDurationData = new JLabel(Integer.toString(p.getTotalDuration()));
		promoInfo.add(totalDurationData);
		JLabel startingDateL = new JLabel("Date de démarrage");
		promoInfo.add(startingDateL);
		JLabel startingDateData = new JLabel(p.getStartingDate().toString());
		promoInfo.add(startingDateData);
		JLabel pastDurationL = new JLabel("Durée réalisée");
		promoInfo.add(pastDurationL);
		JLabel pastDurationData = new JLabel(Integer.toString(p.getPastDuration()));
		promoInfo.add(pastDurationData);
		promoPanel.add(promoInfo);
		JList<Learner> promoLearnerList = new JList<Learner>(new Vector<Learner>(p.getListLearner()));
		//sélection dans la liste limitée à un seul élément à la fois
		promoLearnerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		promoPanel.add(promoLearnerList);
		JButton learnerDisplay = new JButton("Afficher l'apprenant sélectionné");
		promoPanel.add(learnerDisplay);
		
		JPanel learnerPanel = new JPanel();
		
		//le layout nous permet de gérer comment les éléments vont se placer les uns par rapport aux autres
		this.getContentPane().setLayout(new GridLayout(1, 3));
		//on ajoute les éléments à la fenêtre
		this.getContentPane().add(checkPanel);
		this.getContentPane().add(promoPanel);
		this.getContentPane().add(learnerPanel);
		
		
//		JLabel label = new JLabel("Bienvenue sur GestionPromo !");
//		label.setFont(new Font("Calibri", Font.BOLD, 35));
//		label.setHorizontalAlignment(SwingConstants.CENTER);
//		this.getContentPane().add(label, BorderLayout.NORTH); //ne pas oublier d'ajouter les éléments créés !
//		
//		JPanel panel = new JPanel(new GridLayout(2, 2));
//		JComboBox promoBox = new JComboBox(new Vector(listePromo));
//		panel.add(promoBox);
//		JButton promoButton = new JButton("Afficher promotion");
//		panel.add(promoButton);
//		
//		//listener : quand on clique sur le bouton, on récupère la promotion selectionnée dans la combobox et on va l'afficher dans une nouvelle fenêtre
//		promoButton.addActionListener((e)->{new PromoWindow((Promotion)promoBox.getSelectedItem());});
//		
//		Vector<Learner> learnerList = new Vector<Learner>();
//		//pour chaque promotion : on ajoute les apprenants de sa liste au vecteur learnerList
//		for (Promotion p : listePromo) {
//			learnerList.addAll(p.getListLearner());
//		}
//		JComboBox learnerBox = new JComboBox(learnerList);
//		panel.add(learnerBox);
//		JButton learnerButton = new JButton("Afficher apprenant");
//		panel.add(learnerButton);
//		
//		this.getContentPane().add(panel);
	}

}
