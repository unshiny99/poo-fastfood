package org.src.ObjetBorne.Commande_Menu.Menu;

import org.src.ObjetBorne.Commande_Menu.Menu.Ingredient.Ingredient;
import org.src.ObjetBorne.Commande_Menu.Menu.Ingredient.IngredientCuisable;
import org.src.ObjetBorne.Commande_Menu.Menu.Ingredient.IngredientInterface;

import java.util.ArrayList;
import java.util.List;

public class Menu implements ProduitInterface, IngredientInterface {
    private String nom;
    private List<Produit> produits;
    private double prix;
    private double tempsPreparation;

    /**
     * Constructeur de menu
     * @param nom : nom du menu
     * @param produits : liste des produits du menu
     * @param prix : prix du menu
     */
    public Menu(String nom, List<Produit> produits, double prix, double tempsPreparation) {
        this.nom = nom;
        this.produits = produits;
        this.prix = prix;
        this.tempsPreparation = tempsPreparation;
    }

    /**
     * Redéfinition de la méthode toString pour Menu
     */
    @Override
    public String toString(){
        return "Nom du menu : " + this.nom +
                ", Prix : " + this.prix +
                ", temps de préparation : " + 
                this.tempsPreparation;
    }


    /**
     * Afficher uniquement les produits non exclusif au menu
     */
    public void afficherProduitNonExclusif(){
        String res = null;
        for(Produit produit : this.produits){
            if(!produit.getIsExclusif()){
                res += "Nom du produit : " + produit.getName() +
                        ", Prix : " + produit.getPrix() +
                        "\n";
            }
            System.out.println(res);
        }
    }
    // Getter
    public String getName() {return this.nom;}

    public List<Produit> getProduits() {return this.produits;}

    public List<Produit> getAccompagnements() {
        List<Produit> accompagnements = new ArrayList<Produit>();
        for (Produit produit : this.produits) {
            if (produit.getType().equals("Accompagnement")) {
                accompagnements.add(produit);
            }
        }
        return accompagnements;
    }

    public String getAccompagnementsName() {
        String accompagnements = "";
        for (Produit produit : this.produits) {
            if (produit.getType().equals("Accompagnement")) {
                accompagnements += produit.getName() + " ";
            }
        }
        return accompagnements;
    }

    public List<Produit> getBoissons() {
        List<Produit> boissons = new ArrayList<Produit>();
        for (Produit produit : this.produits) {
            if (produit.getType().equals("Boisson")) {
                boissons.add(produit);
            }
        }
        return boissons;
    }

    public Double getPrix() {return this.prix;}

    public Double getTempsPreparation() {
        Double tempsPreparation = this.tempsPreparation;
        for (Produit produit : produits) {
            tempsPreparation += produit.getTempsPreparation();
        }
        return tempsPreparation;
    }
}
