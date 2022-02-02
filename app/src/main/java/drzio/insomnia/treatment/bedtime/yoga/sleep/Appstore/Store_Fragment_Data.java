package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Appdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.SubcatData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatalist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PagerContainer;


public class Store_Fragment_Data extends Fragment {

    private TinyDB tinyDB;
    private static int tempos = 0;
    private static Gson gson;
    private ArrayList<Appdata.Datalist> mTopbannerdatamodel = new ArrayList<>();
    private ArrayList<Appdata.Datalist> mTopappdatamodel = new ArrayList<>();
    private ArrayList<Appdata.Datalist> mAppdatamodel = new ArrayList<>();
    private ArrayList<Appdata.Datalist> mBottombannerdatamodel = new ArrayList<>();
    private List<SubcatData.Datalist> mSubcat = new ArrayList<>();
    private String mIntentjson;
    private ArrayList<Appdata.Datalist> mAllappslist = new ArrayList<>();
    private int Lastpos;
    private String catname = "";
    private boolean isFirst = true;
    private LinearLayout mTxtlayout1;
    private LinearLayout mTxtlayout2;
    private LinearLayout mTxtlayout3;
    private LinearLayout mTxtlayout4;
    String mTxtsubcat1 = "";
    String mTxtsubcat2 = "";
    String mTxtsubcat3 = "";
    String mTxtsubcat4 = "";
    private String subcatid = "";
    private BackpainAPIInterface apiInterface;
    public ArrayList<TestDatalist> tempdatalist = new ArrayList<>();
    private ArrayList<Appdata.Datalist> mAllappslist1 = new ArrayList<>();
    private ArrayList<Appdata.Datalist> mAllappslist2 = new ArrayList<>();
    private ArrayList<Appdata.Datalist> mAllappslist3 = new ArrayList<>();
    private ArrayList<Appdata.Datalist> mAllappslist4 = new ArrayList<>();
    private TextView mSubcat1;
    private TextView mSubcat2;
    private TextView mSubcat3;
    private TextView mSubcat4;



