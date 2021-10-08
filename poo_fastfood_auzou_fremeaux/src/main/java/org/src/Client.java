package org.src;

public class Client {
    private Integer id;
    private String nom, prenom;

    /**
     * Constructeur de client
     * @param id Integer : identifiant du client
     * @param nom String : nom du client
     * @param prenom String : prenom du client
     */
    public Client(Integer id, String nom, String prenom){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    // Getter

    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public Integer getId(){return this.id;}

    /**
     * Redifinition de la méthode toString
     */
    @Override
    public String toString(){
        return "Id : " + this.id +
                "nom : " + this.nom +
                "prenom : " + this.prenom;
    }
}
