package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Promotion {

	protected String name;
	protected int totalDuration; //en nombre de jours
	protected LocalDate startingDate;
	protected int pastDuration; //en nombre de jurs
	protected ArrayList<Learner> listLearner; 
	
	public Promotion(String name, int totalDuration, LocalDate startingDate, ArrayList<Learner> listLearner){
		this.name = name;
		this.totalDuration = totalDuration;
		this.startingDate = startingDate;
		this.pastDuration = 0;
		this.listLearner = listLearner;
	}
	
	//méthode vérifiant si un apprenant de la promotion a trop de retard
	public boolean checkLateLearner(Learner learner) {
		return learner.checkLate();
	}
	
	//méthode listant les apprenants de la promotion qui ont trop de retard
	public ArrayList<Learner> lateLearnerList(){
		ArrayList<Learner> lateList = new ArrayList<Learner>();
		for(Learner l : this.listLearner) {
			if (checkLateLearner(l)) {
				lateList.add(l);
			}
		}
		return lateList;
	}
	
	//méthode vérifiant si un apprenant de la promotion a trop d'absences
	public boolean checkAbsenceLearner(Learner learner) {
		return learner.checkAbsences(this);
	}
	
	//méthode listant les apprenants de la promotion qui ont trop d'absences
		public ArrayList<Learner> AbsentLearnerList(){
			ArrayList<Learner> absentList = new ArrayList<Learner>();
			for(Learner l : this.listLearner) {
				if (checkAbsenceLearner(l)) {
					absentList.add(l);
				}
			}
			return absentList;
		}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getPastDuration() {
		return pastDuration;
	}
	public void setPastDuration(int pastDuration) {
		this.pastDuration = pastDuration;
	}	
	
	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public LocalDate getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	public ArrayList<Learner> getListLearner() {
		return listLearner;
	}
	public void setListLearner(ArrayList<Learner> listLearner) {
		this.listLearner = listLearner;
	}
	public String toString() {
		return"C'est la formation "+name+"\nElle commence le "+startingDate+"\nLa durée totale de la formation est "+totalDuration+"\nLa durée passée de la formation est "+ pastDuration+"\nLa liste des apprenants est :"+listLearner.toString();
	}

}
