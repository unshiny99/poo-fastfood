package org.src;

import org.src.ObjetBorne.Commande;

import java.util.List;

public class Employe extends Thread {
    private List<Commande> commandes;

    public Employe(List<Commande> commandes) {
        this.commandes = commandes;
    }

    // chaque employé va avoir une liste de commande qu'il va exécuter séquentiellement
    public void run() {

    }
}
