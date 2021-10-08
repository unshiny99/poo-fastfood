package org.src.ObjetBorne;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.src.Client;
import org.src.ObjetBorne.Menu.Menu;
import org.src.ObjetBorne.Menu.Produit;
import org.src.ObjetBorne.Menu.Ingredient.Ingredient;
import org.src.ObjetBorne.Menu.Ingredient.IngredientCuisable;

public class Data {

    private static List<Menu> liste_menu;
    private static List<Client> liste_client;

    /**
     * Generation des données pour la borne
     * @param liste_menu List<Menu> : liste des menus
     * @param liste_client List<Client> : liste des clients
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
                ))),
            new Produit("Salade", "Accompagnements", false),
            new Produit("King Fries Cheese & Bacon", "Accompagnements", false),
            new Produit("King Fries Cheese & Crispy Onions" , "Accompagnements", false),
            new Produit("Moyennes frites", "Accompagnements", false),
            new Produit("Onion Rings (6)", "Accompagnements", false),
            new Produit("Coca-Cola Sans Sucres® (40cl)", "Boissons", false),
            new Produit("Coca-Cola® (40cl)", "Buissons", false),
            new Produit("Fanta® Goût Original sans sucres (40cl.", "Buissons", false)
        ));

        List<Produit> liste_produits_master_cantal = new ArrayList<Produit>(Arrays.asList(
            new Produit("master cantal", "Hamburger", true, 
                new ArrayList<Ingredient>(Arrays.asList(
                    new Ingredient("Cantal AOP"),
                    new Ingredient("Salade roquette"),
                    new Ingredient("Sauce à la moutarde à l'ancienne"),
                    new Ingredient("Oignons Frits"),
                    new Ingredient("Oingons Caramélisés"),
                    new Ingredient("BUN"),
                    new IngredientCuisable("Viande de boeuf", "Saignant", 3.5)
                ))),
            new Produit("Salade", "Accompagnements", false),
            new Produit("King Fries Cheese & Bacon", "Accompagnements", false),
            new Produit("King Fries Cheese & Crispy Onions" , "Accompagnements", false),
            new Produit("Moyennes frites", "Accompagnements", false),
            new Produit("Onion Rings (6)", "Accompagnements", false),
            new Produit("Coca-Cola Sans Sucres® (40cl)", "Boissons", false),
            new Produit("Coca-Cola® (40cl)", "Buissons", false),
            new Produit("Fanta® Goût Original sans sucres (40cl.", "Buissons", false)
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
                ))),
            new Produit("Salade", "Accompagnements", false),
            new Produit("King Fries Cheese & Bacon", "Accompagnements", false),
            new Produit("King Fries Cheese & Crispy Onions" , "Accompagnements", false),
            new Produit("Moyennes frites", "Accompagnements", false),
            new Produit("Onion Rings (6)", "Accompagnements", false),
            new Produit("Coca-Cola Sans Sucres® (40cl)", "Boissons", false),
            new Produit("Coca-Cola® (40cl)", "Buissons", false),
            new Produit("Fanta® Goût Original sans sucres (40cl.", "Buissons", false)
        ));

        Data.liste_menu = new ArrayList<Menu>(Arrays.asList(
            new Menu("Master Montagnard", liste_produits_master_montagnard, 14, 5.5),
            new Menu("Master Cantal", liste_produits_master_cantal, 16, 3.5),
            new Menu("Wrap Crousty Chèvre", liste_produits_wrap_crousty_chevre, 10, 2)
        ));
    }

    public static List<Client> getListeClient(){return Data.liste_client;}
    public static List<Menu> getListeMenu(){return Data.liste_menu;}
}