    public static Store_Fragment_Data getInstance(String title, ArrayList<TestDatalist> tempdatalist) {
        Store_Fragment_Data of = new Store_Fragment_Data();
        of.catname = "";
        of.catname = title;
         for (int i = 0; i < tempdatalist.size(); i++) {
            if (tempdatalist.get(i).getCatename().equals(title)) {
                of.tempdatalist.add(tempdatalist.get(i));
            }
        }
        gson = new Gson();
        Log.e("Catnames", title);
        if (!of.isFirst) {
            try {
                of.isFirst = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return of;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        tinyDB = new TinyDB(getContext());

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
        return inflater.inflate(R.layout.appstore_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mTopbannerdatamodel.size() != 0) {
            mTopbannerdatamodel.clear();
        }
        if (mTopappdatamodel.size() != 0) {
            mTopappdatamodel.clear();
        }
        if (mAppdatamodel.size() != 0) {
            mAppdatamodel.clear();
        }
        if (mBottombannerdatamodel.size() != 0) {
            mBottombannerdatamodel.clear();
        }
        mSubcat.clear();

        mTxtlayout1 = view.findViewById(R.id.txtlayout);
        mTxtlayout2 = view.findViewById(R.id.txtlayout2);
        mTxtlayout3 = view.findViewById(R.id.txtlayout3);
        mTxtlayout4 = view.findViewById(R.id.txtlayout4);

        mSubcat1 = (TextView) view.findViewById(R.id.subcat1);
        mSubcat2 = (TextView) view.findViewById(R.id.subcat2);
        mSubcat3 = (TextView) view.findViewById(R.id.subcat3);
        mSubcat4 = (TextView) view.findViewById(R.id.subcat4);

        for (int i = 0; i < tempdatalist.size(); i++) {
            TestDatalist testDatalist = tempdatalist.get(i);
            if (i == 0) {
                mTxtsubcat1 = testDatalist.getSubcatname();
                List list = (List) new Gson().fromJson(testDatalist.getDatajson(), new TypeToken<List<Appdata.Datalist>>() {
                }.getType());
                mAllappslist1 = (ArrayList<Appdata.Datalist>) list;
            } else if (i == 1) {
                mTxtsubcat2 = testDatalist.getSubcatname();
                List list = (List) new Gson().fromJson(testDatalist.getDatajson(), new TypeToken<List<Appdata.Datalist>>() {
                }.getType());
                mAllappslist2 = (ArrayList<Appdata.Datalist>) list;
            } else if (i == 2) {
                mTxtsubcat3 = testDatalist.getSubcatname();
                List list = (List) new Gson().fromJson(testDatalist.getDatajson(), new TypeToken<List<Appdata.Datalist>>() {
                }.getType());
                mAllappslist3 = (ArrayList<Appdata.Datalist>) list;
            } else if (i == 3) {
                mTxtsubcat4 = testDatalist.getSubcatname();
                List list = (List) new Gson().fromJson(testDatalist.getDatajson(), new TypeToken<List<Appdata.Datalist>>() {
                }.getType());
                mAllappslist4 = (ArrayList<Appdata.Datalist>) list;
            }
        }

        mSubcat1.setText(mTxtsubcat1);
        mSubcat2.setText(mTxtsubcat2);
        mSubcat3.setText(mTxtsubcat3);
        mSubcat4.setText(mTxtsubcat4);

        if (mAllappslist1.size() != 0) {
            mTopbannerdatamodel = mAllappslist1;
            coverflowdata(view);
        }
        if (mAllappslist2.size() != 0) {
            mTopappdatamodel = mAllappslist2;
            mIntentjson = new Gson().toJson((Object) mTopappdatamodel);
            coverflowdata3(view);
        }
        if (mAllappslist3.size() != 0) {
            mAppdatamodel = mAllappslist3;
            RecyclerView mRvallapps = (RecyclerView) view.findViewById(R.id.rvallapps);
            if (mAppdatamodel != null && mAppdatamodel.size() != 0) {
                mRvallapps.setVisibility(View.VISIBLE);
                mTxtlayout3.setVisibility(View.VISIBLE);
                mRvallapps.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                Store_Adapter_DataAllapps adapterAllapps = new Store_Adapter_DataAllapps(getContext(), mAppdatamodel);
                mRvallapps.setAdapter(adapterAllapps);
                adapterAllapps.notifyDataSetChanged();
            } else {
                mRvallapps.setVisibility(View.GONE);
                mTxtlayout3.setVisibility(View.GONE);
            }
        }
        if (mAllappslist4.size() != 0) {
            mBottombannerdatamodel = mAllappslist4;
            coverflowdata2(view);
        }

        ImageView mViewall = (ImageView) view.findViewById(R.id.imgviewall);
        mViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Store_Activity_Applist.class);
                intent.putExtra("Dataobject", mIntentjson);
                startActivity(intent);
            }
        });


    }


    public void coverflowdata(View view) {
        PagerContainer container = (PagerContainer) view.findViewById(R.id.pager_container);
        if (mTopbannerdatamodel != null && mTopbannerdatamodel.size() != 0) {
            container.setVisibility(View.VISIBLE);
            mTxtlayout1.setVisibility(View.VISIBLE);
            ViewPager pager = container.getViewPager();
            MyPagerAdapter myPagerAdapter = new MyPagerAdapter();
            pager.setAdapter(myPagerAdapter);
            pager.setClipChildren(false);

            pager.setOffscreenPageLimit(myPagerAdapter.getCount());
            pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (myPagerAdapter != null) {
                        myPagerAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    if (myPagerAdapter != null) {
                        myPagerAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            new CoverFlow.Builder()
                    .with(pager)
                    .scale(0.0f)
                    .pagerMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin3))
                    .spaceSize(30f)
                    .build();
        } else {
            container.setVisibility(View.GONE);
            mTxtlayout1.setVisibility(View.GONE);
        }
    }


    public void coverflowdata2(View view) {
        PagerContainer container = (PagerContainer) view.findViewById(R.id.pager_container2);
        if (mBottombannerdatamodel != null && mBottombannerdatamodel.size() != 0) {
            container.setVisibility(View.VISIBLE);
            mTxtlayout4.setVisibility(View.VISIBLE);
            final ViewPager bannerpager = container.getViewPager();
            MyPagerAdapter2 myPagerAdapter2 = new MyPagerAdapter2();
            bannerpager.setAdapter(myPagerAdapter2);
            bannerpager.setClipChildren(false);

            bannerpager.setOffscreenPageLimit(myPagerAdapter2.getCount());
            bannerpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (myPagerAdapter2 != null) {
                        myPagerAdapter2.notifyDataSetChanged();
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    if (myPagerAdapter2 != null) {
                        myPagerAdapter2.notifyDataSetChanged();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            new CoverFlow.Builder()
                    .with(bannerpager)
                    .scale(0.0f)
                    .pagerMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin3))
                    .spaceSize(30f)
                    .build();
        } else {
            container.setVisibility(View.GONE);
            mTxtlayout4.setVisibility(View.GONE);
        }

    }


    public void coverflowdata3(View view) {
        PagerContainer container = (PagerContainer) view.findViewById(R.id.apppager_container);
        if (mTopappdatamodel != null && mTopappdatamodel.size() != 0) {
            container.setVisibility(View.VISIBLE);
            mTxtlayout2.setVisibility(View.VISIBLE);
            final ViewPager bannerpager2 = container.getViewPager();
            MyPagerAdapter3 myPagerAdapter3 = new MyPagerAdapter3();
            bannerpager2.setAdapter(myPagerAdapter3);
            bannerpager2.setOffscreenPageLimit(myPagerAdapter3.getCount());
            bannerpager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    if (myPagerAdapter3 != null) {
                        myPagerAdapter3.notifyDataSetChanged();
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    if (myPagerAdapter3 != null) {
                        myPagerAdapter3.notifyDataSetChanged();
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });


            new CoverFlow.Builder()
                    .with(bannerpager2)
                    .scale(0.0f)
                    .pagerMargin(getResources().getDimensionPixelSize(R.dimen.pager_margin3))
                    .spaceSize(30f)
                    .build();
        } else {
            container.setVisibility(View.GONE);
            mTxtlayout2.setVisibility(View.GONE);
        }


    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.appstore_item_cover, null);
            RelativeLayout main_layout = (RelativeLayout) view.findViewById(R.id.cardview);
            ViewFlipper viewFlipper = view.findViewById(R.id.flipperid);
            viewFlipper.startFlipping();
            RoundedImageView mAppbanner = view.findViewById(R.id.ivappbanner);
            RoundedImageView mAppbanner2 = view.findViewById(R.id.ivappbanner2);
            RoundedImageView mAppicon = view.findViewById(R.id.ivappicon);
            TextView mTvappname = view.findViewById(R.id.tvappname);
            TextView mTvapprating = view.findViewById(R.id.tvapprating);
            final Appdata.Datalist data = mTopbannerdatamodel.get(position);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(data.getImage1())
                    .apply(requestOptions).into(mAppbanner);
            if (!data.getImage2().isEmpty()) {
                Glide.with(FitnessApplication.getInstance()).load(data.getImage2())
                        .apply(requestOptions).into(mAppbanner2);
            } else {
                if (viewFlipper.isFlipping()) {
                    viewFlipper.stopFlipping();
                }
            }
            Glide.with(FitnessApplication.getInstance()).load(data.getImage_name())
                    .apply(requestOptions).into(mAppicon);
            mTvappname.setText(data.getApp_name());
            mTvapprating.setText(data.getRating() + " \u2605");

            main_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rateus(data.getAap_link());
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mTopbannerdatamodel.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }


    private class MyPagerAdapter2 extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.appsore_item_lastbanners, null);

            RelativeLayout mMainlayout = view.findViewById(R.id.cardview);
            ViewFlipper viewFlipper = view.findViewById(R.id.flipperid);
            viewFlipper.startFlipping();
            final Appdata.Datalist data = mBottombannerdatamodel.get(position);
            RoundedImageView mAppbanner = view.findViewById(R.id.ivappbanner);
            RoundedImageView mAppbanner2 = view.findViewById(R.id.ivappbanner2);

            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(data.getImage1())
                    .apply(requestOptions).into(mAppbanner);

            if (!data.getImage2().isEmpty()) {
                Glide.with(FitnessApplication.getInstance()).load(data.getImage2())
                        .apply(requestOptions).into(mAppbanner2);
            } else {
                if (viewFlipper.isFlipping()) {
                    viewFlipper.stopFlipping();
                }
            }
            mMainlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rateus(data.getAap_link());
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mBottombannerdatamodel.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {
                if (getFragmentManager() != null) {
                    getFragmentManager()
                            .beginTransaction()
                            .detach(Store_Fragment_Data.this)
                            .attach(Store_Fragment_Data.this)
                            .commit();
                }
            } catch (Exception e) {
                if (getFragmentManager() != null) {
                    getFragmentManager()
                            .beginTransaction()
                            .detach(Store_Fragment_Data.this)
                            .attach(Store_Fragment_Data.this)
                            .commitAllowingStateLoss();
                }

            }
        }
    }

    private class MyPagerAdapter3 extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.appstore_item_appslist, null);

            RelativeLayout mMainlayout = view.findViewById(R.id.cardview);
            RoundedImageView mAppicon = view.findViewById(R.id.ivappicon);
            TextView mTvappname = view.findViewById(R.id.tvappname);
            TextView mTvapprating = view.findViewById(R.id.tvapprating);
            final Appdata.Datalist data = mTopappdatamodel.get(position);

            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(data.getImage_name())
                    .apply(requestOptions).into(mAppicon);

            mTvappname.setText(data.getApp_name());
            mTvapprating.setText(data.getRating() + " \u2605");
            mMainlayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Rateus(data.getAap_link());
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mTopappdatamodel.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }

    public void Rateus(String link) {
        Uri uri1 = Uri.parse(link);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

}
