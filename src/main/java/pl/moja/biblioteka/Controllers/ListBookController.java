package pl.moja.biblioteka.Controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.moja.biblioteka.database.dao.BookDao;
import pl.moja.biblioteka.database.models.Author;
import pl.moja.biblioteka.database.models.BaseModel;
import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.database.models.Category;
import pl.moja.biblioteka.modelFX.AuthorFX;
import pl.moja.biblioteka.modelFX.BookFX;
import pl.moja.biblioteka.modelFX.CategoryFx;
import pl.moja.biblioteka.modelFX.ListBookModel;
import pl.moja.biblioteka.uties.DialogUtils;
import pl.moja.biblioteka.uties.exception.AplicationException;
import pl.moja.biblioteka.uties.fxmlUties;


import java.io.IOException;
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
    private TableColumn<BookFX, BookFX> EditColumn;
    @FXML
    private TableColumn<BookFX, BookFX> DeleteColumn;
    @FXML
    private ComboBox<CategoryFx> CategoryComboBoX;
    @FXML
    private ComboBox<AuthorFX> AuthorComboBox;

    private static final  String  DeleteButtonPatch = "/icons/delete_icon.png";
    private static final String EditButtonBuch = "/icons/edit_icon.png";

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
        this.DeleteColumn.setCellValueFactory(cellData->new SimpleObjectProperty<BookFX>(cellData.getValue()) );
        this.EditColumn.setCellValueFactory(cellData->new SimpleObjectProperty<BookFX>(cellData.getValue()) );

        this.DeleteColumn.setCellFactory(parm -> new TableCell<BookFX, BookFX>(){

            Button button = createButton(DeleteButtonPatch);

            protected void updateItem(BookFX item , boolean empty) {
                super.updateItem(item, empty);

                if (empty){
                    setGraphic(null);
                    return;
                }

                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                        try {
                            listBookModel.deleteBook(item);
                        } catch (AplicationException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                    });
                }
            }
        });


        this.EditColumn.setCellFactory(parm -> new TableCell<BookFX, BookFX>(){

            Button button = createButton(EditButtonBuch);

            protected void updateItem(BookFX item , boolean empty) {
                super.updateItem(item, empty);

                if (empty){
                    setGraphic(null);
                    return;
                }

                if (!empty) {
                    setGraphic(button);
                    button.setOnAction(event -> {
                       FXMLLoader loader = fxmlUties.getLoader("/fxml/AddBook.fxml");
                        Scene scene = null;
                        try {
                          scene = new Scene(loader.load());
                        } catch (IOException e) {
                            DialogUtils.errorDialog(e.getMessage());
                        }
                        
                        BookControler controler = loader.getController();
                        controler.getBookModel().setBookFXObjectProperty(item );
                        controler.BookBinding();

                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.showAndWait();


                    });
                }
            }
        });



        this.AuthorComboBox.setItems(listBookModel.getAuthorFXObservableList());
        this.CategoryComboBoX.setItems(listBookModel.getCategoryObservableList());

        this.listBookModel.categoryFxObjectPropertyProperty().bind(this.CategoryComboBoX.valueProperty());
        this.listBookModel.authorFXObjectPropertyProperty().bind(this.AuthorComboBox.valueProperty());
    }

    private Button createButton (String pach) {

        Button button = new Button();
        Image image= new Image(this.getClass().getResource(pach).toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        button.setGraphic(imageView);
        return button;
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