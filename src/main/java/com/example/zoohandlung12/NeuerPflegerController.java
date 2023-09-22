package com.example.zoohandlung12;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.TextField;



public class NeuerPflegerController {

    @FXML
    private TextField pflegerName;
    @FXML
    private TextField pflegerAlter;
    @FXML
    private TextField pflegerGehalt;
    @FXML
    private Button pflegerGeschlecht;
    @FXML
    private Label pflegerUngueltig;

    private boolean maennlich = true;
    private ZoohandlungController controller;

    public void setController(ZoohandlungController controller) {
        this.controller = controller;
    }

    @FXML
    protected void onNeuerPfleger(){
        try{
            ZoohandlungManager.addPfleger(new Pfleger(pflegerName.getText(), Integer.parseInt(pflegerAlter.getText()), Double.parseDouble(pflegerGehalt.getText()), maennlich));
            Stage stage = (Stage) pflegerName.getScene().getWindow();
            stage.close();
            controller.aktualisierePflegerTree();
        }catch (NumberFormatException e){
            pflegerUngueltig.setText("ungültige Eingabe");
        }
    }

    @FXML
    protected void changeGeschlecht(){
        if(maennlich){
            maennlich = false;
            pflegerGeschlecht.setText("Weiblich");
        }else{
            maennlich = true;
            pflegerGeschlecht.setText("Männlich");
        }
    }
}
