package org.src.ObjetBorne.Commande_Menu.Menu;

import java.util.List;

import org.src.ObjetBorne.Commande_Menu.Menu.Ingredient.Ingredient;
import org.src.ObjetBorne.Commande_Menu.Menu.Ingredient.IngredientCuisable;
import org.src.ObjetBorne.Commande_Menu.Menu.Ingredient.IngredientInterface;

public class Produit implements ProduitInterface, IngredientInterface {
    private String nom, type;
    private Boolean isExclusif;
    private double tempsPreparation;
    private double prix;
    private List<Ingredient> liste_ingredient;

    /**
     * Constructeur de produit, avec ingrédients
     * @param nom String : nom produit
     * @param type String : type du produit
     * @param isExclusif Boolean : si le produit est exclusif à un menu
     * @param liste_ingredient List<Ingredient> : liste_d'ingredient
     */
    public Produit(String nom, String type, Boolean isExclusif, List<Ingredient> liste_ingredient, double tempsPreparation){
        this.nom = nom;
        this.type = type;
        this.isExclusif = isExclusif;
        this.liste_ingredient = liste_ingredient;
        this.tempsPreparation = tempsPreparation;
    }

        /**
     * Constructeur de produit, avec ingrédients et un prix
     * @param nom String : nom produit
     * @param type String : type du produit
     * @param isExclusif Boolean : si le produit est exclusif à un menu
     * @param prix Double : prix du produit
     * @param liste_ingredient List<Ingredient> : liste_d'ingredient
     */
    public Produit(String nom, String type, Double prix, Boolean isExclusif, List<Ingredient> liste_ingredient, double tempsPreparation){
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.isExclusif = isExclusif;
        this.liste_ingredient = liste_ingredient;
        this.tempsPreparation = tempsPreparation;
    }

    /**
     * Constructeur de produit, sans ingrédients
     * @param nom String : nom produit
     * @param type String : type du produit
     * @param isExclusif Boolean : si le produit est exclusif à un menu
     */
    public Produit(String nom, String type, Boolean isExclusif){
        this.nom = nom;
        this.type = type;
        this.isExclusif = isExclusif;
    }

    /**
     * Constructeur de produit, sans ingrédients avec un prix
     * @param nom String : nom produit
     * @param type String : type du produit
     * @param prix Double : prix du produit
     * @param isExclusif Boolean : si le produit est exclusif à un menu
     */
    public Produit(String nom, String type, Double prix, Boolean isExclusif, Double tempsPreparation){
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.isExclusif = isExclusif;
        this.tempsPreparation = tempsPreparation;
    }

    /**
     * Redéfinition de la méthode toString pour Produits
     */
    @Override
    public String toString(){
        return "Nom produit : " + this.nom +
                ", Type : " + this.type +
                ", Est exclusif : " + this.isExclusif +
                ", Liste des ingrédients : " +
                this.liste_ingredient;
    }

    // Getter

    /**
     * Retourne la liste des noms ingredients du produit
     * @return String : liste des noms d'ingrédients
     */
    public String getListIngredients(){
        String liste_string = null;
        for(Ingredient ingredient : this.liste_ingredient){
            liste_string += ingredient.getName() + "\n";
        }
        return liste_string;
    }

    public Boolean getIsExclusif(){return this.isExclusif;}

    public String getName(){return this.nom;}
    public Double getPrix(){return this.prix;}
    public String getAffichage(){return "Nom produit : " + this.nom + ", Prix : " + this.prix + ", tempsPreparation : " + this.tempsPreparation;}

    public String getType() {
        return type;
    }

    public Double getTempsPreparation() {
        if (this.liste_ingredient != null) {
            Double tempsPreparation = this.tempsPreparation;
            for (Ingredient ingredient : liste_ingredient) {
                if (ingredient instanceof IngredientCuisable) {
                    tempsPreparation += ((IngredientCuisable) ingredient).getTimeCuisson();
                }
            }
            return tempsPreparation;
        } else
            return this.tempsPreparation;
    }
}
