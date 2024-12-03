public class virtualbox {
    public static String get_version() {
        String test = terminal.sendCommand("vboxmanage --version");
        if (test.contains("command not found")) {
            return "VirtualBox not installed";
        } else {
            return test;
        }
    }
}
