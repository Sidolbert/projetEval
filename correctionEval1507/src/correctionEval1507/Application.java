package correctionEval1507;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import model.*;
import view.AppWindow;

public class Application {
	

	public static void main(String[] args) {
		Intern intern1 = new Intern("Alex", "Dupont", LocalDate.now(), "0612342536", "Alex.dupont@gmail.com", "pôle emploi", "ARE", 1200);
		intern1.setAbsence(new ArrayList<LocalDate>(Arrays.asList(LocalDate.now())));
		Intern intern2 = new Intern("Alicia","Ledore", LocalDate.now() , "0612278936", "Alicia.ledore@gmail.com", "pôle emploi","allocation de formation" ,685.5);
		Intern intern3 = new Intern("Ambroise","Dupont", LocalDate.now() , "0789875254", "Ambroise.dupont@gmail.com", "pôle emploi","ARE" ,1200);
		AlternateStudent altS1 = new AlternateStudent("Sonia","Chaigne", LocalDate.now() , "0685647291", "Sonia.chaigne@gmail.com", "Atos",1250.2);
		AlternateStudent altS2 = new AlternateStudent("Emilien","Beton", LocalDate.now() , "0764568952", "0764568952", "Amadeus",1500.1);
		Promotion promo1 = new Promotion("Java4", 75, LocalDate.now(), new ArrayList<Learner>(Arrays.asList(intern1, intern2, intern3)));
		Promotion promo2 = new Promotion("CDA3", 100, LocalDate.now(), new ArrayList<Learner>(Arrays.asList(altS1, altS2)));
//		System.out.println(intern1);
//		System.out.println(altS1);
//		System.out.println(promo1);
		AppWindow appWindow = new AppWindow(new ArrayList<Promotion>(Arrays.asList(promo1, promo2)));
		appWindow.setVisible(true);
	}
	
	

}
