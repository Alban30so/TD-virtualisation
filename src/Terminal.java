import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Terminal {
	// Envoyer une commande système via le shell
	public static String sendCommand(String command) {
		try {
			@SuppressWarnings("deprecation")
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			return reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
			return "\n[VBox-Wish] Erreur commande : " + e.getMessage();
		}
	}
}
