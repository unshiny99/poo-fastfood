package org.src.ObjetBorne.Thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.src.Commande.Commande;

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
    }

    /**
     * 
     * @param commande
     */
    public void traiterCommande(Commande commande, Employer employer) throws InterruptedException {
        UpdateTask updateTask = new UpdateTask(commande);
        schedule(updateTask, 0, 1000);
        updateTask = null;
        Thread.sleep((long) (commande.getTempsPreparation()*1000));
        // System.out.println("[Info] : Commande Traitée par : " + employer.getNom() + " " + employer.getPrenom());
        this.retourCommande(commande, employer);
    }

    private class UpdateTask extends TimerTask {
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
            double tempsPasse = (currentDate.getTime()-this.date.getTime())/1000; // temps en secondes depuis le début de la préparation de commande
            if(tempsPasse >= this.commande.getTempsPreparation()) {
                this.commande.setStatus("Commande prête");
                this.cancel(); // arrêt du traitement des temps
            } else {
                this.commande.setStatus((int) (tempsPasse/this.commande.getTempsPreparation()*100) + "%");
                // System.out.println((int) (tempsPasse/this.commande.getTempsPreparation()*100) + "%");
            }
        }
    }
}
