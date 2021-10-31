package org.src.ObjetBorne.Data;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.src.Commande.Menu.Menu;
import org.src.Commande.Menu.Produit;
import org.src.Commande.Menu.Ingredient.Ingredient;
import org.src.Commande.Menu.Ingredient.IngredientCuisable;
import org.src.ObjetBorne.Client.Client;

public class Data {

    private static List<Menu> liste_menu;
    private static List<Client> liste_client;
    private static List<Produit> liste_produits_non_exlusif;

    /**
     * Génération des données pour la borne
     */
    public static void GenerateData(){
        
        Data.liste_client = new ArrayList<Client>(Arrays.asList(
            new Client("Client_1_nom", "Client_1_prenom"),
            new Client("Client_2_nom", "Client_2_prenom"),
            new Client("Client_3_nom", "Client_3_prenom")
        ));

        List<Produit> liste_produits_master_montagnard = new ArrayList<Produit>(Arrays.asList(
            new Produit("Master Montagnard", "Hamburger", true, 
                new ArrayList<Ingredient>(Arrays.asList(
                    new Ingredient("BUN Brioché"), 
                    new Ingredient("Sauce crème échalotte"),
                    new Ingredient("Cornichon"),
                    new Ingredient("Oignons Caramélisés"),
                    new Ingredient("Oignons"),
                    new IngredientCuisable("Bacon", "fumé", 2.0),
                    new IngredientCuisable("Viande de boeuf", "Saignant", 3.5)
                )),5.0),
            new Produit("Salade", "Accompagnement", false),
            new Produit("King Fries Cheese & Bacon", "Accompagnement", false),
            new Produit("King Fries Cheese & Crispy Onions" , "Accompagnement", false),
            new Produit("Moyennes frites", "Accompagnement", false),
            new Produit("Onion Rings (6)", "Accompagnement", false),
            new Produit("Coca-Cola Sans Sucres® (40cl)", "Boisson", false),
            new Produit("Coca-Cola® (40cl)", "Boisson", false),
            new Produit("Fanta® Goût Original sans sucres (40cl)", "Boisson", false)
        ));

        List<Produit> liste_produits_master_cantal = new ArrayList<Produit>(Arrays.asList(
            new Produit("Master Cantal", "Hamburger", true,
                new ArrayList<Ingredient>(Arrays.asList(
                    new Ingredient("Cantal AOP"),
                    new Ingredient("Salade roquette"),
                    new Ingredient("Sauce à la moutarde à l'ancienne"),
                    new Ingredient("Oignons Frits"),
                    new Ingredient("Oignons Caramélisés"),
                    new Ingredient("BUN"),
                    new IngredientCuisable("Viande de boeuf", "Saignant", 3.5)
                )),3.0),
            new Produit("Salade", "Accompagnement", false),
            new Produit("King Fries Cheese & Bacon", "Accompagnement", false),
            new Produit("King Fries Cheese & Crispy Onions" , "Accompagnement", false),
            new Produit("Moyennes frites", "Accompagnement", false),
            new Produit("Onion Rings (6)", "Accompagnement", false),
            new Produit("Coca-Cola Sans Sucres® (40cl)", "Boisson", false),
            new Produit("Coca-Cola® (40cl)", "Boisson", false),
            new Produit("Fanta® Goût Original sans sucres (40cl)", "Boisson", false)
        ));

        List<Produit> liste_produits_wrap_crousty_chevre = new ArrayList<Produit>(Arrays.asList(
            new Produit("Wrap crousty chèvre", "Wrap", true, 
                new ArrayList<Ingredient>(Arrays.asList(
                    new Ingredient("Crousty Chèvre"),
                    new Ingredient("Tomate"),
                    new Ingredient("Tortilla"),
                    new Ingredient("Oignons Frits"),
                    new Ingredient("Sauce Cajun"),
                    new Ingredient("Salade")
                )),1.5),
            new Produit("Salade", "Accompagnement", false),
            new Produit("King Fries Cheese & Bacon", "Accompagnement", false),
            new Produit("King Fries Cheese & Crispy Onions" , "Accompagnement", false),
            new Produit("Moyennes frites", "Accompagnement", false),
            new Produit("Onion Rings (6)", "Accompagnement", false),
            new Produit("Coca-Cola Sans Sucres® (40cl)", "Boisson", false),
            new Produit("Coca-Cola® (40cl)", "Boisson", false),
            new Produit("Fanta® Goût Original sans sucres (40cl)", "Boisson", false)
        ));

        Data.liste_menu = new ArrayList<Menu>(Arrays.asList(
            new Menu("Master Montagnard", liste_produits_master_montagnard, 14, 5.5),
            new Menu("Master Cantal", liste_produits_master_cantal, 16, 3.5),
            new Menu("Wrap Crousty Chèvre", liste_produits_wrap_crousty_chevre, 10, 2)
        ));

        Data.liste_produits_non_exlusif = new ArrayList<Produit>(Arrays.asList(
            new Produit("Salade", "Accompagnement", 2.0 ,false, 2.0),
            new Produit("King Fries Cheese & Bacon", "Accompagnement", 3.0 ,false, 4.0),
            new Produit("King Fries Cheese & Crispy Onions" , "Accompagnement", 3.5, false, 5.0),
            new Produit("Moyennes frites", "Accompagnement", 2.0 ,false, 2.0),
            new Produit("Onion Rings (6)", "Accompagnement", 4.0 ,false, 6.0),
            new Produit("Coca-Cola Sans Sucres® (40cl)", "Boisson", 3.0, false, 1.0),
            new Produit("Coca-Cola® (40cl)", "Boisson", 2.5, false, 1.0),
            new Produit("Fanta® Goût Original sans sucres (40cl)", "Boisson", 3.5, false, 1.0)
        ));
    }

    public static List<Client> getListeClient(){return Data.liste_client;}
    public static List<Menu> getListeMenu(){return Data.liste_menu;}
    public static List<Produit> getListeProduits(){return Data.liste_produits_non_exlusif;}

    // /**
    //  * fonction d'écriture initiale du fichier JSON
    //  * @param nomFic
    //  * @throws Exception
    //  */
    // public static void ecrireJSON(String nomFic) {
    //     File f = new File(nomFic);

    //     if (!f.exists()) {
    //         System.out.println("Génération d'un fichier de sauvegarde...");
    //         JSONArray historiqueComplet = new JSONArray();

    //         for (Client currentClient : liste_client) {

    //             JSONArray commandes = new JSONArray();

    //             JSONObject client = new JSONObject();
    //             client.put("idClient",currentClient.getId().toString());
    //             client.put("commandes",commandes);

    //             historiqueComplet.add(client);
    //         }

    //         // écriture du fichier JSON
    //         try (FileWriter file = new FileWriter(nomFic)) {
    //             //We can write any JSONArray or JSONObject instance to the file
    //             file.write(historiqueComplet.toJSONString());
    //             file.flush();
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     } else {
    //         System.out.println("Importation de la sauvegarde...");
    //     }
    // }
}
