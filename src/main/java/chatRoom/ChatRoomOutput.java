package chatRoom;

import org.myclient.Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ChatRoomOutput extends Thread{
    private Scanner scanner = Client.getScanner();
    private ObjectOutputStream output;
    private ChatRoom chatRoom;

    public ChatRoomOutput(ObjectOutputStream output, ChatRoom chatRoom){
        this.output = output;
        this.chatRoom = chatRoom;
    }

    @Override
    public void run(){
        while(chatRoom.isRunning()){
            String mensage = scanner.nextLine();

            if(!mensage.equals("")) {

                System.out.println("Eu: " + mensage);
                try {
                    output.flush();
                    output.writeObject(mensage);
                } catch (IOException e) {
                    chatRoom.close();
                    throw new RuntimeException(e);
                }
            }

            if(mensage.equals("/SAIR")){
                chatRoom.setNotRunning();
            }
        }
    }

    public void close() {
        this.interrupt();
    }
}
