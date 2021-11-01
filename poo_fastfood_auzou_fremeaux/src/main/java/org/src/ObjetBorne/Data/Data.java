package org.src.ObjetBorne.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.src.ObjetBorne.Client.Client;
import org.src.ObjetBorne.Commande_Menu.Menu.Menu;
import org.src.ObjetBorne.Commande_Menu.Menu.Produit;
import org.src.ObjetBorne.Commande_Menu.Menu.Ingredient.Ingredient;
import org.src.ObjetBorne.Commande_Menu.Menu.Ingredient.IngredientCuisable;

public class Data {

    private static List<Menu> liste_menu;
    private static List<Client> liste_client;
    private static List<Produit> liste_produits_non_exlusif;

    /**
     * Génération des données pour la borne
     */
    public static void GenerateData(){
        
        Data.liste_client = new ArrayList<Client>(Arrays.asList(
            new Client("Maxime", "Frémeaux"),
            new Client("Joe", "Dupond"),
            new Client("Marie", "Proba")
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
}
