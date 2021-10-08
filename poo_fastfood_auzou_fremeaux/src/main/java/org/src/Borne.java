package org.src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
     * Lancement de la borne
     */
    public void runBorne(){
        Integer id = null;
        do {
            System.out.println("Identifiez-vous : ");
            try {
                id = this.scanner.nextInt();
                if(this.verifIdentifiantCLient(id)){
                    System.out.println("Vous pouvez commencer votre commande !");
                }
                else {
                    System.out.println("Identifiant client inconnu !");
                    id=null;
                }
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Merci d'entrer un identifiant correct.");
                scanner.next();
            }
        } while(id==null);
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
