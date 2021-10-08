package org.src.ObjetBorne.Menu_Produit.Ingredient;

public class IngredientCuisable extends Ingredient{
    private Boolean cuit;
    private Double time_cuisson;
    private String type_cuisson;

    /**
     * Constructeur d'ingrédient avec cuisson
     * @param nom String : nom de l'ingrédient
     * @param type_cuisson String : type de cuisson
     * @param time_cuisson Double : temps de cuisson
     */
    public IngredientCuisable(String nom, String type_cuisson, Double time_cuisson){
        super(nom);
        this.type_cuisson = type_cuisson;
        this.time_cuisson = time_cuisson;
    }

    /**
     * Redéfinition de la méthode toString
     */
    @Override
    public String toString(){
        return "Nom de l'ingrédient : " + this.getName() +
                ", Type de cuisson : "  + this.type_cuisson +
                ", Temps de cuisson : " + this.time_cuisson;
    }

    // Getter
    public String getName(){return super.getName();}
    public String getTypeCuisson(){return this.type_cuisson;}
    
    public Double getTimeCuisson(){return this.time_cuisson;}

    public Boolean cuit(){return this.cuit;}
}
