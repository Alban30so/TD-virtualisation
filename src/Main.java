public class Main {
    public static void main(String[] args) {
        System.out.println("[VBox-Wish] Bienvenue sur l'assistant VM\n");
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        if (VBoxWrapper.isVBoxInstalled()) {
            do {
                String[] osTypesList = VBoxWrapper.GetOSType("src/ostype_virtualbox.txt");

                System.out.println("1. Créer une machine virtuelle");
                System.out.println("2. Lister les machines virtuelles");
                System.out.println("3. Démarrer une machine virtuelle");
                System.out.println("4. Arrêter une machine virtuelle");
                System.out.println("5. Deployer un template");
                System.out.println("6. Supprimer une machine virtuelle");
                System.out.println("7. Exécuter une commande VirtualBox");
                System.out.println("8. Quitter");

                switch (scanner.next()) {
                    case "1":
                        System.out.println("\n[VBox-Wish] Création d'une machine virtuelle\nNom de la machine virtuelle :");
                        String name = scanner.next();
                        System.out.println("Type de système d'exploitation (ex: Linux_64, Windows7_64) : ");
                        String os = scanner.next();
                        int i = 0;

                        for (String osType : osTypesList) {
                            if (osType.equals(os)) {
                                i = 1;
                                break;
                            }
                        }
                        if (i == 0) {
                            System.out.println("Type de système d'exploitation non reconnu");
                            System.exit(1);
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
                        VBoxWrapper.deployTemplate();
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
                        System.exit(0);
                }
            } while (true);
        } else {
            System.out.println("VirtualBox n'est pas installé. Fermeture...");
        }

        scanner.close();
    }
}