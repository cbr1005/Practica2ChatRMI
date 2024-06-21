package es.ubu.lsi.client;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import es.ubu.lsi.common.ChatMessage;
import es.ubu.lsi.server.ChatServer;

public class ChatClientStarter {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java ChatClientStarter <nickname> [<host>]");
            return;
        }

        String nickname = args[0];
        String host = (args.length > 1) ? args[1] : "localhost";

        try {
            ChatClientImpl client = new ChatClientImpl(nickname);
            ChatServer server = (ChatServer) Naming.lookup("//" + host + "/ChatServer");
            
            int clientId = server.checkIn(client);
            client.setId(clientId);

            Scanner scanner = new Scanner(System.in);
            String input;
            while (!(input = scanner.nextLine()).equalsIgnoreCase("logout")) {
                if (input.startsWith("ban ") || input.startsWith("unban ")) {
                    server.publish(new ChatMessage(clientId, nickname, input));
                } else {
                    server.publish(new ChatMessage(clientId, nickname, input));
                }
            }

            server.logout(client);
            scanner.close();
            UnicastRemoteObject.unexportObject(client, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
