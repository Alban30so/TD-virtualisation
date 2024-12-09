import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        System.out.println("[VBox-Wish] Bienvenue sur l'assistant VM");
        Scanner scanner = new Scanner(System.in);
        if (VBoxWrapper.isVBoxInstalled()){
            do {
                String oslist = "src/ostype_virtualbox.txt";
                String[] osTypesList = VBoxWrapper.GetOSType(oslist);
                System.out.println("1. Créer une machine virtuelle");
                System.out.println("2. Lister les machines virtuelles");
                System.out.println("3. Démarrer une machine virtuelle");
                System.out.println("4. Arrêter une machine virtuelle");
                System.out.println("5. Deployer un template");
                System.out.println("6. Supprimer une machine virtuelle");
                System.out.println("7. Exécuter une commande VirtualBox");
                System.out.println("8. Quitter");
                String name;
                switch (scanner.next()) {
                    case "1":
                        System.out.println("Création d'une machine virtuelle");
                        System.out.println("Nom de la machine virtuelle : ");
                        //check if name is in ostypevirtualbox.txt file
                        name = scanner.next();
                        System.out.println("Type de système d'exploitation (ex: Linux_64, Windows7_64) : ");
                        String os = scanner.next();
                        int i = 0;
                        //verify if OS is in the ostype_virtualbox.txt.txt file
                        for (String osType : osTypesList) {
                            if (osType.equals(os)) {
                                i = 1;
                                break;
                            }
                        }
                        if (i == 0) {
                            System.out.println("Type de système d'exploitation non reconnu");
                            exit(1);
                        }
                        VmCreator.create(name, os);
                        break;
                    case "2":
                        System.out.println("Liste des machines virtuelles");
                        System.out.println(VBoxWrapper.list());
                        break;
                    case "3":
                        System.out.println("Démarrage d'une machine virtuelle");
                        System.out.println("Nom de la machine virtuelle : ");
                        name = scanner.next();
                        System.out.println(VBoxWrapper.start(name));
                        break;
                    case "4":
                        System.out.println("Arrêt d'une machine virtuelle");
                        System.out.println("Nom de la machine virtuelle : ");
                        name = scanner.next();
                        System.out.println(VBoxWrapper.stop(name));
                        break;
                    case "5":
                        deployTemplate();
                        break;
                    case "6":
                        System.out.println("Suppression d'une machine virtuelle");
                        System.out.println("Nom de la machine virtuelle : ");
                        name = scanner.next();
                        System.out.println(VBoxWrapper.delete(name));
                        break;
                    case "7":
                        System.out.println("Exécution d'une commande VirtualBox");
                        System.out.println("Commande : ");
                        String command = scanner.next();
                        System.out.println(VBoxWrapper.command(command));
                        break;
                    case "8":
                        System.out.println("Au revoir");
                        exit(0);
                }
            } while (true);
        } else {
            System.out.println("VirtualBox n'est pas installé");
        }
    }

	// Déploiement d'une template (clonage)
	public static void deployTemplate() {
        Scanner scanner = new Scanner(System.in);
		System.out
				.println("\n\n[VBox-Wish] == Déploiement d'une template ==\n\nFaites votre choix =>");
		System.out.println("1. Exporter/Importer une appliance\n2. Clonage direct");

		int choix = scanner.nextInt();
		scanner.nextLine();

		System.out.println("\n[VBox-Wish] Nom machine source :");
		String sourceVM = scanner.nextLine();

		System.out.println("\n[VBox-Wish] Nom machine déployée :");
		String cloneName = scanner.nextLine();

		try {
			switch (choix) {
				case 1:
					VBoxWrapper.deployUsingExportImport(sourceVM, cloneName);
					break;
				case 2:
					VBoxWrapper.deployUsingDirectClone(sourceVM, cloneName);
					break;
				default:
					System.out.println("\n[VBox-Wish] Choix invalide.");
			}
		} catch (Exception e) {
			System.err.println("\n[VBox-Wish] Erreur lors du déploiement : " + e.getMessage());
		}
	}
}