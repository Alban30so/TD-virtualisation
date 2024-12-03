import java.util.Scanner;
public class terminal {
    //check OS
    public static String OS = System.getProperty("os.name").toLowerCase();
    //send command to terminal
    public static String sendCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            Scanner scanner = new Scanner(process.getInputStream());
            String output = "";
            while (scanner.hasNext()) {
                output += scanner.nextLine() + "\n";
            }
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
