package com.grouk.webpost.view;

import com.grouk.webpost.model.Track;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Table view of marks
 * Created by Alena on 13.02.2017.
 */
public class TrackTableDataModel {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();

    public TrackTableDataModel(Track track) {
        setId(track.getId());
        setNumber(track.getNumber());
        setDescription(track.getDescription());
    }

    public TrackTableDataModel(Integer id, String number, String description) {
        setId(id);
        setNumber(number);
        setDescription(description);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNumber() {
        return number.get();
    }

    public SimpleStringProperty numberProperty() {
        return number;
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
