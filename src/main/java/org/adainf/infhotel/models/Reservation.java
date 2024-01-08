package org.adainf.infhotel.models;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import org.adainf.infhotel.Application;
import org.adainf.infhotel.enums.PaymentStatus;
import org.adainf.infhotel.screens.ReservationDetailScreen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Reservation {

    private final String firstName;
    private final String lastName;
    private final PaymentStatus status;
    private final Date checkInDate;
    private final int roomNumber;

    public Reservation(ResultSet result) throws SQLException {
        this.firstName = result.getString("first_name");
        this.lastName = result.getString("last_name");
        this.status = PaymentStatus.PAYED;
        this.checkInDate = null;
        this.roomNumber = result.getInt("room_number");
    }

    public Reservation(String firstName, String lastName, PaymentStatus status, Date checkInDate, int roomNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.checkInDate = checkInDate;
        this.roomNumber = roomNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public FlowPane show() {
        FlowPane reservation = new FlowPane();
        Label fullName = new Label(firstName + " " + lastName);

        reservation.setPrefHeight(120);
        reservation.setStyle("-fx-background-color: #00fc03;");
        reservation.getChildren().add(fullName);
        reservation.setOnMouseClicked(event -> showDetailScreen());

        if(status == PaymentStatus.PAYED) {
            reservation.setStyle("-fx-background-color: #00fc03");
        } else if (status == PaymentStatus.PARTIALLY_PAYED) {
            reservation.setStyle("-fx-background-color: #0000ff");
        } else {
            reservation.setStyle("-fx-background-color: #ff9900");
        }

        switch (status) {
            case PAYED:
                reservation.setStyle("-fx-background-color: #00fc03");
                System.out.println("PAYED");
                break;
            case PARTIALLY_PAYED:
                reservation.setStyle("-fx-background-color: #0000ff");
                System.out.println("PARTIALLY_PAYED");
                break;
            case RESERVED:
                reservation.setStyle("-fx-background-color: #ff9900");
                break;
        }

        return reservation;
    }

    private void showDetailScreen() {
        ReservationDetailScreen detailScreen = new ReservationDetailScreen(this);
        Application.mainStage.setScene(detailScreen.getScene());
    }
}
