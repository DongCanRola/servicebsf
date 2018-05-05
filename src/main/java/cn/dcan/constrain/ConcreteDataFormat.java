package cn.dcan.constrain;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by dongc_000 on 2018/5/5.
 */
public class ConcreteDataFormat {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Date StringToDate(String initial) {
        Date date = null;
        if(initial != null) {
            try {
                date = dateFormat.parse(initial);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public String DateToString(Date date) {
        return dateFormat.format(date);
    }
}
