package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.InterstitialAd;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;

import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.AddLike;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.AddComment;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.GetComment;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment.Fragment_blogs.adapterTopblogs;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment.Fragment_blogs.adapterTopblogs1;

public class Activity_Blogwebview extends AppCompatActivity {
    private WebView webview;
    private static final String TAG = "Main";
    ImageView btnshare, btncomment;
    CheckBox btnlike;
    private String mLink;

    private RelativeLayout netlayout;
    private Dialog dialog;
    private InterstitialAd interstitialAd;
    private String mStringTitle = "Daily Tips";
    Chronometer simpleChronometer;
    String id;
    int position;
    int count = 0;
    Timer T;
    private int mPositioncomment;
    private String[] blogmodelList;
    private String userid, blogname, bloglink;
    private Activity_Blogwebview activity;
    private int mPosition;
    String imageurl = "https://drzio-android.s3.amazonaws.com/blog/";
    private String image;
    private DBHelper mDbhelper;
    private boolean addlike = false;
    private int bloglike;
    private String token;
    TinyDB tinyDB;
    private BackpainAPIInterface apiInterface;
    private ProgressBar loadingPB;
    ImageView btnshare11;
    private String musicurl;
    LinearLayout laycomment;
    private RecyclerView commentrecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__blogwebview);
        mDbhelper = new DBHelper(this);
        tinyDB = new TinyDB(this);
        netlayout = (RelativeLayout) findViewById(R.id.neterror);
        this.webview = (WebView) findViewById(R.id.webview);
        btnshare11 = (ImageView) findViewById(R.id.btnshare11);
        laycomment = (LinearLayout) findViewById(R.id.laycomment);
        musicurl = tinyDB.getString(Constants.Backimageurl);
        ImageView icback = findViewById(R.id.icback);
        icback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        token = tinyDB.getString(Constants.Authtoken);
        // token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";
        netlayout.setVisibility(View.GONE);
        btnshare = (ImageView) findViewById(R.id.share_button);
        btnlike = findViewById(R.id.btnlike);
        btncomment = findViewById(R.id.btncomment);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        loadingPB = findViewById(R.id.idPBLoading);

        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        //DialogPageLoading();
        Bundle bundle = getIntent().getExtras();
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);


                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
//                if (progressBar.isShowing()) {
//                    progressBar.dismiss();
//                }

                loadingPB.setVisibility(View.GONE);
              /*  if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }*/
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                netlayout.setVisibility(View.VISIBLE);
            }
        });

        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer); // initiate a chronometer
        String formatType = simpleChronometer.getFormat();

        simpleChronometer.setFormat("(%m)");
        simpleChronometer.setBase(SystemClock.elapsedRealtime());
        simpleChronometer.start();

        int elapsed = (int) (SystemClock.elapsedRealtime() - simpleChronometer.getBase());

      //  Log.e("formatType", String.valueOf((elapsed)));


        if (bundle != null) {
            mLink = bundle.getString("link");

         //   Log.e("link", mLink);
            id = bundle.getString("id");
            position = bundle.getInt("position");
            userid = bundle.getString("userid");
            blogmodelList = bundle.getStringArray("blogmodelList");
            blogname = bundle.getString("blogname");
            bloglink = bundle.getString("bloglink");
            image = bundle.getString("image");
            bloglike = bundle.getInt("bloglike");
      //      Log.e("id", mLink);
        }
        if (mLink != null) {
            webview.loadUrl(mLink);
        }

        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
     //   Log.e("appLinkData", String.valueOf(appLinkData));
        webview.loadUrl(String.valueOf(appLinkData));


        ArrayList<String> listlikes = mDbhelper.getLikeid();
        for (int i = 0; i < listlikes.size(); i++) {
            String likeid = listlikes.get(i);
            if (likeid != null && listlikes.contains(id)) {
                btnlike.setChecked(true);
                btnlike.setBackground(getResources().getDrawable(R.drawable.heartlike));
            } else {
                btnlike.setChecked(false);
                btnlike.setBackground(getResources().getDrawable(R.drawable.heart1));

            }
        }
        btnlike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {


                    mPosition = position;
                    addlike = true;
                    btnlike.setBackgroundResource(R.drawable.heartlike);
                    CallAddlike(id);
                    mDbhelper.insertContact(id);
                } else {
                    mPosition = position;
                    btnlike.setBackgroundResource(R.drawable.heart1);
                    btnlike.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));

                    CallAddunlike(id);
                    mDbhelper.deleteContact(id);

                }


