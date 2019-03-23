import java.io.*;
import java.net.*;
import java.util.*;

public class MainClient {
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    Socket sock = null;
    int idClient;
    BufferedReader consoleIn;


    public MainClient(String serverAddr, int port) throws IOException {
        sock = new Socket(serverAddr, port);
        // IMPORTANT : instanciation des flux en ordre inverse chez client et serveur sinon blocage.
        oos = new ObjectOutputStream(sock.getOutputStream());
        ois = new ObjectInputStream(sock.getInputStream());
        consoleIn = new BufferedReader(new InputStreamReader(System.in));
    }

    public void mainLoop() throws IOException {
        String req = "";
        boolean stop = false;
        while (!stop) {
            System.out.println("requete >");
            req = consoleIn.readLine();
            String[] lst = req.split(" ");
            if (lst.length == 0) {
                continue;
            }
            if (lst[0].equals("1")) {
                stop = request1(lst);
            } else if (lst[0].equals("2")) {
                stop = request2(lst);
            } else if (lst[0].equals("quit")) {
                stop = true;
            }
        }
    }

    public boolean request1(String[] params) {
        boolean test = false;
        System.out.println("requete 1 client");
        if (params.length != 4) {
            return true;
        }
        double x, y;
        try {
            x = Double.parseDouble(params[1]);
            y = Double.parseDouble(params[2]);
            oos.writeInt(1);
            oos.writeInt(idClient);
            oos.writeDouble(x);
            oos.writeDouble(y);
            oos.writeObject(params[3]);
            oos.flush();
            System.out.println("requete 1 client envoyee");
            // recept resultat
            if (ois.readInt()==1){
                test = true;
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            return true;
        }
        return test;
    }

    public boolean request2(String[] params) {
        boolean test = false;
        System.out.println("requete 2 client");
        if (params.length != 4) {
            return true;
        }
        double x, y;
        try {
            x = Double.parseDouble(params[1]);
            y = Double.parseDouble(params[2]);
            oos.writeInt(2);
            oos.writeInt(idClient);
            oos.writeDouble(x);
            oos.writeDouble(y);
            oos.writeObject(params[3]);
            oos.flush();
            System.out.println("requete 2 client envoyee");
            // recept resultat
            if (ois.readInt()==1){
                test = true;
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            return true;
        }
        return test;
    }
}
