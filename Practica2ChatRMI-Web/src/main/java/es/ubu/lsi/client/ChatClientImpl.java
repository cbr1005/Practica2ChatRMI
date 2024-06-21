package es.ubu.lsi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import es.ubu.lsi.common.ChatMessage;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String nickname;

    protected ChatClientImpl(String nickname) throws RemoteException {
        super();
        this.nickname = nickname;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public void setId(int id) throws RemoteException {
        this.id = id;
    }

    @Override
    public void receive(ChatMessage msg) throws RemoteException {
        System.out.println(msg.getNickname() + ": " + msg.getMessage());
    }

    @Override
    public String getNickName() throws RemoteException {
        return nickname;
    }
}
