import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class VBoxWrapper {
	// Constante pour la commande suppression de fichier selon l'OS
	public static final String DELETE_CMD;

	// Bloc statique pour initialiser la constante
	static {
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			DELETE_CMD = "del ";
		} else {
			DELETE_CMD = "rm ";
		}
	}

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
		return output != null && !output.isEmpty();
	}

	// Partie clonage - Arthur

	// Clonage VM avec la méthode export/import
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
		Terminal.sendCommand(DELETE_CMD + exportPath);

		System.out.println("\n\n[VBox-Wish] Déploiement via export/import terminé avec succès !");
	}

	// Clonage VM avec la méthode direct clone
	public static void deployUsingDirectClone(String sourceVM, String cloneName) {
		// Créer un clone
		System.out.println("\n[VBox-Wish] Clonage direct de la machine...");
		Terminal.sendCommand("vboxmanage clonevm " + sourceVM + " --name " + cloneName + " --register");

		System.out.println("\n\n[VBox-Wish] Déploiement via clonage direct terminé avec succès !");
	}
}
