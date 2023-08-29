package com.example.zoohandlung12;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ZoohandlungController implements Initializable {

    @FXML
    private TreeView treeView;
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

    ZoohandlungManager manager = new ZoohandlungManager();
    private Tier[] tiere = ZoohandlungManager.getTiere();
    private String[] tierNamen = new String[tiere.length];
    private Tier selectedTier;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<String> rootItem = new TreeItem<>("Tiere");

        for(int i = 0; i < tiere.length; i++){
            if(tiere[i]!=null) {
                TreeItem<String> tierItem = new TreeItem<>(tiere[i].getName());
                rootItem.getChildren().add(tierItem);
                tierNamen[i] = tiere[i].getName();
            }

        }

        treeView.setRoot(rootItem);
        treeView.setShowRoot(false);
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


    public void selectItem(){

        TreeItem<String> item = (TreeItem<String>) treeView.getSelectionModel().getSelectedItem();
        selectedTier = tiere[findIndexString(tierNamen,item.getValue())];
        displayTier(selectedTier);
        //System.out.println(item.getValue());
        //System.out.println(tiere[findIndexString(tierNamen,item.getValue())].getAlter());


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
            tierLabel3.setText("a");
        }


    }


}