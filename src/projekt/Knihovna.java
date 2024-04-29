package projekt;
import java.util.Scanner;

public class Knihovna {
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
	}
}
