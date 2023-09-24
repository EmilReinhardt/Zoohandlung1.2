package com.example.zoohandlung12;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ZoohandlungController implements Initializable {

    //alle GUI sachen:
    @FXML
    private TreeView treeViewTiere;
    @FXML
    private TreeView treeViewPfleger;
    @FXML
    private Label tierNamenAnzeige;
    @FXML
    private Label tierAlter;
    @FXML
    private Label tierPreis;
    @FXML
    private Label tierLabel1;
    @FXML
    private Label tierLabel2;
    @FXML
    private Label tierLabel3;
    @FXML
    private Label gesammtGehalt;
    @FXML
    private Button sortieren;
    @FXML
    private TextField suchen;
    @FXML
    private Button tierLoeschen;
    @FXML
    private Button suchart;
    @FXML
    private Pane zoohandlungPane;
    @FXML
    private Pane geschlossenPane;
    @FXML
    private Label pflegerNamenAnzeige;
    @FXML
    private Label pflegerAlter;
    @FXML
    private Label pflegerGehalt;
    @FXML
    private Label pflegerGeschlecht;
    @FXML
    private Button pflegerEntlassen;
    @FXML
    private MenuButton aktuellesTierButton;
    @FXML
    private Button tierFuettern;
    @FXML
    private Label fuetternAnzeige;
    @FXML
    private Label tierLabel4;
    @FXML
    private Label tierLabel5;
    @FXML
    private Button tierStreicheln;
    private TreeItem<String> rootItemTiere = new TreeItem<>("Tiere");
    private TreeItem<String> rootItemPfleger = new TreeItem<>("Pfleger");



    ZoohandlungManager manager = new ZoohandlungManager();
    private Tier[] tiere = ZoohandlungManager.getDisplayTiere();
    private String[] tierNamen = new String[tiere.length];
    private Tier selectedTier;
    private Pfleger selectedPfleger;
    private Pfleger[] pfleger = ZoohandlungManager.getDiplayPfleger();
    private String[] pflegerNamen = new String[pfleger.length];
    private Image hundIcon = new Image("Hund.png",16,16,false,false);
    private Image schlangeIcon = new Image("Schlange.png",16,16,false,false);
    private Image katzeIcon = new Image("Katze.png",16,16,false,false);
    private Image maennlichIcon = new Image("Maennlich.png",16,16,false,false);
    private Image weiblichIcon = new Image("Weiblich.png",16,16,false,false);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {//wird bei Start anugeführt
        zoohandlungPane.setVisible(false);
        geschlossenPane.setVisible(true);
        tierLoeschen.setDisable(true);
        tierStreicheln.setVisible(false);

        for (int i = 0; i < tiere.length; i++) {
            if (tiere[i] != null) {
                if (tiere[i].getTyp().equals("Hund")) {
                    TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(), new ImageView(hundIcon));
                    rootItemTiere.getChildren().add(tierItem);
                } else if (tiere[i].getTyp().equals("Katze")) {
                    TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(), new ImageView(katzeIcon));
                    rootItemTiere.getChildren().add(tierItem);
                } else if (tiere[i].getTyp().equals("Schlange")) {
                    TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(), new ImageView(schlangeIcon));
                    rootItemTiere.getChildren().add(tierItem);
                }

                tierNamen[i] = tiere[i].getName();
            }
        }

        double insgGehalt = 0;
        treeViewTiere.setRoot(rootItemTiere);
        treeViewTiere.setShowRoot(false);
        for (int i = 0; i < pfleger.length; i++) {
            if (pfleger[i] != null) {
                insgGehalt+= pfleger[i].getGehalt();
                TreeItem<String> pflegerItem;
                if (pfleger[i].getMaennlich()) {
                    pflegerItem = new TreeItem<>(pfleger[i].getName(), new ImageView(maennlichIcon));
                } else {
                    pflegerItem = new TreeItem<>(pfleger[i].getName(), new ImageView(weiblichIcon));
                }
                rootItemPfleger.getChildren().add(pflegerItem);
                pflegerNamen[i] = pfleger[i].getName();
            }
        }
        gesammtGehalt.setText("Gesammt Gehalt: " +String.valueOf(insgGehalt)+" €/h");
        treeViewPfleger.setRoot(rootItemPfleger);
        treeViewPfleger.setShowRoot(false);
        pflegerEntlassen.setDisable(true);

    }
    @FXML
    protected void onSortieren(){
        switch (ZoohandlungManager.getSortierArt()){
            case 0:
                ZoohandlungManager.sortOriginal();
                aktualisiereTiereTree();
                ZoohandlungManager.setSortierArt(1);
                sortieren.setText("Sortieren");
                break;
            case 1:
                ZoohandlungManager.sortAlter();
                aktualisiereTiereTree();
                ZoohandlungManager.setSortierArt(2);
                sortieren.setText("Sortiert nach Alter");
                break;
            case 2:
                ZoohandlungManager.sortPreis();
                aktualisiereTiereTree();
                ZoohandlungManager.setSortierArt(3);
                sortieren.setText("Sortiert nach Preis");
                break;
            case 3:
                ZoohandlungManager.sortName();
                aktualisiereTiereTree();
                ZoohandlungManager.setSortierArt(0);
                sortieren.setText("Sortiert nach Name");
                break;
        }
    }


    @FXML
    public void aktualisiereTiereTree(){
        rootItemTiere.getChildren().clear();
        tiere = ZoohandlungManager.getDisplayTiere();
        tierNamen = new String[tiere.length];
        deselectTier();
        rootItemTiere.getChildren().clear();
        for(int i = 0; i < tiere.length; i++){
            if (tiere[i].getTyp().equals("Hund")) {
                TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(hundIcon));
                rootItemTiere.getChildren().add(tierItem);
            }else if (tiere[i].getTyp().equals("Katze")) {
                TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(katzeIcon));
                rootItemTiere.getChildren().add(tierItem);
            }else if (tiere[i].getTyp().equals("Schlange")) {
                TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(schlangeIcon));
                rootItemTiere.getChildren().add(tierItem);
            }
            tierNamen[i] = tiere[i].getName();
        }
    }

    @FXML
    protected void aktualisierePflegerTree(){
        double insgGehalt = 0;
        tierFuettern.setVisible(false);
        tierStreicheln.setVisible(false);
        rootItemPfleger.getChildren().clear();
        pfleger = ZoohandlungManager.getDiplayPfleger();
        pflegerNamen = new String[pfleger.length];
        for (int i = 0; i < pfleger.length; i++) {
            pflegerNamen[i]=pfleger[i].getName();
            insgGehalt += pfleger[i].getGehalt();
            if (pfleger[i] != null) {
                TreeItem<String> pflegerItem;
                if (pfleger[i].getMaennlich()) {
                    pflegerItem = new TreeItem<>(pfleger[i].getName(), new ImageView(maennlichIcon));
                } else {
                    pflegerItem = new TreeItem<>(pfleger[i].getName(), new ImageView(weiblichIcon));
                }
                rootItemPfleger.getChildren().add(pflegerItem);
                pflegerNamen[i] = pfleger[i].getName();
            }
        }
        gesammtGehalt.setText("Gesammt Gehalt: " +String.valueOf(insgGehalt)+" €/h");
        pflegerEntlassen.setDisable(true);
        deselectPfleger();
        aktuellesTierButton.getItems().clear();
        aktuellesTierButton.setVisible(false);
    }

    @FXML
    protected void onOeffnen(){
        geschlossenPane.setDisable(true);
        geschlossenPane.setVisible(false);
        zoohandlungPane.setDisable(false);
        zoohandlungPane.setVisible(true);
    }

    @FXML
    protected void onSchliessen(){
        geschlossenPane.setDisable(false);
        geschlossenPane.setVisible(true);
        zoohandlungPane.setDisable(true);
        zoohandlungPane.setVisible(false);
    }


    @FXML
    protected void onNeuesTier(){

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NeuesTierGUI.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            fxmlLoader.<NeuesTierController>getController().setController(this);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Neues Tier");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onNeuerPfleger(){

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NeuerPflegerGUI.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            fxmlLoader.<NeuerPflegerController>getController().setController(this);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Neuer Pfleger");
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    protected void onSuchen() {

        if (suchen.getText().length() == 0) {
            ZoohandlungManager.sortOriginal();
        } else{
            ZoohandlungManager.suchen(suchen.getText());
        }
        aktualisiereTiereTree();
    }

    @FXML
    protected void onSuchart(){//ändert Suchart
        switch (ZoohandlungManager.getSuchArt()){
            case 0:
                ZoohandlungManager.setSuchArt(1);
                suchart.setFont(new Font("System",8));
                suchart.setText("Suchen Name:");
                break;
            case 1:
                ZoohandlungManager.setSuchArt(2);
                suchart.setFont(new Font("System",8));
                suchart.setText("Suchen Alter:");
                break;
            case 2:
                ZoohandlungManager.setSuchArt(3);
                suchart.setFont(new Font("System",8));
                suchart.setText("Suchen Preis:");
                break;
            case 3:
                ZoohandlungManager.setSuchArt(4);
                suchart.setFont(new Font("System",8));
                suchart.setText("Suchen Rasse:");
                break;
            case 4:
                ZoohandlungManager.setSuchArt(0);
                suchart.setFont(new Font("System",12));
                suchart.setText("Suchen:");
                break;
        }
    }

    @FXML
    private void onLoeschen(){//löscht Tier
        TreeItem<String> item = (TreeItem<String>) treeViewTiere.getSelectionModel().getSelectedItem();
        ZoohandlungManager.removeTier(ZoohandlungManager.getDisplayTier(findIndexString(tierNamen,item.getValue())));
        aktualisiereTiereTree();
    }
    @FXML
    public void selectTier(){

        TreeItem<String> item = (TreeItem<String>) treeViewTiere.getSelectionModel().getSelectedItem();
        if(item!=null){
            selectedTier = tiere[findIndexString(tierNamen,item.getValue())];
            displayTier(selectedTier);
            tierLoeschen.setDisable(false);
        }
    }

    @FXML
    public void selectPfleger(){
        tierStreicheln.setVisible(false);
        tierFuettern.setVisible(false);
        TreeItem<String> item = (TreeItem<String>) treeViewPfleger.getSelectionModel().getSelectedItem();
        aktuellesTierButton.getItems().clear();
        aktuellesTierButton.setVisible(true);

        if(item!=null){
            selectedPfleger = pfleger[findIndexString(pflegerNamen,item.getValue())];
            if(selectedPfleger.getAktTier()!=null){
                aktuellesTierButton.setText("Aktuelles Tier: "+selectedPfleger.getAktTier().getName());
                tierFuettern.setVisible(true);
                tierStreicheln.setVisible(true);
                tierFuettern.setText(selectedPfleger.getAktTier().getName()+" füttern");
                tierStreicheln.setText(selectedPfleger.getAktTier().getName()+" streicheln");
            }else{
                aktuellesTierButton.setText("Aktuelles Tier ");
            }

            displayPfleger(selectedPfleger);
            for(int i = 0; i < tiere.length;i++){
                aktuellesTierButton.getItems().add(new MenuItem(tiere[i].getName()));
                int finalI = i;
                aktuellesTierButton.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        setAktTier(finalI);
                    }
                });
            }
        }
        pflegerEntlassen.setDisable(false);

    }

    @FXML
    protected void setAktTier(int x){
        TreeItem<String> item = (TreeItem<String>) treeViewPfleger.getSelectionModel().getSelectedItem();
        Pfleger selectedPfleger = pfleger[findIndexString(pflegerNamen,item.getValue())];
        Tier selectedTier = tiere[findIndexString(tierNamen,aktuellesTierButton.getItems().get(x).getText())];
        aktuellesTierButton.setText("Aktuelles Tier: "+selectedTier.getName());
        selectedPfleger.setAktTier(selectedTier);
        tierFuettern.setVisible(true);
        tierFuettern.setText(selectedPfleger.getAktTier().getName()+" füttern");
        tierStreicheln.setVisible(true);
        tierStreicheln.setText(selectedPfleger.getAktTier().getName()+" streicheln");

    }
    @FXML
    protected void onPflegerEntlassen(){
        TreeItem<String> item = (TreeItem<String>) treeViewPfleger.getSelectionModel().getSelectedItem();
        ZoohandlungManager.removePfleger(ZoohandlungManager.getDisplayPflegerIn(findIndexString(pflegerNamen,item.getValue())));
        aktualisierePflegerTree();
    }

    @FXML
    protected void tierFuettern(){
        aktualisiereTiereTree();
        TreeItem<String> item = (TreeItem<String>) treeViewPfleger.getSelectionModel().getSelectedItem();
        Pfleger selectedPfleger = pfleger[findIndexString(pflegerNamen,item.getValue())];
        selectedPfleger.fuettern();
        fuetternAnzeige.setText(selectedPfleger.getAktTier().getName() + " wurde von "+selectedPfleger.getName()+" gefüttert");
        delay(3000,()-> fuetternAnzeige.setText(""));

    }

    @FXML
    protected void tierStreicheln() {

        TreeItem<String> item = (TreeItem<String>) treeViewPfleger.getSelectionModel().getSelectedItem();
        Pfleger selectedPfleger = pfleger[findIndexString(pflegerNamen, item.getValue())];
        if (selectedPfleger.getAktTier().getTyp().equals("Katze")) {
            AudioClip clip = new AudioClip(Main.getMain().getClass().getResource("KatzeLaut.wav").toExternalForm());
            clip.play();
        } else if (selectedPfleger.getAktTier().getTyp().equals("Hund")) {
            AudioClip clip = new AudioClip(Main.getMain().getClass().getResource("HundLaut.wav").toExternalForm());
            clip.play();
        } else if (selectedPfleger.getAktTier().getTyp().equals("Schlange")) {
            AudioClip clip = new AudioClip(Main.getMain().getClass().getResource("SchlangeLaut.wav").toExternalForm());
            clip.play();
        }
    }

    public static void delay(long millis, Runnable continuation) {//warten Funktion
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try { Thread.sleep(millis); }
                catch (InterruptedException e) { }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> continuation.run());
        new Thread(sleeper).start();
    }

    private int findIndexString(String[] stringList,String string){//findet Index von String in String Array
        for(int i = 0; i < stringList.length; i++){
            if(stringList[i] == string){
                return i;
            }

        }
        return -1;
    }

    private void displayTier(Tier aktTier){
        tierLoeschen.setDisable(false);
        tierNamenAnzeige.setText(aktTier.getTyp()+": "+aktTier.getName());
        tierAlter.setText("-"+aktTier.getTyp()+" Alter: "+aktTier.getAlter());
        tierPreis.setText("-"+aktTier.getTyp()+" Preis: "+aktTier.getPreis());
        if(aktTier.getTyp()=="Schlange"||aktTier.getTyp()=="Hund"||aktTier.getTyp()=="Katze"){
            tierLabel1.setText("-"+aktTier.getTyp()+" Rasse: "+aktTier.getRasse());
        }
        if(aktTier.getTyp()=="Schlange"){
            tierLabel2.setText("-"+aktTier.getTyp()+" Länge: "+((Schlange)aktTier).getLänge()+"m");
            if(((Schlange)aktTier).getToetlich()){
                tierLabel3.setText("-"+aktTier.getTyp()+" ist tötlich");
            }else{
                tierLabel3.setText("- "+aktTier.getTyp()+" ist nicht tötlich");
            }
            try{
                tierLabel4.setText("-Letzes mal gefüttert: ");
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM HH:mm:ss");
                tierLabel5.setText("- "+aktTier.getZuletztGefuettert().format(myFormatObj));
            }catch(NullPointerException e){
                tierLabel4.setText("- Wurde noch nicht gefüttert");
                tierLabel5.setText("");
            }
        }else{
            try{
                tierLabel2.setText("- Letzes mal gefüttert: ");
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM HH:mm:ss");
                tierLabel3.setText(aktTier.getZuletztGefuettert().format(myFormatObj));
            }catch(NullPointerException e){
                tierLabel2.setText("- Wurde noch nicht gefüttert");
                tierLabel3.setText("");
            }
            tierLabel4.setText("");
            tierLabel5.setText("");

        }


    }

    private void displayPfleger(Pfleger pfleger) {
        pflegerNamenAnzeige.setText("Pfleger: " + pfleger.getName());
        pflegerAlter.setText("Alter: " + pfleger.getAlter());
        pflegerGehalt.setText(("Gehalt: " + pfleger.getGehalt() + " €/h"));
        if(pfleger.getMaennlich()){
            pflegerGeschlecht.setText("Geschlecht: Männlich");
        }else{
            pflegerGeschlecht.setText("Geschlecht: Weiblich");
        }
    }

    private void deselectTier(){
        tierLoeschen.setDisable(true);
        tierNamenAnzeige.setText("Kein Tier ausgewählt");
        tierAlter.setText("");
        tierPreis.setText("");
        tierLabel1.setText("");
        tierLabel2.setText("");
        tierLabel3.setText("");
    }

    private void deselectPfleger(){
        pflegerNamenAnzeige.setText("Kein Pfleger ausgewählt");
        pflegerGeschlecht.setText("");
        pflegerGehalt.setText("");
        pflegerAlter.setText("");
    }
}