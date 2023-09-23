package com.example.zoohandlung12;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NeuesTierController {

    @FXML
    private TextField hundName;
    @FXML
    private TextField hundAlter;
    @FXML
    private TextField hundPreis;
    @FXML
    private TextField hundRasse;
    @FXML
    private Label ungueltigHund;
    @FXML
    private TextField katzeName;
    @FXML
    private TextField katzeAlter;
    @FXML
    private TextField katzePreis;
    @FXML
    private TextField katzeRasse;
    @FXML
    private Label ungueltigKatze;
    @FXML
    private TextField schlangeName;
    @FXML
    private TextField schlangeAlter;
    @FXML
    private TextField schlangePreis;
    @FXML
    private TextField schlangeRasse;
    @FXML
    private TextField schlangeLaenge;
    @FXML
    private CheckBox schlangeGiftig;
    @FXML
    private Label ungueltigSchlange;



    private ZoohandlungController controller;


    public void setController(ZoohandlungController controller) {
        this.controller = controller;
    }




    public void onNeuerHund(){
        try{
            ZoohandlungManager.addTiere(new Hund(hundName.getText(), Integer.parseInt(hundAlter.getText()), Double.parseDouble(hundPreis.getText()), hundRasse.getText()));
            Stage stage = (Stage) hundName.getScene().getWindow();
            stage.close();
            controller.aktualisiereTiereTree();
        }catch (NumberFormatException e){
            ungueltigHund.setText("ungültige Eingabe");
        }

    }
    public void onNeueKatze(){
        try{
            ZoohandlungManager.addTiere(new Katze(katzeName.getText(),Integer.parseInt(katzeAlter.getText()),Double.parseDouble(katzePreis.getText()),katzeRasse.getText()));
            Stage stage = (Stage) katzeName.getScene().getWindow();
            stage.close();
            controller.aktualisiereTiereTree();
        }catch (NumberFormatException e){
            ungueltigKatze.setText("ungültige Eingabe");
        }
    }
    public void onNeueSchlange(){
        try{
            ZoohandlungManager.addTiere(new Schlange(schlangeName.getText(),Integer.parseInt(schlangeAlter.getText()),Double.parseDouble(schlangePreis.getText()),schlangeRasse.getText(),Double.parseDouble(schlangeLaenge.getText()),schlangeGiftig.isSelected()));
            Stage stage = (Stage) schlangeName.getScene().getWindow();
            stage.close();
            controller.aktualisiereTiereTree();
        }catch (NumberFormatException e){
            ungueltigSchlange.setText("ungültige Eingabe");
        }
    }


}
