package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.flyco.tablayout.SlidingTabLayout;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.CategoryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatalist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatamodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestSubcatdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Testcatdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.catnamearray;

public class Store_Activity_Main extends AppCompatActivity {

    //    private ArrayList<String> stringArray = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private boolean success;
    private Gson gson;
    private boolean success2;
    private boolean success3;
    private RelativeLayout mNointernet;
    private BackpainAPIInterface apiInterface;
    private RelativeLayout mLoadlay;
    //    public ArrayList<TestDatalist> tempdatalist = new ArrayList<>();
    private SlidingTabLayout mCattab;
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.appstore_main);
        mNointernet = (RelativeLayout) findViewById(R.id.latoutnonet);
        mNointernet.setVisibility(View.GONE);
        mLoadlay = (RelativeLayout) findViewById(R.id.loadlayout);
        LottieAnimationView mLottie = (LottieAnimationView) findViewById(R.id.lotti2);
        mLottie.setVisibility(View.VISIBLE);
        mLottie.setAnimation("loadanimdial.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        tinyDB = new TinyDB(Store_Activity_Main.this);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

        TextView mTvretry = findViewById(R.id.tvretry);
        ImageView mBtnback = (ImageView) findViewById(R.id.btnback);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mCattab = (SlidingTabLayout) findViewById(R.id.catetab);
        if (Constants.storedatalist != null && Constants.storedatalist.size() != 0){
            mLoadlay.setVisibility(View.GONE);
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            for (String title : catnamearray) {
                mFragments.add(Store_Fragment_Data.getInstance(title, Constants.storedatalist));
            }
            viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
            mCattab.setViewPager(viewPager);
        }else {
            mLoadlay.setVisibility(View.VISIBLE);
            gson = new Gson();
            callCategoryApi();
            mTvretry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mLoadlay.setVisibility(View.VISIBLE);
                    gson = new Gson();
                    callCategoryApi();
                }
            });
        }
    }


    @SuppressLint("WrongConstant")
    public void callCategoryApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getAppstoreClient().create(BackpainAPIInterface.class);
            this.apiInterface.getAppCategory().enqueue(new Callback<CategoryData>() {
                @Override
                public void onResponse(@NotNull Call<CategoryData> call, @NotNull Response<CategoryData> response) {
                    try {


                        CategoryData dietCateData = (CategoryData) response.body();
                        assert dietCateData != null;
                        ArrayList<CategoryData.Datalist> dataist = dietCateData.dataist;
                        CategoryData.Datalist.setCategoryModals(dataist);
                        Testcatdata testcatdata = new Testcatdata();
                        ArrayList<Testcatdata> testcatdataArrayList = new ArrayList<>();
                        for (int i = 0; i < dataist.size(); i++) {
                            CategoryData.Datalist catedata = dataist.get(i);
                            testcatdata.setCatid(catedata.getId());
                            testcatdata.setCatname(catedata.getName());
                            catnamearray.add(catedata.getName());
                            testcatdataArrayList.add(testcatdata);
                            int last = dataist.size() - 1;
                            if (i == last) {
                                callSubcatApi(catedata.getId(), catedata.getName(), true);
                            } else {
                                callSubcatApi(catedata.getId(), catedata.getName(), false);
                            }
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(@NotNull Call<CategoryData> call, @NotNull Throwable t) {
                    mLoadlay.setVisibility(View.GONE);
                    mNointernet.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mLoadlay.setVisibility(View.GONE);
            mNointernet.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("WrongConstant")
    public void callSubcatApi(String catid, String catname, boolean islast) {
        try {
            String appid = tinyDB.getString(Constants.Appstore_Appid);
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getAppstoreClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("cat_id", catid)
                    .addFormDataPart("app_id", appid)
                    .build();
            this.apiInterface.getStoredata(requestBody).enqueue(new Callback<TestDatamodel>() {
                @Override
                public void onResponse(@NotNull Call<TestDatamodel> call, @NotNull Response<TestDatamodel> response) {
                    try {
                        TestDatamodel subcatData = (TestDatamodel) response.body();
                        ArrayList<TestSubcatdata> dataist = subcatData.dataist;
                        for (int i = 0; i < dataist.size(); i++) {
                            try {
                                TestSubcatdata testSubcatdata = dataist.get(i);
                                TestDatalist testDatalist = new TestDatalist();
                                testDatalist.setCatename(catname);
                                testDatalist.setId(testSubcatdata.subcatid);
                                testDatalist.setSubcatname(testSubcatdata.subcatname);
                                String json = gson.toJson((Object) testSubcatdata.subcatdata);
                                testDatalist.setDatajson(json);
                                Constants.storedatalist.add(testDatalist);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        if (islast) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Log.e("appp3", String.valueOf(Constants.storedatalist.size()));
                                    mLoadlay.setVisibility(View.GONE);
                                    ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
                                    for (String title : catnamearray) {
                                        mFragments.add(Store_Fragment_Data.getInstance(title, Constants.storedatalist));
                                    }
                                    viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
                                    mCattab.setViewPager(viewPager);
                                }
                            }, 1000);
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(@NotNull Call<TestDatamodel> call, @NotNull Throwable t) {
                  //  Log.e("appp3", Objects.requireNonNull(t.getMessage()));
                    mLoadlay.setVisibility(View.GONE);
                    mNointernet.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mLoadlay.setVisibility(View.GONE);
            mNointernet.setVisibility(View.VISIBLE);
        }
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        @SuppressLint("WrongConstant")
        public MyPagerAdapter(FragmentManager fm) {
            super(fm, 0);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public String getPageTitle(int position) {
            if (catnamearray != null && catnamearray.size() != 0){
                return catnamearray.get(position);
            }else {
                return "";
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
