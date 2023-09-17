package org.myclient;

public abstract class ConsoleMenu {

    protected static final String ANSI_GREEN_BACKGROUND =	"\u001B[42m";
    protected static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN = "\u001B[32m";

    protected int user_option = 0;
    protected int optionsLen;
    protected MenuManager menuManager;

    public abstract void print();

    protected abstract void enter();

    public int getUser_option(){
        return user_option;
    }

    public int getOptionsLen(){
        return  optionsLen;
    }

    public  void setOption(int op){
        user_option = op;
    }
}
