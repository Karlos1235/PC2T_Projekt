package projekt;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner scanner =  new Scanner(System.in);

		System.out.println("Vítejte v knihovně U Dvou přátel");

		while(true) {
			System.out.println("Vyberte akci: ");
			System.out.println("1. Přidat novou knihu");
			System.out.println("2. Ukončit program");
			System.out.println("Vaše volba: ");
			int volba = scanner.nextInt();

			switch (volba) {
			case 1:
				pridatNovouKnihu(scanner);
				break;
			case 2:
				System.out.println("Děkujeme za využití naší aplikace. Ukončuji program.");
				return;
			default:
				System.out.println("Neplatná volba. Zkuste to prosím znovu.");
				break;
			}
		}
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
		
		System.out.print("Dostupnost (Dostupná/Nedostupná): ");
		scanner.nextLine();
		String dostupnostText = scanner.nextLine();
		boolean dostupná = dostupnostText.equalsIgnoreCase("Dostupná");
		
		if(typ == 1) {
			System.out.print("Žánr: ");
			String žánr = scanner.next();
			
			//Vytvoření nového románu
			Román román = new Román(název, autoři, rokVydání, dostupná, žánr);
			
			//Todo Vytvořit logiku pro uložení románu do knihovny
			
			
		} else if (typ == 2) {
			System.out.print("Úroveň ročníku: ");
			int úroveňRočníku = scanner.nextInt();
			
			//Vytvoření nové učebnice
			Učebnice učebnice = new Učebnice(název, autoři, úroveňRočníku, dostupná, úroveňRočníku);
			
			//Todo Vytvořit logiku pro uložení učebnice do knihovny
			
			
		} else {
			System.out.println("Neplatný typ knihy");
		}
	}

}
