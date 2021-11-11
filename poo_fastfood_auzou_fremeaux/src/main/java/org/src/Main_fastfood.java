package org.src;

import org.src.GUI.Gui_javafx;
import org.src.ObjetBorne.Borne;
import org.src.ObjetBorne.Data.Data;
import org.src.ObjetBorne.Json.JsonEdit;
import org.src.ObjetBorne.Thread.BorneCommandes;
import org.src.ObjetBorne.Thread.Employer;

public class Main_fastfood extends Gui_javafx{
    public static void main(String[] args) {
        Main_fastfood main_fastfood = new Main_fastfood();

        System.out.println("----------" + Data.COLOR_GREEN + "FIS'eat" + Data.COLOR_RESET + "----------");

        main_fastfood.setOnGUI(args);

        Data.GenerateData();
        JsonEdit.initJSON("./poo_fastfood_auzou_fremeaux/src/main/java/org/src/ObjetBorne/Data/HistoriqueCommandes.json");

        BorneCommandes borneCommandes = new BorneCommandes();

        Employer employer_1 = new Employer(0, "Dupond", "Employer", borneCommandes);
        Employer employer_2 = new Employer(0, "Bernard", "Employer", borneCommandes);
        Employer employer_3 = new Employer(0, "Maxime", "Employer", borneCommandes);

        Borne borne_1 = new Borne(1, Data.getListeMenu(),
                Data.getListeClient(),
                Data.getListeProduits(),
                borneCommandes);

        employer_1.start();employer_2.start();employer_3.start();

        try{
            borne_1.runBorne();
        }catch(Exception e){
            System.out.println(Data.COLOR_BLUE + "Merci de votre visite!" + Data.COLOR_RESET);
        }
    }
}
