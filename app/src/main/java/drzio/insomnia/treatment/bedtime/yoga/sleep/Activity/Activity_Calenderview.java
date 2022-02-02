package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.applikeysolutions.cosmocalendar.model.Day;
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener;
import com.applikeysolutions.cosmocalendar.selection.SingleSelectionManager;
import com.applikeysolutions.cosmocalendar.selection.criteria.BaseCriteria;
import com.applikeysolutions.cosmocalendar.selection.criteria.WeekDayCriteria;
import com.applikeysolutions.cosmocalendar.selection.criteria.month.CurrentMonthCriteria;
import com.applikeysolutions.cosmocalendar.selection.criteria.month.NextMonthCriteria;
import com.applikeysolutions.cosmocalendar.selection.criteria.month.PreviousMonthCriteria;
import com.applikeysolutions.cosmocalendar.utils.SelectionType;
import com.applikeysolutions.cosmocalendar.view.CalendarView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.HistoryAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.HistoryProgressOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.ProgressModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Calenderview extends AppCompatActivity {
    private CalendarView calendarView;
    private List<BaseCriteria> threeMonthsCriteriaList;
    private WeekDayCriteria fridayCriteria;
    HistoryProgressOperations operations;
    private ArrayList<ProgressModal> progressModals = new ArrayList<>();
    private ArrayList<ProgressModal> tempdata = new ArrayList<>();
    private String mTempdata;
    private TinyDB tinydb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        tinydb = new TinyDB(Activity_Calenderview.this);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);


        setContentView(R.layout.activity_calenderview);
        RecyclerView mHRecycler = (RecyclerView) findViewById(R.id.reportdata);
        mHRecycler.setLayoutManager(new LinearLayoutManager(Activity_Calenderview.this, LinearLayoutManager.VERTICAL, true));
        operations = new HistoryProgressOperations(Activity_Calenderview.this);
        TextView mNodata = (TextView) findViewById(R.id.txtnodata);
        ImageView mImgback = (ImageView) findViewById(R.id.imgback);
        mImgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (operations.CheckHistoryDBEmpty() != 0) {
            progressModals = (ArrayList<ProgressModal>) operations.getAllDaysProgress();
            HistoryAdapter historyAdapter = new HistoryAdapter(Activity_Calenderview.this,progressModals);
            mHRecycler.setAdapter(historyAdapter);
        }
        TextView mBtnall = (TextView) findViewById(R.id.btnall);
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        mGbannerlay.setVisibility(View.GONE);
        Smallnative(this, mGbannerlay);
        calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setCalendarOrientation(OrientationHelper.HORIZONTAL);
        calendarView.setSelectionType(SelectionType.SINGLE);
        calendarView.setCurrentDayTextColor(R.color.tbtncolor);
        calendarView.setFirstDayOfWeek(1);
        calendarView.setSelectedDayBackgroundStartColor(getResources().getColor(R.color.tbtncolor));
        calendarView.setSelectedDayBackgroundEndColor(getResources().getColor(R.color.tbtncolor));
        calendarView.setSelectedDayBackgroundColor(getResources().getColor(R.color.tbtncolor));
        calendarView.setSelectionManager(new SingleSelectionManager(new OnDaySelectedListener() {
            @Override
            public void onDaySelected() {
                try {
                    tempdata.clear();
                    if (calendarView.getSelectedDates().size() != 0){
                         Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                        String formattedDate = df.format(calendarView.getSelectedDates().get(0).getTime());
                        progressModals = (ArrayList<ProgressModal>) operations.getAllDaysProgress();
                        for (int i = 0;i<progressModals.size();i++){
                            ProgressModal data = progressModals.get(i);
                            Log.e("selecteddate",data.getExe_date());
                            if (data.getExe_date().equals(formattedDate)){
                                tempdata.add(data);
                            }
                        }
                        if (tempdata.size() !=0){
                            mNodata.setVisibility(View.GONE);
                            mHRecycler.setVisibility(View.VISIBLE);
                            if (operations.CheckHistoryDBEmpty() != 0) {
                                HistoryAdapter historyAdapter = new HistoryAdapter(Activity_Calenderview.this,tempdata);
                                mHRecycler.setAdapter(historyAdapter);
                                historyAdapter.notifyDataSetChanged();
                            }
                        }else {
                            if (isDayafter(formattedDate)){
                                mNodata.setText("Sorry! You can't read future!");
                            }else {
                                mNodata.setText("Oops! You missed workout this very day!");

                            }
                            mNodata.setVisibility(View.VISIBLE);
                            mHRecycler.setVisibility(View.GONE);
                        }
//                    calendarView.clearSelections();
//                    UpdatSelection();
//                    createCriterias();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }));

        mBtnall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operations.CheckHistoryDBEmpty() != 0) {
                    mNodata.setVisibility(View.GONE);
                    mHRecycler.setVisibility(View.VISIBLE);
                    progressModals = (ArrayList<ProgressModal>) operations.getAllDaysProgress();
                    HistoryAdapter historyAdapter = new HistoryAdapter(Activity_Calenderview.this,progressModals);
                    mHRecycler.setAdapter(historyAdapter);
                    historyAdapter.notifyDataSetChanged();
                }
            }
        });

        calendarView.clearSelections();
        mTempdata = " ";
        UpdatSelection();
        createCriterias();

    }

    public void UpdatSelection(){
        if (progressModals!= null && progressModals.size() != 0){
            for (int i = 0;i<progressModals.size();i++){
                if (!mTempdata.equals(progressModals.get(i).getExe_date())){
                    mTempdata = progressModals.get(i).getExe_date();
                     Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                    Day secondDay = null;
                    try {
                        secondDay = new Day(df.parse(mTempdata));
                        calendarView.getSelectionManager().toggleDay(secondDay);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void createCriterias() {
        fridayCriteria = new WeekDayCriteria(Calendar.FRIDAY);

        threeMonthsCriteriaList = new ArrayList<>();
        threeMonthsCriteriaList.add(new CurrentMonthCriteria());
        threeMonthsCriteriaList.add(new NextMonthCriteria());
        threeMonthsCriteriaList.add(new PreviousMonthCriteria());
    }


    public static void Smallnative(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_native_banner, null);
                populateSmallContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);

            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Log.e("error", "Failed to load native ad:: " + i);
                FitnessApplication.AdfailToast("MainActivity Small Native", String.valueOf(i));
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("WrongConstant")
    public static void populateSmallContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((Button) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        com.google.android.gms.ads.formats.NativeAd.Image logo = nativeContentAd.getLogo();
        if (logo == null) {
            nativeContentAdView.getLogoView().setVisibility(4);
        } else {
            ((ImageView) nativeContentAdView.getLogoView()).setImageDrawable(logo.getDrawable());
            nativeContentAdView.getLogoView().setVisibility(0);
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
    }


    public boolean isDayafter(String inputdate){
        boolean isbefore = false;
        Calendar calCurr = Calendar.getInstance();
        Calendar day = Calendar.getInstance();
        try {
            day.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(inputdate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (day.after(calCurr)) {
            isbefore = true;
        }else {
            isbefore = false;
        }
        return isbefore;
    }



    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
