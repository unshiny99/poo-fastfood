package org.src;

import java.util.*;

import org.src.ObjetBorne.Commande;
import org.src.ObjetBorne.Menu.Menu;
import org.src.ObjetBorne.Menu.Produit;

public class Borne {
    private Integer id; 
    private List<Menu> liste_menu;
    private List<Client> liste_client;
    private List<Produit> liste_produits;
    private Scanner scanner;
    
    /**
     * Constructeur de Borne
     * @param id Integer : id de la borne
     */
    public Borne(Integer id, List<Menu> liste_menu, List<Client> liste_client, List<Produit> liste_produits){
        this.id = id;
        this.liste_client = liste_client;
        this.liste_menu = liste_menu;
        this.liste_produits = liste_produits;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Affiche le menu de séléction sur le terminal
     */
    public void afficherMenuPrincipal() {
        System.out.println("\n1 Menus");
        System.out.println("2 Compléments");
        System.out.println("3 Afficher Panier");
        System.out.println("4 Valider panier");
        System.out.println("5 Mes commandes");
        System.out.println("6 Annuler et quitter\n");
    }

    /**
     * Gestion d'une commande sur une borne
     * @param client Client : le client faisant la commande
     */
    public void gererCommande(Client client) {
        List<Object> panier = new ArrayList<Object>();
        Commande commande = new Commande(client);
        Scanner scanner_menu = new Scanner(System.in);
        Scanner scanner_choix = new Scanner(System.in);
        Integer choix, ajout = 0;
        Integer nb = 1;

        do {
            afficherMenuPrincipal();
            choix = scanner_menu.nextInt();
            switch (choix) {
                case 1:
                    this.separation();
                    for (Menu menu : this.liste_menu) {
                        System.out.println(nb + " : " + menu);
                        ++nb;
                    }
                    this.separation();
                    nb = 1;
                    System.out.println("Faîte un choix ? (Tapper 0 pour revenir en arrière)");
                    ajout = scanner_choix.nextInt();
                    if(ajout > 0 && ajout-1 < this.liste_menu.size()){
                        panier.add(this.liste_menu.get(ajout-1));
                    }else{
                        System.out.println("Erreur de saisi !");
                    }
                    break;
                case 2:
                    this.separation();
                    for(Produit produit : this.liste_produits){
                        System.out.println(nb + " : " + produit.getAffichage());
                        ++nb;
                    }
                    this.separation();
                    nb = 1;
                    System.out.println("Faîte un choix ? (Tapper 0 pour revenir en arrière)");
                    ajout = scanner_choix.nextInt();
                    if(ajout > 0 && ajout-1 < this.liste_produits.size()){
                        panier.add(this.liste_produits.get(ajout-1));
                    }else{
                        System.out.println("Erreur de saisi !");
                    }
                    break;
                    // List<Produit> complements = new ArrayList<Produit>();
                    // boolean found = false;
                    // for (Menu menu : liste_menu) {        
                    //     for(Produit produit : menu.getProduits()) {
                    //         if (!produit.getIsExclusif()) {
                    //             //System.out.println(complements.size());
                    //             for (Produit produit_save : complements) {
                    //                 if (produit.getNom().equals(produit_save.getNom())) {
                    //                     found = true;
                    //                     break;
                    //                 }
                    //             }
                    //         }
                    //         if (!found) {
                    //             complements.add(produit);
                    //             this.afficherCommandeClient(complements);
                    //         }
                    //     }
                    // }
                case 3:
                    this.separation();
                    for(Object object : panier){
                        System.out.println(object);
                    }
                    this.separation();
                    break;
                case 4:
                    System.out.println("Valider Panier");
                    break;
                case 5:
                    System.out.println("Mes commandes");
                    break;
                case 6:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("Merci de faire un choix correct");
                    break;
            }
        } while (choix!=6);
        scanner_choix.close();
        scanner_menu.close();
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
                        lettre = this.scanner.next();
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

    /**
     * Afficher la commande
     */
    public void afficherCommandeClient(List<Produit> liste){
        for(Produit commande : liste){
            System.out.println(commande.getNom());
        }
    }

    private void separation(){System.out.println("############################################################");}
}
