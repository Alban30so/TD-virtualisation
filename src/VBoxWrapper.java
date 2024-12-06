import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class VBoxWrapper {
    //Set list of OS type from ostype_virtualbox.txt.txt
    public static String[] GetOSType(String filePath) {
        ArrayList<String> osTypesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    osTypesList.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        // Convertir la liste en tableau de chaînes
        return osTypesList.toArray(new String[0]);
    }
    public static String create(String name, String os) {
        System.out.println("création d'une VM");
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

    public static String get_version() {
        String test = Terminal.sendCommand("vboxmanage --version");
        if (test.contains("command not found")) {
            return "VirtualBox not installed";
        } else {
            return test;
        }
    }
}
