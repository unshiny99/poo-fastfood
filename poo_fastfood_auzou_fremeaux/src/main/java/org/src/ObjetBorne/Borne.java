package org.src.ObjetBorne;

import java.util.*;

import org.src.Commande.Commande;
import org.src.Commande.Menu.Menu;
import org.src.Commande.Menu.Produit;
import org.src.ObjetBorne.Client.Client;
import org.src.ObjetBorne.Thread.BorneCommandes;

public class Borne {
    private Integer id;
    private List<Menu> liste_menu;
    private List<Client> liste_client;
    private List<Produit> liste_produits;
    private Scanner scanner;
    private BorneCommandes borneCommandes;

    /**
     * Constructeur de Borne
     * @param id Integer : id de la borne
     */
    public Borne(Integer id, List<Menu> liste_menu, List<Client> liste_client, List<Produit> liste_produits, BorneCommandes borneCommandes){
        this.id = id;
        this.liste_client = liste_client;
        this.liste_menu = liste_menu;
        this.liste_produits = liste_produits;
        this.scanner = new Scanner(System.in);
        this.borneCommandes = borneCommandes;
    }

    /**
     * Affiche le menu de séléction sur le terminal
     */
    public void afficherMenuPrincipal() {
        System.out.println("\n1 Menus");
        System.out.println("2 Compléments");
        System.out.println("3 Afficher panier");
        System.out.println("4 Valider panier");
        System.out.println("5 Mes commandes");
        System.out.println("6 Quitter\n");
    }

    /**
     * Gestion d'une commande sur une borne
     * @param client Client : le client faisant la commande
     */
    public void gererCommande(Client client) {
        Commande commande = new Commande(client);
        Integer choix, nb = 1;
        Integer ajout = 0, ajout2, ajout3;
        boolean flagAnnuler = true;

        do {
            afficherMenuPrincipal();
            choix = this.scanner.nextInt();
            switch (choix) {
                /*
                Liste des menus
                 */
                case 1:
                    this.separation();
                    for (Menu menu : this.liste_menu) {
                        System.out.println(nb + " : " + menu);
                        ++nb;
                    }
                    this.separation();
                    nb = 1;
                    System.out.println("Faites un choix ? (Taper 0 pour revenir en arrière)");
                    ajout = this.scanner.nextInt();
                    if(ajout > 0 && ajout-1 < this.liste_menu.size()) {
                        System.out.println(this.liste_menu.get(ajout-1));

                        List<Produit> accompagnements = this.liste_menu.get(ajout-1).getAccompagnements();
                        List<Produit> boissons = this.liste_menu.get(ajout-1).getBoissons();

                        Produit accompagnement = null;
                        Produit boisson = null;
                        // affichage des choix d'accompagnements du menu
                        int nbAccomp = 1;
                        this.separation();
                        for (Produit produit : accompagnements) {
                            System.out.println(nbAccomp + " : " + produit.getNom());
                            nbAccomp++;
                        }
                        this.separation();

                        boolean pass = false;

                        do{
                            System.out.println("Faites un choix ? (Taper 0 pour annuler)");
                            ajout2 = this.scanner.nextInt();
                            if(ajout2-1 > accompagnements.size() || ajout2 < 0){
                                System.out.println("Numéro non valide");
                            }else if(ajout2 == 0){
                                pass = true;
                            }else{
                                pass = true;
                            }
                        }while(!(pass));

                        if (ajout2 == 0 || ajout2 > accompagnements.size()){
                            break;
                        }else{
                            accompagnement = this.liste_menu.get(ajout-1).getAccompagnements().get(ajout2-1);
                            System.out.println("Produit ajouté : " + accompagnement.getNom());
                        }

                        int nbBoissons = 1;
                        this.separation();
                        for (Produit produit : boissons) {
                            System.out.println(nbBoissons + " : " + produit.getNom());
                            nbBoissons++;
                        }
                        this.separation();

                        pass = false;

                        do{
                            System.out.println("Faites un choix ? (Taper 0 pour annuler)");
                            ajout3 = this.scanner.nextInt();
                            if(ajout3-1 > boissons.size() || ajout3 < 0){
                                System.out.println("Numéro non valide");
                            }else if(ajout3 == 0){
                                pass = true;
                            }else{
                                pass = true;
                            }
                        }while(!(pass));

                        if (ajout3 == 0 || ajout3 > boissons.size()){
                            break;
                        }else{
                            boisson = this.liste_menu.get(ajout-1).getBoissons().get(ajout3-1);
                            System.out.println("Produit ajouté : " + boisson.getNom());
                        }

                        commande.addElt(this.liste_menu.get(ajout-1));
                        commande.addFreeElt(accompagnement, boisson);
                    }else if(ajout.equals(0)) {
                        break;
                    }else{
                        System.out.println("Erreur de saisie !");
                    }
                    break;
                /*
                Liste des compléments (ou produits)
                 */
                case 2:
                    this.separation();
                    for(Produit produit : this.liste_produits){
                        System.out.println(nb + " : " + produit.getAffichage());
                        ++nb;
                    }
                    this.separation();
                    nb = 1;
                    System.out.println("Faites un choix ? (Taper 0 pour revenir en arrière)");
                    ajout = this.scanner.nextInt();
                    if(ajout > 0 && ajout-1 < this.liste_produits.size()){
                        commande.addElt(this.liste_produits.get(ajout-1));
                        System.out.println("Vous avez choisi : " + this.liste_produits.get(ajout-1).getNom());
                    }else if(ajout.equals(0)) {
                        break;
                    }else{
                        System.out.println("Erreur de saisie !");
                    }
                    break;
                case 3:
                    this.affichagePannier(commande, ajout);
                    if(ajout == 0) {break;}
                    break;
                case 4:
                    this.validationCommande(commande, client);
                    break;
                case 5:
                    this.affichageAllCommandeClient(client);
                    break;
                case 6:
                    this.quitterApplication(commande, flagAnnuler);
                    break;
                /*
                Cas par défaut (choix incorrect)
                 */
                default:
                    System.out.println("Merci de faire un choix correct");
                    break;
            }
        } while (choix != 6 || !flagAnnuler);
    }

