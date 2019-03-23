import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) {

        MainServer server = null;

        if (args.length != 1) {
            System.out.println("usage: Server port");
            System.exit(1);
        }

        try {
            int port = Integer.parseInt(args[0]);
            server = new MainServer(port);
            server.mainLoop();
        } catch (IOException e) {
            System.out.println("problème creation serveur : " + e.getMessage());
            System.exit(1);
        }
    }
}
