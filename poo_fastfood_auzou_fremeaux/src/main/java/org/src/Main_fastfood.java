package org.src;

import java.util.List;

import org.src.ObjetBorne.Commande;
import org.src.ObjetBorne.Data;
import org.src.ObjetBorne.Menu.Menu;

public class Main_fastfood{
    public static void main(String[] args){
        System.out.println("FIS'eat");

        Data.GenerateData();

        System.out.println(Data.getListeClient());

        Borne borne_1 = new Borne(1, Data.getListeMenu(), Data.getListeClient());
        borne_1.runBorne();
    }
}