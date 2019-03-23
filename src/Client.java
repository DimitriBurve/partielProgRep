import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) {

        MainClient client;

        if ((args.length < 3) || (args.length > 4)) {
            System.out.println("usage: Client ip_server port rayon|largeur [hauteur]");
            System.exit(1);
        }

        try {
            String serverAddr = args[0];
            int port = Integer.parseInt(args[1]);
            client = new MainClient(serverAddr, port);
            client.mainLoop();
        } catch (IOException e) {
            System.out.println("problème de connexion au serveur : " + e.getMessage());
            System.exit(1);
        }
    }
}
