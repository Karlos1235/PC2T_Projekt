package projekt;

public class Knihy {
	private String název;
	private String[] autoři;
	private int rokVydání;
	private boolean dostupná;

	public Knihy (String název, String[] autoři, int rokVydání, boolean dostupná) {
		this.název = název;
		this.autoři = autoři;
		this.rokVydání = rokVydání;
		this.dostupná = dostupná;
	}

	public String getNázev() {
		return název;
	}

	public void setNázev(String název) {
		this.název = název;
	}

	public String[] getAutoři() {
		return autoři;
	}

	public void setAutoři(String[] autoři) {
		this.autoři = autoři;
	}

	public int getRokVydání() {
		return rokVydání;
	}

	public void setRokVydání(int rokVydání) {
		this.rokVydání = rokVydání;
	}

	public boolean isDostupná() {
		return dostupná;
	}

	public void setDostupná(boolean dostupná) {
		this.dostupná = dostupná;
	}
}
