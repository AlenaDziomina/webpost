package com.grouk.webpost.controler;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;

/**
 * Created by Alena_Grouk on 3/29/2017.
 */
public abstract class FXMLController implements InitializingBean, Initializable {

    protected String title;
    protected Node view;
    protected String fxmlFilePath;

    public FXMLController() {
        super();
    }

    public abstract void setFxmlFilePath(String filePath);

    public abstract void setTitle(String title);

    @Override
    public void afterPropertiesSet() throws Exception {
        loadFXML();
    }

    protected final void loadFXML() throws IOException {
        try (InputStream fxmlStream = getClass().getResourceAsStream(fxmlFilePath)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(this);
            this.view = (loader.load(fxmlStream));
        }
    }

    public Node getView() {
        return view;
    }

    public String getTitle() {
        return title;
    }
}
