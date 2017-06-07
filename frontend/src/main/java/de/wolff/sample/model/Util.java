package de.wolff.sample.model;

import javax.ws.rs.BadRequestException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Util {

    static Date dateFromWWWForm(String date) {
        if (date == null || date.isEmpty()) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new BadRequestException(e);
        }
    }

    static String dateToWWWForm(Date date){
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}
