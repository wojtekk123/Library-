package pl.moja.biblioteka.Controllers;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import pl.moja.biblioteka.modelFX.BookModel;
import pl.moja.biblioteka.modelFX.AuthorFX;
import pl.moja.biblioteka.modelFX.CategoryFx;
import pl.moja.biblioteka.uties.DialogUtils;
import pl.moja.biblioteka.uties.exception.AplicationException;

/**
 * Created by wojtek on 18.02.2019.
 */
public class BookControler {

    @FXML
    private Button addBook;
    @FXML
    private ComboBox <CategoryFx> CategoryComboBox;
    @FXML
    private ComboBox <AuthorFX> AuthorComboBox;
    @FXML
    private TextArea DescriptionTextArea;
    @FXML
    private Slider RankingSlider;
    @FXML
    private TextField isbnTextField;
    @FXML
    private DatePicker RelaseDataPicker;
    @FXML
    private TextField TiitleTextField;


    private BookModel bookModel;


    public void initialize () {

        this.bookModel = new BookModel();

        try {
            this.bookModel.init();
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }

        BookBinding();
        validation();

    }

    public void BookBinding() {
        this.CategoryComboBox.setItems(this.bookModel.getCategoryObservableList());
        this.AuthorComboBox.setItems(this.bookModel.getAuthorFXObservableList());
        this.AuthorComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().authorFXProperty());
        this.RelaseDataPicker.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().releseDataProperty());
        this.isbnTextField.textProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().isbnProperty());
        this.RankingSlider.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().ratingProperty());
        this.DescriptionTextArea.textProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().discriptionProperty());
        this.TiitleTextField.textProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().titleProperty());
        this.CategoryComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().categoryFXProperty());
        this.AuthorComboBox.valueProperty().bindBidirectional(this.bookModel.getBookFXObjectProperty().authorFXProperty());


    }

    private void validation () {
        this.addBook.disableProperty().bind(this.AuthorComboBox.valueProperty().isNull()
                .or(this.CategoryComboBox.valueProperty().isNull())
                .or(this.TiitleTextField.textProperty().isEmpty())
                .or(this.DescriptionTextArea.textProperty().isEmpty())
                .or(this.RelaseDataPicker.valueProperty().isNull())
                .or(this.AuthorComboBox.valueProperty().isNull())
                .or(this.isbnTextField.textProperty().isEmpty())

        );

    }


    @FXML
    public void AddActionBook() {
        clearField ();
        try {

            this.bookModel.saveBookinDatabase();
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());

        }
    }

    private void clearField() {

      // this.AuthorComboBox.getSelectionModel().clearSelection();
       //   this.CategoryComboBox.getSelectionModel().clearSelection();
        this.DescriptionTextArea.clear();
      this.TiitleTextField.clear();
      this.isbnTextField.clear();
        this.RelaseDataPicker.getEditor().clear();
       this.RankingSlider.setValue(1);

    }

    public BookModel getBookModel() {
        return bookModel;
    }

    public void setBookModel(BookModel bookModel) {
        this.bookModel = bookModel;
    }
}
