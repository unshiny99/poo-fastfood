// package org.src.ObjetBorne.Json;

// import java.io.FileNotFoundException;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import org.json.simple.JSONArray;
// import org.json.simple.JSONObject;
// import org.json.simple.parser.JSONParser;
// import org.json.simple.parser.ParseException;

// public class JsonEdit {

//      /**
//      * ajout d'une commande dans le fichier de sauvegarde
//      * @param commande
//      */
//     public void ajouterCommandeJSON(String nomFic, Commande commande) {

//         JSONObject commandeObj = new JSONObject();
//         commandeObj.put("date",String.valueOf(commande.getDate()));
//         commandeObj.put("prix",String.valueOf(commande.getPrix()));
//         for (Object element : commande.getElements()) {
//             if (element instanceof Menu) {
//                 JSONArray menus = new JSONArray();
//                 JSONObject menu = new JSONObject();
//                 menu.put("nom",((Menu) element).getNom());
//                 menu.put("prix",String.valueOf(((Menu) element).getPrix()));
//                 menu.put("contenus",((Menu) element).getAccompagnements());
//                 for (Produit p : ((Menu) element).getAccompagnements()) {
//                     JSONArray produits = new JSONArray();
//                     JSONObject produit = new JSONObject();
//                     produit.put("nom",p.getNom());
//                     produit.put("type",p.getType());
//                     produits.add(produit);
//                 }
//                 menus.add(menu);
//                 commandeObj.put("menus", menus);
//             }
//             if (element instanceof Produit) {
//                 JSONArray complements = new JSONArray();
//                 JSONObject complement = new JSONObject();
//                 complement.put("nom",((Produit) element).getNom());
//                 complement.put("type",((Produit) element).getType());
//                 complement.put("prix",String.valueOf(((Produit) element).getPrix()));
//                 complements.add(complement);
//                 commandeObj.put("complements", complements);
//             }
//         }

//         JSONArray historique = getHistoriqueComplet();

//         for (JSONObject client : (Iterable<JSONObject>) historique) {
//             String idClient = (String) client.get("idClient");
//             if (Objects.equals(idClient, commande.getClient().getId().toString())) {
//                 JSONArray commandes = getHistoriqueClient(commande.getClient().getId().toString());
//                 //récupérer le tableau de commandes d'un client donné
//                 commandes.add(commandeObj);
//                 System.out.println("La commande a été ajouté dans l'historique");
//                 client.put("commandes",commandes);
//             }
//         }

//         // écriture du fichier JSON
//         try (FileWriter file = new FileWriter(nomFic)) {
//             //We can write any JSONArray or JSONObject instance to the file
//             file.write(historique.toJSONString());
//             file.flush();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public void parseMenuObject(JSONObject menu) {
//         // on affiche les infos des menus
//         String prix = (String) menu.get("prix");
//         String nom = (String) menu.get("nom");
//         System.out.println("\tMenu " + nom + " , prix : " + prix);

//         // puis leurs produits
//         JSONArray contenus = (JSONArray) menu.get("contenus");
//         if (contenus != null){
//             contenus.forEach(contenu -> parseContenuObject((JSONObject)contenu));
//         }
//     }

//     /**
//      * récupérer les données des objets json "commande"
//      * @param commande
//      */
//     public void parseCommandeObject(JSONObject commande) {
//         // on affiche les infos des commandes
//         String date = (String) commande.get("date");
//         String prix = (String) commande.get("prix");
//         System.out.println("Commande du " + date + " - Montant : " + prix + "€");

//         // puis leurs articles
//         JSONArray menus = (JSONArray) commande.get("menus");
//         if (menus != null)
//             menus.forEach(menu -> parseMenuObject((JSONObject) menu));
//         JSONArray complements = (JSONArray) commande.get("complements");
//         if (complements != null)
//             complements.forEach(complement -> parseComplementObject((JSONObject) complement));
//     }

//     public void parseClientObject(JSONObject client, String idClient) {
//         // on affiche les id des clients et on récupère celui qui nous intéresse
//         String idClientJson = (String) (client.get("idClient"));
//         if (Objects.equals(idClient, idClientJson)) {
//             JSONArray commandes = (JSONArray) client.get("commandes");

//             // pour chaque commande du client, on la liste
//             if (commandes != null)
//                 commandes.forEach(commande -> parseCommandeObject((JSONObject) commande));
//         }
//     }

//     public void afficherHistorique(Integer idClient) {
//         JSONArray liste = null;
//         try {
//             liste = (JSONArray) readJsonSimpleDemo("historique.json");
//         } catch (IOException | ParseException e) {
//             e.printStackTrace();
//         }
//         if (liste != null)
//             liste.forEach(client -> parseClientObject((JSONObject) client, idClient.toString()));
//     }

//     public JSONArray getHistoriqueClient(String idClient) {
//         JSONArray liste = null;
//         try {
//             liste = (JSONArray) readJsonSimpleDemo("historique.json");
//         } catch (IOException | ParseException e) {
//             e.printStackTrace();
//         }
//         //JSONArray commandes = getClientObject(idClient.toString());
//         for (Object client : liste) {
//             JSONObject clientObj = (JSONObject) client;
//             String idClientJson = (String) (clientObj.get("idClient"));
//             if (Objects.equals(idClient, idClientJson)) {
//                 return (JSONArray) clientObj.get("commandes");
//             }
//         }
//         System.out.println("Client non trouvé !");
//         return null;
//     }

//     public JSONArray getHistoriqueComplet() {
//         JSONArray liste = null;
//         try {
//             liste = (JSONArray) readJsonSimpleDemo("historique.json");
//         } catch (IOException | ParseException e) {
//             e.printStackTrace();
//         }
//         return liste;
//     }

//     public static Object readJsonSimpleDemo(String filename) throws IOException, ParseException {
//         FileReader reader = null;
//         try {
//             reader = new FileReader(filename);
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         }
//         JSONParser jsonParser = new JSONParser();
//         return jsonParser.parse(reader);
//     }

//     public void parseComplementObject(JSONObject complement) {
//         // on affiche les infos des compléments
//         String prix = (String) complement.get("prix");
//         String type = (String) complement.get("type");
//         String nom = (String) complement.get("nom");
//         System.out.println("\tComplément " + nom + " , type : " + type + " , prix : " + prix);
//     }

//     public void parseContenuObject(JSONObject contenu) {
//         // on affiche les infos des produits
//         String type = (String) contenu.get("type");
//         String nom = (String) contenu.get("nom");
//         System.out.println("\t\t" + nom + " (" + type + ")");
//     }
// }
