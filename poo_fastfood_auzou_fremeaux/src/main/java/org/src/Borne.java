package org.src;

import java.util.*;

import org.src.ObjetBorne.Menu.Menu;

public class Borne {
    private Integer id; 
    private List<Menu> liste_menu;
    private List<Client> liste_client;
    private Scanner scanner;
    
    /**
     * Constructeur de Borne
     * @param id Integer : id de la borne
     */
    public Borne(Integer id, List<Menu> liste_Menu, List<Client> liste_client){
        this.id = id;
        this.liste_client = liste_client;
        this.liste_menu = liste_Menu;
        this.scanner = new Scanner(System.in);
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
                    System.out.format("Bonjour %s %s\n",this.liste_client.get(id-1).getPrenom(),this.liste_client.get(id-1).getNom());
                    System.out.println("Vous pouvez commencer votre commande !");
                }
                else {
                    System.out.println("Identifiant client inconnu !");
                    id=null;

                    try {
                        System.out.println("Quitter ? (o/N)");
                        lettre=this.scanner.next();
                        if (lettre.equals("o")) {
                            return; // on quitte le programme
                        }
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
