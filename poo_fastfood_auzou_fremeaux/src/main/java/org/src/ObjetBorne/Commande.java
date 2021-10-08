package org.src.ObjetBorne;

import java.util.ArrayList;
import java.util.List;

public class Commande {
    private List<Menu> liste_menu; 
    private List<Produits> liste_produits;

    /**
     * Constructeur par defaut, initialisation des listes
     */
    public Commande(){
        this.liste_menu = new ArrayList<Menu>();
        this.liste_produits = new ArrayList<Produits>();
    }
}
