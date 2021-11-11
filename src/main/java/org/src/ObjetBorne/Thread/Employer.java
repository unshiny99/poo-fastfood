package org.src.ObjetBorne.Thread;

public class Employer extends Thread {
    private Integer id;
    private String nom;
    private String prenom;
    private BorneCommandes borneCommandes;

    /**
     * Constructeur de l'employé (thread) 
     * @param id Id : identifiant unique de l'employé
     * @param nom Nom : nom de l'employé
     * @param prenom Prenom : Prénom de l'employé 
     * @param borneCommandes Instance de borneCommandes
     */
    public Employer(Integer id, String nom, String prenom, BorneCommandes borneCommandes){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.borneCommandes = borneCommandes;
    }

    public void run(){
        try{
            // Gestion des commandes
            this.borneCommandes.prendreCommmande(this);
            sleep(1000); // attente indicative (1s)
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public long getId(){return this.id;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
}
