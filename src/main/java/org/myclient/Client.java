package org.myclient;

import org.myclient.MenuManager;
import org.myclient.ServerCommunicationSingleton;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    private static MenuManager menuManager;
    private static boolean inChat = false;

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        menuManager = new MenuManager();
        boolean flag = false;

        int teste = 0;

        System.out.print("Informe o IP do servidor: ");
        ServerCommunicationSingleton.setIP(scanner.nextLine());
        ServerCommunicationSingleton.getInstance();



        while(true){
            if(!Client.inChat()) {
                menuManager.clear();
                menuManager.update();
                menuManager.setOption(scanner.nextInt());
            }
        }
    }

    public static boolean inChat(){
        return inChat;
    }

    public static void setInChat(boolean val){
        inChat = val;
    }


    public static Scanner getScanner(){
        return scanner;
    }
}