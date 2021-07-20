package model;

import java.time.LocalDate;

public class AlternateStudent extends Learner {
	
	protected double salary;
	
	public AlternateStudent(String firstName, String lastName, LocalDate inscriptionDate, String tel, String mail,
			String company, double salary) {
		super(firstName, lastName, inscriptionDate, tel, mail, company);
		this.salary = salary;
		
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		String s = super.toString();
		s += " Alternant";
		return s;
	}
	

}
