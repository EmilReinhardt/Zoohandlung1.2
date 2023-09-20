package com.example.zoohandlung12;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ZoohandlungController implements Initializable {

    @FXML
    private TreeView treeView;
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
    private Button sortieren;
    @FXML
    private TextField suchen;
    @FXML
    private Button tierLoeschen;
    @FXML
    private Button suchart;
    private TreeItem<String> rootItem = new TreeItem<>("Tiere");
    private TreeItem<String> rootItemPfleger = new TreeItem<>("Pfleger");



    ZoohandlungManager manager = new ZoohandlungManager();
    private Tier[] tiere = ZoohandlungManager.getDisplayTiere();
    private String[] tierNamen = new String[tiere.length];
    private Tier selectedTier;
    private Pfleger[] pfleger = ZoohandlungManager.getDiplayPfleger();
    private String[] pflegerNamen = new String[pfleger.length];
    private Image hundIcon = new Image("Hund.png",16,16,false,false);
    private Image schlangeIcon = new Image("Schlange.png",16,16,false,false);
    private Image katzeIcon = new Image("Katze.png",16,16,false,false);







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        for(int i = 0; i < tiere.length; i++){
            if(tiere[i]!=null) {
                if (tiere[i].getTyp().equals("Hund")) {
                    TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(hundIcon));
                    rootItem.getChildren().add(tierItem);
                }else if (tiere[i].getTyp().equals("Katze")) {
                    TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(katzeIcon));
                    rootItem.getChildren().add(tierItem);
                }else if (tiere[i].getTyp().equals("Schlange")) {
                    TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(schlangeIcon));
                    rootItem.getChildren().add(tierItem);
                }

                tierNamen[i] = tiere[i].getName();
            }

        }

        treeView.setRoot(rootItem);
        treeView.setShowRoot(false);
        for(int i = 0; i < pfleger.length; i++){
            if(pfleger[i]!=null) {
                TreeItem<String> pflegerItem = new TreeItem<>(pfleger[i].getName());
                rootItemPfleger.getChildren().add(pflegerItem);
                pflegerNamen[i] = pfleger[i].getName();
            }

        }

        treeViewPfleger.setRoot(rootItemPfleger);
        treeViewPfleger.setShowRoot(false);
    }
    @FXML
    protected void onSortieren(){
        System.out.println(ZoohandlungManager.getSortierArt());
        switch (ZoohandlungManager.getSortierArt()){
            case 0:
                ZoohandlungManager.sortOriginal();
                aktualisiereTree();
                ZoohandlungManager.setSortierArt(1);
                sortieren.setText("Sortieren");
                break;
            case 1:
                ZoohandlungManager.sortAlter();
                aktualisiereTree();
                ZoohandlungManager.setSortierArt(2);
                sortieren.setText("Sortiert nach Alter");
                break;
            case 2:
                ZoohandlungManager.sortPreis();
                aktualisiereTree();
                ZoohandlungManager.setSortierArt(3);
                sortieren.setText("Sortiert nach Preis");
                break;
            case 3:
                ZoohandlungManager.sortName();
                aktualisiereTree();
                ZoohandlungManager.setSortierArt(0);
                sortieren.setText("Sortiert nach Name");
                break;
        }
    }


    @FXML
    protected void aktualisiereTree(){
        //ZoohandlungManager.sortAlter();
        rootItem.getChildren().clear();
        tiere = ZoohandlungManager.getDisplayTiere();
        tierNamen = new String[tiere.length];
        System.out.println(tiere.length);

        rootItem.getChildren().clear();
        for(int i = 0; i < tiere.length; i++){
            if (tiere[i].getTyp().equals("Hund")) {
                TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(hundIcon));
                rootItem.getChildren().add(tierItem);
            }else if (tiere[i].getTyp().equals("Katze")) {
                TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(katzeIcon));
                rootItem.getChildren().add(tierItem);
            }else if (tiere[i].getTyp().equals("Schlange")) {
                TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName(),new ImageView(schlangeIcon));
                rootItem.getChildren().add(tierItem);
            }
                tierNamen[i] = tiere[i].getName();
        }
    }




    @FXML
    protected void onNeuesTier(){

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NeuesTierGUI.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Neues Tier");
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
        aktualisiereTree();
    }

    @FXML
    protected void onSuchart(){
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
    private void onLoeschen(){
        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        ZoohandlungManager.removeTier(ZoohandlungManager.getDisplayTier(findIndexString(tierNamen,item.getValue())));
        aktualisiereTree();
    }

    public void selectItem(){

        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        //System.out.println(item.getValue().substring(item.getValue().indexOf(" ")+1));
        selectedTier = tiere[findIndexString(tierNamen,item.getValue())];
        displayTier(selectedTier);



    }

    private int findIndexString(String[] stringList,String string){
        for(int i = 0; i < stringList.length; i++){
            if(stringList[i] == string){
                return i;
            }

        }
        return -1;
    }

    private void displayTier(Tier aktTier){
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
                tierLabel3.setText("-"+aktTier.getTyp()+" ist nicht tötlich");
            }
        }else{
            tierLabel2.setText("");
            tierLabel3.setText("");
        }
    }
}