package org.src.GUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.src.ObjetBorne.Data.Data;

public class Gui_javafx extends Application{
    private Scanner choix;

    public Gui_javafx(){this.choix = new Scanner(System.in);}

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("FIS'eat");
        stage.setScene(this.InitScenet());
        stage.setResizable(false);
        stage.setWidth(800);
        stage.setHeight(800);
        stage.centerOnScreen();
        stage.show();
    }

    public void setOnGUI(String[] args){
        Integer scan;
        try{
            do{
                System.out.println(Data.COLOR_BLUE + "Activer l'interface graphique ? (Y:1/N:2)" + Data.COLOR_RESET);
                scan = this.choix.nextInt();
                if(scan.equals(1)){
                    launch(args);
                }else if(scan != 2){
                    System.out.println(Data.COLOR_RED + "Erreur de saisie" + Data.COLOR_RESET);
                }
            }while(scan != 2);
        }catch(Exception e){
            System.out.println(Data.COLOR_RED + "Erreur de saisie" + Data.COLOR_RESET);
        }
    }

    private Scene InitScenet(){
        StackPane stackPane = new StackPane();
        stackPane.setBackground(this.setBackground());
        return new Scene(stackPane,800,800);
    }

    private Background setBackground() {
        try {
            BackgroundImage bgImage;
            ImageView imageView = new ImageView();
        
            Image image = new Image(new FileInputStream("./poo_fastfood_auzou_fremeaux/src/main/java/org/image/animate_menu.gif"));
            bgImage = new BackgroundImage(image, 
                                                BackgroundRepeat.NO_REPEAT,
                                                BackgroundRepeat.NO_REPEAT,
                                                BackgroundPosition.DEFAULT,
                                                new BackgroundSize(800, 800, false, false, false, false));
            return new Background(bgImage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
