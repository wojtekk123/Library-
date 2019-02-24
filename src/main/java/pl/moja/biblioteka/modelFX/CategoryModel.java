package pl.moja.biblioteka.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import org.sqlite.core.DB;
import pl.moja.biblioteka.database.dao.BookDao;
import pl.moja.biblioteka.database.dao.CategoryDao;
import pl.moja.biblioteka.database.dbUtis.DBMenager;
import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.database.models.Category;
import pl.moja.biblioteka.uties.convertes.ConverterCategory;
import pl.moja.biblioteka.uties.exception.AplicationException;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;

/**
 * Created by wojtek on 11.02.2019.
 */
public class CategoryModel {



private ObservableList <CategoryFx> categoryList = FXCollections.observableArrayList();
private ObjectProperty <CategoryFx> category = new SimpleObjectProperty<>();
private TreeItem<String> root = new TreeItem<>();




    public void updateCategoryInDataBase () throws AplicationException{
        CategoryDao categoryDao = new CategoryDao();
        Category tempCategory = categoryDao.findById(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.creatOrUpdate(tempCategory);
        init();
    }


    public void init  () throws AplicationException {

    CategoryDao categoryDao = new CategoryDao( );
    List<Category> categories = categoryDao.queryForAll(Category.class);
    initCategoryList(categories);
    initRoot (categories);


    }

    private void initRoot(List<Category> categories)  {
        this.root.getChildren().clear();
    categories.forEach(c->{
        TreeItem<String>categoryItem = new TreeItem<>(c.getName());
        c.getBooks().forEach(b->{
            categoryItem.getChildren().add(new TreeItem<>(b.getTitle()));
        });
        root.getChildren().add(categoryItem);
            });


    }

    private void initCategoryList(List<Category> categories) {
        this.categoryList.clear();
        categories.forEach(c-> {

            CategoryFx categoryFx = ConverterCategory.convertToCategoty(c);
            this.categoryList.add(categoryFx);

        });
    }

    public void deleteCategory () throws AplicationException, SQLException {

        CategoryDao categoryDao = new CategoryDao( );
        categoryDao.deleteById(Category.class,category.getValue().getId());

        BookDao bookDao = new BookDao();
        bookDao.deletebyColumnName(Book.CATEGORY_ID,this.category.getValue().getId() );
        init();

    }

// model obsluguje zapis do bazy danych, stworzenie kategori i zapisanie jej
    public  void saveCategoryInDatabase (String name) throws AplicationException{

        CategoryDao categoryDao = new CategoryDao();
        Category category = new Category() ;
        category.setName(name);
        categoryDao.creatOrUpdate(category);
       init();

    }

    public ObservableList<CategoryFx> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ObservableList<CategoryFx> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryFx getCategory() {
        return category.get();
    }

    public ObjectProperty<CategoryFx> categoryProperty() {
        return category;
    }

    public void setCategory(CategoryFx category) {
        this.category.set(category);
    }

    public TreeItem<String> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<String> root) {
        this.root = root;
    }
}


