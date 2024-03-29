package org.src.ObjetBorne.Client;

import java.util.ArrayList;
import java.util.List;

import org.src.ObjetBorne.Commande_Menu.Commande;

public class Client {
    private static Integer idClient = 1;
    private Integer id;
    private String nom, prenom;
    private List<Commande> liste_commande;

    /**
     * Constructeur de client
     * @param nom String : nom du client
     * @param prenom String : prenom du client
     */
    public Client(String nom, String prenom){
        this.id = idClient++;
        this.nom = nom;
        this.prenom = prenom;
        this.liste_commande = new ArrayList<Commande>();
    }

    // Getter

    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public Integer getId(){return this.id;}
    public static Integer getIdClient() {
        return idClient;
    }
    public List<Commande> getListe_commande() {
        return liste_commande;
    }

    /**
     * Redifinition de la méthode toString
     */
    @Override
    public String toString(){
        return "Id : " + this.id +
                ", nom : " + this.nom +
                ", prenom : " + this.prenom;
    }

    /**
     * Ajout d'une commande 
     * @param commande Commande du client
     */
    public void addCommande(Commande commande){
        this.liste_commande.add(commande);
    }
}
