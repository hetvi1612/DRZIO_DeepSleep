package drzio.insomnia.treatment.bedtime.yoga.sleep.Diet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Selecteditems;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.customplan;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class CustomUpdateActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] animalNameList;
    private RelativeLayout mLaynodata, dataisnotfound, leaderbord;
    private int index;
    ArrayList<String> arraylists = new ArrayList<String>();
    ArrayList<String> imagearraylists = new ArrayList<String>();
    ArrayList<String> caloryarraylists = new ArrayList<String>();

    ArrayList<String> datamodalsArrayList = new ArrayList<String>();
    ArrayList<String> arraylist1 = new ArrayList<String>();
    ArrayList<String> imagearraylist1 = new ArrayList<String>();
    ArrayList<String> caloryarraylist1 = new ArrayList<String>();
    ArrayList<String> descriptipnlist1 = new ArrayList<String>();
    public static Button save, addfood;
    TinyDB tinyDB;
    public DietplanDbhelper dietplanDbhelper;
    private RecyclerView mCategoryRecycle;
    private RecyclerView mDietrecycler;
    int bmr;
    TextView text2510kclg;
    TextView progresscalories;
    public static ProgressBar progressbar;
    int mealscount, dayscount/*,w25,w5,w1*/;
    private Dialog dialog;
    private String BASEURL;
    String musicurl;
    ImageView btnmenu;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.fragment_custom_diet_plan_fragmet);
        tinyDB = new TinyDB(this);
        list = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter(CustomUpdateActivity.this, arraylists, imagearraylists, caloryarraylists);
        dietplanDbhelper = new DietplanDbhelper(CustomUpdateActivity.this);
        data = getIntent().getStringExtra("noti");
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
        text2510kclg = findViewById(R.id.text2510kclg);
        bmr = tinyDB.getInt(Constants.BMR);
        text2510kclg.setText(String.valueOf(bmr + " cal"));
        progresscalories = (TextView) findViewById(R.id.progresscalories);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);
        progressbar.setMax(bmr);
        musicurl = tinyDB.getString(Constants.Backimageurl);
        BASEURL = tinyDB.getString(Constants.NewBaseUrl);
        int pog = tinyDB.getInt("settext");
        progressbar.setProgress(pog);
        progresscalories.setText(String.valueOf(pog));
        mCategoryRecycle = findViewById(R.id.cate_recycle);
        mDietrecycler = findViewById(R.id.diet_recyclerview11);
        mDietrecycler.setLayoutManager(new LinearLayoutManager(CustomUpdateActivity.this, LinearLayoutManager.VERTICAL, false));
        String veg = tinyDB.getString(Constants.USERTYPEKEY);

        btnmenu=findViewById(R.id.btnmenu);
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        if (veg.equals("veg")) {
            if (datamodalsArrayList!=null){
                datamodalsArrayList.clear();
            }
            datamodalsArrayList.add("fruit");
            datamodalsArrayList.add("vegetables");

            datamodalsArrayList.add("recipe");
            datamodalsArrayList.add("other");
        } else {
            if (datamodalsArrayList!=null){
                datamodalsArrayList.clear();
            }
            datamodalsArrayList.add("fruit");
            datamodalsArrayList.add("vegetables");
            datamodalsArrayList.add("meat");
            datamodalsArrayList.add("sea-food");
            datamodalsArrayList.add("recipe");
            datamodalsArrayList.add("other");

        }
        if (veg.equals("vegan")) {
            if (datamodalsArrayList!=null){
                datamodalsArrayList.clear();
            }
            datamodalsArrayList.add("fruit");
            datamodalsArrayList.add("vegetables");
            datamodalsArrayList.add("recipe");
            datamodalsArrayList.add("other");
        }
        callDietApi("fruit");
        leaderbord = (RelativeLayout) findViewById(R.id.leaderbord);
        mLaynodata = (RelativeLayout) findViewById(R.id.noiternets);
        dataisnotfound = (RelativeLayout) findViewById(R.id.dataisnotfound);

        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
        //   DialogPageLoading();
        if (netInfo != null) {
            if (netInfo.isConnected()) {
                leaderbord.setVisibility(View.VISIBLE);
                mLaynodata.setVisibility(View.GONE);
                //  Toast.makeText(CustomUpdateActivity.this,"Available",Toast.LENGTH_SHORT).show();
                // Internet Available
            } else {
                //No internet
                leaderbord.setVisibility(View.GONE);
                //   Toast.makeText(CustomUpdateActivity.this,"No internet",Toast.LENGTH_SHORT).show();
                mLaynodata.setVisibility(View.VISIBLE);
            }
        } else {
            leaderbord.setVisibility(View.GONE);
            //   Toast.makeText(CustomUpdateActivity.this,"No internet1",Toast.LENGTH_SHORT).show();
            mLaynodata.setVisibility(View.VISIBLE);
            //No internet
        }
        final CustomCategorieAdaper categoriesAdapter = new CustomCategorieAdaper(CustomUpdateActivity.this, datamodalsArrayList);

        mCategoryRecycle.setLayoutManager(new LinearLayoutManager(CustomUpdateActivity.this, LinearLayoutManager.HORIZONTAL, false));
        mCategoryRecycle.setAdapter(categoriesAdapter);
        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);

        editsearch.setOnQueryTextListener(CustomUpdateActivity.this);

        save = (Button) findViewById(R.id.save);
        addfood = (Button) findViewById(R.id.addfood);
        addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomUpdateActivity.this, AddFoodActivity.class);
                startActivity(i);
            }
        });

        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
        RelativeLayout banner1 = (RelativeLayout) findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
        showBanner(this, mGbannerlay2, banner1,mLoadlay);
    }else {
        mLoadlay.setVisibility(View.GONE);
    }
    }



    public void showBanner(Context context, final FrameLayout adMobView, RelativeLayout loadlayout, ShimmerFrameLayout mLoadlay) {
        try {
            final com.google.android.gms.ads.AdView mAdView = new com.google.android.gms.ads.AdView(context);
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.setAdUnitId(Constants.admob_banner);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mLoadlay.setVisibility(View.GONE);
                    if (adMobView != null) {
                        adMobView.removeAllViews();
                    }
                    adMobView.addView(mAdView);
                    loadlayout.setVisibility(View.VISIBLE);
                    adMobView.setVisibility(View.VISIBLE);
                    Constants.isReportBanner = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    mLoadlay.setVisibility(View.GONE);
                    loadlayout.setVisibility(View.GONE);
                    loadlayout.setVisibility(View.GONE);
                }
            });
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            if (adMobView != null) {
                adMobView.removeAllViews();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        callDiet(text);
        adapter.filter(text);
        list.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
        if (newText.length() == 0) {
            list.setVisibility(View.GONE);
            editsearch.clearFocus();
        }
        return false;
    }

    public void callDiet(String text) {


        //   ApiInterface   apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
        try {
            BackpainAPIInterface apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            JSONObject paramObject = new JSONObject();
            paramObject.put("term", text);
            //  paramObject.put("country_name", "INR");


            Call<List<customplan>> userCall = apiInterface.getcustomplan(paramObject.toString());
            userCall.enqueue(new Callback<List<customplan>>() {
                @Override
                public void onResponse(Call<List<customplan>> call, Response<List<customplan>> response) {
                    try {


                        arraylists.clear();
                        caloryarraylists.clear();
                        imagearraylists.clear();
                        //Log.e("response", String.valueOf(response.body()));
                        List<customplan> loginData = response.body();
                        String[] heroes = new String[loginData.size()];

                        for (int i = 0; i < loginData.size(); i++) {
                            heroes[i] = loginData.get(i).getName();
                            //    Log.e("id", String.valueOf(loginData.get(i).getName()));
                            String food_name = loginData.get(i).getName();
                            if (food_name.equals(text)) {
                            } else {
                                addfood.setVisibility(View.VISIBLE);

                            }
                            String image = loginData.get(i).getImage();

                            imagearraylists.add(image);
                            arraylists.add(loginData.get(i).getName());

                            customplan.Nutritions c = loginData.get(i).getNutritions();
                            String a = c.getCalories();
                            //   Log.e("getCalories", a);
                            caloryarraylists.add(a);
                            // Pass results to ListViewAdapter Class

                            adapter = new ListViewAdapter(CustomUpdateActivity.this, arraylists, imagearraylists, caloryarraylists);

                            // Binds the Adapter to the ListView
                            list.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                        if (loginData == null) {
                            dataisnotfound.setVisibility(View.VISIBLE);
                            leaderbord.setVisibility(View.GONE);
                        } else {
                            dataisnotfound.setVisibility(View.GONE);
                            leaderbord.setVisibility(View.VISIBLE);

                        }
                        //   String id =loginData.get(0).getId();

                        //   Log.e("w5", String.valueOf(response.body()));
                        //List<customplan>. datalists = loginData.result;
                        //  Log.e("id", String.valueOf(List<customplan>.id));
                  /*  Log.e("w25", String.valueOf(loginData.w25));
                    Log.e("w5", String.valueOf(loginData.w5));
                    Log.e("w1", String.valueOf(loginData.w1));*/
                        dialog.dismiss();
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<List<customplan>> call, Throwable t) {
                    //  Log.e("response1", String.valueOf("response"));
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void DialogPageLoading() {
        try {
            dialog = new Dialog(CustomUpdateActivity.this);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_adloading);
            LinearLayout mMainlay = dialog.findViewById(R.id.mainlay);
            mMainlay.setBackground(null);
            TextView temptext = dialog.findViewById(R.id.txttitle);
            LottieAnimationView mLottie1 = (LottieAnimationView) dialog.findViewById(R.id.lotti);
            mLottie1.setVisibility(View.GONE);
            LottieAnimationView mLottie = (LottieAnimationView) dialog.findViewById(R.id.lotti2);
            mLottie.setVisibility(View.VISIBLE);
            temptext.setText("Loading...");
            mLottie.setAnimation("loader.json");
            mLottie.playAnimation();
            mLottie.loop(true);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ListViewAdapter extends BaseAdapter {

        // Declare Variables

        Context mContext;
        LayoutInflater inflater;
        private List<customplan> animalNamesList = null;
        /**/      private ArrayList<String> arraylist, imagearraylist, caloriesarray;
        private ArrayList<String> newarraylist = new ArrayList<String>();

        public ListViewAdapter(Context context, ArrayList<String> arraylist, ArrayList<String> imagearraylist, ArrayList<String> caloriesarray) {
            mContext = context;
            //  this.animalNamesList = animalNamesList;
            inflater = LayoutInflater.from(mContext);
            this.arraylist = arraylist;
            this.imagearraylist = imagearraylist;
            this.caloriesarray = caloriesarray;
            // this.arraylist.addAll(animalNamesList);
        }

        public class ViewHolder {
            TextView name, calories;
            CheckBox add;
            ImageView dietimg;
            RelativeLayout relativelayouts;
        }

        @Override
        public int getCount() {
            return arraylist.size();
        }

        @Override
        public Object getItem(int position) {
            return arraylist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view == null) {

                holder = new ViewHolder();

                view = inflater.inflate(R.layout.listview_item, null);
                // Locate the TextViews in listview_item.xml
                holder.name = (TextView) view.findViewById(R.id.name);
                holder.dietimg = (ImageView) view.findViewById(R.id.dietimg11);
                RequestOptions requestOptions = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(mContext).load(musicurl + "food/" + imagearraylist.get(position))
                        .apply(requestOptions).into(holder.dietimg);

                holder.calories = (TextView) view.findViewById(R.id.calories);
                holder.calories.setText(caloriesarray.get(position));

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.add = (CheckBox) view.findViewById(R.id.relatives);

            holder.add.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                   /* String s=arraylist.get(position);
                    newarraylist.add(s);
                    Log.e("newarray", String.valueOf(s));*/
                    if (isChecked) {
                        Selecteditems selecteditems = new Selecteditems();
                        //    selecteditems.setId(allImages.getId());
                        selecteditems.setName(arraylist.get(position));
                        selecteditems.setCalories(caloriesarray.get(position));
                        selecteditems.setImage(musicurl + "food/" + imagearraylist.get(position));

                        // selecteditems.setIs_active(allImages.getIs_active());
                        //selecteditems.setUser_type(allImages.getUser_type());
                        // selecteditems.setCategory_id(allImages.getCategory_id());
                        Constants.mTemplist.add(selecteditems);
                        Integer progress = Integer.valueOf(caloriesarray.get(position));
                        int i = Integer.valueOf(progresscalories.getText().toString());
                        int settext = progress + i;
                        Log.e("settext", String.valueOf(settext));
                        progresscalories.setText(settext + "");
                        progressbar.setProgress(settext);
                    } else {
                        Integer progress = Integer.valueOf(caloriesarray.get(position));
                        int i = Integer.valueOf(progresscalories.getText().toString());
                        int settext = i - progress;
                        Log.e("settext", String.valueOf(settext));
                        progresscalories.setText(settext + "");
                        progressbar.setProgress(settext);
                    }

                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Constants.mTemplist.size() != 0) {
                        for (int i = 0; i < Constants.mTemplist.size(); i++) {
                            Selecteditems mData = Constants.mTemplist.get(i);
                            Date c = Calendar.getInstance().getTime();
                             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                            String formattedDate = df.format(c);
                            tinyDB.putString(Constants.DIETPLANDATE_KEY, formattedDate);
                            dietplanDbhelper.insertExcALLDayData(formattedDate, mData.getId(), mData.getName(),
                                    mData.getDescription(), mData.getImage(), mData.getIs_active(), mData.getUser_type()
                                    , mData.getCategory_id());
                        }
                        Constants.mTemplist.clear();
                        tinyDB.putBoolean(Constants.ReadyDietPlan, true);
                        Intent intent = new Intent(CustomUpdateActivity.this, DefalutAndCustomplanActivity.class);
                        // getActivity().finish();
                        startActivity(intent);
                        finish();
                        /*Intent intent = new Intent(mContext, CustomUpdatedietplan.class);
                        intent.putExtra("isFrom2", true);
                        startActivity(intent);
                        getActivity().finish();*/
                    }
                 /*   Intent i = new Intent(mContext,CustomUpdatedietplan.class);
                    i.putStringArrayListExtra("list", newarraylist);

                    mContext.startActivity(i);*/
                }
            });
            // Set the results into TextViews
            holder.name.setText(arraylist.get(position));
            return view;
        }

        // Filter Class
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            arraylist.clear();
            callDiet(charText);

       /* if (charText.length() == 0) {
            newarraylist.addAll(arraylist);
        } else {
            for (customplan wp : arraylist) {
                if (wp.getAnimalName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    animalNamesList.add(wp);
                }
            }
        }*/
            notifyDataSetChanged();
        }
    }

    class CustomCategorieAdaper extends RecyclerView.Adapter<CustomCategorieAdaper.CategoryViewholder> {
        private ArrayList<String> categoryitems;
        private Context context;
        private int selected = 0;

        public CustomCategorieAdaper(Context context, ArrayList<String> categoryitems) {
            this.categoryitems = categoryitems;
            this.context = context;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_dietcategories,
                    viewGroup, false);
            CategoryViewholder viewHolder = new CategoryViewholder(view);
            viewHolder.mCardclick = (RelativeLayout) view.findViewById(R.id.cardlay);
            viewHolder.cattitles = (TextView) view
                    .findViewById(R.id.cattitle);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final CategoryViewholder categoryViewholder, final int position) {
            //     final DietCateData.Datalist catitems = categoryitems.get(position);
            categoryViewholder.cattitles.setText(categoryitems.get(position));
            categoryViewholder.mCardclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = position;
                    notifyDataSetChanged();
                    String catname = categoryitems.get(position);
                    Log.e("catname", catname);
                    callDietApi(catname);
                }
            });
            if (index == position) {
                categoryViewholder.cattitles.setTextColor(Color.WHITE);
                categoryViewholder.cattitles.setBackgroundResource(R.drawable.gradbtn);
            } else {
                categoryViewholder.cattitles.setTextColor(context.getResources().getColor(R.color.headercolor));
                categoryViewholder.cattitles.setBackgroundResource(R.drawable.cat_unselected);
            }
        }

        @Override
        public int getItemCount() {
            return categoryitems == null ? 0 : categoryitems.size();
        }

        class CategoryViewholder extends RecyclerView.ViewHolder {
            RelativeLayout mCardclick;
            TextView cattitles;

            public CategoryViewholder(View itemView) {
                super(itemView);
            }
        }
    }

    private void callDietApi(String catname) {
        try {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            //BackpainAPIInterface   apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);
            BackpainAPIInterface apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);
            Call<List<Customplancat>> userCall = apiInterface.getcustomplancatagory(catname);
            userCall.enqueue(new Callback<List<Customplancat>>() {
                @Override
                public void onResponse(Call<List<Customplancat>> call, Response<List<Customplancat>> response) {
                    try {
                        imagearraylist1.clear();
                        arraylist1.clear();
                        descriptipnlist1.clear();
                        caloryarraylist1.clear();
                        //    Log.e("response", String.valueOf(response.body()));
                        List<Customplancat> loginData = response.body();
                        String[] heroes = new String[loginData.size()];

                        for (int i = 0; i < loginData.size(); i++) {
                            heroes[i] = loginData.get(i).getName();
                            Log.e("id", String.valueOf(loginData.get(i).getName()));

                            String image = loginData.get(i).getImage();
                            String desc = loginData.get(i).getDescription();
                            imagearraylist1.add(image);
                            arraylist1.add(heroes[i]);

                            Customplancat.Nutritions c = loginData.get(i).getNutritions();
                            int a = c.getCalories();
                            //  Log.e("getCalories", String.valueOf(a));
                            caloryarraylist1.add(String.valueOf(a));
                            descriptipnlist1.add(desc);
                            // Pass results to ListViewAdapter Class
                            //
                        }
                        Adapter_Alldiets adapter = new Adapter_Alldiets(CustomUpdateActivity.this, loginData, mDietrecycler, arraylist1, imagearraylist1, caloryarraylist1, descriptipnlist1);

                        // Binds the Adapter to the ListView
                        mDietrecycler.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                   /* SubcatListViewAdapter adapter = new SubcatListViewAdapter(CustomUpdateActivity.this, arraylist1, imagearraylist1, caloryarraylist1, descriptipnlist1);
                    Log.e("arraylist1", String.valueOf(arraylist1));
                    // Binds the Adapter to the ListView
                    mDietrecycler.setAdapter(adapter);*/
                        adapter.notifyDataSetChanged();
                        //   String id =loginData.get(0).getId();

                        //   Log.e("w5", String.valueOf(response.body()));
                        //List<customplan>. datalists = loginData.result;
                        //  Log.e("id", String.valueOf(List<customplan>.id));
                    /*Log.e("w25", String.valueOf(loginData.w25));
                    Log.e("w5", String.valueOf(loginData.w5));
                    Log.e("w1", String.valueOf(loginData.w1));*/
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<List<Customplancat>> call, Throwable t) {
                    Log.e("response1", String.valueOf(t.getStackTrace()));
                   // Toast.makeText(CustomUpdateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();


                }
            });
        }catch (Exception e){

        }
    }

    /*   public class SubcatListViewAdapter extends RecyclerView.Adapter<SubcatListViewAdapter.SubcatListholder> {
           ArrayList<String> arraylist = new ArrayList<String>();
           ArrayList<String> imagearraylist = new ArrayList<String>();
           ArrayList<String> caloriesarray = new ArrayList<String>();
           ArrayList<String> descripationarrray = new ArrayList<String>();
           Context mContext;
           String subpostion;
           public SubcatListViewAdapter(Context context, ArrayList<String> arraylist, ArrayList<String> imagearraylist, ArrayList<String> caloriesarray, ArrayList<String> descripationarrray) {
               mContext = context;
               //  this.animalNamesList = animalNamesList;
               this.arraylist = arraylist;
               this.imagearraylist = imagearraylist;
               this.caloriesarray = caloriesarray;
               this.descripationarrray = descripationarrray;
               // this.arraylist.addAll(animalNamesList);
           }
           @Override
           public int getItemViewType(int position) {
               return super.getItemViewType(position);
           }

           @NonNull
           @Override
           public SubcatListViewAdapter.SubcatListholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
               View view = LayoutInflater.from(mContext).inflate(R.layout.listview_item,
                       viewGroup, false);
               SubcatListViewAdapter.SubcatListholder holder = new SubcatListViewAdapter.SubcatListholder(view);


               holder.calories = (TextView) view.findViewById(R.id.calories);
               holder.add = (CheckBox) view.findViewById(R.id.relatives);
               holder.name = (TextView) view.findViewById(R.id.name);
               holder.dietimg = (ImageView) view.findViewById(R.id.dietimg);
               holder.relativelayouts = (RelativeLayout) view.findViewById(R.id.relativelayouts);

               return holder;
           }

           @Override
           public void onBindViewHolder(@NonNull final SubcatListViewAdapter.SubcatListholder holder, final int position) {
               //     final DietCateData.Datalist catitems = categoryitems.get(position);
               holder.name.setText(arraylist.get(position));
               Log.e("(arraylist.get(position)", String.valueOf(arraylist.get(position)));
               holder.calories.setText(caloriesarray.get(position));
               RequestOptions requestOptions = new RequestOptions()
                       .diskCacheStrategy(DiskCacheStrategy.ALL);
               Glide.with(mContext).load(musicurl + "food/" + imagearraylist.get(position))
                       .apply(requestOptions).into(holder.dietimg);
               holder.relativelayouts.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       subpostion = String.valueOf(position);
                       Intent i = new Intent(mContext, DietDetailsActivity.class);
                       i.putStringArrayListExtra("name", arraylist);
                       i.putStringArrayListExtra("image", imagearraylist);
                       i.putStringArrayListExtra("calories", caloriesarray);
                       i.putStringArrayListExtra("descripation", descripationarrray);
                       i.putExtra("position", subpostion);
                       i.putExtra("arraylist", arraylist.get(position));
                       Log.e("postion", String.valueOf(subpostion));
                       Log.e("arraylist", String.valueOf(arraylist.get(position)));
                       i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       mContext.startActivity(i);
                       //   ((customDietplanActivity) mContext).finish();
                   }
               });
               String name=arraylist.get(position);
               String calories=caloriesarray.get(position);
               String image=imagearraylist.get(position);
               String descriptipnlist=descriptipnlist1.get(position);
               holder.add.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                   @Override
                   public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                       if (isChecked) {
                           holder.add.setChecked(true);
                           Selecteditems selecteditems = new Selecteditems();
                           //    selecteditems.setId(allImages.getId());
                           selecteditems.setName(name);
                           selecteditems.setCalories(calories);
                           selecteditems.setImage(image);
                           selecteditems.setDescription(descriptipnlist);
                           // selecteditems.setIs_active(allImages.getIs_active());
                           //selecteditems.setUser_type(allImages.getUser_type());
                           // selecteditems.setCategory_id(allImages.getCategory_id());
                           Constants.mTemplist.add(selecteditems);
                           if (!mDietrecycler.isComputingLayout()) {
                               notifyItemChanged(position);
                           }
                          Integer progress = Integer.valueOf(caloriesarray.get(position));
                           int i = Integer.valueOf(progresscalories.getText().toString());
                           int settext = progress + i;
                           Log.e("settext", String.valueOf(settext));
                           progresscalories.setText(settext + "");
                           progressbar.setProgress(settext/5);
                       }else {
                           if (!mDietrecycler.isComputingLayout()) {
                               notifyItemChanged(position);
                           }
                    Integer progress = Integer.valueOf(caloriesarray.get(position));
                           int i = Integer.valueOf(progresscalories.getText().toString());
                           int settext = i - progress;
                           Log.e("settext", String.valueOf(settext));
                           progresscalories.setText(settext+"");
                           progressbar.setProgress(settext/5);
                       }


                   }
               });
               save.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       if (Constants.mTemplist.size() != 0) {
                           for (int i = 0; i < Constants.mTemplist.size(); i++) {
                               Selecteditems mData = Constants.mTemplist.get(i);
                               Date c = Calendar.getInstance().getTime();
                                Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                               String formattedDate = df.format(c);
                               tinyDB.putString(Constants.DIETPLANDATE_KEY, formattedDate);
                               dietplanDbhelper.insertExcALLDayData(formattedDate, mData.getId(), mData.getName(),
                                       mData.getDescription(), mData.getImage(), mData.getIs_active(), mData.getUser_type()
                                       , mData.getCategory_id());
                           }
                           Constants.mTemplist.clear();
                           Intent intent = new Intent(mContext, CustomUpdatedietplan.class);
                           intent.putExtra("isFrom2", true);
                           startActivity(intent);
                           getActivity().finish();
                       }

                   }
               });

           }

           @Override
           public int getItemCount() {
               return arraylist == null ? 0 : arraylist.size();
           }

           class SubcatListholder extends RecyclerView.ViewHolder {
               TextView name, calories;
               CheckBox add;
               ImageView dietimg;
               RelativeLayout relativelayouts;

               public SubcatListholder(View itemView) {
                   super(itemView);
               }
           }
       }*/
    public class SubcatListViewAdapter extends BaseAdapter {

        // Declare Variables

        Context mContext;
        LayoutInflater inflater;
        private List<customplan> animalNamesList = null;
        /**/   //   private ArrayList<String>     arraylist, imagearraylist, caloriesarray, descripationarrray;
        private ArrayList<String> newarraylist = new ArrayList<String>();
        String subpostion;
        ArrayList<String> arraylist = new ArrayList<String>();
        ArrayList<String> imagearraylist = new ArrayList<String>();
        ArrayList<String> caloriesarray = new ArrayList<String>();
        ArrayList<String> descripationarrray = new ArrayList<String>();

        public SubcatListViewAdapter(Context context, ArrayList<String> arraylist, ArrayList<String> imagearraylist, ArrayList<String> caloriesarray, ArrayList<String> descripationarrray) {
            mContext = context;
            //  this.animalNamesList = animalNamesList;
            inflater = LayoutInflater.from(mContext);
            this.arraylist = arraylist;
            this.imagearraylist = imagearraylist;
            this.caloriesarray = caloriesarray;
            this.descripationarrray = descripationarrray;
            // this.arraylist.addAll(animalNamesList);
        }

        public class ViewHolder {
            TextView name, calories;
            CheckBox add;
            ImageView dietimg;
            RelativeLayout relativelayouts;

        }

        @Override
        public int getCount() {
            return arraylist.size();
        }

        @Override
        public Integer getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            final ViewHolder holder;
            if (view == null) {

                holder = new ViewHolder();

                view = inflater.inflate(R.layout.listview_item, null);
                // Locate the TextViews in listview_item.xml
                holder.name = (TextView) view.findViewById(R.id.name);
                holder.dietimg = (ImageView) view.findViewById(R.id.dietimg);
                RequestOptions requestOptions = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(mContext).load(musicurl + "food/" + imagearraylist.get(position))
                        .apply(requestOptions).into(holder.dietimg);

                holder.calories = (TextView) view.findViewById(R.id.calories);
                holder.calories.setText(caloriesarray.get(position));
                holder.relativelayouts = (RelativeLayout) view.findViewById(R.id.relativelayouts);


                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.add = (CheckBox) view.findViewById(R.id.relatives);
            holder.relativelayouts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    subpostion = String.valueOf(position);
                    Intent i = new Intent(mContext, DietDetailsActivity.class);
                    i.putStringArrayListExtra("name", arraylist);
                    i.putStringArrayListExtra("image", imagearraylist);
                    i.putStringArrayListExtra("calories", caloriesarray);
                    i.putStringArrayListExtra("descripation", descripationarrray);
                    i.putExtra("position", subpostion);
                    i.putExtra("arraylist", arraylist.get(position));
                    Log.e("postion", String.valueOf(subpostion));
                    Log.e("arraylist", String.valueOf(arraylist.get(position)));
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mContext.startActivity(i);
                    //   ((customDietplanActivity) mContext).finish();
                }
            });
            holder.add.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        Selecteditems selecteditems = new Selecteditems();
                        //    selecteditems.setId(allImages.getId());
                        selecteditems.setName(arraylist.get(position));
                        selecteditems.setCalories(caloriesarray.get(position));
                        selecteditems.setImage(musicurl + "food/" + imagearraylist.get(position));
                        selecteditems.setDescription(descriptipnlist1.get(position));
                        // selecteditems.setIs_active(allImages.getIs_active());
                        //selecteditems.setUser_type(allImages.getUser_type());
                        // selecteditems.setCategory_id(allImages.getCategory_id());
                        Constants.mTemplist.add(selecteditems);
                    }

                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Constants.mTemplist.size() != 0) {
                        for (int i = 0; i < Constants.mTemplist.size(); i++) {
                            Selecteditems mData = Constants.mTemplist.get(i);
                            Date c = Calendar.getInstance().getTime();
                             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                            String formattedDate = df.format(c);
                            tinyDB.putString(Constants.DIETPLANDATE_KEY, formattedDate);
                            dietplanDbhelper.insertExcALLDayData(formattedDate, mData.getId(), mData.getName(),
                                    mData.getDescription(), mData.getImage(), mData.getIs_active(), mData.getUser_type()
                                    , mData.getCategory_id());
                        }
                        Constants.mTemplist.clear();
                  /*   Intent intent = new Intent(mContext, CustomUpdatedietplan.class);
                     intent.putExtra("isFrom2", true);
                     startActivity(intent);*/
                        tinyDB.putBoolean(Constants.ReadyDietPlan, true);
                        Intent intent = new Intent(CustomUpdateActivity.this, DefalutAndCustomplanActivity.class);
                        // getActivity().finish();
                        startActivity(intent);
                        finish();
                        tinyDB.putBoolean("iscustom", true);
                        //   finish();
                    }
                 /*   Intent i = new Intent(mContext,CustomUpdatedietplan.class);
                    i.putStringArrayListExtra("list", newarraylist);

                    mContext.startActivity(i);*/
                }
            });
            // Set the results into TextViews
            holder.name.setText(arraylist.get(position));
            return view;
        }

    }

    public class Adapter_Alldiets extends RecyclerView.Adapter<Adapter_Alldiets.ViralvideoHolder> {

        private List<Customplancat> effectItems;
        DietDatabase_tempselect dietDatabase_tempselect;
        private Context context;
        private int selected = 0;
        private boolean mSuccess;
        private int mPos;
        private boolean iscallviewapi = false;
        RecyclerView recyclerView;
        String subpostion;
        ArrayList<String> arraylist = new ArrayList<String>();
        ArrayList<String> imagearraylist = new ArrayList<String>();
        ArrayList<String> caloriesarray = new ArrayList<String>();
        ArrayList<String> descripationarrray = new ArrayList<String>();

        public Adapter_Alldiets(Context context, List<Customplancat> customplancats, RecyclerView recyclerView, ArrayList<String> arraylist, ArrayList<String> imagearraylist, ArrayList<String> caloriesarray, ArrayList<String> descripationarrray) {
            this.effectItems = customplancats;
            this.context = context;
            this.recyclerView = recyclerView;
            this.arraylist = arraylist;
            this.imagearraylist = imagearraylist;
            this.caloriesarray = caloriesarray;
            this.descripationarrray = descripationarrray;

            dietDatabase_tempselect = new DietDatabase_tempselect(context);
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @NotNull
        @Override
        public ViralvideoHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_dietimages,
                    parent, false);
            ViralvideoHolder viewHolder = new ViralvideoHolder(view);


            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final ViralvideoHolder holder, final int position) {
            final Customplancat allImages = effectItems.get(position);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(musicurl + "food/" + imagearraylist.get(position))
                    .apply(requestOptions).into(holder.thumbImage);
          /*  if (allImages.isSelected()) {
                holder.mBtnselect.setChecked(true);
            } else {
                holder.mBtnselect.setChecked(false);
            }*/


            ArrayList<String> listlikes = dietDatabase_tempselect.getLikeid();
            for (int i = 0; i < listlikes.size(); i++) {
                String likeid = listlikes.get(i);
                if (likeid != null && listlikes.contains(allImages.getId())) {
                    holder.mBtnselect.setChecked(true);
                    holder.mBtnselect.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_checkbox_checked));


                } else {
                    holder.mBtnselect.setChecked(false);
                    holder.mBtnselect.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_checkbox_unchecked));


                }
            }



          /*  try {
                Selecteditems selecteditems = new Selecteditems();
                if (selecteditems.getId().equals(allImages.getId())) {
                    holder.mBtnselect.setChecked(true);
                } else {
                    holder.mBtnselect.setChecked(false);
                }
            } catch (Exception e) {

            }*/

            //     selecteditems.setId(allImages.getId());


            holder.mTxtName.setText(allImages.getName());

            holder.calories123.setText(caloriesarray.get(position));
            holder.mItemlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    subpostion = String.valueOf(position);
                    Intent i = new Intent(context, DietDetailsActivity.class);
                    i.putStringArrayListExtra("name", arraylist);
                    i.putStringArrayListExtra("image", imagearraylist);
                    i.putStringArrayListExtra("calories", caloriesarray);
                    i.putStringArrayListExtra("descripation", descripationarrray);
                    i.putExtra("position", subpostion);


                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(i);
                 /*   Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("listdiet", (Serializable) effectItems);
                    Intent intent = new Intent(context, DietDetailsActivity.class);
                    intent.putExtra("isbottom", true);
                    intent.putExtra("dietid", allImages.getId());
                    intent.putExtras(bundle1);
                    context.startActivity(intent);*/
                }
            });
            holder.mBtnselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        allImages.setSelected(true);
                        Selecteditems selecteditems = new Selecteditems();
                        selecteditems.setId(allImages.getId());
                        selecteditems.setName(allImages.getName());
                        selecteditems.setDescription(allImages.getDescription());
                        selecteditems.setImage(musicurl + "food/" + allImages.getImage());
                        holder.mBtnselect.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_checkbox_checked));

                        Integer progress = Integer.valueOf(caloriesarray.get(position));
                        int i = Integer.valueOf(progresscalories.getText().toString());
                        int settext = progress + i;
                        Log.e("settext", String.valueOf(settext));
                        progresscalories.setText(settext + "");
                        progressbar.setProgress(settext);
                        tinyDB.putInt("settext", settext);

                 /*   selecteditems.setIs_active(allImages.getIs_active());
                    selecteditems.setUser_type(allImages.getUser_type());
                    selecteditems.setCategory_id(allImages.getCategory_id());
                 */
                        Constants.mTemplist.add(selecteditems);
                        dietDatabase_tempselect.insertContact(allImages.getId());

                      /*  if (!recyclerView.isComputingLayout()) {
                            notifyItemChanged(position);
                        }*/
