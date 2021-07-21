package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import model.*;

public class AppWindow extends JFrame {
	
	private JList<Learner> promoLearnerList;
	private JList<LocalDate> learnerAbsenceList;

	public AppWindow(ArrayList<Promotion> listePromo) {
		//on construit la fenêtre et on la configure
		this.setSize(1500, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Welcome to GestionPromo");
		//on crée les différents panels avec lesquels on va travailler
		JPanel checkPanel = new JPanel(new GridLayout(3,1));
		
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
		/*JList<Learner>*/ promoLearnerList = new JList<Learner>(new Vector<Learner>(p.getListLearner()));
		//sélection dans la liste limitée à un seul élément à la fois
		promoLearnerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//au cas où la liste deviendrait grande : barre de défilement
		JScrollPane promoLearnerListScrolling = new JScrollPane(promoLearnerList);
		promoPanel.add(promoLearnerListScrolling);
		JButton learnerDisplay = new JButton("Afficher l'apprenant sélectionné");
		
		promoPanel.add(learnerDisplay);
		
		JPanel learnerPanel = new JPanel(new GridLayout(11, 2));
		
		learnerPanel.add(new JLabel("Prénom : "));
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel("Nom : "));
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel("Date d'inscription : "));
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel("Tel : "));
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel("Mail : "));
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel("Société : "));
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel("Jours d'absence "));
		learnerAbsenceList = new JList<LocalDate>();
		learnerPanel.add(learnerAbsenceList);
		learnerPanel.add(new JLabel("Retard total : "));
		learnerPanel.add(new JLabel());
		//composants qu'on va modifier selon les différents types d'apprenant
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel());
		learnerPanel.add(new JLabel());
		
		learnerDisplay.addActionListener((e)->{
			if(!promoLearnerList.isSelectionEmpty()) {
				Learner selectedLearner = promoLearnerList.getSelectedValue();
				this.learnerPanelDisplay(learnerPanel, selectedLearner);
			}
		});
		//version simple avec les mêmes champs pour tous les apprenants, on va faire une version plus dynamique
//		learnerPanel.add(new JLabel("Revenu : "));
//		learnerPanel.add(new JLabel());
//		learnerPanel.add(new JLabel("Montant : "));
//		learnerPanel.add(new JLabel());
		
		//le layout nous permet de gérer comment les éléments vont se placer les uns par rapport aux autres
		this.getContentPane().setLayout(new GridLayout(1, 3));
		//on ajoute les éléments à la fenêtre
		this.getContentPane().add(checkPanel);
		this.getContentPane().add(promoPanel);
		this.getContentPane().add(learnerPanel);
		
		//checkPanel : le grand titre de l'appli + deux zones pour afficher retards et absences de tous les apprenants
		JPanel titlePanel = new JPanel();
		JLabel titleLabel = new JLabel("Bienvenue sur GestionPromo !");
		titleLabel.setFont(new Font("Mv Boli", Font.BOLD, 30));
		titlePanel.add(titleLabel);
		checkPanel.add(titlePanel);
		JPanel delayPanel = new JPanel();
		this.displayLateOrAbsence(delayPanel, listePromo, true);
		checkPanel.add(delayPanel);
		JPanel absencePanel = new JPanel();
		this.displayLateOrAbsence(absencePanel, listePromo, false);
		checkPanel.add(absencePanel);
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
		//JList learnerList = (JList)(((JScrollPane)(promoPanel.getComponent(2))).getViewport().getView());
		//learnerList.setListData(new Vector<Learner>(p.getListLearner()));
		
		//ou sinon on la met simplement en attribut (c'est moins sécurisé mais c'est beaucoup plus facile d'accès)
		this.promoLearnerList.setListData(new Vector<Learner>(p.getListLearner()));
		
		
	}
	
	public void learnerPanelDisplay(JPanel learnerPanel, Learner selectedLearner) {
		((JLabel)learnerPanel.getComponent(1)).setText(selectedLearner.getFirstName());
		((JLabel)learnerPanel.getComponent(3)).setText(selectedLearner.getLastName());
		((JLabel)learnerPanel.getComponent(5)).setText(selectedLearner.getInscriptionDate().toString());
		((JLabel)learnerPanel.getComponent(7)).setText(selectedLearner.getTel());
		((JLabel)learnerPanel.getComponent(9)).setText(selectedLearner.getMail());
		((JLabel)learnerPanel.getComponent(11)).setText(selectedLearner.getCompany());
		this.learnerAbsenceList.setListData(new Vector<LocalDate>(selectedLearner.getAbsence()));
		String delay = Integer.toString(selectedLearner.getDelay());
		((JLabel)learnerPanel.getComponent(15)).setText(delay);
		if(selectedLearner.getClass().getSimpleName().equals("Intern")) {
			((JLabel)learnerPanel.getComponent(16)).setText("Type d'allocation : ");
			((JLabel)learnerPanel.getComponent(17)).setText(((Intern)selectedLearner).getAllowanceType());
			((JLabel)learnerPanel.getComponent(18)).setText("Montant de l'allocation : ");
			((JLabel)learnerPanel.getComponent(19)).setText(Double.toString(((Intern)selectedLearner).getAllowanceAmount()));
		}else if(selectedLearner.getClass().getSimpleName().equals("AlternateStudent")) {
			((JLabel)learnerPanel.getComponent(16)).setText("Salaire : ");
			((JLabel)learnerPanel.getComponent(17)).setText(Double.toString(((AlternateStudent)selectedLearner).getSalary()));
			((JLabel)learnerPanel.getComponent(18)).setText(null);
			((JLabel)learnerPanel.getComponent(19)).setText(null);
		}
		
	}
	
	//fonction affichant les apprenants en retard ou absents
	// check = true -> retards, false -> absences
	public void displayLateOrAbsence(JPanel delayPanel, ArrayList<Promotion> listePromo, boolean check) {
		JList delayList = new JList();
		
		ArrayList<Learner> lateList = new ArrayList();
		for(Promotion promo : listePromo) {
			if(check) {
				lateList.addAll(promo.lateLearnerList());
			}else {
				lateList.addAll(promo.absentLearnerList());
			}	
		}
		delayList.setListData(new Vector(lateList));
		if(lateList.size() > 0) {
			JLabel lateAlert;
			if(check) {
				lateAlert = new JLabel("APPRENANTS EN RETARD !");
			}else {
				lateAlert = new JLabel("APPRENANTS ABSENTS !");
			}	
			
			lateAlert.setForeground(Color.RED);
			delayPanel.add(lateAlert);
		}
		JScrollPane delayListScroll = new JScrollPane(delayList);
		delayPanel.add(delayListScroll);
	}
	

}
