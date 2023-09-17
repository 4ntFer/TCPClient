package org.myclient;


import java.io.IOException;
import java.net.Socket;

public class ServerCommunicationSingleton {
    private static String ip = null;
    private static ServerCommunication instance = null;

    public static synchronized ServerCommunication getInstance(){
        if(instance == null){
            MenuManager.clear();
            try {
                instance = new ServerCommunication(new Socket(ip,4242));
                System.out.println("Conectado ao servidor.");
            } catch (IOException e) {
                MenuManager.clear();
                System.out.println("Não foi possivel conectar ao servidor.");
                throw new RuntimeException(e);
            }
        }

        return instance;
    }

    public static void setIP(String newIp){
        ip = newIp;
    }
}
