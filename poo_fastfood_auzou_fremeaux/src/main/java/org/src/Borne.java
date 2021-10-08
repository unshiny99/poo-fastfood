package org.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.src.ObjetBorne.Commande;

public class Borne {
    private Integer id; 
    private List<Commande> liste_commande;
    private List<Client> liste_client;
    private Scanner scanner;
    
    /**
     * Constructeur de Borne
     * @param id Integer : id de la borne
     */
    public Borne(Integer id){
        this.id = id;
        this.liste_client = new ArrayList<Client>();
        this.liste_commande = new ArrayList<Commande>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Lancement de la borne
     */
    public void idletBorne(){
        Integer id = null;
        while(true){
            System.out.println("Identifier vous :");
            id = this.scanner.nextInt();
            if(this.verifIdentifiantCLient(id)){
                /**TODO */
            }else{
                System.out.println("Identifiant client inconnue !");
            }
        }
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
