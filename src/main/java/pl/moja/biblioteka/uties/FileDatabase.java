package pl.moja.biblioteka.uties;

import com.j256.ormlite.dao.DaoManager;
import pl.moja.biblioteka.database.dao.BookDao;
import pl.moja.biblioteka.database.dao.CategoryDao;
import pl.moja.biblioteka.database.dbUtis.DBMenager;
import pl.moja.biblioteka.database.models.Author;
import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.database.models.Category;
import pl.moja.biblioteka.uties.exception.AplicationException;

import java.util.Date;

/**
 * Created by wojtek on 15.02.2019.
 */
public class FileDatabase {

    public  static void FillDataBase () {



        Category category1 = new Category();
        category1.setName("Dramat");
        Author author1 = new Author();
        author1.setName("Mariusz");
        author1.setSurname("Pudzianowski");
        Book book1 = new Book();
        book1.setAuthor(author1);
        book1.setCategory(category1);
        book1.setTitle("Siłaczka");
        book1.setDataAdd(new Date());
        book1.setIsbn("12345");
        book1.setRanking(5);
        book1.setRealseData(new Date());

        Category category2 = new Category();
        category2.setName("Semsacja");
        CategoryDao categoryDao = new CategoryDao();
        try {
            categoryDao.creatOrUpdate(category2);
        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }


        Category category3 = new Category();
        category3.setName("Przygodowe");
        Author author2 = new Author();
        author2.setName("Andrzej");
        author2.setSurname("Sapkowski");
        Book book2 = new Book();
        book2.setAuthor(author2);
        book2.setCategory(category3);
        book2.setTitle("Wiedzmin");
        book2.setDataAdd(new Date());
        book2.setIsbn("123412");
        book2.setRanking(4);
        book2.setRealseData(new Date());

        Category category4 = new Category();
        category4.setName("Astronomia");
        Author author3 = new Author();
        author3.setName("Stephen");
        author3.setSurname("Hawking");
        Book book3 = new Book();
        book3.setAuthor(author3);
        book3.setCategory(category4);
        book3.setTitle("Krótka historia czasu");
        book3.setDataAdd(new Date());
        book3.setIsbn("31241");
        book3.setRanking(1);
        book3.setRealseData(new Date());
        book3.setDescription(" very interesting book");


        BookDao bookDao= new BookDao();
        try {
         bookDao.creatOrUpdate(book1);
         bookDao.creatOrUpdate(book2);
         bookDao.creatOrUpdate(book3);

        } catch (AplicationException e) {
            DialogUtils.errorDialog(e.getMessage());
        }




    }

}
