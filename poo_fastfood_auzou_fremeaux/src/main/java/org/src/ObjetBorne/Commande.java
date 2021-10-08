package org.src.ObjetBorne;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.src.Client;
import org.src.ObjetBorne.Menu.*;

public class Commande {
    private static int idCommande = 1;
    private int id;
    private List<Menu> menus;
    private List<Produit> produits;
    private double tempsPreparation;
    private String statut;
    private String date;
    private double prix;
    private Client client;

    /**
     * Constructeur par défaut d'une commande
     */
    public Commande(Client client) {
        this.prix = 0;
        this.id = idCommande++;
        this.menus = new ArrayList<Menu>();
        this.produits = new ArrayList<Produit>();
        this.tempsPreparation = 0.0;
        this.statut = "En attente de validation";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        this.date=dateFormat.format(date);
        this.client = client;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", menus=" + menus +
                ", produits=" + produits +
                ", tempsPreparation=" + tempsPreparation +
                ", statut='" + statut + '\'' +
                ", date='" + date + '\'' +
                ", prix=" + prix +
                ", client=" + client +
                '}';
    }

    // test pour vérifier que la date est ok
    public static void main(String[] args) {
        Client cli = new Client("Frémeaux","Maxime");
        Commande c = new Commande(cli);
        Commande cbis = new Commande(cli);
        Client cli2 = new Client("Auzou","Geoffrey");
        Commande c2 = new Commande(cli2);
        System.out.println(c);
        System.out.println(cbis);
        System.out.println(c2);
    }
}
