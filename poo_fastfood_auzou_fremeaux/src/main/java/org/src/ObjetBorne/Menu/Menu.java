package org.src.ObjetBorne.Menu;

import java.util.List;

public class Menu {
    private String nom;
    private List<Produit> produits;
    private double prix;

    /**
     * Constructeur de menu
     * @param nom : nom du menu
     * @param produits : liste des produits du menu
     * @param prix : prix du menu
     */
    public Menu(String nom, List<Produit> produits, double prix) {
        this.nom = nom;
        this.produits = produits;
        this.prix = prix;
    }

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
}
