package com.grouk.webpost.view;

import com.grouk.webpost.model.TrackInfo;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Alena on 02.04.2017.
 */
public class TrackInfoDataModel {
    private final SimpleStringProperty data = new SimpleStringProperty();
    private final SimpleStringProperty event = new SimpleStringProperty();
    private final SimpleStringProperty office = new SimpleStringProperty();

    public TrackInfoDataModel(TrackInfo trackInfo) {
        setData(trackInfo.getData());
        setEvent(trackInfo.getEvent());
        setOffice(trackInfo.getOffice());
    }

    public TrackInfoDataModel(String data, String event, String office) {
        setData(data);
        setEvent(event);
        setOffice(office);
    }

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public SimpleStringProperty dataProperty() {
        return data;
    }

    public String getEvent() {
        return event.get();
    }

    public void setEvent(String event) {
        this.event.set(event);
    }

    public SimpleStringProperty eventProperty() {
        return event;
    }

    public String getOffice() {
        return office.get();
    }

    public void setOffice(String office) {
        this.office.set(office);
    }

    public SimpleStringProperty officeProperty() {
        return office;
    }
}
