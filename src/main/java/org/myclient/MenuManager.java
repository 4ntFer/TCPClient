package org.myclient;


import java.io.File;
import java.util.ArrayList;

public class MenuManager{

    public static  final int ENTER_OPTION = 0;
    public static final int FILE_MENU = 1;
    public static final int HOME_MENU_INDEX = 0;

    private ArrayList<ConsoleMenu> menu = new ArrayList<ConsoleMenu>();
    private int currentMenu = HOME_MENU_INDEX;

    public MenuManager(){
        menu.add(new HomeMenu(this));
        menu.add(new FileMenu(this));
    }

    public void update(){
        menu.get(currentMenu).print();

    }

    public void setOption(int op){
        if(op == ENTER_OPTION){
            menu.get(currentMenu).enter();
        }
        menu.get(currentMenu).setOption(op);
    }

    public static void clear(){
        System.out.println("\n\n");
    }

    public void setMenu(int menu){
        currentMenu = menu;
    }

    public FileMenu getFileMenu(){
        return (FileMenu) menu.get(FILE_MENU);
    }

}