//                    notifyDataSetChanged();
                    } else {
                    /*    if (!recyclerView.isComputingLayout()) {
                            notifyItemChanged(position);
                        }*/
                        holder.mBtnselect.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ic_checkbox_unchecked));

                        dietDatabase_tempselect.deleteContact(allImages.getId());
                        allImages.setSelected(false);
                        Integer progress = Integer.valueOf(caloriesarray.get(position));
                        int i = Integer.valueOf(progresscalories.getText().toString());
                        int settext = i - progress;
                        Log.e("settext", String.valueOf(settext));
                        progresscalories.setText(settext + "");
                        progressbar.setProgress(settext);
                        tinyDB.putInt("settext", settext);
                    }
                }
            });

            holder.mBtnselect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tinyDB.putBoolean("customplanready", true);
                    if (Constants.mTemplist.size() != 0) {
                        for (int i = 0; i < Constants.mTemplist.size(); i++) {
                            Selecteditems mData = Constants.mTemplist.get(i);
                            Date c = Calendar.getInstance().getTime();
                             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                            String formattedDate = df.format(c);
                            tinyDB.putString(Constants.DIETPLANDATE_KEY, formattedDate);
                            dietplanDbhelper.insertExcALLDayData(formattedDate, mData.getId(), mData.getName(),
                                    mData.getDescription(), mData.getImage(), mData.getIs_active(), mData.getUser_type()
                                    , mData.getCategory_id());
                        }
                        Constants.mTemplist.clear();
                        tinyDB.putBoolean(Constants.ReadyDietPlan, true);
                        Intent intent = new Intent(CustomUpdateActivity.this, DefalutAndCustomplanActivity.class);
                        // getActivity().finish();
                        startActivity(intent);
                        finish();
/*

                            DefalutAndCustomplanActivity.mFragments.clear();
                            DefalutAndCustomplanActivity.mFragments.add(Fragment_DefalutDietPlan.getInstance(mealscount, dayscount, bmr));
                            DefalutAndCustomplanActivity.mFragments.add(CustomUpdatedietplanFragment1.getInstance());
*/



                       /* Intent intent = new Intent(context, CustomUpdatedietplan.class);
                        intent.putExtra("isFrom2", true);
                        startActivity(intent);*/
                        //finish();
                    }
                 /*   Intent i = new Intent(mContext,CustomUpdatedietplan.class);
                    i.putStringArrayListExtra("list", newarraylist);

                    mContext.startActivity(i);*/
                }
            });
        }


        private boolean isConnectingToInternet() {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return true;
            else
                return false;
        }


        @Override
        public int getItemCount() {
            return effectItems.size();
        }


        class ViralvideoHolder extends RecyclerView.ViewHolder {
            ImageView thumbImage;
            CheckBox mBtnselect;
            TextView mTxtName;
            RelativeLayout mItemlay;
            TextView calories123;

            public ViralvideoHolder(View itemView) {
                super(itemView);
                mBtnselect = (CheckBox) itemView
                        .findViewById(R.id.btncheck);
                thumbImage = (ImageView) itemView
                        .findViewById(R.id.dietimg);
                mItemlay = (RelativeLayout) itemView
                        .findViewById(R.id.dietlayout);
                mTxtName = (TextView) itemView
                        .findViewById(R.id.dietname);
                calories123 = (TextView) itemView.findViewById(R.id.calories123);

            }
        }

    }

    @Override
    public void onBackPressed() {

        if (data!=null) {
            if (data.equals("true")) {
                Intent intent = new Intent(CustomUpdateActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(CustomUpdateActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }else {
            finish();
        }

    }
}