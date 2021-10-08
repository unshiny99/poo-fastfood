package org.src.ObjetBorne;

public class Ingredient {
    private Boolean cuit;
    private Double time_cuisson;
    private String nom, type_cuisson;

    /**
     * Constructeur d'ingrédient avec cuisson
     * @param nom String : nom de l'ingrédient
     * @param type_cuisson String : type de cuisson
     * @param time_cuisson Double : temps de cuisson
     */
    public Ingredient(String nom, String type_cuisson, Double time_cuisson){
        this.cuit = true;
        this.nom = nom;
        this.type_cuisson = type_cuisson;
        this.time_cuisson = time_cuisson;
    }

    /**
     * Constructeur d'ingrédient sans cuisson
     * @param nom String : nom de l'ingrédient
     */
    public Ingredient(String nom){
        this.nom = nom;
        this.cuit = false;
        this.type_cuisson = null;
        this.time_cuisson = null;
    }

    /**
     * Redéfinition de la méthode toString
     */
    @Override
    public String toString(){
        if(this.cuit){
            return "Nom de l'ingrédient : " + this.nom +
                    ", Type de cuisson : "  + this.type_cuisson +
                    ", Temps de cuisson : " + this.time_cuisson;
        }else{
            return "Nom de l'ingrédient : " + this.nom;       
        }
    }

    //Getter

    public String getName(){return this.nom;}
    public String getTypeCuisson(){return this.type_cuisson;}
    
    public Double getTimeCuisson(){return this.time_cuisson;}

    public Boolean cuit(){return this.cuit;}
}
