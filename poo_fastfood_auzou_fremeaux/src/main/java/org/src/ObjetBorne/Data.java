package org.src.ObjetBorne;

import java.util.List;
import java.util.ArrayList;

import org.src.Client;
import org.src.ObjetBorne.Menu.Menu;

public class Data {

    /**
     * Generation des données pour la borne
     * @param liste_menu List<Menu> : liste des menus
     * @param liste_client List<Client> : liste des clients
     */
    public static void GenerateData(List<Menu> liste_menu, List<Client> liste_client){
        liste_client.add(new Client("Client_1_nom", "Client_1_prenom"));
        liste_client.add(new Client("Client_2_nom", "Client_2_prenom"));
        liste_client.add(new Client("Client_3_nom", "Client_3_prenom"));
        liste_client.add(new Client("Client_4_nom", "Client_4_prenom"));
        liste_client.add(new Client("Client_5_nom", "Client_5_prenom"));
    
        
    }
}
