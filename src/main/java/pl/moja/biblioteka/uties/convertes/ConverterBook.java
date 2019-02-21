package pl.moja.biblioteka.uties.convertes;

import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.modelFX.BookFX;
import pl.moja.biblioteka.uties.exception.Uties;

/**
 * Created by wojtek on 20.02.2019.
 */
public class ConverterBook {

    public static Book convertToBook (BookFX bookFX) {

        Book  book = new Book();
        book.setId(bookFX.getId());
        book.setTitle(bookFX.getTitle());
        book.setDescription(bookFX.getDiscription());
        book.setRanking(bookFX.getRating());
        book.setIsbn(bookFX.getIsbn());
        book.setRealseData(Uties.convertToDate(bookFX.getReleseData()));

        return book;

    }


}
