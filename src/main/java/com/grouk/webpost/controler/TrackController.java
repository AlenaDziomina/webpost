package com.grouk.webpost.controler;

import com.grouk.webpost.manager.TrackManager;
import com.grouk.webpost.view.TrackInfoDataModel;
import com.grouk.webpost.view.TrackTableDataModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

/**
 * Created by Alena_Grouk on 3/29/2017.
 */
@Component
public class TrackController extends FXMLController {

    @Autowired
    TrackManager trackManager;
    @FXML
    private TableView<TrackTableDataModel> trackList;
    @FXML
    private TableColumn<TrackTableDataModel, Integer> idTrack;
    @FXML
    private TableColumn<TrackTableDataModel, String> number;
    @FXML
    private TableColumn<TrackTableDataModel, String> description;
    @FXML
    private TableView<TrackInfoDataModel> trackInfo;
    @FXML
    private TableColumn<TrackInfoDataModel, String> dataInfo;
    @FXML
    private TableColumn<TrackInfoDataModel, String> eventInfo;
    @FXML
    private TableColumn<TrackInfoDataModel, String> officeInfo;
    private ObservableList<TrackTableDataModel> trackData;
    private ObservableList<TrackInfoDataModel> infoData;

    @Value("${fxml.demo.view}")
    @Override
    public void setFxmlFilePath(String filePath) {
        this.fxmlFilePath = filePath;
    }

    @Value("${track.title}")
    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        trackData = trackList.getItems();
        idTrack.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        number.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        description.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        infoData = trackInfo.getItems();
        dataInfo.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        eventInfo.setCellValueFactory(cellData -> cellData.getValue().eventProperty());
        officeInfo.setCellValueFactory(cellData -> cellData.getValue().officeProperty());

        resetTrackData();
    }

    private void resetTrackData() {
        Collection<TrackTableDataModel> trackNumbers = trackManager.getTrackNumbers();
        trackData.setAll(trackNumbers);
    }

    @FXML
    private void onTrackTableClick() {
        TrackTableDataModel track = getSelectedTrack();
        if (track != null) {
            Collection<TrackInfoDataModel> trackInfo = trackManager.getTrackInfo(track);
            infoData.setAll(trackInfo);
        }
    }

    private TrackTableDataModel getSelectedTrack() {
        return trackList.getSelectionModel().getSelectedItem();
    }
}
