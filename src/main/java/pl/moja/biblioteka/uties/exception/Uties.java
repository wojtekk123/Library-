package pl.moja.biblioteka.uties.exception;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by wojtek on 20.02.2019.
 */
public class Uties {

    public static Date convertToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate covereterToLocalData (Date date){

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


}
