package projekt;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		Scanner scanner =  new Scanner(System.in);
		Knihovna knihovna = new Knihovna();

		System.out.println("Vítejte v knihovně U Dvou feláků");

		while(true) {
			System.out.println("Vyberte akci: ");
			System.out.println("1. Přidat novou knihu");
			System.out.println("2. Upravit knihu");
			System.out.println("3. Ukončit program");
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
				System.out.println("Děkujeme za využití naší aplikace. Ukončuji program.");
				return;
			default:
				System.out.println("Neplatná volba. Zkuste to prosím znovu.");
				break;
			}
		}
	}
}
