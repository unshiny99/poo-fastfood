package org.src;

import java.util.ArrayList;
import java.util.List;

import org.src.ObjetBorne.Commande;

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
     * Afficher les commandes du client
     */
    public void afficherCommandes(){
        for(Commande commande : this.liste_commande){
            System.out.println(commande.afficherCommande());
        }
        if (this.liste_commande.size() == 0) {
            System.out.println("Vous n'avez pas encore fait de commande chez nous.");
        }
    }

    /**
     * Ajout d'une commande 
     * @param commande Commande du client
     */
    public void addCommande(Commande commande){
        this.liste_commande.add(commande);
    }
}
