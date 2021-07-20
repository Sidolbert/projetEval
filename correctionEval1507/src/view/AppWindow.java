package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		//on ajoute un listener 
		promoBox.addActionListener((e)->{
			
			this.promoDisplay(promoPanel);
			this.paintComponents(getGraphics());
			
		}); 
			

		promoPanel.add(promoBox);
		JPanel promoInfo = new JPanel(new GridLayout(4, 2));
		JLabel nameL = new JLabel("Nom de la promotion");
		promoInfo.add(nameL);
		//on récupère la Promotion sélectionnée dans la combobox et on affiche son nom dans le label
		//on accède à la combobox en tant que premier élément du panel promoPanel, et on cast pour pouvoir utiliser sa méthode getSelectedItem()  
		//le cast en Promotion de promoBox.getSelectedItem() retourné en Object nous permet d'avoir accès aux fonctions de l'objet Promotion
		Promotion p = (Promotion)((JComboBox)(promoPanel.getComponent(0))).getSelectedItem();
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
		//au cas où la liste deviendrait grande : barre de défilement
		JScrollPane promoLearnerListScrolling = new JScrollPane(promoLearnerList);
		promoPanel.add(promoLearnerListScrolling);
		JButton learnerDisplay = new JButton("Afficher l'apprenant sélectionné");
		promoPanel.add(learnerDisplay);
		
		JPanel learnerPanel = new JPanel();
		
		//le layout nous permet de gérer comment les éléments vont se placer les uns par rapport aux autres
		this.getContentPane().setLayout(new GridLayout(1, 3));
		//on ajoute les éléments à la fenêtre
		this.getContentPane().add(checkPanel);
		this.getContentPane().add(promoPanel);
		this.getContentPane().add(learnerPanel);
		
	}
	
	//on factorise le code affichant les infos de promo et la JList d'apprenants
	public void promoDisplay(JPanel promoPanel) {
		//on récupère la Promotion sélectionnée dans la combobox et on affiche son nom dans le label
		//on accède à la combobox en tant que premier élément du panel promoPanel, et on cast pour pouvoir utiliser sa méthode getSelectedItem()  
		//le cast en Promotion de promoBox.getSelectedItem() retourné en Object nous permet d'avoir accès aux fonctions de l'objet Promotion
		Promotion p = (Promotion)((JComboBox)(promoPanel.getComponent(0))).getSelectedItem();
		//déclaration d'une variable représentant le panel d'information, pour accès plus rapide
		//on le récupère depuis son parent avec getComponent(ind index)
		JPanel promoInfo = (JPanel)promoPanel.getComponent(1);
		//on récupère le nom de la promo sélectionnée et on le stocke dans le label
		((JLabel)(promoInfo.getComponent(1))).setText(p.getName());
		//on récupère la durée totale de la promo sélectionnée et on la stocke dans le label
		((JLabel)(promoInfo.getComponent(3))).setText(Integer.toString(p.getTotalDuration()));
		//on récupère la date de départ de la promo sélectionnée et on la stocke dans le label
		((JLabel)(promoInfo.getComponent(5))).setText(p.getStartingDate().toString());
		//on récupère la durée totale de la promo sélectionnée et on la stocke dans le label
		((JLabel)(promoInfo.getComponent(7))).setText(Integer.toString(p.getPastDuration()));
		//on récupère la JList depuis le JScrollPane et son viewPort
		JList learnerList = (JList)(((JScrollPane)(promoPanel.getComponent(2))).getViewport().getView());
		learnerList.setListData(new Vector<Learner>(p.getListLearner()));
		
	}

}
