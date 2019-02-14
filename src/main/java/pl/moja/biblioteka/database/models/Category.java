package pl.moja.biblioteka.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

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


}
