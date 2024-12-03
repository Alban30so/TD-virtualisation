import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Vérifier si VirtualBox est présent
        if (!VBoxWrapper.isVBoxInstalled()) {
            System.out.println("[VBox-Wish] VirtualBox n'est pas installé. Fermeture...");
            System.exit(1);
        }

        System.out.println("[VBox-Wish] Bienvenue sur l'assistant VM !\n\nFaites votre choix =>");
        System.out.println("1. Déployer une template");

        int choix = scanner.nextInt();
        switch (choix) {
            case 1:
                VBoxWrapper.deployTemplate();
                break;
            default:
                System.out.println("[VBox-Wish] Choix invalide.");
        }

        scanner.close();
    }
}