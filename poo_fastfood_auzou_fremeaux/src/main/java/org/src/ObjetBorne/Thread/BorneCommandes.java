package org.src.ObjetBorne.Thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.src.ObjetBorne.Commande_Menu.Commande;
import org.src.ObjetBorne.Json.JsonEdit;

public class BorneCommandes extends Timer{
    private List<Commande> listeCommandes;

    public BorneCommandes(){this.listeCommandes = new ArrayList<Commande>();}

    /**
     * @param commande 
     * Ajout d'une commande dans la liste
     */
    public synchronized void addCommande(Commande commande){
        this.listeCommandes.add(commande);
        notifyAll(); // notification de tous les threads
        // System.out.println("[Info] : Commande ajoutée à la file de préparation");
    }

    /**
     * gestion des commandes (threads)
     */
    public synchronized void prendreCommmande(Employer employer) throws InterruptedException {
        while(this.listeCommandes.isEmpty()){
            // System.out.println("[Info] : En attente : " + employer.getNom() + " " + employer.getPrenom());
            wait(); // tant que liste vide on attend
        }
        traiterCommande(this.listeCommandes.get(0), employer);
        notifyAll(); // notification de tous les threads
        //System.out.println("[Info] : Commandre prise");
    }

    /**
     * @param commande
     * notification de la commande prête à l'utilisateur
     */
    public synchronized void retourCommande(Commande commande, Employer employer) {
        this.listeCommandes.remove(commande); // supprimer la liste des commandes
        // System.out.println("[Info] : Commande terminée par : " + employer.getNom() + " " + employer.getPrenom());

        // code pour prévenir le client
        System.out.println("Commande " + commande.getId() + " pour le client" + commande.getClient().getId() + " prête");
    }

    /**
     * préparer une commande validée
     * @param commande
     */
    public void traiterCommande(Commande commande, Employer employer) throws InterruptedException {
        UpdateTask updateTask = new UpdateTask(commande);
        schedule(updateTask, 0, 1000); // mettre à jour toutes les secondes
        updateTask = null;
        Thread.sleep((long) (commande.getTempsPreparation()*1000));
        // System.out.println("[Info] : Commande Traitée par : " + employer.getNom() + " " + employer.getPrenom());
        this.retourCommande(commande, employer);
    }

    private static class UpdateTask extends TimerTask {
        private Commande commande;
        private Date date;

        public UpdateTask(Commande commande) {
            super();
            this.commande = commande;
            this.date = new Date(); // date d'origine
        }
        
        @Override
        public void run(){
            Date currentDate = new Date(); // date courante
            double tempsPasse = (currentDate.getTime()-this.date.getTime())/1000.0; // temps en secondes depuis le début de la préparation de commande
            /*
                TODO : remove DEBUG
             */
            System.out.println(this.commande.getStatut());

            if(tempsPasse >= this.commande.getTempsPreparation()) {
                this.commande.setStatus("Commande prête");

                this.cancel(); // arrêt du traitement des temps
            } else {
                this.commande.setStatus((int) (tempsPasse/this.commande.getTempsPreparation()*100) + "%");
                System.out.println((int) (tempsPasse/this.commande.getTempsPreparation()*100) + "%");
            }
            // todo : remove debug
            System.out.println(this.commande.getStatut());
        }
    }
}
