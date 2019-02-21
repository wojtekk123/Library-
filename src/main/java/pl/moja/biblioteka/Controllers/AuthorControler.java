package pl.moja.biblioteka.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import pl.moja.biblioteka.modelFX.AuthorFX;
import pl.moja.biblioteka.modelFX.AuthorModel;
import pl.moja.biblioteka.uties.DialogUtils;
import pl.moja.biblioteka.uties.exception.AplicationException;


/**
 * Created by wojtek on 15.02.2019.
 */
public class AuthorControler {

    @FXML
    private Button addButton;
    @FXML
    private TextField  nameTextField;
    @FXML
    private TextField  surnameTextField;

    @FXML
    private TableView<AuthorFX> authorTableView;
    @FXML
    private TableColumn <AuthorFX,String> nameColumn;
    @FXML
    private TableColumn <AuthorFX,String> surnameColumn;
    @FXML
    private MenuItem deleteMenuItem;

    private AuthorModel authorModel;



    public void ButtonAddAction() {

        try {
            this.authorModel.saveAutorFXinDatabase();
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        this.nameTextField.clear();
        this.surnameTextField.clear();
    }


    public void initialize() {
        this.authorModel=new AuthorModel();
        try {
            this.authorModel.init();
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
        binging();
        bindingTable();

    }

    private void bindingTable() {
        this.authorTableView.setItems(this.authorModel.getAuthorFXObservableList());

        this.nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        this.surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());

        this.nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        this.surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        this.authorTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            this.authorModel.setAuthorFXObjectPropertyEdit(newValue);



        } );
    }

    private void binging() {
        this.authorModel.authorFXObjectPropertyProperty().get().nameProperty().bind(this.nameTextField.textProperty());
        this.authorModel.authorFXObjectPropertyProperty().get().surnameProperty().bind(this.surnameTextField.textProperty());
        this.addButton.disableProperty().bind(this.nameTextField.textProperty().isEmpty().or(this.surnameTextField.textProperty().isEmpty()));
        this.deleteMenuItem.disableProperty().bind(this.authorTableView.getSelectionModel().selectedItemProperty().isNull());
    }

    public void EditCommitName(TableColumn.CellEditEvent<AuthorFX, String> authorFXStringCellEditEvent) {
        this.authorModel.getAuthorFXObjectPropertyEdit().setName( authorFXStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    public void CommitEditeSurame(TableColumn.CellEditEvent<AuthorFX, String> authorFXStringCellEditEvent) {
        this.authorModel.getAuthorFXObjectPropertyEdit().setSurname( authorFXStringCellEditEvent.getNewValue());
        updateInDatabase();
    }

    private void updateInDatabase() {
        try {
            this.authorModel.saveAutorFXinDatabaseEdit();
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }

    public void deleteOnAction() {
        try {
            this.authorModel.deleteAuthorInDatabase();
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }
    }




}