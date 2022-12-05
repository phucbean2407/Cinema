package fa.training.service.utils;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class DateTimeUtils {
  //utils dung để các class sữ dụng nhiều lần
    public static Date fromStringToDate(String a){
        return new Date();
    }

    public static  Date fromStringToTime(String a) throws ParseException {
        DateFormat format = new SimpleDateFormat("HH:mm");
        return new Date((format.parse(a).getTime()));
    }
}
