package pl.moja.biblioteka.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wojtek on 04.02.2019.
 */

@DatabaseTable (tableName = "TABLE")
public class Author implements BaseModel {

    private Author () {

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField (columnName = "Colum_Name", canBeNull = false)
    private String nameAndSuername;

    @ForeignCollectionField (eager = true)
    private ForeignCollection<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameAndSuername() {
        return nameAndSuername;
    }

    public void setNameAndSuername(String nameAndSuername) {
        this.nameAndSuername = nameAndSuername;
    }

    public ForeignCollection<Book> getBooks() {
        return books;
    }

    public void setBooks(ForeignCollection<Book> books) {
        this.books = books;
    }
}
