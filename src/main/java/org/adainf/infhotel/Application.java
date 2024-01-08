package org.adainf.infhotel;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.adainf.infhotel.screens.HomeScreen;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));

        HomeScreen homeScreen = new HomeScreen();

        mainStage = stage;

        mainStage.setTitle("InfHotel");
        mainStage.setScene(homeScreen.getScene());
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}