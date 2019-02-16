package pl.moja.biblioteka.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Created by wojtek on 31.01.2019.
 */
public class TopMenuButtonController  {

    public static final String LIBRARY_FXML = "/fxml/Library.fxml";
    public static final String LIST_BOOK_FXML = "/fxml/ListBook.fxml";
    public static final String STATISTIC_FXML = "/fxml/Statistic.fxml";
    public static final String ADD_BOOK_FXML = "/fxml/AddBook.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";

    private MainController mainController;



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private ToggleGroup toggleButton;


    @FXML
    public void openLibrey() {
        mainController.setCenters(LIBRARY_FXML);

    }

    @FXML
    public void openListBooks() {
        mainController.setCenters(LIST_BOOK_FXML);
    }

    @FXML

    public void openStatistic() {
        mainController.setCenters(STATISTIC_FXML);
    }

    @FXML
    public void addBook() {
        resetToggleButtons();
        mainController.setCenters(ADD_BOOK_FXML);

    }
    @FXML
    public void addCategory() {
        mainController.setCenters(ADD_CATEGORY_FXML);
        resetToggleButtons();
    }
    @FXML
    public void AddAthorAction() {
        mainController.setCenters(ADD_AUTHOR_FXML);

    }



    private void resetToggleButtons() {
        if (toggleButton.getSelectedToggle() != null) {
            toggleButton.getSelectedToggle().setSelected(false);
        }
    }



}