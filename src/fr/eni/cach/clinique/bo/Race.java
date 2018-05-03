package fr.eni.cach.clinique.bo;

public class Race {

	private String race;
	
	public Race() {
		// TODO Auto-generated constructor stub
	}
	

	public Race(String race) {
		super();
		this.race = race;
	}



	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
//		builder.append("Race [getRace()=");
		builder.append(getRace());
//		builder.append(", getEspeces()=");
//		builder.append(getEspeces());
//		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
