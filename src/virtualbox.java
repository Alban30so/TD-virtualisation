public class virtualbox {
    public static String create(String name, String os) {
        return terminal.sendCommand("vboxmanage createvm --name "+name+" --ostype "+os+" --register");
    }
    public static String delete(String name) {
        return terminal.sendCommand("vboxmanage unregistervm "+name+" --delete");
    }
    public static String clone(String name, String clone) {
        return terminal.sendCommand("vboxmanage clonevm "+name+" --name "+clone+" --register");
    }
    public static String start(String name) {
        return terminal.sendCommand("vboxmanage startvm "+name+" --type headless");
    }
    public static String stop(String name) {
        return terminal.sendCommand("vboxmanage controlvm "+name+" poweroff");
    }
    public static String list() {
        return terminal.sendCommand("vboxmanage list vms");
    }
    public static String command(String command) {
        return terminal.sendCommand("vboxmanage "+command);
    }
    public static String get_version() {
        String test = terminal.sendCommand("vboxmanage --version");
        if (test.contains("command not found")) {
            return "VirtualBox not installed";
        } else {
            return test;
        }
    }
}
