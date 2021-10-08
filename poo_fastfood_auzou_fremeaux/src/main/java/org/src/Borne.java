package org.src;

import java.util.*;

import org.src.ObjetBorne.Commande;
import org.src.ObjetBorne.Menu.Menu;
import org.src.ObjetBorne.Menu.Produit;

public class Borne {
    private Integer id; 
    private List<Menu> liste_menu;
    private List<Client> liste_client;
    private Scanner scanner;
    
    /**
     * Constructeur de Borne
     * @param id Integer : id de la borne
     */
    public Borne(Integer id, List<Menu> liste_menu, List<Client> liste_client){
        this.id = id;
        this.liste_client = liste_client;
        this.liste_menu = liste_menu;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Affiche le menu de séléction sur le terminal
     */
    public void afficherMenuPrincipal() {
        System.out.println("\n1 Menus");
        System.out.println("2 Compléments");
        System.out.println("3 Valider panier");
        System.out.println("4 Mes commandes");
        System.out.println("5 Annuler et quitter\n");
    }

    /**
     * Gestion d'une commande sur une borne
     * @param client Client : le client faisant la commande
     */
    public void gererCommande(Client client) {
        Commande commande = new Commande(client);
        Scanner sc = new Scanner(System.in);
        Integer choix = 0;

        do {
            afficherMenuPrincipal();
            choix = sc.nextInt(); // lire le code
            switch (choix) {
                case 1:
                    //System.out.println("on affichera les menus ici");
                    for (Menu m : liste_menu) {
                        System.out.println(m);
                    }
                    break;
                case 2:
                    System.out.println("on affichera les compléments ici");
                    break;
                case 3:
                    System.out.println("on affichera le temps de la commande et son statut");
                    break;
                case 4:
                    System.out.println("on affichera la liste des commandes");
                    break;
                case 5:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Merci de faire un choix correct");
                    break;
            }
        } while (choix!=5);
    }

    /**
     * Lancement du programme de la borne
     */
    public void runBorne(){
        Integer id = null;
        String lettre;
        do {
            System.out.println("Identifiez-vous : ");
            try {
                id = this.scanner.nextInt();
                if(this.verifIdentifiantCLient(id)){
                    Client client = this.liste_client.get(id-1);
                    System.out.format("Bonjour %s %s\n",
                                        client.getPrenom(),
                                        client.getNom(),
                                        "Vous pouvez commencer votre commande !");
                    gererCommande(client);
                }else { 
                    // si le client donne autre chose qu'un entier
                    System.out.println("Identifiant client inconnu !");
                    id=null;

                    try {
                        System.out.println("Quitter ? (o/N)");
                        lettre=this.scanner.next();

                        if (lettre.equals("o")) {return;} // on quitte le programme}
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                System.out.println("Merci d'entrer un identifiant correct.");
                scanner.next();
            }
        } while(id==null); // tant que pas d'id correct attribué
        this.scanner.close();
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
}
