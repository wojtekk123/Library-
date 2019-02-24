package pl.moja.biblioteka.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.uties.exception.AplicationException;

import java.sql.SQLException;

/**
 * Created by wojtek on 10.02.2019.
 */
public class BookDao extends CommonDao {


   public BookDao () {
   super();
   }


   public void deletebyColumnName (String columnName, int id) throws AplicationException, SQLException {

      Dao<Book, Object> dao = getDao(Book.class);
      DeleteBuilder<Book, Object> deleteBulider = dao.deleteBuilder();
      deleteBulider.where().eq(columnName,id);
      dao.delete(deleteBulider.prepare());
   }

}
