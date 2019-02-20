/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

/**
 *
 * @author Darnex
 */
public class Main extends Application{
    int x;
    int y;
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Dibujo de cuadros");
        
        PaneOrganizer pane = new PaneOrganizer(stage);
        
        Scene sc = new Scene(pane.getRoot());
        stage.setScene(sc);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}