    /**
     * Lancement du programme principal de la borne
     */
    public void runBorne(){
        Integer id = null;
        do {
            System.out.println("Identifiez-vous : ");
            try {
                id = this.scanner.nextInt();
                if(this.verifIdentifiantCLient(id)){
                    Client client = this.liste_client.get(id-1);
                    System.out.format("Bonjour %s %s\n",
                            client.getPrenom(),
                            client.getNom());
                    System.out.println("Vous pouvez commencer votre commande !");
                    gererCommande(client);
                }else {
                    // si identifiant inconnu
                    System.out.println("Identifiant client inconnu !");
                }
            } catch (InputMismatchException e) {
                // si le client donne autre chose qu'un entier
                System.out.println("Merci d'entrer un identifiant correct.");
                scanner.next();
            }
        } while(true); // tant que pas d'id correct attribué
    }

    /**
     * Validation de la commande (passage dans liste des commande et vidage du panier)
     * @param commande Commande du client
     * @param client Client 
     */
    public void validationCommande(Commande commande, Client client){
        if (commande.getSize()==0) {
            System.out.println("Impossible de valider un panier vide !");
        } else {
            client.addCommande(commande);
            borneCommandes.addCommande(commande);
            System.out.println("Commande validée");

            // fonction d'ajout de la commande pour le client donné
            //ajouterCommandeJSON("historique.json",commande);
        }
        commande.viderAll();
    }

    /**
     * Quitter l'application
     * @param commande Commande du client
     * @param flagAnnuler Boolean 
     */
    public void quitterApplication(Commande commande, Boolean flagAnnuler){
        if (commande.getSize() != 0) {
            System.out.println("Souhaitez-vous vraiment annuler la commande en cours (O/n) ?");
            try {
                String annuler = this.scanner.next();
                if (annuler.equals("n")) {
                    flagAnnuler=false;
                    System.out.println("Retour commande");
                } else {
                    flagAnnuler=true;
                    System.out.println("Au revoir");
                }
            } catch (InputMismatchException e) {
                System.out.println("Choix non correct");
            }
        }
    }

    /**
     * Affichage du panier (et suppression éventuelle d'un article)
     * @param commande Commande du client
     * @param ajout Integer : choix du client
     */
    public void affichagePannier(Commande commande, Integer ajout){
        if (commande.getSize() == 0) {System.out.println("Votre panier est vide !");}
        else {
            this.separation();
            commande.listerAll();
            System.out.println("Coût total de la commande : " + commande.getPrixAll() +
                                "\nTemps total de préparation : " + commande.getTempsPreparation());
            this.separation();

            do {
                System.out.println("Supprimer un élément ? (Taper 0 pour revenir en arrière)");
                try {
                    ajout = scanner.nextInt();
                    if (ajout > 0 && ajout - 1 < commande.getSize()) {
                        commande.removeElt(ajout - 1);
                    } else if (ajout != 0){
                        System.out.println("Erreur de saisie !");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Merci d'entrer un nombre !");
                    scanner.next();
                }
            } while (ajout < 0 || ajout > commande.getSize());
        }
    }

    /**
     * Afficher toutes les commandes d'un client (et leur état)
     * @param client Client
     */
    public void affichageAllCommandeClient(Client client){
        client.afficherCommandes();
        // try {
        //     // lit à partir du fichier json fourni en paramètre
        //     this.afficherHistorique(client.getId());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
    }

    /**
     * Vérification si un client existe
     * @param id Integer : numéro client
     * @return Boolean : true ou false si le client existe
     */
    public Boolean verifIdentifiantCLient(Integer id){
        for(Client client : this.liste_client){
            if(client.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    // champ de séparation pour l'affichage (esthétique)
    private void separation(){System.out.println("############################################################");}
}
