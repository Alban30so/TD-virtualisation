import java.util.Scanner;

public class Main {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
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
				deployTemplate();
				break;
			default:
				System.out.println("\n[VBox-Wish] Choix invalide.");
		}
	}

	// Déploiement d'une template (clonage)
	public static void deployTemplate() {
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