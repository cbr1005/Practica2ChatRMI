package es.ubu.lsi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import es.ubu.lsi.client.ChatClient;
import es.ubu.lsi.common.ChatMessage;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ChatClient> clients;
    private int clientIdCounter = 0;

    protected ChatServerImpl() throws RemoteException {
        super();
        clients = new ArrayList<>();
    }

    @Override
    public synchronized int checkIn(ChatClient client) throws RemoteException {
        clientIdCounter++;
        client.setId(clientIdCounter);
        clients.add(client);
        return clientIdCounter;
    }

    @Override
    public synchronized void logout(ChatClient client) throws RemoteException {
        clients.remove(client);
    }

    @Override
    public synchronized void publish(ChatMessage msg) throws RemoteException {
        for (ChatClient client : clients) {
            if (client.getId() != msg.getId()) {
                client.receive(msg);
            }
        }
    }

    @Override
    public void shutdown(ChatClient client) throws RemoteException {
        // No se implementa en esta práctica
    }
}
