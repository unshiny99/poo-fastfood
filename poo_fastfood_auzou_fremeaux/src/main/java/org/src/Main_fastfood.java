package org.src;
import org.src.ObjetBorne.Data;
// import org.src.ObjetBorne.Menu.Menu;

public class Main_fastfood{
    public static void main(String[] args){
        System.out.println("FIS'eat");

        Data.GenerateData();

        // System.out.println("##### DEBUG #####");
        // for(Menu menu : Data.getListeMenu()){
        //     System.out.println(menu);
        //     System.out.println(menu.getProduits());
        // }
        // System.out.println("##########");

        Borne borne_1 = new Borne(1, Data.getListeMenu(), Data.getListeClient(), Data.getListeProduits());
        borne_1.runBorne();
    }
}