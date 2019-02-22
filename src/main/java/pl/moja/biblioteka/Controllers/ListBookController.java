package pl.moja.biblioteka.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.omg.CORBA.DATA_CONVERSION;
import pl.moja.biblioteka.database.models.Author;
import pl.moja.biblioteka.database.models.Category;
import pl.moja.biblioteka.modelFX.AuthorFX;
import pl.moja.biblioteka.modelFX.BookFX;
import pl.moja.biblioteka.modelFX.CategoryFx;
import pl.moja.biblioteka.modelFX.ListBookModel;
import pl.moja.biblioteka.uties.DialogUtils;
import pl.moja.biblioteka.uties.exception.AplicationException;

import java.time.LocalDate;

/**
 * Created by wojtek on 21.02.2019.
 */
public class ListBookController {

    @FXML
    private TableView<BookFX> TableBookList;
    @FXML
    private TableColumn<BookFX, String> TitleBookListColumn;
    @FXML
    private TableColumn<BookFX, String> DescBookListColumn;
    @FXML
    private TableColumn<BookFX, AuthorFX> AuthorBookListColumn;
    @FXML
    private TableColumn<BookFX, CategoryFx> CategoryBookListColumn;
    @FXML
    private TableColumn<BookFX, Number> RankingBookListColumn;
    @FXML
    private TableColumn<BookFX, String> ISBNBookListColumn;
    @FXML
    private TableColumn<BookFX, LocalDate> DataBookListColumn;
    @FXML
    private ComboBox<CategoryFx> CategoryComboBoX;
    @FXML
    private ComboBox<AuthorFX> AuthorComboBox;


    private ListBookModel listBookModel;

    public void initialize() {





        listBookModel = new ListBookModel();
        try {
            this.listBookModel.init();
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        this.TableBookList.setItems(this.listBookModel.getBookFXObservableList());
        this.TitleBookListColumn.setCellValueFactory(cellData-> cellData.getValue().titleProperty());
        this.DescBookListColumn.setCellValueFactory(cellData-> cellData.getValue().discriptionProperty());
        this.ISBNBookListColumn.setCellValueFactory(cellData-> cellData.getValue().isbnProperty());
        this.DataBookListColumn.setCellValueFactory(cellData->cellData.getValue().releseDataProperty());
        this.AuthorBookListColumn.setCellValueFactory(cellData->cellData.getValue().authorFXProperty());
        this.CategoryBookListColumn.setCellValueFactory(cellData->cellData.getValue().categoryFXProperty());
        this.RankingBookListColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());


        this.AuthorComboBox.setItems(listBookModel.getAuthorFXObservableList());
        this.CategoryComboBoX.setItems(listBookModel.getCategoryObservableList());

        this.listBookModel.categoryFxObjectPropertyProperty().bind(this.CategoryComboBoX.valueProperty());
        this.listBookModel.authorFXObjectPropertyProperty().bind(this.AuthorComboBox.valueProperty());
    }

    public void filterOnActionCombobox() {

        this.listBookModel.filterBookList();


    }



    public void deleteCategoryAction() {

        this.CategoryComboBoX.getSelectionModel().clearSelection();

    }

    public void deleteAuthorAction() {
        this.AuthorComboBox.getSelectionModel().clearSelection();

    }
}