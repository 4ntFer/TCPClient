package org.myclient;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class FileMenu extends ConsoleMenu{
    private static final int SPECS_OPTION = 1;
    private static final int SAVEFILE_OPTION = 2;
    private static final int VERIFYHASH_OPTION = 3;
    private static final int BACK_OPTION = 4;
    private WebFile webFile;

    public FileMenu (MenuManager menuManager){
        this.menuManager = menuManager;
    }

    public void print() {
        if(user_option == 1){
            System.out.println(ANSI_GREEN_BACKGROUND + "1 - Especificações" + ANSI_RESET);
        }else{
            System.out.println("1 - Especificações");
        }

        if(user_option == 2){
            System.out.println(ANSI_GREEN_BACKGROUND + "2 - Salvar arquivo" + ANSI_RESET);
        }else{
            System.out.println("2 - Salvar arquivo");
        }

        if(user_option == 3){
            System.out.println(ANSI_GREEN_BACKGROUND + "3 - Verificar Hash" + ANSI_RESET);
        }else{
            System.out.println("3 - Verificar Hash");
        }

        if(user_option == 4){
            System.out.println(ANSI_GREEN_BACKGROUND + "4 - Voltar" + ANSI_RESET);
        }else{
            System.out.println("4 - Voltar");
        }

        System.out.println(ANSI_GREEN + "0 - ENTER" + ANSI_RESET);
    }

    public void setFile(WebFile webFile){
        this.webFile = webFile;
    }

    private void printWebFileSpecs(){
        MenuManager.clear();
        System.out.println("Nome do arquivo: " + webFile.getName());
        System.out.println("Tamanho do arquivo: " + webFile.getSize() + " kb");
        System.out.println("Hash recebido: " + webFile.getHashToString());
    }

    @Override
    protected void enter() {
        switch (user_option){
            case SPECS_OPTION:
                printWebFileSpecs();

                break;

            case SAVEFILE_OPTION:
                try {
                    saveFile();
                } catch (IOException e) {
                    MenuManager.clear();
                    System.out.println("Erro ao salvar o arquivo!");
                    throw new RuntimeException(e);
                }


                break;

            case VERIFYHASH_OPTION:
                printHashVerifition();

                break;

            case BACK_OPTION:
                menuManager.setMenu(MenuManager.ENTER_OPTION);

                break;

        }


    }

    private void saveFile() throws IOException {
        FileOutputStream fileOutputStream;

        fileOutputStream = new FileOutputStream("downloads/extremely_important_file.mp4");
        fileOutputStream.write(webFile.getContent());
        fileOutputStream.close();

    }

    private void printHashVerifition() {
        try {
            boolean verification = ServerCommunication.hashVerifition(webFile);


            MenuManager.clear();
            System.out.print("HASH STATUS: ");
            if(verification){
                System.out.println("OK");
            }else{
                System.out.println("NOK");
                System.out.println("O hash calculado difere do recebido pelo servidor.");
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Falha ao verificar o Hash!");
            throw new RuntimeException(e);
        }
    }

}
