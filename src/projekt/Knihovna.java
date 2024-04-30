package projekt;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Knihovna {
	private Map<String, Knihy> knihovna;

	public Knihovna() {
		this.knihovna = new HashMap<>();
	}


	public void pridatNovouKnihu(Scanner scanner) {
		System.out.println("Vyberte typ knihy: ");
		System.out.println("1. Román ");
		System.out.println("2. Učebnice ");
		System.out.println("Vaše volba: ");
		int typ = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Název knihy: ");
		String název = scanner.nextLine().trim();

		System.out.println("Počet autorů: ");
		int početAutorů = scanner.nextInt();
		scanner.nextLine();

		String[] autoři = new String [početAutorů];
		for(int i = 0; i < početAutorů; i++) {
			System.out.println("Autor " + (i + 1) + ": ");
			autoři[i] = scanner.nextLine();
		}

		System.out.println("Rok vydání: ");
		int rokVydání = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Dostupnost (Dostupná/Nedostupná): ");
		String dostupnostText = scanner.nextLine();
		boolean dostupná = dostupnostText.equalsIgnoreCase("Dostupná");
		
		Knihy kniha = new Knihy(název,autoři, rokVydání, dostupná);
		if(typ == 1) {
			System.out.println("Žánr: ");
			String žánr = scanner.next();

			kniha = new Román(název, autoři, rokVydání, dostupná, žánr);
		} else if (typ == 2) {
			System.out.println("Úroveň ročníku: ");
			int úroveňRočníku = scanner.nextInt();

			kniha = new Učebnice(název, autoři, úroveňRočníku, dostupná, úroveňRočníku);

		} else {
			System.out.println("Neplatný typ knihy");
		}
		
		knihovna.put(název,kniha);
		System.out.println(knihovna);
		System.out.println("Kniha byla úspěšně přidána.");
	}

	public void upravitKnihu(Scanner scanner) {
		System.out.println("Zadejte název knihy, kterou chcete upravit: ");
		String název = scanner.nextLine();

		if(!knihovna.containsKey(název)) {
			System.out.println("Kniha nebyla nalezena.");
			return;
		} 

		Knihy kniha = knihovna.get(název);
		System.out.println("Vyberte, který parametr knihy chcete upravit:");
		System.out.println("1. Autora/y");
		System.out.println("2. Rok vydání");
		System.out.println("3. Dostupnost");
		System.out.println("Vaše volba: ");
		int volba = scanner.nextInt();

		switch (volba) {
		case 1:
			System.out.println("Zadejte nového autora/y: ");
			String[] autoři = scanner.nextLine().split(", ");
			kniha.setAutoři(autoři);
			break;
		case 2:
			System.out.println("Zadejte nový rok vydání: ");
			int rokVydání = scanner.nextInt();
			kniha.setRokVydání(rokVydání);
			break;
		case 3: 
			System.out.print("Zadejte novou dostupnost (Dostupná/Nedostupná): ");
			String dostupnostText = scanner.nextLine();
			boolean dostupná = dostupnostText.equalsIgnoreCase("Dostupná");
			kniha.setDostupná(dostupná);
			break;
		default:
			System.out.println("Neplatná volba.");
			break;
		}
		knihovna.put(název, kniha);
		System.out.println("Kniha byla úspěšně aktualizována.");
	}
	
	public void smazatKnihu(Scanner scanner) {
		System.out.println("Zadejte název knihy, kterou chcete smazat: ");
		String název = scanner.nextLine();
		
		if(!knihovna.containsKey(název)) {
			System.out.println("Kniha nebyla nalezena.");
			return;
		} 
		
		knihovna.remove(název);
		System.out.println("Kniha " + název + " byla úspěšně odebrána ze seznamu.");
	}
	
	public void zmenitStavKnihy(Scanner scanner) {
		System.out.println("Zadejte název knihy, u které chcete změnit stav: ");
		String název = scanner.nextLine();
		
		if(!knihovna.containsKey(název)) {
			System.out.println("Kniha nebyla nalezena.");
			return;
		} 
		
		Knihy kniha = knihovna.get(název);
		System.out.println("Zadejte stav knihy (vypůjčená/vrácená): ");
		String stav = scanner.nextLine();
		
		if (stav.equalsIgnoreCase("vypůjčená")) {
			kniha.setDostupná(false);
		} else if (stav.equalsIgnoreCase("vrácená")) {
			kniha.setDostupná(true);
		} else {
			System.out.println("Špatně zadaný stav.");
			return;
		}
		
		knihovna.put(název, kniha);
		System.out.println("Stav knihy " + název + " byl úspěšně změněn.");
	}
	
	public void vypisKnih(Scanner scanner) {
		if (knihovna.isEmpty()) {
			System.out.println("Knihovna je prázdná.");
			return;
		}
		
		System.out.println("Zde je seznam knih v abecedním pořadí: ");
		
		for(Map.Entry<String, Knihy> entry : knihovna.entrySet()) {
			Knihy kniha = entry.getValue();
			String název = entry.getKey();
			String[] autoři = kniha.getAutoři();
			int rokVydání = kniha.getRokVydání();
			boolean dostupná = kniha.isDostupná();
			String typKnihy = "";
			String detailTypuKnihy = "";
			
			if (kniha instanceof Román) {
				typKnihy = "Román";
				detailTypuKnihy = "Žánr: " + ((Román) kniha).getŽánr();
			} else if (kniha instanceof Učebnice) {
				typKnihy = "Učebnice";
				detailTypuKnihy = "Ročník: " + ((Učebnice) kniha).getÚroveňRočníku();
			}
			
			System.out.println("Název: " + název);
			System.out.println("Autoři: " + String.join(", ", autoři));
			System.out.println("Typ knihy: " + typKnihy);
			System.out.println(detailTypuKnihy);
			System.out.println("Rok vydání: " + rokVydání);
			System.out.println("Stav dostupnosti: " + (dostupná ? "Dostupná" : "Nedostupná"));
			System.out.println();
		}
	}
}
