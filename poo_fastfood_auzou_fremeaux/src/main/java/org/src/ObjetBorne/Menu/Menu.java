package org.src.ObjetBorne.Menu;

import java.util.List;

public class Menu {
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

    // méthodes get et set des attributs
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getTempsPreparation() {
        return tempsPreparation;
    }

    public void setTempsPreparation(double tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }
}
