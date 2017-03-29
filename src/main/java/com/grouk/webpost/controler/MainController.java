package com.grouk.webpost.controler;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * Controller for main window
 * Created by Alena_Grouk on 3/29/2017.
 */
public class MainController implements Initializable {
    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @FXML
    private ResourceBundle resources;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;

    }

}
