package pl.moja.biblioteka.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.core.DB;
import pl.moja.biblioteka.database.dao.CategoryDao;
import pl.moja.biblioteka.database.dbUtis.DBMenager;
import pl.moja.biblioteka.database.models.Category;

import java.util.List;
import java.util.Observable;

/**
 * Created by wojtek on 11.02.2019.
 */
public class CategoryModel {



private ObservableList <CategoryFx> categoryList = FXCollections.observableArrayList();
private ObjectProperty <CategoryFx> category = new SimpleObjectProperty<>();




    public void updateCategoryInDataBase () {
        CategoryDao categoryDao = new CategoryDao( (DBMenager.getConnectionSource()));
        Category tempCategory = categoryDao.findById(Category.class, getCategory().getId());
        tempCategory.setName(getCategory().getName());
        categoryDao.creatOrUpdate(tempCategory);
        DBMenager.closeConnectionSource();
        init();
    }


public void init  () {

    CategoryDao categoryDao = new CategoryDao( (DBMenager.getConnectionSource()));
    List<Category> categories = categoryDao.queryForAll(Category.class);
    this.categoryList.clear();
    categories.forEach(c-> {

        CategoryFx  categoryFx = new CategoryFx();
        categoryFx.setId(c.getId());
        categoryFx.setName(c.getName());
        this.categoryList.add(categoryFx);

    });
    DBMenager.closeConnectionSource();

}

    public void deleteCategory () {

        CategoryDao categoryDao = new CategoryDao( (DBMenager.getConnectionSource()));
        categoryDao.deleteById(Category.class,category.getValue().getId());
        DBMenager.closeConnectionSource();
        init();

    }

// model obsluguje zapis do bazy danych, stworzenie kategori i zapisanie jej
    public  void saveCategoryInDatabase (String name) {

        CategoryDao categoryDao = new CategoryDao(DBMenager.getConnectionSource());
        Category category = new Category() ;
        category.setName(name);
        categoryDao.creatOrUpdate(category);
        DBMenager.closeConnectionSource();
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
}


