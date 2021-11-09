package org.src.ObjetBorne.Json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.src.ObjetBorne.Client.Client;
import org.src.ObjetBorne.Commande_Menu.Commande;
import org.src.ObjetBorne.Commande_Menu.Menu.Menu;
import org.src.ObjetBorne.Commande_Menu.Menu.Produit;
import org.src.ObjetBorne.Data.Data;

public class JsonEdit {
    /**
     * fonction d'écriture initiale du fichier JSON
     * @param nomFic nom du fichier
     */
    public static void initJSON(String nomFic) {
        File json = new File(nomFic);
        try{
            if (!json.exists()) {
                System.out.println("Génération d'un fichier de sauvegarde...");
                
                JSONArray historiqueComplet = new JSONArray();
                for (Client currentClient : Data.getListeClient()) {

                    JSONArray commandes = new JSONArray();
                    JSONObject client = new JSONObject();
                    
                    client.put("idClient", String.valueOf(currentClient.getId()));
                    client.put("commandes", commandes);
                    historiqueComplet.add(client);
                }
                // écriture du fichier JSON
                FileWriter writer = new FileWriter(nomFic);
                
                //We can write any JSONArray or JSONObject instance to the file
                writer.write(historiqueComplet.toJSONString());
                writer.flush();
                writer.close();
            } else {
                System.out.println("Importation de la sauvegarde...");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

     /**
     * ajout d'une commande dans le fichier de sauvegarde
     * @param nomFic nom du fichier
     * @param commande commande courante
     */
    public static void ajouterCommandeJSON(String nomFic, Commande commande) {
        JSONObject commandeObj = new JSONObject();
        commandeObj.put("idCommande",String.valueOf(commande.getId()));
        commandeObj.put("date",String.valueOf(commande.getDate()));
        commandeObj.put("prix",String.valueOf(commande.getPrix()));
        commandeObj.put("statut",commande.getStatut());

        JSONArray menus = new JSONArray();
        JSONArray complements = new JSONArray();
        for (Object element : commande.getElements()) {
            // si c'est un menu (avec des produits)
            if (element instanceof Menu) {
                JSONObject menu = new JSONObject();
                menu.put("nom",((Menu) element).getNom());
                menu.put("prix",String.valueOf(((Menu) element).getPrix()));

                JSONArray produits = new JSONArray();
                for (ArrayList<Produit> p : commande.getListe_produit_menu()) {
                    for (Produit produit : p) {
                        JSONObject produitObj = new JSONObject();
                        produitObj.put("nom", produit.getNom());
                        produitObj.put("type", produit.getType());
                        produits.add(produitObj);
                    }
                }
                menu.put("contenus",produits);
                menus.add(menu);
            }
            // si c'est un complément
            if (element instanceof Produit) {
                JSONObject complement = new JSONObject();
                System.out.println(element);
                    complement.put("nom",((Produit) element).getNom());
                    complement.put("type",((Produit) element).getType());
                    complement.put("prix",String.valueOf(((Produit) element).getPrix()));
                    complements.add(complement);
            }
        }
        commandeObj.put("menus", menus);
        commandeObj.put("complements", complements);

        JSONArray historique = getHistoriqueComplet();

        for (JSONObject client : (Iterable<JSONObject>) historique) {
            String idClient = (String) client.get("idClient");
            if (Objects.equals(idClient, commande.getClient().getId().toString())) {
                // récupérer le tableau de commandes d'un client donné
                JSONArray commandes = getHistoriqueClient(commande.getClient().getId().toString());

                // mettre à jour le tableau de commandes (ajout)
                commandes.add(commandeObj);
                client.put("commandes",commandes);
            }
        }
        // écriture du fichier JSON
        try {
            FileWriter file = new FileWriter(nomFic);

            file.write(historique.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtenir l'historique complet de commandes (par client)
     * @return tableau de tous les clients
     */
    public static JSONArray getHistoriqueComplet() {
        JSONArray liste = null;
        try {
            liste = (JSONArray) readJsonSimpleDemo("./src/main/java/org/src/ObjetBorne/Data/HistoriqueCommandes.json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return liste;
    }

    /**
     * @param idClient identifiant client
     * @return tableau des commandes du client
     */
    public static JSONArray getHistoriqueClient(String idClient) {
        JSONArray liste = null;
        try {
            liste = (JSONArray) readJsonSimpleDemo("./src/main/java/org/src/ObjetBorne/Data/HistoriqueCommandes.json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        assert liste != null;
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

    /**
     * @param idCommande identifiant commande
     * @param idClient identifiant client
     * @return la commande dont les paramètres sont spécifiés
     */
    public static JSONObject getCommande(String idCommande, String idClient) {
        // must return JSONObject
        JSONArray commandes = JsonEdit.getHistoriqueClient(idClient);
        if(commandes != null){
            for (Object commande : commandes) {
            JSONObject commandeObj = (JSONObject) commande;
            String idCommandeJson = (String) (commandeObj.get("idCommande"));
            if (idCommandeJson.equals(idCommande)){
                return commandeObj;
                }
            }
        }
        return new JSONObject();
    }

    /**
     * mise à jour du statut d'une commande dans le fichier d'historique
     * @param client le client concerné
     * @param nomFic nom du fichier destination
     */
    public static void updateStatut(Client client, String nomFic) {
        JSONArray historique = getHistoriqueComplet();


        for (Object clientObj : historique) {
            JSONObject clientObject = (JSONObject) clientObj;
            if (clientObject.get("idClient").equals(String.valueOf(client.getId()))) {
                //historique.remove(clientObject);
                JSONArray commandes = getHistoriqueClient(String.valueOf(client.getId()));
                JSONObject commandeObj;
                for (Commande commande : client.getListe_commande()) {
                    //System.out.println(commande.getStatut());

                    commandeObj = JsonEdit.getCommande(String.valueOf(commande.getId()),String.valueOf(client.getId()));
                    commandes.remove(commandeObj);
                    commandeObj.put("statut",commande.getStatut()); // réécrire la valeur de l'objet

                    commandes.add(commandeObj);
                }
                clientObject.put("commandes",commandes);
            }
        }

        // écriture du fichier JSON
        try {
            FileWriter file = new FileWriter(nomFic);
            //We can write any JSONArray or JSONObject instance to the file
            file.write(historique.toJSONString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * afficher l'historique d'un client
     * @param idClient identifiant client
     */
    public static void afficherHistorique(Integer idClient) {
        JSONArray liste = null;
        try {
            liste = (JSONArray) readJsonSimpleDemo("./src/main/java/org/src/ObjetBorne/Data/HistoriqueCommandes.json");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        if (liste != null) {
            liste.forEach(client -> parseClientObject((JSONObject) client, idClient.toString()));
        }
    }

    /**
     * lire le fichier JSON spécifié
     * @param filename nom fichier
     * @return l'objet JSON
     * @throws IOException erreur entrée/sortie
     * @throws ParseException erreur parse
     */
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

    /**
     * parser les contenus d'un menu (produits)
     * @param contenu objet contenu
     */
    public static void parseContenuObject(JSONObject contenu) {
        // on affiche les infos des produits
        String type = (String) contenu.get("type");
        String nom = (String) contenu.get("nom");
        System.out.println("\t\t" + nom + " (" + type + ")");
    }

    /**
     * parser un menu
     * @param menu objet menu
     */
    public static void parseMenuObject(JSONObject menu) {
        // on affiche les infos des menus
        String prix = (String) menu.get("prix");
        String nom = (String) menu.get("nom");
        System.out.println("\tMenu " + nom + " (" + prix + " €)");

        // puis leurs produits
        JSONArray contenus = (JSONArray) menu.get("contenus");
        if (contenus != null){
            contenus.forEach(contenu -> parseContenuObject((JSONObject)contenu));
        }
    }

    /**
     * parser un complement
     * @param complement objet complement
     */
    public static void parseComplementObject(JSONObject complement) {
        // on affiche les infos des compléments
        String prix = (String) complement.get("prix");
        String type = (String) complement.get("type");
        String nom = (String) complement.get("nom");
        System.out.println("\tComplément " + nom + " , type : " + type + " , prix : " + prix);
    }

    /**
     * récupérer les données des objets json "commande"
     * @param commande objet commande d'un client
     */
    public static void parseCommandeObject(JSONObject commande) {
        // on affiche les infos des commandes
        String id = (String) commande.get("idCommande");
        String date = (String) commande.get("date");
        String prix = (String) commande.get("prix");
        String statut = (String) commande.get("statut");
        System.out.println("Commande n° " + id + " du " + date + " - Montant total : " + prix + "€ - Statut : " + statut);

        // puis leurs articles
        JSONArray menus = (JSONArray) commande.get("menus");
        if (menus != null)
            menus.forEach(menu -> parseMenuObject((JSONObject) menu));
        JSONArray complements = (JSONArray) commande.get("complements");
        if (complements != null)
            complements.forEach(complement -> parseComplementObject((JSONObject) complement));
    }

    /**
     * parser le spécifié par l'identifiant uniquement
     * @param client client à afficher
     * @param idClient identfiant client
     */
    public static void parseClientObject(JSONObject client, String idClient) {
        // on affiche les id des clients et on récupère celui qui nous intéresse
        String idClientJson = (String) (client.get("idClient"));
        if (Objects.equals(idClient, idClientJson)) {
            JSONArray commandes = (JSONArray) client.get("commandes");

            // pour chaque commande du client, on la liste
            if (commandes != null) {
                commandes.forEach(commande -> parseCommandeObject((JSONObject) commande));
                if (commandes.isEmpty())
                    System.out.println("Vous n'avez pas encore de commande réalisée");
            }

        }
    }
}