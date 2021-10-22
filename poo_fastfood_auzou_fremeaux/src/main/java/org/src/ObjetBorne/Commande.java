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
    private List<Object> elements;
    //private List<Menu> menus;
    //private List<Produit> produits;
    private double tempsPreparation;
    private String statut;
    private String date;
    private double prix;
    private Client client;

    /**
     * Constructeur par défaut d'une commande
     */
    public Commande(Client client) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.elements = new ArrayList<Object>();
        Date date = new Date();
        this.date = dateFormat.format(date);
        this.prix = 0;
        this.id = idCommande++;
        this.tempsPreparation = 0.0;
        this.statut = "En préparation";
        this.client = client;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", elements=" + elements +
                ", tempsPreparation=" + tempsPreparation +
                ", statut='" + statut + '\'' +
                ", date='" + date + '\'' +
                ", prix=" + prix +
                ", client=" + client +
                '}';
    }

    public void addElt(Menu menu) { this.elements.add(menu);}
    public void addElt(Produit produit) { this.elements.add(produit);}

    public void removeElt(int i) {
        this.elements.remove(i);
    }

    public void listerAll() {
        int nb=1;
        for(Object element : this.elements){
            System.out.println(nb + " : " + element);
            nb++;
        }
    }

    public void viderAll() {
        this.elements.removeAll(elements);
    }

    public Integer getSize(){return this.elements.size();}

    // test pour vérifier que la date est ok
    public static void main(String[] args) {
        Client cli = new Client("Frémeaux","Maxime");
        Client cli2 = new Client("Auzou","Geoffrey");
        Commande c = new Commande(cli);
        Commande cbis = new Commande(cli);
        Commande c2 = new Commande(cli2);
        System.out.println(c);
        System.out.println(cbis);
        System.out.println(c2);
    }
}
