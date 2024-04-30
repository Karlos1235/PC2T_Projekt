package projekt;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner scanner =  new Scanner(System.in);
		Knihovna knihovna = new Knihovna();

		System.out.println("Vítejte v knihovně U Dvou feláků");

		while(true) {
			System.out.println("Vyberte číslo: ");
			System.out.println("1. Přidat novou knihu");
			System.out.println("2. Upravit knihu");
			System.out.println("3. Smazat knihu");
			System.out.println("4. Změnit stav knihy");
			System.out.println("5. Zobrazit seznam knih");
			System.out.println("6. Vyhledat knihu");
			System.out.println("7. Výpis všech knih podle autora");
			System.out.println("8. Výpis všech knih podle žánru");
			System.out.println("9. Výpis všech vypůjčených knih");
			System.out.println("10. Ukončit program");
			System.out.println("Vaše volba: ");
			int volba = scanner.nextInt();
			scanner.nextLine();

			switch (volba) {
			case 1:
				knihovna.pridatNovouKnihu(scanner);
				break;
			case 2:
				knihovna.upravitKnihu(scanner);
				break;
			case 3:
				knihovna.smazatKnihu(scanner);
				break;
			case 4:
				knihovna.zmenitStavKnihy(scanner);
				break;
			case 5:
				knihovna.vypisKnih(scanner);
				break;
			case 6:
				knihovna.vyhledatKnihu(scanner);
				break;
			case 7:
				knihovna.vypisKnihPodleAutora(scanner);
				break;
			case 8:
				knihovna.vypisKnihPodleZanru(scanner);
				break;
			case 9:
				knihovna.vypisVypujcenychKnih(scanner);
				break;
			case 10:
				System.out.println("Děkujeme za využití naší aplikace. Ukončuji program.");
				return;
			default:
				System.out.println("Neplatná volba. Zkuste to prosím znovu.");
				break;
			}
		}
	}
}
