package chatRoom;

import org.myclient.ServerCommunication;
import org.myclient.ServerCommunicationSingleton;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ChatRoomInput extends Thread {
    private String client;
    private ObjectInputStream input;
    private String mensage = "";
    private ChatRoom chatRoom;

    public ChatRoomInput(ObjectInputStream input, ChatRoom chatRoom){
        System.out.println("Você iniciou um chat com o servidor. Digite e pressione ENTER para enviar...");
        this.input = input;
        this.chatRoom = chatRoom;
    }

    @Override
    public void run(){
        while(chatRoom.isRunning()){
            try {
                mensage = (String)input.readObject();
                if(!mensage.equals("")) {
                    System.out.println("Server: " + mensage);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            if(mensage.equals("/SAIR")){
                chatRoom.close();
            }
        }
    }

    public void close() {
        this.interrupt();
    }
}
