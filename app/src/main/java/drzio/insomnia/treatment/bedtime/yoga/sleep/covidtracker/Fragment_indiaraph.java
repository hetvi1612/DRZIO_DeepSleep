package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
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

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.CovidTimeDetailsModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.Indiagraphtype;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.TimeDetailsModelList;


public class Fragment_indiaraph extends Fragment implements OnChartValueSelectedListener {
    private View v;
    private GraphAxisValueFormatter xAxisFormatter;
    private LineChart chart;
    private int tempcount = -1;
    private boolean isCmkey = false;
    private int dayofyear;
    public List<CovidTimeDetailsModel> timeDetailsModelList = new ArrayList();
    private TextView mTvtitle;
    private ImageView mIvcolor;
    private TinyDB tinyDB;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_indiagraph, container, false);
        tinyDB = new TinyDB(getContext());

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);

        return this.v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chart = view.findViewById(R.id.chart1);

        timeDetailsModelList = TimeDetailsModelList;

        Date dt1 = Utils.getDate(timeDetailsModelList.get(0).getDate());
        Calendar c = Calendar.getInstance();
        c.setTime(dt1);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        dayofyear = c.get(Calendar.DAY_OF_YEAR);

        TextView mBtncases = (TextView) view.findViewById(R.id.tvbtncases);
        TextView mBtndeaths = (TextView) view.findViewById(R.id.tvbtndeaths);
        TextView mBtnrecover = (TextView) view.findViewById(R.id.tvbtnrecover);


        mTvtitle = (TextView) view.findViewById(R.id.tvtitle);
        mIvcolor = (ImageView) view.findViewById(R.id.ivtempcolor);

        chart = view.findViewById(R.id.chart1);
        chart.setBackgroundColor(Color.WHITE);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setOnChartValueSelectedListener(this);
        chart.setBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(false);

        CovidMarkerView mv = new CovidMarkerView(getContext(), R.layout.covid_marker_view);
        mv.setChartView(chart);
        chart.setMarker(mv);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.setVisibleXRange(2f, 20.0f);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.getAxisRight().setEnabled(false);
        chart.setPinchZoom(false);

        mBtncases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Indiagraphtype = "cases";
                setUserVisibleHint(true);
            }
        });

        mBtndeaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Indiagraphtype = "deaths";
                setUserVisibleHint(true);

            }
        });


        mBtnrecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Indiagraphtype = "recovers";
                setUserVisibleHint(true);

            }
        });


        XAxis xAxis;
        {
            xAxisFormatter = new GraphAxisValueFormatter(chart);
            xAxis = chart.getXAxis();
            xAxis.setValueFormatter(xAxisFormatter);
            xAxis.setTextColor(getContext().getResources().getColor(R.color.tabunselectedtxt));
            xAxis.enableGridDashedLine(10f, 10f, 0f);
        }

        YAxis yAxis;
        {
            yAxis = chart.getAxisLeft();
            yAxis.setTextColor(getContext().getResources().getColor(R.color.tabunselectedtxt));
            yAxis.enableGridDashedLine(10f, 10f, 0f);
            if (Indiagraphtype.equals("deaths")){
                yAxis.setAxisMaximum(16000);
                yAxis.setAxisMinimum(2000);
            }else if (Indiagraphtype.equals("recovers")){
                yAxis.setAxisMaximum(200000);
                yAxis.setAxisMinimum(28000);
            }else {
                yAxis.setAxisMaximum(400000);
                yAxis.setAxisMinimum(30000);
            }

            yAxis.setDrawLimitLinesBehindData(true);
            xAxis.setDrawLimitLinesBehindData(true);

        }
        if (Indiagraphtype.equals("deaths")){
            mTvtitle.setText("Deaths");
            mIvcolor.setBackgroundColor(getResources().getColor(R.color.deathnumcolor));
            mBtncases.setBackgroundResource(R.drawable.main_border);
            mBtndeaths.setBackgroundResource(R.drawable.main_border3);
            mBtnrecover.setBackgroundResource(R.drawable.main_border);
            setDataDeaths();
            chart.zoom(0f, 2f, 0f, 0f);
            chart.animateXY(800,100, Easing.EaseInOutCubic);
            Legend l = chart.getLegend();
            l.setForm(Legend.LegendForm.NONE);
        }else if (Indiagraphtype.equals("recovers")){
            mTvtitle.setText("Recoveries");
            mIvcolor.setBackgroundColor(getResources().getColor(R.color.recovernumcolor));
            mBtncases.setBackgroundResource(R.drawable.main_border);
            mBtndeaths.setBackgroundResource(R.drawable.main_border);
            mBtnrecover.setBackgroundResource(R.drawable.main_border3);
            setDataRecover();
            chart.zoom(0f, 2f, 0f, 0f);
            chart.animateXY(800,100, Easing.EaseInOutCubic);
            Legend l = chart.getLegend();
            l.setForm(Legend.LegendForm.NONE);
        }else {
            mTvtitle.setText("Confirmed cases");
            mIvcolor.setBackgroundColor(getResources().getColor(R.color.casenumcolor));
            mBtncases.setBackgroundResource(R.drawable.main_border3);
            mBtndeaths.setBackgroundResource(R.drawable.main_border);
            mBtnrecover.setBackgroundResource(R.drawable.main_border);
            setDataCases();
            chart.zoom(0f, 2f, 0f, 0f);
            chart.animateXY(800,100, Easing.EaseInOutCubic);
            Legend l = chart.getLegend();
            l.setForm(Legend.LegendForm.NONE);
        }



    }


    private void setDataCases() {
        ArrayList<Entry> values = new ArrayList<>();
        int plusval = dayofyear + 11;
        int minusval = dayofyear;
        int temp = 0;
        for (int i = minusval; i <plusval; i++) {
            if (temp < 11){
                int yval = Integer.parseInt(timeDetailsModelList.get(temp).getTotalconfirmed());
                Log.e("tempval",String.valueOf(yval));
                values.add(new Entry(i, yval));
                temp++;
            }else {
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
            set1.setHighLightColor(R.color.tabunselectedtxt);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setDrawIcons(false);
            set1.setDrawValues(false);
            set1.setColor(getResources().getColor(R.color.casenumcolor));
            set1.setCircleColor(getResources().getColor(R.color.casenumcolor));
            set1.setLineWidth(1.5f);
            set1.setCircleRadius(2f);

            set1.setDrawCircles(true);
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

            if (com.github.mikephil.charting.utils.Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.icasegraphbg);
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

    private void setDataDeaths() {
        ArrayList<Entry> values = new ArrayList<>();
        int plusval = dayofyear + 11;
        int minusval = dayofyear;
        int temp = 0;
        for (int i = minusval; i <plusval; i++) {
            if (temp < 11){
                int yval = Integer.parseInt(timeDetailsModelList.get(temp).getTotaldeceased());
                Log.e("tempval",String.valueOf(yval));
                values.add(new Entry(i, yval));
                temp++;
            }else {
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
            set1.setHighLightColor(R.color.tabunselectedtxt);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setDrawIcons(false);
            set1.setDrawValues(false);
            set1.setColor(getResources().getColor(R.color.deathnumcolor));
            set1.setCircleColor(getResources().getColor(R.color.deathnumcolor));
            set1.setLineWidth(1.5f);
            set1.setCircleRadius(2f);

            set1.setDrawCircles(true);
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

            if (com.github.mikephil.charting.utils.Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ideathgraphbg);
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

    private void setDataRecover() {
        ArrayList<Entry> values = new ArrayList<>();
        int plusval = dayofyear + 11;
        int minusval = dayofyear;
        int temp = 0;
        for (int i = minusval; i <plusval; i++) {
            if (temp < 11){
                int yval = Integer.parseInt(timeDetailsModelList.get(temp).getTotalrecovered());
                values.add(new Entry(i, yval));
                temp++;
            }else {
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
            set1.setHighLightColor(R.color.tabunselectedtxt);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setDrawIcons(false);
            set1.setDrawValues(false);
            set1.setColor(getResources().getColor(R.color.recovernumcolor));
            set1.setCircleColor(getResources().getColor(R.color.recovernumcolor));
            set1.setLineWidth(1.5f);
            set1.setCircleRadius(2f);

            set1.setDrawCircles(true);
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

            if (com.github.mikephil.charting.utils.Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.irecovergraphbg);
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
                            .detach(Fragment_indiaraph.this)
                            .attach(Fragment_indiaraph.this)
                            .commit();
                }
            } catch (Exception e) {
                if (getFragmentManager() != null) {
                    getFragmentManager()
                            .beginTransaction()
                            .detach(Fragment_indiaraph.this)
                            .attach(Fragment_indiaraph.this)
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
