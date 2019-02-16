package pl.moja.biblioteka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.moja.biblioteka.database.dbUtis.DBMenager;
import pl.moja.biblioteka.uties.FileDatabase;
import pl.moja.biblioteka.uties.fxmlUties;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Created by wojtek on 31.01.2019.
 */
public class Main extends Application {

    public static final String BORDER_PANEL_MAIN_FXML = "/fxml/BorderPanelMain.fxml";

    public void start(Stage primaryStage) throws Exception {


      Pane  pane=  fxmlUties.fxmlLoader(BORDER_PANEL_MAIN_FXML);


        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle(fxmlUties.getResource().getString("title.application"));
        primaryStage.show();

        DBMenager.initDatabase();
        FileDatabase.FillDataBase();

    }
}
