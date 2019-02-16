package pl.moja.biblioteka.uties.convertes;

import pl.moja.biblioteka.database.models.Author;
import pl.moja.biblioteka.modelFX.AuthorFX;

import java.util.AbstractCollection;

/**
 * Created by wojtek on 15.02.2019.
 */
public class ConverterAuthor {
    public static Author convertToAuthor(AuthorFX authorFX){

        Author author = new Author();
        author.setId(authorFX.getId());
        author.setName(authorFX.getName());
        author.setSurname(authorFX.getName());
        return author;
    }

    public static AuthorFX convertToAutorFx (Author author) {
        AuthorFX authorFX = new AuthorFX();
        authorFX.setId(author.getId());
        authorFX.setName(author.getName());
        authorFX.setSurname(author.getSurname());
        return authorFX;
    }
}

