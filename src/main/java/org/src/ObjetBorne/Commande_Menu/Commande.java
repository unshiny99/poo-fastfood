package org.src.ObjetBorne.Commande_Menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.src.ObjetBorne.Client.Client;
import org.src.ObjetBorne.Commande_Menu.Menu.*;
import org.src.ObjetBorne.Json.JsonEdit;

public class Commande {
    private int id;
    private List<Object> elements;
    private List<ArrayList<Produit>> liste_produit_menu;
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
        this.liste_produit_menu = new ArrayList<ArrayList<Produit>>();
        Date date = new Date();
        this.date = dateFormat.format(date);
        this.prix = 0;
        this.tempsPreparation = 0.0;
        this.statut = "En préparation";
        this.client = client;
        this.setId();
    }

    /**
     * changer l'id d'une commande
     */
    public void setId() {
        JSONArray nbCommandes = JsonEdit.getHistoriqueClient(String.valueOf(this.client.getId()));
        if (nbCommandes==null) {
            this.id = 1;
        } else {
            this.id = nbCommandes.size() + 1;
        }
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

    public void addElt(Menu menu) {
        this.elements.add(menu);
        this.tempsPreparation += menu.getTempsPreparation();
        this.prix += menu.getPrix();
    }

    /**
     * ajout d'un produit en supplément
     * @param produit le produit à ajouter
     */
    public void addElt(Produit produit) {
        this.elements.add(produit);
        this.tempsPreparation += produit.getTempsPreparation();
        this.prix += produit.getPrix();
    }

    /**
     * ajout d'un élément inclus dans le menu (gratuit)
     * @param produit le produit à ajouter sans supplément
     */
    public void addFreeElt(Produit produit, Produit produit2) {
        this.liste_produit_menu.add(new ArrayList<Produit>(Arrays.asList(produit, produit2)));
    }

    /**
     * retirer un élément
     * @param i l'index de l'élément à retirer
     */
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

    /**
     * lister tous les éléments d'un menu ou d'un produit
     */
    public void listerAll() {
        int nb=1;
        for(Object element : this.elements){
            if (element instanceof Produit){
                System.out.println(nb + " : " + ((Produit) element).getAffichage());
            }else if(element instanceof Menu){
                System.out.println("------------------------------------------------------------");
                System.out.println(nb + " : " + element);
                System.out.println("Contenu : [ " + afficherProduitMenuCustom(nb-1) + "]");
                System.out.println("------------------------------------------------------------");
            }else{
                System.out.println(nb + " : " + element);
            }
            nb++;
        }
    }

    /**
     * affiche le contenu d'un menu
     * @param index
     * @return
     */
    public String afficherProduitMenuCustom(Integer index){
        String menu_custom = "";
        for(Produit produit : this.liste_produit_menu.get(index)){
            menu_custom += produit.getName() + ",";
        }
        return menu_custom;
    }

    /**
     * vider le panier
     */
    public void viderAll() {
        this.elements.clear();
    }

    /**
     * changer le statut de la commande
     * @param preparation le statut à écrire
     */
    public void setStatus(String preparation){this.statut = preparation;}

    /*
        liste des getters nécessaires
     */
    public Integer getSize(){return this.elements.size();}
    public Double getPrixAll(){return this.prix;}
    public Double getTempsPreparation(){return this.tempsPreparation;}

    public int getId() {
        return id;
    }

    public List<Object> getElements() {
        return elements;
    }

    public List<ArrayList<Produit>> getListe_produit_menu() {
        return liste_produit_menu;
    }

    public String getStatut() {
        return statut;
    }

    public String getDate() {
        return date;
    }

    public double getPrix() {
        return prix;
    }

    public Client getClient() {
        return client;
    }
}