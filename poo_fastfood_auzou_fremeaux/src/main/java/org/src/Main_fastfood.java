package org.src;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.src.ObjetBorne.Borne;
import org.src.ObjetBorne.Data.Data;
import org.src.ObjetBorne.Thread.BorneCommandes;
import org.src.ObjetBorne.Thread.Employer;

public class Main_fastfood{
    public static void main(String[] args) {
        System.out.println("---------- FIS'eat ----------");

        Data.GenerateData();

        // Vérification présence du fichier JsonEdit
        // System.out.println(System.getProperty("user.dir"));
        try{
            File json = new File("./src/main/java/org/src/ObjetBorne/Data/HistoriqueCommandes.json");
            if(!json.isFile()){
                System.out.println("Le fichier HistoriqueCommandes.json n'éxiste pas");
                FileWriter writer = new FileWriter(json);
                json.createNewFile();
                writer.write("{\n\n}");
                writer.flush();
                writer.close();
                System.out.println("Génération terminé");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        // Data.ecrireJSON("historique.json");

        BorneCommandes borneCommandes = new BorneCommandes();

        Employer employer_1 = new Employer(0, "Dupond", "Proba", borneCommandes);

        Borne borne_1 = new Borne(1, Data.getListeMenu(),
                Data.getListeClient(),
                Data.getListeProduits(),
                borneCommandes);

        employer_1.start();
        borne_1.runBorne();
    }
}