//                Log.e("id", id);
            }
        });

        btnshare11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mPosition = position;

                    if (!image.isEmpty()) {
                        new RetrieveFeedTask(musicurl + "blog/" + image).execute();

                    }
                } catch (Exception e) {
      //              Log.e("errorinshare", e.toString());
                }
            }
        });

        laycomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogComment();
            }
        });


    }

    EditText addComment;
    ImageView sendcomment;
    BottomSheetBehavior bottomSheetBehavior;


    private void DialogComment() {
        callShowCommentApi();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.custom_view, null);
        commentrecyclerview = (RecyclerView) customView.findViewById(R.id.commentrecyclerview);
        addComment = (EditText) customView.findViewById(R.id.addComment);
        sendcomment = customView.findViewById(R.id.sendcomment);
        sendcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = addComment.getText().toString();
                if (!comment.isEmpty()) {
                    callCommentApi(comment);
                } else {
                    Toast.makeText(Activity_Blogwebview.this, "Please write a comment!!..", Toast.LENGTH_LONG).show();
                }
            }
        });

        new BottomDialog.Builder(this)
                .setTitle("Comments")
                .setCustomView(customView)
                .show();
    }


    private void callCommentApi(String comment) {

        //  String token = Constants.Authtoken;


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(CelluliteAPIInterface.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();*/
      //  String idofblog = blodid.get(mPositioncomment)/*.getId()*/;
        ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
        //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
        //       CelluliteAPIInterface apiInterface = retrofit.create(CelluliteAPIInterface.class);
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("uId", id);
            paramObject.put("comment", comment);


            Call<AddComment> userCall = apiInterface.addComment(id, paramObject.toString(), "Bearer " + token);
            userCall.enqueue(new Callback<AddComment>() {
                @Override
                public void onResponse(Call<AddComment> call, Response<AddComment> response) {
        //            Log.e("TAG1", String.valueOf(response.code()) /*+ response.body().toString()*/);

                    addComment.setText("");
                    callShowCommentApi();
                    try {
                        if (adapterTopblogs1 != null) {
                            adapterTopblogs1.notifyDataSetChanged();
                        }
                        if (adapterTopblogs != null) {
                            adapterTopblogs.notifyDataSetChanged();
                        }
                       // getBlog(page, limit, token);
                    } catch (Exception e) {

                    }

                }

                @Override
                public void onFailure(Call<AddComment> call, Throwable t) {
                    //    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void callShowCommentApi() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


      //  String idofblog = blodid.get(mPositioncomment)/*.getId()*/;
        ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);


        Call<GetComment> userCall = apiInterface.getComment(id, "Bearer " + token);


        userCall.enqueue(new Callback<GetComment>() {
            @Override
            public void onResponse(Call<GetComment> call, Response<GetComment> response) {
//                    Log.d("TAG1", response.code() + "");
                GetComment getComment = (GetComment) response.body();
                assert getComment != null;

                ArrayList<GetComment.BlogComment> blogComments = (ArrayList<GetComment.BlogComment>) getComment.blogComment;


                String[] heroes = new String[blogComments.size()];

                for (int i = 0; i < blogComments.size(); i++) {
                    heroes[i] = blogComments.get(i).getComment();


             //       Log.e("imagesname", String.valueOf(blogComments) + "hgh");


                }

                commentrecyclerview.setLayoutManager(new LinearLayoutManager(Activity_Blogwebview.this, LinearLayoutManager.VERTICAL, false));
                Adapter_addComment adapterTopblogs = new Adapter_addComment(Activity_Blogwebview.this, blogComments, activity);
                commentrecyclerview.setAdapter(adapterTopblogs);


            }

            @Override
            public void onFailure(Call<GetComment> call, Throwable t) {
                //   Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    class RetrieveFeedTask extends AsyncTask<String, String, Bitmap> {
        String src;

        public RetrieveFeedTask(String src) {
            this.src = src;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmaps = BitmapFactory.decodeStream(input);
                return myBitmaps;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Intent shareIntent;

            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Share.png";
            OutputStream out = null;
            File file = new File(path);
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Uri bmpUri = FileProvider.getUriForFile(getApplicationContext(), getPackageName() + ".provider", file);
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            StringBuilder sb = new StringBuilder();
            sb.append(blogname/*.getBlogName()*/ + "\n");
            sb.append(Html.fromHtml(bloglink/*.getBlogLink()*/).toString());
            sb.append("\nHey check this application ");
            sb.append("https://play.google.com/store/apps/details?id=");
            sb.append(getPackageName());
            shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
            shareIntent.setType("image/*");
            startActivity(Intent.createChooser(shareIntent, "Share with"));
            // callShareApi();
        }
    }

    private void CallAddlike(String id) {
        //  String token = Constants.Authtoken;


       // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";


        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            JSONObject paramObject = new JSONObject();
            paramObject.put("update", "true");
            Call<AddLike> call = apiInterface.getLike(id, paramObject.toString(), "Bearer " + token);
            call.enqueue(new Callback<AddLike>() {
                @Override
                public void onResponse(Call<AddLike> call, Response<AddLike> response) {
                    try {
                   //     Log.e("response", String.valueOf(response.code()));
                        AddLike addLike = response.body();
                  ///      Log.e("likes", String.valueOf(addLike.getBlogLike()));
                        String likes = String.valueOf(addLike.getBlogLike());
                  //      Log.e("like", String.valueOf(likes));
                    }catch (Exception e){

                    }

                }

                @Override
                public void onFailure(Call<AddLike> call, Throwable t) {
               //     Log.e("eroor", t.toString());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void CallAddunlike(String id) {

        //  String token = Constants.Authtoken;



        //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";


        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            JSONObject paramObject = new JSONObject();
            paramObject.put("update", "false");
            Call<AddLike> call = apiInterface.getLike(id, paramObject.toString(), "Bearer " + token);
            call.enqueue(new Callback<AddLike>() {
                @Override
                public void onResponse(Call<AddLike> call, Response<AddLike> response) {
                    try {
                  //      Log.e("response", String.valueOf(response.body().toString()));

                        AddLike addLike = response.body();
                //        Log.e("likes", String.valueOf(addLike.getBlogLike()));
                        String likes = String.valueOf(addLike.getBlogLike());
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(Call<AddLike> call, Throwable t) {
                //    Log.e("eroor", t.toString());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


  /*  public void DialogPageLoading() {
        try {
            dialog = new Dialog(Activity_Blogwebview.this);
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
    }*/



    @Override
    public void onBackPressed() {

        super.onBackPressed();


        finish();

    }





}