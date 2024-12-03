import java.util.Scanner;

public class VBoxWrapper {
	public static String create(String name, String os) {
		return Terminal.sendCommand("vboxmanage createvm --name " + name + " --ostype " + os + " --register");
	}

	public static String delete(String name) {
		return Terminal.sendCommand("vboxmanage unregistervm " + name + " --delete");
	}

	public static String clone(String name, String clone) {
		return Terminal.sendCommand("vboxmanage clonevm " + name + " --name " + clone + " --register");
	}

	public static String start(String name) {
		return Terminal.sendCommand("vboxmanage startvm " + name + " --type headless");
	}

	public static String stop(String name) {
		return Terminal.sendCommand("vboxmanage controlvm " + name + " poweroff");
	}

	public static String list() {
		return Terminal.sendCommand("vboxmanage list vms");
	}

	public static String command(String command) {
		return Terminal.sendCommand("vboxmanage " + command);
	}

	public static boolean isVBoxInstalled() {
		String output = Terminal.sendCommand("vboxmanage --version");
		return (output != null && !output.isEmpty());
	}

	public static void deployUsingExportImport(String sourceVM, String cloneName) {
		// Chemin temporaire pour l'export
		String exportPath = sourceVM + "_template.ova";

		// Exporter la machine source
		System.out.println("\n[VBox-Wish] Exportation de la machine source...");
		Terminal.sendCommand("vboxmanage export " + sourceVM + " --output " + exportPath);

		// Importer avec un nouveau nom
		System.out.println("\n[VBox-Wish] Importation avec le nom du clone...");
		Terminal.sendCommand("vboxmanage import " + exportPath + " --vsys 0 --vmname " + cloneName);

		// Suppression du fichier temporaire
		System.out.println("\n[VBox-Wish] Nettoyage du fichier temporaire...");
		// TO-DO: Pour Unix, adapter automatiquement avec Windows ???
		Terminal.sendCommand("rm -f " + exportPath);

		System.out.println("\n\n[VBox-Wish] Déploiement via export/import terminé avec succès !");
	}

	public static void deployUsingDirectClone(String sourceVM, String cloneName) {
		// Créer un clone
		System.out.println("[VBox-Wish] Clonage direct de la machine...");
		Terminal.sendCommand("vboxmanage clonevm " + sourceVM + " --name " + cloneName + " --register");

		System.out.println("\n\n[VBox-Wish] Déploiement via clonage direct terminé avec succès !");
	}

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
					deployUsingExportImport(sourceVM, cloneName);
					break;
				case 2:
					deployUsingDirectClone(sourceVM, cloneName);
					break;
				default:
					System.out.println("[VBox-Wish] Choix invalide.");
			}
		} catch (Exception e) {
			System.err.println("[VBox-Wish] Erreur lors du déploiement : " + e.getMessage());
		}

		scanner.close();
	}
}
