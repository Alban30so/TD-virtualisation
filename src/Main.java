public class Main {
	private static final java.util.Scanner scanner = new java.util.Scanner(System.in);
	private static String name, os;

	public static void main(String[] args) {
		if (VBoxWrapper.isVBoxInstalled()) {
			System.out.println("[VBox-Wish] Bienvenue sur l'assistant VM");
			String[] osTypesList = VBoxWrapper.GetOSType("src/ostype_virtualbox.txt");

			do {
				System.out.println(
						"\n1. Créer une machine virtuelle\n2. Lister les machines virtuelles\n3. Démarrer une machine virtuelle\n4. Arrêter une machine virtuelle\n5. Déployer une template\n6. Supprimer une machine virtuelle\n7. Quitter");

				switch (scanner.nextLine()) {
					case "1":
						System.out.println(
								"\n[VBox-Wish] Création d'une machine virtuelle\nNom de la machine virtuelle :");
						name = scanner.nextLine();
						System.out.println("\n[VBox-Wish] Type de système d'exploitation (ex: Linux_64, Windows7_64) :");
						os = scanner.nextLine();
						boolean osFound = false;

						for (String osType : osTypesList) {
							if (osType.equals(os)) {
								VmCreator.create(name, os);
								osFound = true;
								break;
							}
						}

						if (!osFound) {
							System.out.println("\n[VBox-Wish] Type de système d'exploitation inconnu.");
							System.exit(1);
						}
						break;
					case "2":
						System.out.println("\n[VBox-Wish] Liste des machines virtuelles");
						for (String vm : VBoxWrapper.list()) {
							System.out.println(vm);
						}
						break;
					case "3":
						System.out.println(
								"\n[VBox-Wish] Démarrage d'une machine virtuelle\nNom de la machine virtuelle :");
						name = scanner.nextLine();
						System.out.println(VBoxWrapper.start(name));
						break;
					case "4":
						System.out
								.println("\n[VBox-Wish] Arrêt d'une machine virtuelle\nNom de la machine virtuelle :");
						name = scanner.nextLine();
						System.out.println(VBoxWrapper.stop(name));
						break;
					case "5":
						VBoxWrapper.deployTemplate(scanner);
						break;
					case "6":
						System.out.println(
								"\n[VBox-Wish] Suppression d'une machine virtuelle\nNom de la machine virtuelle :");
						name = scanner.nextLine();
						System.out.println(VBoxWrapper.delete(name));
						break;
					case "7":
						System.exit(0);
						break;
					default:
						System.out.println("\n[VBox-Wish] Erreur dans la sélection.");
				}
			} while (true);
		} else {
			System.out.println("\n[VBox-Wish] VirtualBox n'est pas installé. Fermeture...");
		}
	}
}