package pl.moja.biblioteka.modelFX;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.moja.biblioteka.database.dao.AuthorDao;
import pl.moja.biblioteka.database.dbUtis.DBMenager;
import pl.moja.biblioteka.database.models.Author;
import pl.moja.biblioteka.uties.convertes.ConverterAuthor;
import pl.moja.biblioteka.uties.exception.AplicationException;

import java.util.List;

/**
 * Created by wojtek on 15.02.2019.
 */
public class AuthorModel {

    private ObjectProperty <AuthorFX> authorFXObjectProperty = new SimpleObjectProperty(new AuthorFX());
    private ObjectProperty <AuthorFX> authorFXObjectPropertyEdit = new SimpleObjectProperty(new AuthorFX());
    private ObservableList<AuthorFX> authorFXObservableList = FXCollections.observableArrayList();


    public void deleteAuthorInDatabase () throws AplicationException {

        AuthorDao authorDao = new AuthorDao();
        authorDao.deleteById(Author.class, this.getAuthorFXObjectPropertyEdit().getId());
        this.init();
    }

    public void init () throws AplicationException {

        AuthorDao authorDao = new AuthorDao();
        List<Author> authorsList = authorDao.queryForAll(Author.class);
        authorFXObservableList.clear();
        authorsList.forEach(author->{

            AuthorFX authorFX = ConverterAuthor.convertToAutorFx(author);
            this.authorFXObservableList.add(authorFX);

        });

    }

    public  void saveAutorFXinDatabase () throws AplicationException {
        SaveOrUpdate(this.getAuthorFXObjectProperty());
    }

    public  void saveAutorFXinDatabaseEdit () throws AplicationException {
        SaveOrUpdate(this.getAuthorFXObjectPropertyEdit());
    }

    private void SaveOrUpdate(AuthorFX authorFXObjectProperty) throws AplicationException {
        AuthorDao authorDao = new AuthorDao();
        Author author = ConverterAuthor.convertToAuthor(authorFXObjectProperty);
        authorDao.creatOrUpdate(author);
        this.init();
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

    public ObservableList<AuthorFX> getAuthorFXObservableList() {
        return authorFXObservableList;
    }

    public void setAuthorFXObservableList(ObservableList<AuthorFX> authorFXObservableList) {
        this.authorFXObservableList = authorFXObservableList;
    }

    public AuthorFX getAuthorFXObjectPropertyEdit() {
        return authorFXObjectPropertyEdit.get();
    }

    public ObjectProperty<AuthorFX> authorFXObjectPropertyEditProperty() {
        return authorFXObjectPropertyEdit;
    }

    public void setAuthorFXObjectPropertyEdit(AuthorFX authorFXObjectPropertyEdit) {
        this.authorFXObjectPropertyEdit.set(authorFXObjectPropertyEdit);
    }
}
