package projekt;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Knihovna {
	private static Map<String, Knihy> knihovna;
	
	public Knihovna() {
		this.knihovna = new HashMap<>();
	}


	public static void pridatNovouKnihu(Scanner scanner) {
		System.out.println("Vyberte typ knihy: ");
		System.out.println("1. Román ");
		System.out.println("2. Učebnice ");
		System.out.println("Vaše volba: ");
		int typ = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Název knihy: ");
		String název = scanner.nextLine();

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

		if(typ == 1) {
			System.out.println("Žánr: ");
			String žánr = scanner.next();

			//Vytvoření nového románu
			Román román = new Román(název, autoři, rokVydání, dostupná, žánr);

			//Todo Vytvořit logiku pro uložení románu do knihovny


		} else if (typ == 2) {
			System.out.println("Úroveň ročníku: ");
			int úroveňRočníku = scanner.nextInt();

			//Vytvoření nové učebnice
			Učebnice učebnice = new Učebnice(název, autoři, úroveňRočníku, dostupná, úroveňRočníku);

			//Todo Vytvořit logiku pro uložení učebnice do knihovny


		} else {
			System.out.println("Neplatný typ knihy");
		}
		Knihy kniha = new Knihy(název,autoři, rokVydání, dostupná);
		knihovna.put(název,kniha);
		System.out.println("Kniha byla úspěšně přidána.");
	}

	public static void upravitKnihu(Scanner scanner) {
		System.out.println("Zadejte název knihy, kterou chcete upravit: ");
		String název = scanner.nextLine();
		
		if(!knihovna.containsKey(název)) {
			System.out.println("Knihovna je prázdná");
			return;
		}
		
		Knihy kniha = knihovna.get(název);
		System.out.println("Vyberte, který parametr knihy chcete upravit:");
		System.out.println("1. Autora/y");
		System.out.println("2. Rok vydání");
		System.out.println("3. Dostupnost");
		System.out.println("Vaše volba: ");
		int volba = scanner.nextInt();
		scanner.nextLine();

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
}
