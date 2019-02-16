package pl.moja.biblioteka.uties.convertes;

import com.sun.org.apache.regexp.internal.RE;
import pl.moja.biblioteka.database.models.Category;
import pl.moja.biblioteka.modelFX.CategoryFx;

/**
 * Created by wojtek on 15.02.2019.
 */
public class ConverterCategory {

    public static CategoryFx convertToCategoty (Category category) {

        CategoryFx  categoryFx = new CategoryFx();
        categoryFx.setId(category.getId());
        categoryFx.setName(category.getName());
        return categoryFx;

    }

}
