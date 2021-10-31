package org.src;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.src.ObjetBorne.Commande;
import org.src.ObjetBorne.Data;
import org.src.ObjetBorne.Menu.Menu;
import org.src.ObjetBorne.Menu.Produit;
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
        Integer choix, ajout = 0;
        Integer nb = 1;
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
                        int ajout2;
                        boolean pass = false;

                        do{
                            System.out.println("Faites un choix ? (Taper 0 pour annuler)");
                            ajout2 = this.scanner.nextInt();
                            //commande.addFreeElt(accompagnement); // ajoute le produit dans le panier (et non dans le menu directement)
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

                        int ajout3;
                        pass = false;

                        do{
                            System.out.println("Faites un choix ? (Taper 0 pour annuler)");
                            ajout3 = this.scanner.nextInt();
                            //commande.addFreeElt(accompagnement); // ajoute le produit dans le panier (et non dans le menu directement)
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

                        // faire cela uniquement si on arrive jusqu'ici
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
                /*
                Affichage du panier (et suppression éventuelle d'un article)
                 */
                case 3:
                    if (commande.getSize() == 0) {
                        System.out.println("Votre panier est vide !");
                    }
                    else {
                        this.separation();
                        commande.listerAll();
                        System.out.println("Coût total de la commande : " + commande.getPrixAll());
                        System.out.println("Temps total de préparation : " + commande.getTempsPreparation());
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
                        if(ajout == 0) {
                            break;
                        }
                    }
                    break;
                /*
                Validation de la commande (passage dans liste des commande et vidage du panier)
                 */
                case 4:
                    if (commande.getSize()==0) {
                        System.out.println("Impossible de valider un panier vide !");
                    } else {
                        client.addCommande(commande);
                        borneCommandes.addCommande(commande);
                        System.out.println("Commande validée");

                        // fonction d'ajout de la commande pour le client donné
                        ajouterCommandeJSON("historique.json",commande);
                    }
                    commande.viderAll();
                    break;
                /*
                Afficher toutes les commandes d'un client (et leur état)
                 */
                case 5:
                    //client.afficherCommandes();
                    try {
                        // lit à partir du fichier json fourni en paramètre
                        this.afficherHistorique(client.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                /*
                Quitter l'application
                 */
                case 6:
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
                //e.printStackTrace();
                System.out.println("Merci d'entrer un identifiant correct.");
                scanner.next();
            }
        } while(true); // tant que pas d'id correct attribué
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

    public static Object readJsonSimpleDemo(String filename) throws IOException, ParseException {
        FileReader reader = null;
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    public void parseComplementObject(JSONObject complement) {
        // on affiche les infos des compléments
        String prix = (String) complement.get("prix");
        String type = (String) complement.get("type");
        String nom = (String) complement.get("nom");
        System.out.println("\tComplément " + nom + " , type : " + type + " , prix : " + prix);
    }

    public void parseContenuObject(JSONObject contenu) {
        // on affiche les infos des produits
        String type = (String) contenu.get("type");
        String nom = (String) contenu.get("nom");
        System.out.println("\t\t" + nom + " (" + type + ")");
    }

    public void parseMenuObject(JSONObject menu) {
        // on affiche les infos des menus
        String prix = (String) menu.get("prix");
        String nom = (String) menu.get("nom");
        System.out.println("\tMenu " + nom + " , prix : " + prix);

        // puis leurs produits
        JSONArray contenus = (JSONArray) menu.get("contenus");
        if (contenus != null)
            contenus.forEach(contenu -> parseContenuObject((JSONObject)contenu));
    }

    /**
     * récupérer les données des objets json "commande"
     * @param commande
     */
    public void parseCommandeObject(JSONObject commande) {
        // on affiche les infos des commandes
        String date = (String) commande.get("date");
        String prix = (String) commande.get("prix");
        System.out.println("Commande du " + date + " - Montant : " + prix + "€");

        // puis leurs articles
        JSONArray menus = (JSONArray) commande.get("menus");
        if (menus != null)
            menus.forEach(menu -> parseMenuObject((JSONObject) menu));
        JSONArray complements = (JSONArray) commande.get("complements");
        if (complements != null)
            complements.forEach(complement -> parseComplementObject((JSONObject) complement));
    }

    public void parseClientObject(JSONObject client, String idClient) {
        // on affiche les id des clients et on récupère celui qui nous intéresse
        String idClientJson = (String) (client.get("idClient"));
        if (Objects.equals(idClient, idClientJson)) {
            JSONArray commandes = (JSONArray) client.get("commandes");

            // pour chaque commande du client, on la liste
            if (commandes != null)
                commandes.forEach(commande -> parseCommandeObject((JSONObject) commande));
        }
    }

    public void afficherHistorique(Integer idClient) {
        JSONArray liste = null;
        try {
            liste = (JSONArray) readJsonSimpleDemo("historique.json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (liste != null)
            liste.forEach(client -> parseClientObject((JSONObject) client, idClient.toString()));
    }

    public JSONArray getHistoriqueClient(String idClient) {
        JSONArray liste = null;
        try {
            liste = (JSONArray) readJsonSimpleDemo("historique.json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        //JSONArray commandes = getClientObject(idClient.toString());
        for (Object client : liste) {
            JSONObject clientObj = (JSONObject) client;
            String idClientJson = (String) (clientObj.get("idClient"));
            if (Objects.equals(idClient, idClientJson)) {
                return (JSONArray) clientObj.get("commandes");
            }
        }
        System.out.println("Client non trouvé !");
        return null;
    }

    public JSONArray getHistoriqueComplet() {
        JSONArray liste = null;
        try {
            liste = (JSONArray) readJsonSimpleDemo("historique.json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return liste;
    }

    /**
     * ajout d'une commande dans le fichier de sauvegarde
     * @param commande
     */
    public void ajouterCommandeJSON(String nomFic, Commande commande) {

        JSONObject commandeObj = new JSONObject();
        commandeObj.put("date",String.valueOf(commande.getDate()));
        commandeObj.put("prix",String.valueOf(commande.getPrix()));
        for (Object element : commande.getElements()) {
            if (element instanceof Menu) {
                JSONArray menus = new JSONArray();
                JSONObject menu = new JSONObject();
                menu.put("nom",((Menu) element).getNom());
                menu.put("prix",String.valueOf(((Menu) element).getPrix()));
                menu.put("contenus",((Menu) element).getAccompagnements());
                for (Produit p : ((Menu) element).getAccompagnements()) {
                    JSONArray produits = new JSONArray();
                    JSONObject produit = new JSONObject();
                    produit.put("nom",p.getNom());
                    produit.put("type",p.getType());
                    produits.add(produit);
                }
                menus.add(menu);
                commandeObj.put("menus", menus);
            }
            if (element instanceof Produit) {
                JSONArray complements = new JSONArray();
                JSONObject complement = new JSONObject();
                complement.put("nom",((Produit) element).getNom());
                complement.put("type",((Produit) element).getType());
                complement.put("prix",String.valueOf(((Produit) element).getPrix()));
                complements.add(complement);
                commandeObj.put("complements", complements);
            }
        }

        JSONArray historique = getHistoriqueComplet();

        for (JSONObject client : (Iterable<JSONObject>) historique) {
            String idClient = (String) client.get("idClient");
            if (Objects.equals(idClient, commande.getClient().getId().toString())) {
                JSONArray commandes = getHistoriqueClient(commande.getClient().getId().toString());
                //récupérer le tableau de commandes d'un client donné
                commandes.add(commandeObj);
                System.out.println("La commande a été ajouté dans l'historique");
                client.put("commandes",commandes);
            }
        }

        // écriture du fichier JSON
        try (FileWriter file = new FileWriter(nomFic)) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(historique.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // champ de séparation pour l'affichage (esthétique)
    private void separation(){System.out.println("############################################################");}
}
