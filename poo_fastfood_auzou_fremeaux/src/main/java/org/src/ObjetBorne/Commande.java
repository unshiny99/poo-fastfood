package org.src.ObjetBorne;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.src.ObjetBorne.Menu.*;

public class Commande {
    private static int id = 1;
    private List<Menu> menus;
    private List<Produit> produits;
    private double tempsPreparation;
    private String statut;
    private Date date;
    private double prix;

    public Commande(double prix) {
        this.prix = prix;
        this.menus = new ArrayList<Menu>();
        this.produits = new ArrayList<Produit>();
        this.tempsPreparation = 0.0;
        this.statut = "En attente de validation";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.date = new Date();
    }

    @Override
    public String toString() {
        return "Commande{" +
                "menus=" + menus +
                ", produits=" + produits +
                ", tempsPreparation=" + tempsPreparation +
                ", statut='" + statut + '\'' +
                ", date=" + date +
                ", prix=" + prix +
                '}';
    }

    /*
    // test pour vérifier que la date est ok
    public static void main(String[] args) {
        Commande c = new Commande(10.0);
        System.out.println(c.toString());
    }
     */
}
