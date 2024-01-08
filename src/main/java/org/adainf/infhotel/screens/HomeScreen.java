package org.adainf.infhotel.screens;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.adainf.infhotel.MySQLConnector;
import org.adainf.infhotel.models.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeScreen {

    private final Scene scene;
    private final FlowPane sidebar;
    private final FlowPane menu;

    public HomeScreen() {
        Pane root = new Pane();
        scene = new Scene(root, 1024, 600);

        sidebar = new FlowPane();
        sidebar.setStyle("-fx-background-color: #292c33");
        sidebar.setPrefSize(140, scene.getHeight());
        sidebar.setMaxSize(140, scene.getHeight());
        sidebar.setOrientation(Orientation.VERTICAL);

        Label logo = new Label("InfHotel");
        logo.setPrefSize(sidebar.getMaxWidth(), 50);
        logo.setStyle("-fx-background-color: #09e3c9;");
        logo.setAlignment(Pos.CENTER);

        menu = new FlowPane();
        menu.setStyle("-fx-background-color: #fff000;");
        menu.setMaxSize(sidebar.getMaxWidth(), 300);

        for(int i = 0; i < 5; i++)
           generateMenuItem("Menu Item " + i);

        double whitespace = scene.getWidth() - sidebar.getMaxWidth();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setStyle("-fx-background-color: #ff00ff;");
        scrollPane.relocate(sidebar.getMaxWidth(), 50);
        scrollPane.setPrefSize(whitespace / 2, scene.getHeight() - scrollPane.getTranslateY());

        VBox content = new VBox(10);
        content.setPrefWidth(whitespace / 2);

        try {
            MySQLConnector connector = new MySQLConnector("localhost", "8888", "codeclass_plus", "root", "root");
            ResultSet reservations = connector.query("SELECT * FROM reservation");

            while (reservations.next()) {
                content.getChildren().add(new Reservation(reservations).show());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        scrollPane.setContent(content);
        sidebar.getChildren().addAll(logo, menu);
        root.getChildren().addAll(sidebar, scrollPane);
    }

    public void generateMenuItem(String title) {
        FlowPane menuItem = new FlowPane();
        menuItem.setStyle("-fx-background-color: #00fc03;");
        menuItem.setPrefSize(sidebar.getMaxWidth(), sidebar.getMaxWidth() - 30);

        Pane activeIndicator = new Pane();
        activeIndicator.setStyle("-fx-background-color: #ff0000;");
        activeIndicator.setPrefSize(10, menuItem.getPrefHeight());

        FlowPane itemInfo = new FlowPane();
        itemInfo.setPrefSize(menuItem.getPrefWidth() - activeIndicator.getPrefWidth(), menuItem.getPrefHeight());
        itemInfo.setStyle("-fx-background-color: #0000ff;");

        Text itemName = new Text(title);
        itemName.setStyle("-fx-font-size: 20px;");

        itemInfo.getChildren().add(itemName);

        menuItem.getChildren().addAll(activeIndicator, itemInfo);
        menu.getChildren().addAll(menuItem);
    }

    public Scene getScene() {
        return scene;
    }
}
