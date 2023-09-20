package com.example.zoohandlung12;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Main main;

    public static Main getMain() {
        return main;
    }

    public ZoohandlungManager getManager() {
        return manager;
    }

    private ZoohandlungManager manager;

    public Main(){main = main;}

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ZoohandlungFenster.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Zoohandlung");




        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        this.manager = new ZoohandlungManager();

    }

    public static void main(String[] args) {
        launch();
    }
}