package projekt;
public class Učebnice extends Knihy{
	private int úroveňRočníku;
	
	public Učebnice(String název, String[] autoři, int rokVydání, boolean dostupná, int úroveňRočníku) {
		super(název, autoři, rokVydání, dostupná);
		this.úroveňRočníku = úroveňRočníku;
	}
	
	public int getÚroveňRočníku() {
		return úroveňRočníku;
	}
	
	public void setÚroveňRočníku(int úroveňRočníku) {
		this.úroveňRočníku = úroveňRočníku;
	}

}
