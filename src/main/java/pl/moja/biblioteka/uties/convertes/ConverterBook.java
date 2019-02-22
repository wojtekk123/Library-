package pl.moja.biblioteka.uties.convertes;

import pl.moja.biblioteka.database.models.Book;
import pl.moja.biblioteka.modelFX.BookFX;
import pl.moja.biblioteka.uties.exception.Uties;

import javax.swing.plaf.PanelUI;

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

    public static BookFX converterToBookFx (Book book){

        BookFX bookFX = new BookFX();
        bookFX.setTitle(book.getTitle());
        bookFX.setId(book.getId());
        bookFX.setDiscription(book.getDescription());
        bookFX.setRating(book.getRanking());
        bookFX.setIsbn(book.getIsbn());
        bookFX.setReleseData(Uties.covereterToLocalData(book.getRealseData()));
        bookFX.setAuthorFX(ConverterAuthor.convertToAutorFx(book.getAuthor()));
        bookFX.setCategoryFX(ConverterCategory.convertToCategoty(book.getCategory()));
        return bookFX;

    }


}
