package pl.moja.biblioteka.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import javafx.fxml.FXML;

/**
 * Created by wojtek on 04.02.2019.
 */

@DatabaseTable (tableName = "CATEGORIES")
public class  Category implements BaseModel {

    public Category() {

    }

    @DatabaseField (generatedId = true)
    private int id;

    @DatabaseField(columnName = "Name ", canBeNull = false, unique = true)
    private String name ;

@ForeignCollectionField (columnName = "Book_ID")
    private ForeignCollection<Book> books;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ForeignCollection<Book> getBooks() {
        return books;
    }

    public void setBooks(ForeignCollection<Book> books) {
        this.books = books;
    }
}
