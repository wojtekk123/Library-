package pl.moja.biblioteka.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.moja.biblioteka.modelFX.CategoryFx;
import pl.moja.biblioteka.modelFX.CategoryModel;
import pl.moja.biblioteka.uties.DialogUtils;

/**
 * Created by wojtek on 11.02.2019.
 */
public class CategoryController {

    @FXML
    private  TextField categoryTextField;

    @FXML
    private  Button addCategory;

    @FXML
    private Button deleteButton;

    @FXML
    private  ComboBox<CategoryFx> comboBox;

    @FXML
    private Button ControllerEdditButton;



    private CategoryModel categoryModel;
    @FXML public void initialize () {

        this.categoryModel = new CategoryModel();
        this.categoryModel.init();
        this.comboBox.setItems(this.categoryModel.getCategoryList());
        iniBindings ();

    }

    private void iniBindings() {

        this.addCategory.disableProperty().bind(categoryTextField.textProperty().isEmpty());
        this.deleteButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());
        this.ControllerEdditButton.disableProperty().bind(this.categoryModel.categoryProperty().isNull());

    }


    public void AddCategoryAction() {
      categoryModel.saveCategoryInDatabase(categoryTextField.getText());
      categoryTextField.clear();

    }

    public void deleteAction() {

    this.categoryModel.deleteCategory();

    }


    public void OnActionComboBox() {

        this.categoryModel.setCategory(this.comboBox.getSelectionModel().getSelectedItem());

    }

    public void EditControlerAction() {

        String newCatogreyName = DialogUtils.editDialog(this.categoryModel.getCategory().getName());
        if ( newCatogreyName != null){
            this.categoryModel.getCategory().setName(newCatogreyName);
            this.categoryModel.updateCategoryInDataBase();
        }


    }
}
