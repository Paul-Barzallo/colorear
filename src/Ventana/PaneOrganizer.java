/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventana;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Darnex
 */
public class PaneOrganizer {

    private BorderPane root;
    private String estilo;
    private String rojo;
    private String azul;
    private String verde;
    private String amarillo;
    private String blanco;
    private String negro;
    private ComboBox<String> colores;
    private GridPane tablero;
    private TextField filas;
    private TextField columnas;
    private int x;
    private int y;
    private Stage st;

    public PaneOrganizer(Stage stage) {
        //variables de los colores
        negro = "-fx-border-color: white; -fx-border-width: 1; -fx-background-color: rgb(0,0,0)";
        rojo = "-fx-border-color: white; -fx-border-width: 1; -fx-background-color: rgb(219, 17, 44)";
        azul = "-fx-border-color: white; -fx-border-width: 1; -fx-background-color: rgb(17, 131, 219)";
        verde = "-fx-border-color: white; -fx-border-width: 1; -fx-background-color: rgb(45, 198, 76)";
        amarillo = "-fx-border-color: white; -fx-border-width: 1; -fx-background-color: rgb(232, 221, 67)";
        blanco = "-fx-border-color: white; -fx-border-width: 1; -fx-background-color: rgb(249, 244, 244)";
        estilo = "-fx-border-color: white; -fx-border-width: 1; -fx-background-color: ";
        estilo = negro;
        
        x = 20;
        y = 20;
        st = stage;
        
        //ingreso de botones al tablero
        tablero = new GridPane();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Button b = new Button(" ");
                b.setStyle(estilo);
                b.setOnAction(new Pintar());
                tablero.add(b, i, j);
            }
        }

        //desplegable de colores
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("rojo", "amarillo", "azul", "verde", "blanco");
        colores = new ComboBox<>(items);
        colores.setOnAction(new Seleccionar());

        //boton para quitar los colores
        Button borrador = new Button("Limpiar");
        borrador.setOnAction(new Limpiar());

        //numero de filas y columnas
        filas = new TextField();
        filas.setPrefWidth(50);
        filas.setOnAction(new TamañoY());
        columnas = new TextField();
        columnas.setPrefWidth(50);
        columnas.setOnAction(new TamañoX());
        
        //union de filas y columnas
        HBox dimension = new HBox(10,columnas,filas);
        dimension.setAlignment(Pos.CENTER);
        dimension.setPadding(new Insets(10));
        
        //layout derecha
        VBox herramientas = new VBox(10,colores, borrador, dimension);
        herramientas.setAlignment(Pos.BASELINE_CENTER);
        herramientas.setPadding(new Insets(10));

        //layout raiz
        root = new BorderPane();
        root.setCenter(tablero);
        root.setRight(herramientas);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: rgb(132, 130, 116)");
        
        estilo = blanco;
    }

    public BorderPane getRoot() {
        return root;
    }

    private class Pintar implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            ((Button) event.getSource()).setStyle(estilo);
        }
    }
    
    private class TamañoX implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try{
                x = Integer.parseInt(columnas.getText());
                tablero = new GridPane();
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        Button b = new Button(" ");
                        b.setStyle(negro);
                        b.setOnAction(new Pintar());
                        tablero.add(b, i, j);
                    }
                }
                root.setCenter(tablero);
                st.sizeToScene();
            }catch(NumberFormatException e){}
        }    
    }
            
    private class TamañoY implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try{
                y = Integer.parseInt(filas.getText());
                tablero = new GridPane();
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        Button b = new Button(" ");
                        b.setStyle(negro);
                        b.setOnAction(new Pintar());
                        tablero.add(b, i, j);
                    }
                }
                root.setCenter(tablero);
                st.sizeToScene();
            }catch(NumberFormatException e){}
        }
    }

    private class Seleccionar implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if(colores.getValue().equals("amarillo")){
                estilo = amarillo;
            }else if(colores.getValue().equals("azul")){
                estilo = azul;
            }else if(colores.getValue().equals("rojo")){
                estilo = rojo;
            }else if(colores.getValue().equals("verde")){
                estilo = verde;
            }else if(colores.getValue().equals("blanco")){
                estilo = blanco;
            }
        }
    }
    
    private class Limpiar implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            for(Node nodo: tablero.getChildren()){
                Button b = (Button) nodo;
                b.setStyle(negro);
            }
            
        }
    }
}

