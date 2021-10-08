package org.src.ObjetBorne.Menu_Produit.Produit;

import java.util.ArrayList;
import java.util.List;

import org.src.ObjetBorne.Menu_Produit.Ingredient.Ingredient;

public class Produits {
    private String nom;
    private List<Ingredient> liste_ingredient;

    /**
     * Constructeur de produit
     * @param nom String : nom produit
     * @param liste_ingredient List<Ingredient> : liste_d'ingredient
     */
    public Produits(String nom, List<Ingredient> liste_ingredient){
        this.nom = nom;
        this.liste_ingredient = new ArrayList<Ingredient>(liste_ingredient);
    }

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

    /**
     * Redéfinition de la méthode toString pour Produits
     */
    @Override
    public String toString(){
        return "Nom produits : " + this.nom + 
                ", Lists des ingrédients : " + 
                this.getListIngredients(); 
    }
}
