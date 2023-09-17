package org.myclient;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WebFile implements Serializable {
    private String name;
    private double size;
    private  byte[] content;
    private byte[] hash;

    public WebFile(String name, double size, byte[] content, byte[] hash){
        this.name = name;
        this.size = size;
        this.content = content;
        this.hash = hash;
    }

    public String toString(){
        return name;
    }

    public byte[] getContent() {
        return content;
    }

    public double getSize() {
        return size;
    }

    public String getName(){
        return name;
    }

    public byte[] calcHash() throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

        messageDigest.update(content);
        return messageDigest.digest();
    }

    // método retirado da internet
    public String getHashToString(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            int parteAlta = ((hash[i] >> 4) & 0xf) << 4;
            int parteBaixa = hash[i] & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }

    public byte[] getHash() {
        return hash;
    }

    public File fileDesserialize(){

        try {
            ByteArrayInputStream byteArrayInput = new ByteArrayInputStream(content);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInput);
            File file = (File)objectInputStream.readObject();

            byteArrayInput.close();
            objectInputStream.close();

            return file;
        } catch (IOException e) {
            System.out.println("ERRO NA DESSERUALIZAÇÃO DO ARQUIVO");
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("ERRO NA DESSERUALIZAÇÃO DO ARQUIVO");
            throw new RuntimeException(e);
        }

    }
}
