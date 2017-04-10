package com.grouk.webpost.view;

import com.grouk.webpost.model.Track;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.*;
import javafx.scene.image.Image;

import java.awt.*;
import java.io.ByteArrayInputStream;

/**
 * Table view of marks
 * Created by Alena on 13.02.2017.
 */
public class TrackTableDataModel {
    private final SimpleIntegerProperty id = new SimpleIntegerProperty();
    private final SimpleStringProperty number = new SimpleStringProperty();
    private final SimpleStringProperty description = new SimpleStringProperty();
    private final SimpleObjectProperty<ImageView> image = new SimpleObjectProperty();
    private final SimpleStringProperty color = new SimpleStringProperty();

    public TrackTableDataModel(Track track) {
        setId(track.getId());
        setNumber(track.getNumber());
        setDescription(track.getDescription());
        if (track.getImage() != null) {
            ImageView imageView = new ImageView(new Image(new ByteArrayInputStream(track.getImage())));
            setImage(imageView);
        }
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

    public String getColor() {
        return color.get();
    }

    public SimpleStringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public ImageView getImage() {
        return image.get();
    }

    public SimpleObjectProperty imageProperty() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image.set(image);
    }
}
