package projekt;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Knihovna {
	private Map<String, Knihy> knihovna;

	public Knihovna() {
		this.knihovna = new TreeMap<>();
	}

	private static void ulozUčebniciDoSouboru(String název, String[] autoři, int rokVydání, boolean dostupná, int úroveňRočníku) {
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(název + ".txt"));
            writer.write("Název: " + název + "\n");
            writer.write("Autoři: ");
            for (String autor : autoři) {
                writer.write(autor + ", ");
            }
            writer.write("\nRok vydání: " + rokVydání + "\n");
            writer.write("Dostupnost: " + (dostupná ? "Ano" : "Ne") + "\n");
            writer.write("Úroveň ročníku: " + úroveňRočníku + "\n");
            writer.close();
            System.out.println("Informace byly úspěšně uloženy do souboru.");
        } catch (IOException e) {
            System.out.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
	}
	
	private static void ulozRománDoSouboru(String název, String[] autoři, int rokVydání, boolean dostupná, String žánr) {
		try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(název + ".txt"));
            writer.write("Název: " + název + "\n");
            writer.write("Autoři: ");
            for (String autor : autoři) {
                writer.write(autor + ", ");
            }
            writer.write("\nRok vydání: " + rokVydání + "\n");
            writer.write("Dostupnost: " + (dostupná ? "Ano" : "Ne") + "\n");
            writer.write("Žánr: " + žánr + "\n");
            writer.close();
            System.out.println("Informace byly úspěšně uloženy do souboru.");
        } catch (IOException e) {
            System.out.println("Chyba při zápisu do souboru: " + e.getMessage());
        }
	}
	
	public void načtiUčebniciZeSouboru(String cestaSouboru) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(cestaSouboru))) {
	        String název = reader.readLine().substring(8);
	        String autořiString = reader.readLine().substring(8);
	        String[] autoři = autořiString.split(", ");
	        int rokVydání = Integer.parseInt(reader.readLine().substring(13));
	        boolean dostupná = reader.readLine().substring(13).equals("Ano");
	        int úroveňRočníku = Integer.parseInt(reader.readLine().substring(17));

	        
	        Knihy kniha = new Učebnice(název, autoři, rokVydání, dostupná, úroveňRočníku);
	        knihovna.put(název,kniha);
			System.out.println(knihovna);
			System.out.println("Kniha byla úspěšně přidána.");
			System.out.println();
	    } catch (IOException e) {
	        System.out.println("Chyba při čtení ze souboru: " + e.getMessage());
	    }
	    
	}

	public void načtiRománZeSouboru(String cestaSouboru) {
	    try (BufferedReader reader = new BufferedReader(new FileReader(cestaSouboru))) {
	        String název = reader.readLine().substring(8);
	        String autořiString = reader.readLine().substring(8);
	        String[] autoři = autořiString.split(", ");
	        int rokVydání = Integer.parseInt(reader.readLine().substring(13));
	        boolean dostupná = reader.readLine().substring(13).equals("Ano"); 
	        String žánr = reader.readLine().substring(7);

	        Knihy kniha = new Román(název, autoři, rokVydání, dostupná, žánr);
	        knihovna.put(název,kniha);
			System.out.println(knihovna);
			System.out.println("Kniha byla úspěšně přidána.");
			System.out.println();
	    } catch (IOException e) {
	        System.out.println("Chyba při čtení ze souboru: " + e.getMessage());
	    }
	}

	public void pridatNovouKnihu(Scanner scanner) {
		try {
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
			Knihovna.ulozRománDoSouboru(název, autoři, rokVydání, dostupná, žánr);
		} else if (typ == 2) {
			System.out.println("Úroveň ročníku: ");
			int úroveňRočníku = scanner.nextInt();

			kniha = new Učebnice(název, autoři, rokVydání, dostupná, úroveňRočníku);
			Knihovna.ulozUčebniciDoSouboru(název, autoři, rokVydání, dostupná, úroveňRočníku);

		} else {
			System.out.println("Neplatný typ knihy");
		}

		knihovna.put(název,kniha);
		System.out.println(knihovna);
		System.out.println("Kniha byla úspěšně přidána.");
		System.out.println();
		} catch (InputMismatchException e) {
			System.out.println("Špatně zadaný parametr.");
			scanner.nextLine();
		}
	
	}
	public void upravitKnihu(Scanner scanner) {
		try{
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
		System.out.println();
		} catch (InputMismatchException e) {
			System.out.println("Špatně zadaný parametr.");
			scanner.nextLine();
		}
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
		System.out.println();
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
		System.out.println();
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

	public void vyhledatKnihu(Scanner scanner) {
		System.out.println("Zadejte název knihy, kterou chcete vyhledat: ");
		String název = scanner.nextLine();
		
		if(knihovna.containsKey(název)) {
			Knihy kniha = knihovna.get(název);
			System.out.println("Informace o knize: ");
			System.out.println("Název: " + kniha.getNázev());
			System.out.println("Autoři: " + String.join(", ", kniha.getAutoři()));
			System.out.println("Stav dostupnosti: " + (kniha.isDostupná() ? "Dostupná" : "Nedostupná"));
			System.out.println("Rok vydání: " + kniha.getRokVydání());

			if(kniha instanceof Román) {
				System.out.println("Typ knihy: Román");
				System.out.println("Žánr: " + ((Román) kniha).getŽánr());
			} else if (kniha instanceof Učebnice) {
				System.out.println("Typ knihy: Učebnice");
				System.out.println("Ročník: " + ((Učebnice) kniha).getÚroveňRočníku());
			} else {
				System.out.println("Kniha s názvem " + název + " nebyla nalezena.");
				System.out.println();
			}
		}
	}
/*String nazevSouboru = název + ".txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(nazevSouboru))) {
            int rokVydání = Integer.parseInt(reader.readLine().split(": ")[1]);
            String[] autoři = reader.readLine().split(": ")[1].split(", ");
            boolean dostupná = reader.readLine().split(": ")[1].equalsIgnoreCase("Ano");

            Knihy kniha = new Knihy(název, autoři, rokVydání, dostupná);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
	public void vypisKnihPodleAutora(Scanner scanner) {
		System.out.println("Zadejte jmeno autora: ");
		String autor = scanner.nextLine();

		List<Knihy> knihyPodleAutora = new ArrayList<>();


		for(Knihy kniha : knihovna.values()) {


			String[] autoři = kniha.getAutoři();
			for (String a : autoři) {
				if (a.equalsIgnoreCase(autor)) {
					knihyPodleAutora.add(kniha);
					break;
				}
			}
		}


		knihyPodleAutora.sort(Comparator.comparingInt(Knihy::getRokVydání));

		if (!knihyPodleAutora.isEmpty()) {
			System.out.println("Seznam knih autora " + autor + " v chronologickém pořadí: ");
			for(Knihy kniha : knihyPodleAutora) {
				System.out.println("Název: " + kniha.getNázev());
				System.out.println("Rok vydání: " + kniha.getRokVydání());
				System.out.println();
			}
		} else {
			System.out.println("Žádné knihy od autora " + " nebyly nalezeny.");
		}
	}

	public void vypisKnihPodleZanru(Scanner scanner) {
		System.out.println("Zadejte žánr: ");
		String žánr = scanner.nextLine();

		List<Knihy> knihyPodleŽánru = new ArrayList<>();

		for (Knihy kniha : knihovna.values()) {
			if(kniha instanceof Román && ((Román) kniha).getŽánr().equalsIgnoreCase(žánr)) {
				knihyPodleŽánru.add(kniha);
			}
		}

		if (!knihyPodleŽánru.isEmpty()) {
			System.out.println("Seznam knih žánru " + žánr + ":");
			for (Knihy kniha : knihyPodleŽánru) {
				System.out.println("Název: " + kniha.getNázev());
				System.out.println("Rok vydání: " + kniha.getRokVydání());
				System.out.println();
			}
		} else {
			System.out.println("ˇŽádné knihy tohoto žánru nebyly nalezeny.");
		}
	}

	public void vypisVypujcenychKnih(Scanner scanner) {
		boolean existujiVypůjčenéKnihy = false;

		System.out.println("Seznam vypůjčených knih: ");
		for(Knihy kniha : knihovna.values()) {
			if (!kniha.isDostupná()) {
				existujiVypůjčenéKnihy = true;
				String typKnihy = "";
				if (kniha instanceof Román) {
					typKnihy = "Román";
				} else if (kniha instanceof Učebnice) {
					typKnihy = "Učebnice";
				}
				
				System.out.println("Název: " + kniha.getNázev());
				System.out.println("Typ knihy: " + typKnihy);
				System.out.println();
			}
		}
		
		if(!existujiVypůjčenéKnihy) {
			System.out.print("Žádné knihy nejsou vypůjčené");
			System.out.println();
		}
	}
		public TreeSet<Knihy> getSeznamKnih() {
		    return new TreeSet<>(knihovna.values());
		}
}