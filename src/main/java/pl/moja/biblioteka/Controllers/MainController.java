package pl.moja.biblioteka.Controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pl.moja.biblioteka.uties.DialogUtils;
import pl.moja.biblioteka.uties.fxmlUties;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by wojtek on 31.01.2019.
 */
public class MainController  {

    @FXML
    public BorderPane borderPane;

    @FXML
    public TopMenuButtonController topMenuButtonController;

    public void setCenters ( String Patch) {

        borderPane.setCenter(fxmlUties.fxmlLoader(Patch));
        }


    public void initialize() {
            topMenuButtonController.setMainController(this);
    }

    public void closeApplcation() {
       Optional<ButtonType> result = DialogUtils.confirmationDialog();
       if (result.get()==ButtonType.OK){
           Platform.exit();
           System.exit(0);
       }
    }

    public void setCaspian() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void setModena() {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);

    }

    public void setAlwasyOnTop(ActionEvent event) {

        Stage stage = (Stage) borderPane.getScene().getWindow();
       boolean  value =  ((CheckMenuItem) event.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(true);

    }

    public void about() {

        DialogUtils.dialogApplication();
    }
}

