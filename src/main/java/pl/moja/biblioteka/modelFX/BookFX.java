package pl.moja.biblioteka.modelFX;

/**
 * Created by wojtek on 18.02.2019.
 */
import javafx.beans.property.*;
import pl.moja.biblioteka.database.models.Author;

import java.time.LocalDate;

public class BookFX {


    private IntegerProperty id = new SimpleIntegerProperty();
    private  StringProperty authorFX1 = new SimpleStringProperty();
    private SimpleObjectProperty<CategoryFx> authorFX = new SimpleObjectProperty();
    private ObjectProperty<AuthorFX> categoryFX = new SimpleObjectProperty();
    private SimpleStringProperty title = new SimpleStringProperty();
    private SimpleStringProperty discription = new SimpleStringProperty();
    private StringProperty isbn = new SimpleStringProperty();
    private IntegerProperty rating = new SimpleIntegerProperty();
    private ObjectProperty<LocalDate> addDate = new SimpleObjectProperty<>(LocalDate.now());
    private ObjectProperty<LocalDate> releseData = new SimpleObjectProperty<>();


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public CategoryFx getAuthorFX() {
        return authorFX.get();
    }

    public ObjectProperty<CategoryFx> authorFXProperty() {
        return authorFX;
    }

    public void setAuthorFX(CategoryFx authorFX) {
        this.authorFX.set(authorFX);
    }

    public AuthorFX getCategoryFX() {
        return categoryFX.get();
    }

    public ObjectProperty<AuthorFX> categoryFXProperty() {
        return categoryFX;
    }

    public void setCategoryFX(AuthorFX categoryFX) {
        this.categoryFX.set(categoryFX);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDiscription() {
        return discription.get();
    }

    public SimpleStringProperty discriptionProperty() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription.set(discription);
    }

    public int getRating() {
        return rating.get();
    }

    public IntegerProperty ratingProperty() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating.set(rating);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public LocalDate getAddDate() {
        return addDate.get();
    }

    public ObjectProperty<LocalDate> addDateProperty() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate.set(addDate);
    }

    public LocalDate getReleseData() {
        return releseData.get();
    }

    public ObjectProperty<LocalDate> releseDataProperty() {
        return releseData;
    }

    public void setReleseData(LocalDate releseData) {
        this.releseData.set(releseData);
    }

    public String getAuthorFX1() {
        return authorFX1.get();
    }

    public StringProperty authorFX1Property() {
        return authorFX1;
    }

    public void setAuthorFX1(String authorFX1) {
        this.authorFX1.set(authorFX1);
    }


    @Override
    public String toString() {
        return "BookFX{" +
                "id=" + id +
                ", authorFX1=" + authorFX1 +
                ", authorFX=" + authorFX +
                ", categoryFX=" + categoryFX +
                ", title=" + title +
                ", discription=" + discription +
                ", isbn=" + isbn +
                ", rating=" + rating +
                ", addDate=" + addDate +
                ", releseData=" + releseData +
                '}';
    }
}
