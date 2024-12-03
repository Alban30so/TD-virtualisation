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

    public static String get_version() {
        String test = Terminal.sendCommand("vboxmanage --version");
        if (test.contains("command not found")) {
            return "VirtualBox not installed";
        } else {
            return test;
        }
    }
}
