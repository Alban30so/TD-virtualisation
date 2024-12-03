import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue sur VirtualBox de Wish");
        Scanner scanner = new Scanner(System.in);
        System.out.println(virtualbox.get_version());
        if(!virtualbox.get_version().contains("VirtualBox not installed")) {
            System.out.println("1. Créer une machine virtuelle");
            System.out.println("2. Lister les machines virtuelles");
            System.out.println("3. Démarrer une machine virtuelle");
            System.out.println("4. Arrêter une machine virtuelle");
            System.out.println("5. Cloner une machine virtuelle");
            System.out.println("6. Supprimer une machine virtuelle");
            System.out.println("7. Exécuter une commande VirtualBox");
            System.out.println("8. Quitter");
            String name;
            switch (scanner.next()){
                case "1":
                    System.out.println("Création d'une machine virtuelle");
                    System.out.println("Nom de la machine virtuelle : ");
                    name = scanner.next();
                    System.out.println("Type de système d'exploitation (ex: Linux_64, Windows7_64) : ");
                    String os = scanner.next();
                    System.out.println(virtualbox.create(name, os));
                    break;
                case "2":
                    System.out.println("Liste des machines virtuelles");
                    System.out.println(virtualbox.list());
                    break;
                case "3":
                    System.out.println("Démarrage d'une machine virtuelle");
                    System.out.println("Nom de la machine virtuelle : ");
                    name = scanner.next();
                    System.out.println(virtualbox.start(name));
                    break;
                case "4":
                    System.out.println("Arrêt d'une machine virtuelle");
                    System.out.println("Nom de la machine virtuelle : ");
                    name = scanner.next();
                    System.out.println(virtualbox.stop(name));
                    break;
                case "5":
                    System.out.println("Clonage d'une machine virtuelle");
                    System.out.println("Nom de la machine virtuelle à cloner : ");
                    name = scanner.next();
                    System.out.println("Nom du clone : ");
                    String clone = scanner.next();
                    System.out.println(virtualbox.clone(name, clone));
                    break;
                case "6":
                    System.out.println("Suppression d'une machine virtuelle");
                    System.out.println("Nom de la machine virtuelle : ");
                    name = scanner.next();
                    System.out.println(virtualbox.delete(name));
                    break;
                case "7":
                    System.out.println("Exécution d'une commande VirtualBox");
                    System.out.println("Commande : ");
                    String command = scanner.next();
                    System.out.println(virtualbox.command(command));
                    break;
                case "8":
                    System.out.println("Au revoir");
                    break;
            }
        }
        else{
            System.out.println("VirtualBox n'est pas installé");
        }
    }
}