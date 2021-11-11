package org.src.ObjetBorne;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.src.ObjetBorne.Data.Data;

import org.src.ObjetBorne.Client.Client;
import org.src.ObjetBorne.Commande_Menu.Commande;
import org.src.ObjetBorne.Commande_Menu.Menu.Menu;
import org.src.ObjetBorne.Commande_Menu.Menu.Produit;
import org.src.ObjetBorne.Json.JsonEdit;
import org.src.ObjetBorne.Thread.BorneCommandes;

public class Borne {
    private Integer id;
    private Integer choix;
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
        this.choix = null;
    }

    /**
     * Affiche le menu de séléction sur le terminal
     */
    public void afficherMenuPrincipal() {
        System.out.println(Data.COLOR_BLUE +"\n1 Menus");
        System.out.println("2 Compléments");
        System.out.println("3 Afficher panier");
        System.out.println("4 Valider panier");
        System.out.println("5 Mes commandes");
        System.out.println(Data.COLOR_RED + "6 Quitter\n" + Data.COLOR_RESET);
    }

    /**
     * Gestion d'une commande sur une borne
     * @param client Client : le client faisant la commande
     */
    public void gererCommande(Client client) {
        Integer nb = 1;
        Integer ajout = 0, ajout2, ajout3;
        boolean flagAnnuler = true;
        Commande commande = null;

        do {
            afficherMenuPrincipal();
            this.choix = this.scanner.nextInt();
            switch (this.choix) {
                /*
                Liste des menus
                 */
                case 1:
                    if (commande == null)
                        commande = new Commande(client);

                    this.separation();
                    for (Menu menu : this.liste_menu) {
                        System.out.println(nb + " : " + menu);
                        ++nb;
                    }
                    this.separation();
                    nb = 1;
                    System.out.println(Data.COLOR_BLUE + "Faites un choix ? (Taper 0 pour revenir en arrière)" + Data.COLOR_RESET);
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
                            System.out.println(nbAccomp + " : " + produit.getName());
                            nbAccomp++;
                        }
                        this.separation();

                        boolean pass = false;

                        do{
                            System.out.println(Data.COLOR_BLUE + "Faites un choix ? (Taper 0 pour annuler)" + Data.COLOR_RESET);
                            ajout2 = this.scanner.nextInt();
                            if(ajout2 > accompagnements.size() || ajout2 < 0){
                                System.out.println(Data.COLOR_RED + "Numéro non valide" + Data.COLOR_RESET);
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
                            System.out.println(Data.COLOR_GREEN + "Produit ajouté : " + accompagnement.getName() + Data.COLOR_RESET);
                        }

                        int nbBoissons = 1;
                        this.separation();
                        for (Produit produit : boissons) {
                            System.out.println(nbBoissons + " : " + produit.getName());
                            nbBoissons++;
                        }
                        this.separation();

                        pass = false;

                        do{
                            System.out.println(Data.COLOR_BLUE + "Faites un choix ? (Taper 0 pour annuler)" + Data.COLOR_RESET);
                            ajout3 = this.scanner.nextInt();
                            if(ajout3 > boissons.size() || ajout3 < 0){
                                System.out.println(Data.COLOR_RED + "Numéro non valide" + Data.COLOR_RESET);
                            } else if(ajout3 == 0) {
                                pass = true;
                            } else {
                                pass = true;
                            }
                        }while(!(pass));

                        if (ajout3 == 0 || ajout3 > boissons.size()) {
                            break;
                        } else {
                            boisson = this.liste_menu.get(ajout-1).getBoissons().get(ajout3-1);
                            System.out.println(Data.COLOR_GREEN + "Produit ajouté : " + boisson.getName() + Data.COLOR_RESET);
                        }

                        commande.addElt(this.liste_menu.get(ajout-1));
                        commande.addFreeElt(accompagnement, boisson);
                    }else if(ajout.equals(0)) {
                        break;
                    }else{
                        System.out.println(Data.COLOR_RED + "Erreur de saisie !" + Data.COLOR_RESET);
                    }
                    break;
                /*
                Liste des compléments (ou produits)
                 */
                case 2:
                    if (commande == null)
                        commande = new Commande(client);

                    this.separation();
                    for(Produit produit : this.liste_produits){
                        System.out.println(nb + " : " + produit.getAffichage());
                        ++nb;
                    }
                    this.separation();
                    nb = 1;
                    System.out.println(Data.COLOR_BLUE + "Faites un choix ? (Taper 0 pour revenir en arrière)" + Data.COLOR_RESET);
                    ajout = this.scanner.nextInt();
                    if(ajout > 0 && ajout-1 < this.liste_produits.size()){
                        commande.addElt(this.liste_produits.get(ajout-1));
                        System.out.println(Data.COLOR_GREEN + "Vous avez choisi : " + this.liste_produits.get(ajout-1).getName() + Data.COLOR_RESET);
                    }else if(ajout.equals(0)) {
                        break;
                    }else{
                        System.out.println(Data.COLOR_RED + "Erreur de saisie !" + Data.COLOR_RESET);
                    }
                    break;
                case 3:
                    this.affichagePanier(commande, ajout);
                    if(ajout == 0) {break;}
                    break;
                case 4:
                    this.validationCommande(commande, client);
                    flagAnnuler=this.quitterApplication(commande);
                    //commande = new Commande(client); // on recrée une nouvelle instance de commande
                    commande = null;
                    break;
                case 5:
                    this.affichageAllCommandeClient(client);
                    break;
                case 6:
                    flagAnnuler=this.quitterApplication(commande);
                    break;
                /*
                Cas par défaut (choix incorrect)
                 */
                default:
                    System.out.println(Data.COLOR_RED + "Merci de faire un choix correct" + Data.COLOR_RESET);
                    break;
            }
        } while (this.choix != 6 || !flagAnnuler);
    }

    /**
     * Lancement du programme principal de la borne
     */
    public void runBorne(){
        Integer id = null;
        do {
            System.out.println(Data.COLOR_BLUE + "Identifiez-vous : " + Data.COLOR_RESET);
            try {
                id = this.scanner.nextInt();
                if(this.verifIdentifiantCLient(id)){
                    Client client = this.liste_client.get(id-1);
                    System.out.format(Data.COLOR_GREEN + "Bonjour :\n%s,%s\n" + Data.COLOR_RESET,
                            client.getPrenom(),
                            client.getNom());
                    System.out.println("\n" + Data.COLOR_GREEN + "Vous pouvez commencer votre commande !" + Data.COLOR_RESET);
                    gererCommande(client);
                }else {
                    // si identifiant inconnu
                    System.out.println(Data.COLOR_RED + "Identifiant client inconnu !" + Data.COLOR_RESET);
                }
            } catch (InputMismatchException e) {
                // si le client donne autre chose qu'un entier
                System.out.println(Data.COLOR_RED + "Merci d'entrer un identifiant correct." + Data.COLOR_RESET);
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
        if (commande==null || commande.getSize()==0) {
            System.out.println(Data.COLOR_RED + "Impossible de valider un panier vide !" + Data.COLOR_RESET);
        } else {
            client.addCommande(commande);
            borneCommandes.addCommande(commande);
            this.choix = 6;
            System.out.println(Data.COLOR_GREEN + "Commande validée" + Data.COLOR_RESET);

            // fonction d'ajout de la commande pour le client donné
            JsonEdit.ajouterCommandeJSON("./poo_fastfood_auzou_fremeaux/src/main/java/org/src/ObjetBorne/Data/HistoriqueCommandes.json",commande);
            System.out.println(Data.COLOR_BLUE + "Numéro de la commande : " + commande.getId() + Data.COLOR_RESET);
            commande.viderAll();
        }
    }

    /**
     * Quitter l'application
     * @param commande Commande du client
     */
    public Boolean quitterApplication(Commande commande){
        Boolean flagAnnuler = true;
        if (commande != null && commande.getSize() != 0) {
            System.out.println(Data.COLOR_BLUE + "Souhaitez-vous vraiment annuler la commande en cours (O/n) ?" + Data.COLOR_RESET);
            try {
                String annuler = this.scanner.next();
                if (annuler.equals("n")) {
                    flagAnnuler=false;
                    System.out.println(Data.COLOR_BLUE + "Retour commande" + Data.COLOR_RESET);
                } else {
                    flagAnnuler=true;
                    System.out.println(Data.COLOR_BLUE + "Au revoir" + Data.COLOR_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(Data.COLOR_RED + "Choix non correct" + Data.COLOR_RESET);
            }
        } else {
            System.out.println("Au revoir");
        }
        return flagAnnuler;
    }

    /**
     * Affichage du panier (et suppression éventuelle d'un article)
     * @param commande Commande du client
     * @param ajout Integer : choix du client
     */
    public void affichagePanier(Commande commande, Integer ajout){
        if (commande == null || commande.getSize() == 0) {System.out.println(Data.COLOR_RED + "Votre panier est vide !" + Data.COLOR_RESET);}
        else {
            this.separation();
            commande.listerAll();
            System.out.println(Data.COLOR_BLUE + "Coût total de la commande : " + Data.COLOR_RESET + commande.getPrixAll() +
                                "\n" + Data.COLOR_BLUE + "Temps total de préparation : " + Data.COLOR_RESET + commande.getTempsPreparation());
            this.separation();

            do {
                System.out.println(Data.COLOR_RED + "Supprimer un élément ? (Taper 0 pour revenir en arrière)" + Data.COLOR_RESET);
                try {
                    ajout = scanner.nextInt();
                    if (ajout > 0 && ajout - 1 < commande.getSize()) {
                        commande.removeElt(ajout - 1);
                    } else if (ajout != 0){
                        System.out.println(Data.COLOR_RED + "Erreur de saisie !" + Data.COLOR_RESET);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(Data.COLOR_RED + "Merci d'entrer un nombre !" + Data.COLOR_RESET);
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
        //client.afficherCommandes();

        // mise à jour des statuts de commande
        String nomFic = "./poo_fastfood_auzou_fremeaux/src/main/java/org/src/ObjetBorne/Data/HistoriqueCommandes.json";
        JsonEdit.updateStatut(client, nomFic);

        // lit à partir du fichier json fourni en paramètre
        JsonEdit.afficherHistorique(client.getId());
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
