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

    public String afficherCommande() {
        return "date=" + date + ", statut=" + statut + ", prix=" + prix + ", tempsPreparation=" + tempsPreparation;
    }

    public void addElt(Menu menu) {
        this.elements.add(menu);
        this.tempsPreparation += menu.getTempsPreparation();
        this.prix += menu.getPrix();
    }
    public void addElt(Produit produit) {
        this.elements.add(produit);
        this.tempsPreparation += produit.getTempsPreparation();
        this.prix += produit.getPrix();
    }

    public void removeElt(int i) {
        Object o = this.elements.get(i);
        if (o instanceof Menu) {
            this.tempsPreparation -= ((Menu) o).getTempsPreparation();
            this.prix -= ((Menu) o).getPrix();
        }
        if (o instanceof Produit) {
            this.tempsPreparation -= ((Produit) o).getTempsPreparation();
            this.prix -= ((Produit) o).getPrix();
        }
        this.elements.remove(i);
    }

    public void listerAll() {
        int nb=1;
        for(Object element : this.elements){
            if (element instanceof Produit)
                System.out.println(nb + " : " + ((Produit) element).getAffichage());
            else
                System.out.println(nb + " : " + element);
            nb++;
        }
    }

    public void viderAll() {
        this.elements.clear();
    }

    public void setStatus(String preparation){this.statut = preparation;}

    public Integer getSize(){return this.elements.size();}
    public Double getPrixAll(){return this.prix;}
    public Double getTempsPreparation(){return this.tempsPreparation;}
}