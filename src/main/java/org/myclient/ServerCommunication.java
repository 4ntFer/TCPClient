package org.myclient;

import chatRoom.ChatRoom;

import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ServerCommunication {
    private Socket serverSocket;
    private ObjectOutputStream  output;
    private ObjectInputStream input;

    public ServerCommunication(Socket server){
        this.serverSocket = server;


        try {
            input = new ObjectInputStream(server.getInputStream());
            output = new ObjectOutputStream(server.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void initChat(){
        putMenssage("CHAT");
        new ChatRoom(input, output);
    }

    private void putMenssage(String string){
        try {
            output.flush();
            output.writeObject(string);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public WebFile getFile(){
        String name;
        double size;
        byte[] content;
        byte[] hash;


        putMenssage("ARQUIVO");

        /*
        * No TCP os dados são recebidos na ordem em
        * que são enviados. Esse trecho demenstra
        * exatamente isso.
        */
        try {
            name = (String)input.readObject();
            size = (double)input.readObject();
            content = (byte[])input.readObject();
            hash = (byte[])input.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        putMenssage("RECEBIDO");

        return new WebFile(name, size, content, hash);



        /*try {


            //Recebendo e desserializando o arquivo
            byte[] objectAsByte = (byte[])input.readObject();

            return fileDesserialize(objectAsByte);
        } catch (IOException e) {
            System.out.println("Erro ao receber o arquivo!");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao receber o arquivo!");
            throw new RuntimeException(e);
        }*/
    }

    public static boolean hashVerifition(WebFile webFile) throws NoSuchAlgorithmException {
        byte[] currentStateHash = webFile.calcHash();
        if(Arrays.equals(currentStateHash,webFile.getHash())){
            return true;
        }else{
            return false;
        }
    }

    public void close() {
        putMenssage("SAIR");
        // Aguarda a resposta do servidor para fechar o programa
        try {
            input.readObject();
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
