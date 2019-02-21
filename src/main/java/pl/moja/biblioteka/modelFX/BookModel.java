package pl.moja.biblioteka.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.moja.biblioteka.database.dao.AuthorDao;
import pl.moja.biblioteka.database.dao.BookDao;
import pl.moja.biblioteka.database.dao.CategoryDao;
import pl.moja.biblioteka.database.dbUtis.DBMenager;
import pl.moja.biblioteka.database.models.Author;
import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.database.models.Category;
import pl.moja.biblioteka.modelFX.BookFX;
import pl.moja.biblioteka.uties.convertes.ConverterAuthor;
import pl.moja.biblioteka.uties.convertes.ConverterBook;
import pl.moja.biblioteka.uties.convertes.ConverterCategory;
import pl.moja.biblioteka.uties.exception.AplicationException;

import java.util.List;
import java.util.Locale;

/**
 * Created by wojtek on 18.02.2019.
 */
public class BookModel {

    private ObjectProperty<BookFX> bookFXObjectProperty = new SimpleObjectProperty<>(new BookFX());
    private ObservableList<CategoryFx> categoryObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFX> authorFXObservableList = FXCollections.observableArrayList();

    public void init () throws AplicationException {

        initAuthorList() ;
        initCategoyList() ;

    }

    private void initCategoyList() throws AplicationException {
        CategoryDao categoryDao = new CategoryDao();
        List<Category>  categoryList = categoryDao.queryForAll(Category.class);
        categoryObservableList.clear();
        categoryList.forEach(category ->{

            CategoryFx categoryFx = ConverterCategory.convertToCategoty(category);
            categoryObservableList.add(categoryFx);

        } );



    }

    private void initAuthorList() throws AplicationException {
        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        categoryObservableList.clear();
        authorList.forEach(a -> {

            AuthorFX authorFX = ConverterAuthor.convertToAutorFx(a);
            authorFXObservableList.add(authorFX);

        });
    }

    //metoda odpowiedzialna  za zapis ksiazki do bazy danych

    public void saveBookinDatabase () throws AplicationException {

        Book book = ConverterBook.convertToBook(this.getBookFXObjectProperty());
        CategoryDao categoryDao = new CategoryDao();
        Category category = categoryDao.findById(Category.class, this.getBookFXObjectProperty().getCategoryFX().getId());

        AuthorDao authorDao = new AuthorDao();
        Author author = authorDao.findById(Author.class,this.getBookFXObjectProperty().getAuthorFX().getId());

        book.setAuthor(author );
        book.setCategory(category);

        BookDao bookDao = new BookDao();

        bookDao.creatOrUpdate(book);

    }


    public BookFX getBookFXObjectProperty() {
        return bookFXObjectProperty.get();
    }

    public ObjectProperty<BookFX> bookFXObjectPropertyProperty() {
        return bookFXObjectProperty;
    }

    public void setBookFXObjectProperty(BookFX bookFXObjectProperty) {
        this.bookFXObjectProperty.set(bookFXObjectProperty);
    }

    public ObservableList<CategoryFx> getCategoryObservableList() {
        return categoryObservableList;
    }

    public void setCategoryObservableList(ObservableList<CategoryFx> categoryObservableList) {
        this.categoryObservableList = categoryObservableList;
    }

    public ObservableList<AuthorFX> getAuthorFXObservableList() {
        return authorFXObservableList;
    }

    public void setAuthorFXObservableList(ObservableList<AuthorFX> authorFXObservableList) {
        this.authorFXObservableList = authorFXObservableList;
    }
}
