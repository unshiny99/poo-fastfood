package org.src.ObjetBorne.Commande_Menu.Menu.Ingredient;

public class Ingredient implements IngredientInterface {
    private String nom;

    /**
     * Constructeur d'ingrédient
     * @param nom String : nom de l'ingrédient
     */
    public Ingredient(String nom){this.nom = nom;}

    /**
     * Redéfinition de la méthode toString
     */
    @Override
    public String toString(){return "Nom de l'ingrédient : " + this.nom;}

    // Getter
    public String getName(){return this.nom;}
}
