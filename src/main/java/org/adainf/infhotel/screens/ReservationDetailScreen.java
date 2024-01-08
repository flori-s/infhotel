package org.adainf.infhotel.screens;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.adainf.infhotel.models.Reservation;

public class ReservationDetailScreen {

    private final Scene scene;

    public ReservationDetailScreen(Reservation reservation) {
        Pane root = new Pane();
        scene = new Scene(root, 1024, 600);

        root.getChildren().add(new Label(reservation.getLastName()));
    }

    public Scene getScene() {
        return scene;
    }
}
