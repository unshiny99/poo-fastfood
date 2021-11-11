package org.src.ObjetBorne.Thread;

import org.src.ObjetBorne.Data.Data;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.src.ObjetBorne.Commande_Menu.Commande;

public class BorneCommandes extends Timer{
    private List<Commande> listeCommandes;

    public BorneCommandes(){this.listeCommandes = new ArrayList<Commande>();}

    /**
     * @param commande 
     * Ajout d'une commande dans la liste
     */
    public synchronized void addCommande(Commande commande){
        this.listeCommandes.add(commande);
        this.notifyAll(); // notification de tous les threads
        // System.out.println("[THREAD][Info] : Commande ajoutée à la file de préparation");
    }

    /**
     * gestion des commandes (threads)
     * @throws AWTException
     */
    public synchronized void prendreCommmande(Employer employer) throws InterruptedException, AWTException {
        while(this.listeCommandes.isEmpty()){
            // System.out.println("[thread][Info] : En attente : " + employer.getNom() + " " + employer.getPrenom());
            this.wait(5000); // tant que liste vide on attend
        }
        traiterCommande(this.listeCommandes.get(0), employer);
        // System.out.println("[THREAD][Info] : Commandre prise : " + employer.getNom() + " " + employer.getPrenom());
    }

    /**
     * @param commande
     * notification de la commande prête à l'utilisateur
     * @throws AWTException
     */
    public synchronized void retourCommande(Commande commande, Employer employer) throws AWTException {
        this.listeCommandes.remove(commande); // supprimer la liste des commandes
        // System.out.println("[THREAD][Info] : Commande terminée par : " + employer.getNom() + " " + employer.getPrenom());

        // code pour prévenir le client
        if(SystemTray.isSupported()){
            SystemTray tray = SystemTray.getSystemTray();

            Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
            
            TrayIcon trayIcon = new TrayIcon(image, "test");
            trayIcon.setImageAutoSize(true);
            trayIcon.setToolTip("Système notification PNG");
            tray.add(trayIcon);
            trayIcon.displayMessage("Client : " +
                                    commande.getClient().getId() +
                                    ", commande : " + commande.getId(),
                                    "prête", 
                                    MessageType.INFO);
        }else{
            System.out.println(Data.COLOR_GREEN + "Client " + commande.getClient().getId() + " : Commande " + commande.getId() + " prête" + Data.COLOR_RESET);
        }
    }

    /**
     * préparer une commande validée
     * @param commande
     * @throws AWTException
     */
    public void traiterCommande(Commande commande, Employer employer) throws InterruptedException, AWTException {
        UpdateTask updateTask = new UpdateTask(commande);
        schedule(updateTask, 0, 1000); // mettre à jour toutes les secondes
        updateTask = null;
        Thread.sleep((long) (commande.getTempsPreparation()*1000));
        // System.out.println("[THREAD][Info] : Commande Traitée par : " + employer.getNom() + " " + employer.getPrenom());
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

            if(tempsPasse >= this.commande.getTempsPreparation()) {
                this.commande.setStatus("Commande prête");
                this.cancel(); // arrêt du traitement des temps
            } else {
                this.commande.setStatus((int) (tempsPasse/this.commande.getTempsPreparation()*100) + "%");
                //System.out.println((int) (tempsPasse/this.commande.getTempsPreparation()*100) + "%");
            }
        }
    }
}
