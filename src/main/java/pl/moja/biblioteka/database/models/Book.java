package pl.moja.biblioteka.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by wojtek on 08.02.2019.
 */

@DatabaseTable (tableName = "BOOKS")
public class Book implements BaseModel {

    public static final String AUTHOR = "Author";
    public static final String CATEGORY_ID = "Category_ID";

    public Book () {

    }

    @DatabaseField (generatedId =  true)
    private int id;

    @DatabaseField (columnName = AUTHOR, foreign = true, foreignAutoRefresh = true,foreignAutoCreate = true,canBeNull = false)
    private Author author;

    @DatabaseField (columnName = CATEGORY_ID, foreign = true,foreignAutoRefresh = true,foreignAutoCreate = true,canBeNull = false)
    private Category category;

    @DatabaseField (columnName =  "Title", canBeNull = false)
    private String title;

    @DatabaseField (columnName =  "Relase_Data")
    private Date realseData;

    @DatabaseField (columnName =  "ISBN", unique = true, width = 1)
    private String isbn;

    @DatabaseField (columnName =  "Rating",width = 1)
    private int ranking;

    @DatabaseField (columnName =  "Data_Add")
    private Date dataAdd;

    @DatabaseField (columnName= "Description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRealseData() {
        return realseData;
    }

    public void setRealseData(Date realseData) {
        this.realseData = realseData;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public Date getDataAdd() {
        return dataAdd;
    }

    public void setDataAdd(Date dataAdd) {
        this.dataAdd = dataAdd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
