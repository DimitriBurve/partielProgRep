import java.io.*;
import java.net.*;
import java.util.*;

public class MainServer {
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    ServerSocket conn = null;
    Socket sock = null;
    int port = -1;
    int idClient;

    public MainServer(int port) throws IOException {
        this.port = port;
        conn = new ServerSocket(port);
        idClient = 1;
    }

    public void mainLoop() {


        try {
            while (true) {
                sock = conn.accept();

                ois = new ObjectInputStream(sock.getInputStream());
                oos = new ObjectOutputStream(sock.getOutputStream());

                System.out.println("action");

//                while (true) {
//                    if (idClient == 55){
//                        break;
//                    }
                    requestLoop();
//                }

                oos.close();
                ois.close();
                System.out.println("fin");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


    public void requestLoop() {
        boolean stop = false;
        while (!stop) {
            try {
                int numReq = ois.readInt();
                if (numReq == 1) {
                    request1();
                } else if (numReq == 2) {
                    request2();
                }
            } catch (IOException e) {
                System.out.println("error in request loop :" + e.getMessage());
                stop = true;
            }
        }
    }

    public void request1() throws IOException {
        System.out.println("requete 1 server");
        System.out.println(ois.readInt());
        oos.writeInt(1);
    }

    public void request2() throws IOException {
        System.out.println("requete 2 server");
        System.out.println(ois.readInt());
    }

//    Remarque : dans ce canevas les requêtes ne renvoient rien mais il est parfois utile d’ajouter un type de retour.
//    Remarque : de même, on peut ajouter des paramètres lors de l’appel à ces requêtes, par ex l’id du client qui l’envoie.

}
