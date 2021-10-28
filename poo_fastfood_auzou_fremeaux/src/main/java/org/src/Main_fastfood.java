package org.src;
import org.src.ObjetBorne.Data;
// import org.src.ObjetBorne.Menu.Menu;
import org.src.ObjetBorne.Thread.BorneCommandes;
import org.src.ObjetBorne.Thread.Employer;

public class Main_fastfood{
    public static void main(String[] args){
        System.out.println("FIS'eat");

        Data.GenerateData();

        BorneCommandes borneCommandes = new BorneCommandes();

        Employer employer_1 = new Employer(0, "Dupond", "Proba", borneCommandes);

        Borne borne_1 = new Borne(1, Data.getListeMenu(),
                Data.getListeClient(),
                Data.getListeProduits(),
                borneCommandes);

        employer_1.start();
        borne_1.runBorne(); // démarrage de la borne (application principale)
    }
}