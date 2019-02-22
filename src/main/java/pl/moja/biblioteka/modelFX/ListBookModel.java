package pl.moja.biblioteka.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.moja.biblioteka.database.dao.AuthorDao;
import pl.moja.biblioteka.database.dao.BookDao;
import pl.moja.biblioteka.database.dao.CategoryDao;
import pl.moja.biblioteka.database.models.Author;
import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.database.models.Category;
import pl.moja.biblioteka.uties.convertes.ConverterAuthor;
import pl.moja.biblioteka.uties.convertes.ConverterBook;
import pl.moja.biblioteka.uties.convertes.ConverterCategory;
import pl.moja.biblioteka.uties.exception.AplicationException;

import javax.naming.ldap.PagedResultsControl;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by wojtek on 21.02.2019.
 */
public class ListBookModel {

    //klasa ktora inicializuje liste naszych ksiażek

    private ObservableList <BookFX> bookFXObservableList = FXCollections.observableArrayList();
    private ObservableList<AuthorFX> authorFXObservableList = FXCollections.observableArrayList();
    private ObservableList<CategoryFx> categoryObservableList = FXCollections.observableArrayList();

    private ObjectProperty <AuthorFX> authorFXObjectProperty = new SimpleObjectProperty<>();
    private ObjectProperty <CategoryFx> categoryFxObjectProperty = new SimpleObjectProperty<>();
    private List <BookFX> BookFxList = new ArrayList<BookFX>();


    public void init () throws AplicationException {

        BookDao bookDao = new BookDao();
        List<Book> books = bookDao.queryForAll(Book.class);
        books.forEach(book -> {

            this.BookFxList.add(ConverterBook.converterToBookFx(book));

        });

        this.bookFXObservableList.setAll(BookFxList);
        initAuthors () ;
        initCategory () ;

    }


    public void delete (){

        this.bookFXObservableList.setAll(BookFxList);


    }

    public void filterBookList (){
        if (getAuthorFXObjectProperty()!=null && getCategoryFxObjectProperty()!=null){
            filtePredicate(predicateAuthor().and(predicateCategory()));
        }else  if (getAuthorFXObjectProperty()!=null){
            filtePredicate(predicateAuthor());
        }
        else  if (getCategoryFxObjectProperty()!=null){
            filtePredicate(predicateCategory());
        }else  {

            this.bookFXObservableList.setAll(this.BookFxList);

        }

}

    private void initCategory() throws AplicationException {

        CategoryDao categoryDao = new CategoryDao();
        List<Category>  categoryList = categoryDao.queryForAll(Category.class);
        categoryObservableList.clear();
        categoryList.forEach(category ->{

            CategoryFx categoryFx = ConverterCategory.convertToCategoty(category);
            categoryObservableList.add(categoryFx);

        } );

    }

    private void initAuthors() throws AplicationException {


        AuthorDao authorDao = new AuthorDao();
        List<Author> authorList = authorDao.queryForAll(Author.class);
        categoryObservableList.clear();
        authorList.forEach(a -> {

            AuthorFX authorFX = ConverterAuthor.convertToAutorFx(a);
            authorFXObservableList.add(authorFX);

        });
        
    }



    private Predicate<BookFX> predicateCategory (){
        return bookFX -> bookFX.getCategoryFX().getId()==getCategoryFxObjectProperty().getId();
    }

    private Predicate<BookFX> predicateAuthor () {
        return bookFX -> bookFX.getAuthorFX().getId() == getAuthorFXObjectProperty().getId();
    }


    private void filtePredicate (Predicate <BookFX> predicate) {

    List<BookFX> list = BookFxList.stream().filter(predicate).collect(Collectors.toList());
    this.bookFXObservableList.setAll(list);

    }



    public ObservableList<BookFX> getBookFXObservableList() {
        return bookFXObservableList;
    }

    public void setBookFXObservableList(ObservableList<BookFX> bookFXObservableList) {
        this.bookFXObservableList = bookFXObservableList;
    }

    public ObservableList<AuthorFX> getAuthorFXObservableList() {
        return authorFXObservableList;
    }

    public void setAuthorFXObservableList(ObservableList<AuthorFX> authorFXObservableList) {
        this.authorFXObservableList = authorFXObservableList;
    }

    public ObservableList<CategoryFx> getCategoryObservableList() {
        return categoryObservableList;
    }

    public void setCategoryObservableList(ObservableList<CategoryFx> categoryObservableList) {
        this.categoryObservableList = categoryObservableList;
    }

    public AuthorFX getAuthorFXObjectProperty() {
        return authorFXObjectProperty.get();
    }

    public ObjectProperty<AuthorFX> authorFXObjectPropertyProperty() {
        return authorFXObjectProperty;
    }

    public void setAuthorFXObjectProperty(AuthorFX authorFXObjectProperty) {
        this.authorFXObjectProperty.set(authorFXObjectProperty);
    }

    public CategoryFx getCategoryFxObjectProperty() {
        return categoryFxObjectProperty.get();
    }

    public ObjectProperty<CategoryFx> categoryFxObjectPropertyProperty() {
        return categoryFxObjectProperty;
    }

    public void setCategoryFxObjectProperty(CategoryFx categoryFxObjectProperty) {
        this.categoryFxObjectProperty.set(categoryFxObjectProperty);
    }
}
