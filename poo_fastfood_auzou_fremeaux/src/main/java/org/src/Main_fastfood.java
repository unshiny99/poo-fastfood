package org.src;

import org.src.ObjetBorne.Borne;
import org.src.ObjetBorne.Data.Data;
import org.src.ObjetBorne.Json.JsonEdit;
import org.src.ObjetBorne.Thread.BorneCommandes;
import org.src.ObjetBorne.Thread.Employer;

public class Main_fastfood {
    public static void main(String[] args) {
        System.out.println('________ ___  ________  _______   ________  _________   
                    |\  _____\\  \|\   ____\|\  ___ \ |\   __  \|\___   ___\ 
                    \ \  \__/\ \  \ \  \___|\ \   __/|\ \  \|\  \|___ \  \_| 
                    \ \   __\\ \  \ \_____  \ \  \_|/_\ \   __  \   \ \  \  
                    \ \  \_| \ \  \|____|\  \ \  \_|\ \ \  \ \  \   \ \  \ 
                    \ \__\   \ \__\____\_\  \ \_______\ \__\ \__\   \ \__\
                        \|__|    \|__|\_________\|_______|\|__|\|__|    \|__|
                                    \|_________|');

        Data.GenerateData();
        
        JsonEdit.initJSON("./src/main/java/org/src/ObjetBorne/Data/HistoriqueCommandes.json");

        BorneCommandes borneCommandes = new BorneCommandes();

        Employer employer_1 = new Employer(0, "Dupond", "Employer", borneCommandes);
        Employer employer_2 = new Employer(0, "Bernard", "Employer", borneCommandes);
        Employer employer_3 = new Employer(0, "Maxime", "Employer", borneCommandes);

        Borne borne_1 = new Borne(1, Data.getListeMenu(),
                Data.getListeClient(),
                Data.getListeProduits(),
                borneCommandes);

        employer_1.start();employer_2.start();employer_3.start();
        borne_1.runBorne();
    }
}