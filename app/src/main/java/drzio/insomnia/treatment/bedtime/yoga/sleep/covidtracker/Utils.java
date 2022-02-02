package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker;

import android.util.Log;

import org.joda.time.LocalDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.stateWise.CovidStateWiseListDetailsModel;

public class Utils {

    public static List<CovidStateWiseListDetailsModel> indiadatalist = new ArrayList();


    public static Date getDate(String dates){
        LocalDate date2 = LocalDate.now();
        int years = date2.getYear();
        int months = date2.getMonthOfYear();
        int days = date2.getDayOfMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(dates);
        sb.append(", ");
        sb.append(years);
        String string = sb.toString();
        DateFormat format = new SimpleDateFormat("dd MMMM ", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(string);
            Log.e("date",date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("dateerror",e.getMessage());
        }
        return date;
    }

    public static Date getDateformate2(String dates){
        LocalDate date2 = LocalDate.now();
        int years = date2.getYear();
        int months = date2.getMonthOfYear();
        int days = date2.getDayOfMonth();
        StringBuilder sb = new StringBuilder();
        sb.append(dates);
        sb.append(", ");
        sb.append(years);
        String string = sb.toString();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(string);
            Log.e("date",date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            Log.e("dateerror",e.getMessage());
        }
        return date;
    }
}
