package com.grouk.webpost.controler;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.grouk.webpost.manager.TrackManager;
import com.grouk.webpost.view.TrackTableDataModel;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Created by Alena_Grouk on 3/29/2017.
 */
@Component
public class TrackController extends FXMLController {

    @Autowired
    TrackManager trackManager;

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

    @FXML
    private TableView<TrackTableDataModel> trackList;

    @FXML
    private TableColumn<TrackTableDataModel, Integer> idTrack;

    @FXML
    private TableColumn<TrackTableDataModel, String> number;

    @FXML
    private TableColumn<TrackTableDataModel, String> description;

    private ObservableList<TrackTableDataModel> markData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        markData = trackList.getItems();
        idTrack.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        number.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        description.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());

        resetTrackData();
    }

    private void resetTrackData() {
        Collection<TrackTableDataModel> trackNumbers = trackManager.getTrackNumbers();
        markData.setAll(trackNumbers);
    }
}
