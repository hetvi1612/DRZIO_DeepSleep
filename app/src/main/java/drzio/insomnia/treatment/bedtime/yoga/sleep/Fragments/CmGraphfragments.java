package drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment.Fragment_Report;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.MyMarkerView;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.StickyDateAxisValueFormatter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.getCalculatedDate;


public class CmGraphfragments extends Fragment implements OnChartValueSelectedListener {
    private View v;
    private GraphdataOperations graphdataOperations;
    private List<GraphData> mGraphdatalist;
    private List<GraphData> dataList;
    private StickyDateAxisValueFormatter xAxisFormatter;
    private LineChart chart;
    public TinyDB mTinydb;
    private int tempcount = -1;
    private boolean isCmkey = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_cmgraph, container, false);
        mTinydb = new TinyDB(getContext());
        String languages = mTinydb.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
      /*  ((Activity_Reports) getActivity()).setFragmentRefreshListener(new Activity_Reports.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                onDestroy();
            }
        });*/
        Fragment_Report.setFragmentRefreshListener(new Fragment_Report.FragmentRefreshListener() {
            @Override
            public void onRefresh() {
                onDestroy();
            }
        });

        return this.v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart = view.findViewById(R.id.chart1);

        graphdataOperations = new GraphdataOperations(getContext());
        mTinydb = new TinyDB(getContext());
        String Gheight = String.valueOf(mTinydb.getFloat(Constants.HEIGHT_KEY));
        Log.e("hri", Gheight);
        int mHftvalue;
        int mHinchvalue;
        String CM = null;
        isCmkey = mTinydb.getBoolean(Constants.ISCM_KEY);
        if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
            float height = round(Float.parseFloat(Gheight), 1);
            CM = Gheight;
        } else {
         /*   int indexOfDecimal = Gheight.indexOf(".");
            mHftvalue = Integer.parseInt(Gheight.substring(0, indexOfDecimal));
            String mTrim = Gheight.substring(indexOfDecimal).replace(".", "");
            mHinchvalue = Integer.parseInt(mTrim);
            Log.e("hright_data", String.valueOf(mHinchvalue));
            double d = convertFeetandInchesToCentimeter(String.valueOf(mHftvalue), String.valueOf(mHinchvalue));
//            cmdetails.add(String.valueOf(round((float) d,0)));
            CM = String.valueOf(round((float) d,0));
*/


            float mData = mTinydb.getFloat(Constants.HEIGHT_KEY);
            String doubleAsString = String.valueOf(mData);
            Log.e("hright_datadoub", String.valueOf(doubleAsString));
            int indexOfDecimal = doubleAsString.indexOf(".");
            mHftvalue = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
            String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
            mHinchvalue = Integer.parseInt(mTrim);
            double d = convertFeetandInchesToCentimeter(String.valueOf(mHftvalue), String.valueOf(mHinchvalue));
//            cmdetails.add(String.valueOf(round((float) d,0)));
            CM = String.valueOf(round((float) d, 0));

            Log.e("hright_data", String.valueOf(mHinchvalue));


        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateandTime = sdf.format(new Date());

        final TextView sticky = view.findViewById(R.id.sticky_label);

        TextView mTvtitle = view.findViewById(R.id.tvtitle);
        mTvtitle.setText(getContext().getResources().getString(R.string.height_graph));

        String tempdate2 = mTinydb.getString(Constants.GRAPHDATE_KEY);

        String height2 = String.valueOf(mTinydb.getFloat(Constants.HEIGHT_KEY));

        chart = view.findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.WHITE);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setOnChartValueSelectedListener(this);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);

        MyMarkerView mv = new MyMarkerView(getContext(), R.layout.custom_marker_view);
        mv.setChartView(chart);
        chart.setMarker(mv);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setVisibleXRange(3f, 20.0f);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.getAxisRight().setEnabled(false);
        chart.setPinchZoom(false);

        XAxis xAxis;
        {
            try {
                Date c2 = Calendar.getInstance().getTime();
                SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate2 = df2.format(c2);
                String tempdate = getCalculatedDate(formattedDate2, "dd-MM-yyyy", -3);

                DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy");

                LocalDate date = formatter.parseLocalDate(tempdate);
                int years = date.getYear();
                int months = date.getMonthOfYear();
                int dayOfMonths = date.getDayOfMonth();
                xAxisFormatter = new StickyDateAxisValueFormatter(chart, sticky, years, months, dayOfMonths);
            } catch (Exception e) {
                e.printStackTrace();
                xAxisFormatter = new StickyDateAxisValueFormatter(chart, sticky, 2020, 1, 1);
            }
            xAxis = chart.getXAxis();
            xAxis.setValueFormatter(xAxisFormatter);
            xAxis.setTextColor(getContext().getResources().getColor(R.color.headercolor));
            xAxis.enableGridDashedLine(10f, 10f, 0f);

        }

        YAxis yAxis;
        {
            yAxis = chart.getAxisLeft();
            yAxis.setTextColor(getContext().getResources().getColor(R.color.headercolor));
            yAxis.enableGridDashedLine(10f, 10f, 0f);
            int height = (int) mTinydb.getFloat(Constants.HEIGHT_KEY);

            if (isCmkey) {
                if (height > 300) {
                    yAxis.setAxisMaximum(800f);
                } else {
                    yAxis.setAxisMaximum(600f);
                }
            } else {
                if (height > 8) {
                    yAxis.setAxisMaximum(22f);
                } else {
                    yAxis.setAxisMaximum(16f);
                }
            }
            yAxis.setAxisMinimum(0f);
        }

        {
            LimitLine llXAxis = new LimitLine(9f, "Index 10");
            llXAxis.setLineWidth(4f);
            llXAxis.enableDashedLine(10f, 10f, 0f);
            llXAxis.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            llXAxis.setTextSize(10f);

            LimitLine ll1 = new LimitLine(150f, "Upper Limit");
            ll1.setLineWidth(4f);
            ll1.enableDashedLine(10f, 10f, 0f);
            ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
            ll1.setTextSize(10f);

            LimitLine ll2 = new LimitLine(-30f, "Lower Limit");
            ll2.setLineWidth(4f);
            ll2.enableDashedLine(10f, 10f, 0f);
            ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
            ll2.setTextSize(10f);

            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

        }


        SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
        currentDateandTime = sdf2.format(new Date());
        if (graphdataOperations.CheckHistoryDBEmpty() == 0) {
            graphdataOperations.insertgraphdata(currentDateandTime, height2, String.valueOf(160));
            Log.e("hrightdta11", String.valueOf(height2));
        } else {
            mGraphdatalist = graphdataOperations.getgraphdata();
            for (int i = 0; i < mGraphdatalist.size(); i++) {
                GraphData data = mGraphdatalist.get(i);
                if (!data.getDate().equals(currentDateandTime)) {
                    graphdataOperations.insertgraphdata(currentDateandTime, height2, String.valueOf(160));
                    Log.e("hrightdta51", String.valueOf(height2));
                }
                if (data.getDate().equals(currentDateandTime)) {
                    graphdataOperations.update(currentDateandTime, height2, String.valueOf(160));
                    Log.e("hrightdta112", String.valueOf(height2));
                }
            }
        }

        setData(1095, 150);

        int todaypos;
        if (getDayofyear(currentDateandTime) < 5) {
            todaypos = 1;
        } else {
            todaypos = getDayofyear(currentDateandTime) - 4;
        }
        chart.moveViewToX(todaypos);
        chart.zoom(170f, 2f, 5f, 0f);

        chart.animateY(1500, Easing.EaseInOutQuad);
        Legend l = chart.getLegend();
        l.setForm(Legend.LegendForm.LINE);


    }


    private void setData(int count, float range) {
        dataList = graphdataOperations.getgraphdata();
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float val = 0;
            if (dataList.size() != 0) {
                if (i == getDayofyear(dataList.get(0).getDate())) {
                    tempcount++;
                }
                try {
                    if (tempcount >= 0) {
                        Log.e("tempnumber", String.valueOf(tempcount));
                        if (i == getDayofyear(dataList.get(tempcount).getDate())) {
                            val = Float.parseFloat(dataList.get(tempcount).getHeight());
                            Log.e("hrightval1", String.valueOf(val));
                            values.add(new Entry(i, val));
                            if (tempcount <= dataList.size()) {
                                if (tempcount != dataList.size()) {
                                    tempcount++;
                                }
                            }
                        } else {
                            values.add(new Entry(i, 0));
                        }
                    } else {
                        values.add(new Entry(i, 0));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    values.add(new Entry(i, 0));
                }
            } else {
                values.add(new Entry(i, 0));
            }
        }

        LineDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            set1.notifyDataSetChanged();
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values, "");

            set1.setDrawIcons(false);
            set1.setDrawValues(false);
            set1.setColor(getResources().getColor(R.color.tbtncolor));
            set1.setCircleColor(getResources().getColor(R.color.tbtncolor));
            set1.setLineWidth(1.5f);
            set1.setCircleRadius(0f);

            set1.setDrawCircles(false);
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setDrawCircleHole(false);

            set1.setFormLineWidth(1f);
            set1.setFormSize(15.f);
            set1.setValueTextSize(9f);

            set1.setDrawFilled(true);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return chart.getAxisLeft().getAxisMinimum();
                }
            });

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            chart.setData(data);
        }
    }

    public int getDayofyear(String input_date) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        Date dt1 = null;
        try {
            dt1 = format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat format2 = new SimpleDateFormat("EEEE");
        String finalDay = format2.format(dt1);

        Calendar c = Calendar.getInstance();
        c.setTime(dt1);
        int dayofyear = c.get(Calendar.DAY_OF_YEAR);
        Log.e("dayofyear", String.valueOf(dayofyear));
        return dayofyear;
    }


    public double convertFeetandInchesToCentimeter(String feet, String inches) {
        double heightInFeet = 0;
        double heightInInches = 0;
        try {
            if (feet != null && feet.trim().length() != 0) {
                heightInFeet = Double.parseDouble(feet);
            }
            if (inches != null && inches.trim().length() != 0) {
                heightInInches = Double.parseDouble(inches);
            }
        } catch (NumberFormatException nfe) {

        }
        Log.e("hhhh", String.valueOf(heightInFeet));
        Log.e("iiiii", String.valueOf(heightInInches));
        return (heightInFeet * 30.48) + (heightInInches * 2.54);

    }

    public String cmtofeet(int val) {
        String feetval;
        float mCmval = val;
        float feet = (float) (mCmval / 30.48);
        feetval = String.valueOf(feet);
        return feetval;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                if (getFragmentManager() != null) {
                    getFragmentManager()
                            .beginTransaction()
                            .detach(CmGraphfragments.this)
                            .attach(CmGraphfragments.this)
                            .commit();
                }
            } catch (Exception e) {
                if (getFragmentManager() != null) {
                    getFragmentManager()
                            .beginTransaction()
                            .detach(CmGraphfragments.this)
                            .attach(CmGraphfragments.this)
                            .commitAllowingStateLoss();
                }

            }

        }
    }

    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
