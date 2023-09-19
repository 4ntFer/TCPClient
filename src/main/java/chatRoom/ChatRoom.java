package chatRoom;


import org.myclient.Client;
import org.myclient.ServerCommunicationSingleton;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ChatRoom {
    private ObjectInputStream input;
    private boolean isRunning = true;
    private ChatRoomOutput chatRoomOutput;
    private ChatRoomInput chatRoomInput;

    public ChatRoom(ObjectInputStream input, ObjectOutputStream output){
        clear();
        chatRoomInput = new ChatRoomInput(input, this);
        chatRoomOutput = new ChatRoomOutput(output, this);
        this.input = input;

        Client.setInChat(true);
        chatRoomOutput.start();
        chatRoomInput.start();
    }

    public static void clear(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    public void close() {
        isRunning = false;
        Client.setInChat(false);
        chatRoomOutput.close();
        chatRoomInput.close();

        System.exit(0);


        ServerCommunicationSingleton.getInstance().close();
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void setNotRunning(){
        isRunning = false;
    }
}
