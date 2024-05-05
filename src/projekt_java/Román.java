package projekt_java;

public class Román extends Knihy {
	private String žánr;
	
	public Román(String název, String[] autoři, int rokVydání, boolean dostupná, String žánr) {
		super(název, autoři, rokVydání, dostupná);
		this.žánr = žánr;
	}
	
	public String getŽánr() {
		return žánr;
	}
	
	public void setŽánr(String žánr) {
		this.žánr = žánr;
	}
}