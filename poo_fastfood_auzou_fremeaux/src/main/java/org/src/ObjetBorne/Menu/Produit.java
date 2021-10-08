package org.src.ObjetBorne.Menu;

import java.util.List;

import org.src.ObjetBorne.Menu.Ingredient.Ingredient;

public class Produit {
    private String nom, type;
    private Boolean isExclusif;
    private List<Ingredient> liste_ingredient;

    /**
     * Constructeur de produit, avec ingrédients
     * @param nom String : nom produit
     * @param type String : type du produit
     * @param Boolean Boolean : si le produit est exclusif à un menu
     * @param liste_ingredient List<Ingredient> : liste_d'ingredient
     */
    public Produit(String nom, String type, Boolean isExclusif, List<Ingredient> liste_ingredient){
        this.nom = nom;
        this.type = type;
        this.isExclusif = isExclusif;
        this.liste_ingredient = liste_ingredient;
    }

    /**
     * Constructeur de produit, sans ingrédients
     * @param nom String : nom produit
     * @param type String : type du produit
     * @param Boolean Boolean : si le produit est exclusif à un menu
     */
    public Produit(String nom, String type, Boolean isExclusif){
        this.nom = nom;
        this.type = type;
        this.isExclusif = isExclusif;
    }

    // Getter

    /**
     * Retourne la liste des noms ingredients du produit
     * @return String : liste des noms d'ingrédients
     */
    private String getListIngredients(){
        String liste_string = null;
        for(Ingredient ingredient : this.liste_ingredient){
            liste_string += ingredient.getName() + "\n";
        }
        return liste_string;
    }

    private Boolean getIsExclusif(){return this.isExclusif;}

    /**
     * Redéfinition de la méthode toString pour Produits
     */
    @Override
    public String toString(){
        return "Nom produits : " + this.nom +
                "Type : " + this.type +
                "Est exclusif : " + this.isExclusif +
                ", Lists des ingrédients : " + 
                this.liste_ingredient + "\n"; 
    }
}
