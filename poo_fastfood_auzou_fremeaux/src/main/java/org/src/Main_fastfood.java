package org.src;

import java.util.ArrayList;
import java.util.List;

import org.src.ObjetBorne.Commande;
import org.src.ObjetBorne.Data;
import org.src.ObjetBorne.Menu.Menu;

public class Main_fastfood{
    public static void main(String[] args){
        System.out.println("FIS'eat");

        List<Menu> liste_Menu = new ArrayList<Menu>();
        List<Client> liste_client = new ArrayList<Client>();

        Data.GenerateData(liste_Menu, liste_client);

        Borne borne_1 = new Borne(1, liste_Menu, liste_client);
        borne_1.runBorne();
    }
}