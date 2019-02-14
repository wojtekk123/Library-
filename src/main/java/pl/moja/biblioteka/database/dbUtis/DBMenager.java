package pl.moja.biblioteka.database.dbUtis;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import javafx.scene.control.Tab;
import pl.moja.biblioteka.database.models.Author;
import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.database.models.Category;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by wojtek on 10.02.2019.
 */
public class DBMenager {


    private static final Logger LOGGER = LoggerFactory.getLogger(DBMenager.class);
    private static final  String JDBC_DRIVER_HD = "jdbc:h2:./libraryDB";
    private static final String User = " admin";
    private static final String password = " admin";

    private static ConnectionSource  connectionSource;

    public static void initDatabase () {
        createConnectionSource();
        dropTable();
        creatTabe () ;
        closeConnectionSource () ;
    }

    private static void createConnectionSource (){
        try {
            connectionSource= new JdbcConnectionSource(JDBC_DRIVER_HD,User,password);
        }catch (SQLException e ){
            LOGGER.warn(e.getMessage());
        }

    }

    public static ConnectionSource getConnectionSource () {
        if (connectionSource  == null){
            createConnectionSource();
        }
        return connectionSource;


    }



    public static void closeConnectionSource () {

        if ( connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
               LOGGER.warn(e.getMessage());
            }
        }

    }

    public  static void creatTabe () {

        try {
            TableUtils.createTableIfNotExists(connectionSource, Author. class);
            TableUtils.createTableIfNotExists(connectionSource, Book.class);
            TableUtils.createTableIfNotExists(connectionSource, Category.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }

    }


    public static void dropTable () {


        try {
            TableUtils.dropTable(connectionSource, Author. class,true);
            TableUtils.dropTable(connectionSource, Book.class,true);
            TableUtils.dropTable(connectionSource, Category.class,true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }























}
