package es.ubu.lsi.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ChatServerStarter {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ChatServerImpl server = new ChatServerImpl();
            Naming.rebind("ChatServer", server);
            System.out.println("Chat server started...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
