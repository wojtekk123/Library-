package pl.moja.biblioteka.Controllers;

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
    private ComboBox<CategoryFx> CategoryComboBox;
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

    }

    private void BookBinding() {
        this.CategoryComboBox.setItems(this.bookModel.getCategoryObservableList());
        this.AuthorComboBox.setItems(this.bookModel.getAuthorFXObservableList());


        //  this.bookModel.getBookFXObjectProperty().categoryFXProperty().bind(this.CategoryComboBox.valueProperty());

        this.bookModel.getBookFXObjectProperty().titleProperty().bind(this.TiitleTextField.textProperty());
        this.bookModel.getBookFXObjectProperty().discriptionProperty().bind(this.DescriptionTextArea.textProperty());
        this.bookModel.getBookFXObjectProperty().ratingProperty().bind(this.RankingSlider.valueProperty());
        this.bookModel.getBookFXObjectProperty().isbnProperty().bind(this.isbnTextField.textProperty());
        this.bookModel.getBookFXObjectProperty().releseDataProperty().bind(this.RelaseDataPicker.valueProperty());
    }

    @FXML
    public void AddActionBook(ActionEvent event) {

        try {
            this.bookModel.saveBookinDatabase();
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());

        }

    }
}
