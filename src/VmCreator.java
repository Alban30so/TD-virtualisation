import java.beans.XMLEncoder;
import java.util.Scanner;
public class VmCreator {
    private static void infomenu(){
        System.out.println("1 : modifier les paramètres mémoire et processeurs");
        System.out.println("2 : modifier les paramètres d'affichage");
        System.out.println("3 : modifier les paramètres de boot");
        System.out.println("4 : modifier les paramètres stockage");
        System.out.println("5 : modifier les paramètres réseau");
    }
    private static void switchmenu(String name){
        Scanner scanner = new Scanner(System.in);
        switch(scanner.next()){
            case "1":
                System.out.println("Mémoire RAM (en Mo) : ");
                int ram = scanner.nextInt();
                System.out.println("Nombre de processeurs : ");
                int cpu = scanner.nextInt();
                System.out.println(VBoxWrapper.command("modifyvm " + name + " --memory " + ram + " --cpus " + cpu));
                break;
            case "2":
                System.out.println("En attente");
                break;
            case "3":
                System.out.println("Modification des paramètres de boot");
                break;
            case "4":
                System.out.println("Modification des paramètres de stockage");
                System.out.println("Ajouter un ISO ? (O/N)");
                if(scanner.next().equals("O")){
                    set_iso(name);
                }
                break;
            case "5":
                System.out.println("Modification des paramètres réseau");
                System.out.println("Numéro de carte réseau à modifier : ");
                int nic = scanner.nextInt();
                System.out.println("Type de réseau (ex: nat, bridged, intnet) : ");
                String network_type = scanner.next();
                System.out.println(VBoxWrapper.command("modifyvm " + name + " --nic" + nic + " " + network_type));
                break;
        }
    }
    public static void set_iso(String name){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer le chemin de l'ISO : ");
        String iso = scanner.next();
        System.out.println(VBoxWrapper.command("storagectl " + name + " --name ISO --add ISO"));
        System.out.println(VBoxWrapper.command("storageattach " + name + " --storagectl ISO --port 0 --device 0 --type dvddrive --medium " + iso));
    }
    public static String create(String name, String os) {
        System.out.println(VBoxWrapper.create(name, os));
        System.out.println("Machine virtuelle créée\nVoici les paramètres de la machine :");
        Scanner scanner = new Scanner(System.in);
        do{
            infomenu();
            switchmenu(name);
            System.out.println("Machine virtuelle modifiée avec succès, voulez vous modifier d'autres éléments ? (O/N)");
        }while(scanner.next().equals("O"));
        return "Machine virtuelle créée";
    }
}
