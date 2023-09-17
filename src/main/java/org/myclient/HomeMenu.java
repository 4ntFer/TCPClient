package org.myclient;

import chatRoom.ChatRoom;

public class HomeMenu extends ConsoleMenu{

    private static final int FILE_OPTION = 1;
    private static final int EXIT_OPTION = 3;
    private static final int CHAT_OPTION  = 2;
    public HomeMenu(MenuManager menuManager){
        this.menuManager = menuManager;
    }
    @Override
    public void print() {

        if(user_option == 1){
            System.out.println(ANSI_GREEN_BACKGROUND + "1 - Arquivo" + ANSI_RESET);
        }else{
            System.out.println("1 -Arquivo");
        }

        if(user_option == 2){
            System.out.println(ANSI_GREEN_BACKGROUND + "2 - Chat" + ANSI_RESET);
        }else{
            System.out.println("2 - Chat");
        }

        if(user_option == 3){
            System.out.println(ANSI_GREEN_BACKGROUND + "3 - Sair" + ANSI_RESET);
        }else{
            System.out.println("3 - Sair");
        }

        System.out.println(ANSI_GREEN + "0 - ENTER" + ANSI_RESET);
    }

    @Override
    protected void enter() {
        ServerCommunication serverCommunication = ServerCommunicationSingleton.getInstance();
        switch (user_option){
            case FILE_OPTION:
                menuManager.getFileMenu().setFile(serverCommunication.getFile());
                menuManager.setMenu(MenuManager.FILE_MENU);


                break;

            case CHAT_OPTION:
                serverCommunication.initChat();

                break;

            case EXIT_OPTION:
                serverCommunication.close();


                break;
        }
    }
}
