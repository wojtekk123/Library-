package pl.moja.biblioteka.uties;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by wojtek on 02.02.2019.
 */
public class fxmlUties {


    public static Pane fxmlLoader (String patch) {


        FXMLLoader loader = new FXMLLoader(fxmlUties.class.getResource(patch));
        loader.setResources(getResource());

        try {
            return loader.load();
        } catch (Exception e) {
            DialogUtils.errorDialog(e.getMessage());
        }return null;

    }



    public static ResourceBundle getResource () {

        return ResourceBundle.getBundle("bundles.message");


    }

}
