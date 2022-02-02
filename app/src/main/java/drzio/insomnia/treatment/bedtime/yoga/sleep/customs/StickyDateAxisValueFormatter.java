package drzio.insomnia.treatment.bedtime.yoga.sleep.customs;

import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class StickyDateAxisValueFormatter extends ValueFormatter {

    private Calendar c;
    private LineChart chart;
    private TextView sticky;
    private float lastFormattedValue = 1e9f;
    private int lastMonth = 0;
    private int lastYear = 0;
    private int stickyMonth = -1;
    private int stickyYear = -1;
    private SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM", Locale.getDefault());
    private int currentyear = 2020;
    private int currentmonth = 0;
    private int currentdate = 1;

    public StickyDateAxisValueFormatter(LineChart chart, TextView sticky, int currentyear, int currentmonth, int currentdate) {
        c = new GregorianCalendar();
        this.chart = chart;
        this.sticky = sticky;
        this.currentyear = currentyear;
//        this.currentmonth = currentmonth;
//        this.currentdate = currentdate;
    }


    @Override
    public String getFormattedValue(float value) {

        // Sometimes this gets called on values much lower than the visible range
        // Catch that here to prevent messing up the sticky text logic
        if (value < chart.getLowestVisibleX()) {
            return "";
        }

        // NOTE: I assume for this example that all data is plotted in days
        // since Jan 1, 2018. Update for your scheme accordingly.

        int days = (int) value;

        boolean isFirstValue = value < lastFormattedValue;
        if (isFirstValue) {
            // starting over formatting sequence
            lastMonth = 50;
            lastYear = 5000;

            c.set(currentyear, 0, 1);
            c.add(Calendar.DATE, (int) chart.getLowestVisibleX());

            stickyMonth = c.get(Calendar.MONTH);
            stickyYear = c.get(Calendar.YEAR);

            String stickyText = monthFormatter.format(c.getTime()) + " " + stickyYear;
            sticky.setText(stickyText);
        }


        c.set(currentyear, 0, 1);
        c.add(Calendar.DATE, days);
        Date d = c.getTime();

        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        String monthStr = monthFormatter.format(d);

        if ((month > stickyMonth || year > stickyYear) && isFirstValue) {
            stickyMonth = month;
            stickyYear = year;
            String stickyText = monthStr + " " + year;
            sticky.setText(stickyText);
        }

        String ret;

        if ((month > lastMonth || year > lastYear) && !isFirstValue) {
            ret = monthStr;
        } else {
            ret = Integer.toString(dayOfMonth);
        }

        lastMonth = month;
        lastYear = year;
        lastFormattedValue = value;

      //  Log.e("tempstring",ret);
        return ret;
    }



}