package model;

import java.time.LocalDate;

public class Intern extends Learner {
	
	protected String allowanceType;
	protected double allowanceAmount;

	public Intern(String firstName, String lastName, LocalDate inscriptionDate, String tel, String mail,
			String company, String allowanceType, double allowanceAmount) {
		super(firstName, lastName, inscriptionDate, tel, mail, company);
		this.allowanceType = allowanceType;
		this.allowanceAmount = allowanceAmount;
		
	}

	public String getAllowanceType() {
		return allowanceType;
	}

	public void setAllowanceType(String allowanceType) {
		this.allowanceType = allowanceType;
	}

	public double getAllowanceAmount() {
		return allowanceAmount;
	}

	public void setAllowanceAmount(double allowanceAmount) {
		this.allowanceAmount = allowanceAmount;
	}

//	@Override
//	public String toString() {
//		String s = super.toString();
//		s += "\n" + this.allowanceType + " : " + this.allowanceAmount + "\n";
//		return s;
//	}
	
	
	
	

}
