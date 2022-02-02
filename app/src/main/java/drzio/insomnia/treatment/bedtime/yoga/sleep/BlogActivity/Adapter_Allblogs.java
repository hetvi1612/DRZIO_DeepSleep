package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.AddLike;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.BlogList;
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


@SuppressLint("WrongConstant")
public class Adapter_Allblogs extends RecyclerView.Adapter<Adapter_Allblogs.BlogdataHolder> {

    ArrayList personNames;
      ArrayList<BlogList> blogmodelList;
    Context context;
    DBHelper mDbhelper;

    Activity activity;
    TinyDB tinyDB;
    // List<Catagorylist> catagorylists;
    Boolean clicked = true;
    private int mPosition;
    private int mPositioncomment;
    String imageurl = "https://drzio-android.s3.ap-south-1.amazonaws.com/blog/";
    String userId;
    boolean addlike = false;
    private String imagename;
    int pos;
    ArrayList<String> blog_arrayname = new ArrayList<>();
    RecyclerView.ViewHolder holder;
    String userID;
    private BackpainAPIInterface apiInterface;
    private String token;
    private String BASEURL;

    ArrayList<String> blodid = new ArrayList<>();
    ArrayList<String> blodname = new ArrayList<>();
    ArrayList<List<String>> blodimage = new ArrayList<List<String>>();
    ArrayList<String> blodlink = new ArrayList<>();
    ArrayList<String> blodblog_Des = new ArrayList<>();
    ArrayList<Boolean> blodlikestatus = new ArrayList<Boolean>();
    ArrayList<Integer> blodbloglike = new ArrayList<Integer>();
    ArrayList<String> blodcreated_at = new ArrayList<>();
    ArrayList<String> blodupdated_at = new ArrayList<>();
    ArrayList<Integer> blogcommnet=new ArrayList<>();
    private final int ADS = 1, DATA = 0;
    private String musicurl;
    int page = 1, limit = 10;

    public Adapter_Allblogs(Context context, ArrayList<BlogList> blogmodelList, Activity_Bloglists activity) {
        this.context = context;
        this.blogmodelList = blogmodelList;
        mDbhelper = new DBHelper(context);
        this.userID = userID;


    }

    public Adapter_Allblogs(Context context, ArrayList<BlogList> blogmodelList) {
        this.context = context;
        this.blogmodelList = blogmodelList;
        mDbhelper = new DBHelper(context);
        this.userID = userID;
    }



    public Adapter_Allblogs(Context context, ArrayList<String> blodid1, ArrayList<String> blodname1, ArrayList<List<String>> blodimage1,
                             ArrayList<String> blodlink1, ArrayList<String> blodblog_des1, ArrayList<Boolean> blodlikestatus1,
                             ArrayList<Integer> blodbloglike1, ArrayList<String> blodcreated_at1, ArrayList<String> blodupdated_at1,
                             ArrayList<Integer> blogcommnet1) {
        this.context = context;
        this.blodid = blodid1;
        blodname = blodname1;
        blodimage = blodimage1;
        blodlink = blodlink1;
        blodblog_Des = blodblog_des1;
        blodlikestatus = blodlikestatus1;
        blodbloglike = blodbloglike1;
        blodcreated_at = blodcreated_at1;
        blodupdated_at = blodupdated_at1;
        blogcommnet=blogcommnet1;
        mDbhelper = new DBHelper(context);
        tinyDB = new TinyDB(context);
    }

    public Adapter_Allblogs.BlogdataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           /* View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blogdata, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;*/


        Adapter_Allblogs.BlogdataHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

                View v2 = inflater.inflate(R.layout.item_blogdata, parent, false);
                viewHolder = new Adapter_Allblogs.BlogdataHolder(v2);


        assert viewHolder != null;
        return viewHolder;
    }


 /*   @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blogdata, parent, false);
        Adapter_Allblogs.BlogdataHolder vh = new Adapter_Allblogs.BlogdataHolder(v);
        tinyDB = new TinyDB(context);
        return vh;
    }*/


    @Override
    public void onBindViewHolder(Adapter_Allblogs.BlogdataHolder holders, final int pos) {

                //  final int position = pos - Math.round(pos / 2);
                final int position = pos - Math.round(pos / 4);
                Adapter_Allblogs.BlogdataHolder holder = (Adapter_Allblogs.BlogdataHolder) holders;
                token = tinyDB.getString(Constants.Authtoken);
                BASEURL = tinyDB.getString(Constants.NewBaseUrl);
                TimeAgo2 timeAgo2 = new TimeAgo2();


                ArrayList<String> images = (ArrayList<String>) blodimage.get(position)/*.getImage()*/;
                String[] imagearray = new String[images.size()];
                for (int i1 = 0; i1 < images.size(); i1++) {
                    imagearray[i1] = images.get(i1);
                    Log.e("imagesname", String.valueOf(images.get(i1)));
                }

                try {
                    imagename = blodimage.get(position)/*.getImage()*/.get(0);
                } catch (Exception e) {
                    Log.e("exception", e.toString());
                }


                holder.btnexestart.setText("Following");


                holder.blog_catagoryname.setText(context.getResources().getString(R.string.height_increase_workout));


                Log.e("size", String.valueOf(blog_arrayname.size()));
                Log.e("blogsize", String.valueOf(blodname.size()));
                holder.nameofblog.setText(blodname.get(position)/*.getBlogName()*/);


                ArrayList<String> listlikes = mDbhelper.getLikeid();
                for (int i = 0; i < listlikes.size(); i++) {
                    String likeid = listlikes.get(i);
                    if (likeid != null && listlikes.contains(blodid.get(position)/*.getId()*/)) {
                        holder.like.setChecked(true);
                        holder.like.setBackground(context.getResources().getDrawable(R.drawable.heartlike));
                    } else {
                        holder.like.setChecked(false);
                        holder.like.setBackground(context.getResources().getDrawable(R.drawable.heart1));

                    }
                }
                holder.like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  /*  if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                        if (isChecked) {
                            int likeadd = Integer.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + 1;
                            String likes = String.valueOf(likeadd);

                            if (blogcommnet.get(position)== 0) {
                                holder.addcomment.setText(likes + " Likes");
                            } else {
                                holder.addcomment.setText(likes + " Likes  . "
                                        + String.valueOf(blogcommnet.get(position)) + " Comments");
                            }

                            CallAddlike(blodid.get(position)/*.getId()*/, position);
                            mDbhelper.insertContact(blodid.get(position)/*.getId()*/);
                            holder.like.setBackground(context.getResources().getDrawable(R.drawable.heartlike));
                        } else {
                            CallAddunlike(blodid.get(position)/*.getId()*/, position);
                            holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/));
                            mDbhelper.deleteContact(blodid.get(position)/*.getId()*/);
                            holder.like.setBackground(context.getResources().getDrawable(R.drawable.heart1));



                            if (blogcommnet.get(position)/*.blogComment.size()*/ == 0) {
                                holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");
                            } else {
                                holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes  . "
                                        + String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                            }
                            holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");
                        }


                    }
                });



                int blog_likess = blodbloglike.get(position)/*.getBlogLike()*/;

                if (blog_likess != 0) {
                    holder.addcomment.setText(blog_likess + " Likes");
                }
                if (blogcommnet.get(position)/*.blogComment.size()*/ > 0) {
                    holder.addcomment.setText(String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                }
                if (blog_likess != 0 && blogcommnet.get(position)/*.blogComment.size()*/ > 0) {
                    holder.addcomment.setText(blog_likess + " Likes  . "
                            + String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                }


                if (blog_likess == 0 && blogcommnet.get(position)/*.blogComment.size()*/ == 0) {
                    holder.addcomment.setText("Write a Comment");
                }

                // holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");

                holder.addcomment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPositioncomment = position;
                        DialogComment();

                    }
                });
                Adapter_Allblogs.SliderAdapter adapter = new Adapter_Allblogs.SliderAdapter(context, blodimage.get(position)/*.getImage()*/);
                holder.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                holder.slider.setSliderAdapter(adapter);
                holder.slider.setScrollTimeInSec(7);
                holder.slider.setAutoCycle(true);
                holder.slider.startAutoCycle();

                if (blodimage.get(position)/*.getImage()*/.size() == 1) {
              /*  holder.slider.setVisibility(View.GONE);
                Log.e("gone","gone");
                Glide.with(getContext())
                        .load(imageurl + blogmodelList.get(position).getImage().get(0))
                        .into(holder.ivicon1);*/
                    holder.slider.setFocusable(false);
                } else {
                    Log.e("gone1", "gone1");
                    //   holder.ivicon1.setVisibility(View.GONE);
                    holder.slider.setVisibility(View.VISIBLE);
                    holder.slider.setScrollTimeInSec(7);
                    holder.slider.setAutoCycle(true);
                    holder.slider.startAutoCycle();
                }
                holder.blogdescription.setHtml(blodblog_Des.get(position)/*.getBlogDescription()*/);








/*
        if (blogmodelList.get(position).getBlogDescription() != null) {
            Log.e("desss",blogmodelList.get(position).getBlogDescription());
            holder.blogdescription.setVisibility(View.VISIBLE);
            holder.blogdescription.setHtml(blogmodelList.get(position).getBlogDescription());
        } else {
            holder.blogdescription.setVisibility(View.GONE);
        }*/

                try {
                    URL url = new URL(blodlink.get(position)/*.getBlogLink()*/);
                    String host = url.getHost();
                    holder.link.setText(host);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                try {
                    holder.time_text.setText(timeAgo2.covertTimeToText(blodcreated_at.get(position)/*.getCreatedAt()*/));
                } catch (Exception e) {

                }


                holder.slider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                            Log.e("share", "gfgdedd");
                            Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                            intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("id", blodid.get(position)/*.getId()*/);
                            intent.putExtra("position", position);
                            intent.putExtra("userid", userId);
                            intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                            intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                            String[] blogdata = new String[blodname.size()];
                            intent.putExtra("blogmodelList", String.valueOf(blogdata));
                            try {
                                if (!imagename.isEmpty()) {
                                    intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                }
                            } catch (Exception e) {

                            }

                            context.startActivity(intent);
                        } else {
                            //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                holder.link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.e("share", "gfg");
                   /* if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                        if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                            Log.e("share", "gfgdedd");
                            Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                            intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("id", blodid.get(position)/*.getId()*/);
                            intent.putExtra("position", position);
                            intent.putExtra("userid", userId);
                            intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                            intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                            String[] blogdata = new String[blodname.size()];
                            intent.putExtra("blogmodelList", String.valueOf(blogdata));
                            try {
                                if (!imagename.isEmpty()) {
                                    intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                }
                            } catch (Exception e) {

                            }

                            context.startActivity(intent);
                        } else {
                            //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                     /*   } else {

                            leaderbord.setVisibility(View.GONE);
                            mLaynodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        leaderbord.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                    }*/

                    }
                });


                holder.mBlogdetail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.e("share", "gfg");
                   /* if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                        if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                            Log.e("share", "gfgdedd");
                            Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                            intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("id", blodid.get(position)/*.getId()*/);
                            intent.putExtra("position", position);
                            intent.putExtra("userid", userId);
                            intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                            intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                            String[] blogdata = new String[blodname.size()];
                            intent.putExtra("blogmodelList", String.valueOf(blogdata));
                            try {
                                if (!imagename.isEmpty()) {
                                    intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                }
                            } catch (Exception e) {

                            }

                            context.startActivity(intent);
                        } else {
                            //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                        }

                     /*   } else {

                            leaderbord.setVisibility(View.GONE);
                            mLaynodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        leaderbord.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                    }*/

                    }
                });
                holder.blogdescription.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                 /*   if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                        if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                            Log.e("share", "gfgdedd");
                            Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                            intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("id", blodid.get(position)/*.getId()*/);
                            intent.putExtra("position", position);
                            intent.putExtra("userid", userId);
                            intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                            intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                            String[] blogdata = new String[blodname.size()];
                            intent.putExtra("blogmodelList", String.valueOf(blogdata));
                            try {
                                if (!imagename.isEmpty()) {
                                    intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                }
                            } catch (Exception e) {

                            }

                            context.startActivity(intent);
                        } else {
                            //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                        }

                   /*     } else {

                            leaderbord.setVisibility(View.GONE);
                            mLaynodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        leaderbord.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                    }
*/
                    }
                });
                holder.ad_icon_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                  /*  if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                        if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                            Log.e("share", "gfgdedd");
                            Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                            intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("id", blodid.get(position)/*.getId()*/);
                            intent.putExtra("position", position);
                            intent.putExtra("userid", userId);
                            intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                            intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                            intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                            String[] blogdata = new String[blodname.size()];
                            intent.putExtra("blogmodelList", String.valueOf(blogdata));
                            try {
                                if (!imagename.isEmpty()) {
                                    intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                }
                            } catch (Exception e) {

                            }

                            context.startActivity(intent);
                        } else {
                            //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                        }

                    }
                });
                musicurl = tinyDB.getString(Constants.Backimageurl);
                holder.sharebutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            mPosition = position;

                            if (!blodimage.get(position)/*.getImage()*/.get(0).isEmpty()) {
                                new Adapter_Allblogs.RetrieveFeedTask(musicurl + "blog/" + blodimage.get(position)/*.getImage()*/.get(0)).execute();

                            }
                        } catch (Exception e) {
                            Log.e("errorinshare", e.toString());
                        }
                    }
                });



    }



    EditText addComment;
    ImageView sendcomment;
    BottomSheetBehavior bottomSheetBehavior;
    public   RecyclerView commentrecyclerview;
    private void DialogComment() {
        callShowCommentApi();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                    Toast.makeText(context, "Please write a comment!!..", Toast.LENGTH_LONG).show();
                }
            }
        });

        new BottomDialog.Builder(context)
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
        String idofblog = blodid.get(mPositioncomment)/*.getId()*/;
        ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
        //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
        //       CelluliteAPIInterface apiInterface = retrofit.create(CelluliteAPIInterface.class);
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("uId", userId);
            paramObject.put("comment", comment);


            Call<AddComment> userCall = apiInterface.addComment(idofblog, paramObject.toString(), "Bearer " + token);
            userCall.enqueue(new Callback<AddComment>() {
                @Override
                public void onResponse(Call<AddComment> call, Response<AddComment> response) {
                    Log.e("TAG1", String.valueOf(response.code()) /*+ response.body().toString()*/);

                    addComment.setText("");
                    callShowCommentApi();
                    try {
                        if (adapterTopblogs1 != null) {
                            adapterTopblogs1.notifyDataSetChanged();
                        }
                        if (adapterTopblogs != null) {
                            adapterTopblogs.notifyDataSetChanged();
                        }
                        //getBlog(page, limit,token);
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

        //     String token = Constants.Authtoken;


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
      /*  Retrofit retrofit = new Retrofit.Builder().baseUrl(CelluliteAPIInterface.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();*/

        String idofblog = blodid.get(mPositioncomment)/*.getId()*/;
        ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
        //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
        //  CelluliteAPIInterface apiInterface = retrofit.create(CelluliteAPIInterface.class);

        Call<GetComment> userCall = apiInterface.getComment(idofblog, "Bearer " + token);


        userCall.enqueue(new Callback<GetComment>() {
            @Override
            public void onResponse(Call<GetComment> call, Response<GetComment> response) {
                Log.d("TAG1", response.code() + "");
                GetComment getComment = (GetComment) response.body();
                assert getComment != null;

                ArrayList<GetComment.BlogComment> blogComments = (ArrayList<GetComment.BlogComment>) getComment.blogComment;


                String[] heroes = new String[blogComments.size()];

                for (int i = 0; i < blogComments.size(); i++) {
                    heroes[i] = blogComments.get(i).getComment();


                    Log.e("imagesname", String.valueOf(blogComments) + "hgh");


                }

                commentrecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                Adapter_addComment adapterTopblogs = new Adapter_addComment(context, blogComments, activity);
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
            Uri bmpUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            StringBuilder sb = new StringBuilder();
            sb.append(blodname.get(mPosition)/*.getBlogName()*/ + "\n");
            sb.append(Html.fromHtml(blodlink.get(mPosition)/*.getBlogLink()*/).toString());
            sb.append("\nHey check this application ");
            sb.append("https://play.google.com/store/apps/details?id=");
            sb.append(context.getPackageName());
            shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
            shareIntent.setType("image/*");
            context.startActivity(Intent.createChooser(shareIntent, "Share with"));
            // callShareApi();
        }
    }

    @Override
    public int getItemCount() {
        int size = blodname.size();
        if (blodname.size() > 0) {
            return blodname.size() + Math.round(blodname.size() / 6);
        }
        return blodname.size();
    }

    @Override
    public int getItemViewType(int position) {
        if ((position + 1) % 4 == 0) {
            return ADS;
        }
        return DATA;
    }
 /*   @Override
    public int getItemCount() {
        return blodname.size();
    }*/

    public class BlogdataHolder extends RecyclerView.ViewHolder {
        public int position;
        ImageView mIvicon, mIvsmallicon;
        TextView nameofblog, mTxtviews, blog_catagoryname, likecount, addcomment, blog_catagoryfollowers, btnexestart;
        RelativeLayout mBlogdetail;
        TextView time_text;
        CheckBox like;
        ImageView sharebutton;
        LinearLayout mLaylike;
        RelativeLayout ad_icon_layout;
        SliderView slider;
        HtmlTextView blogdescription;
        TextView link;
        RoundedImageView ivicon1;
        RelativeLayout catagoty_lay;

        public BlogdataHolder(View itemView) {
            super(itemView);
            nameofblog = (TextView) itemView.findViewById(R.id.nameofblog);
            blog_catagoryname = (TextView) itemView.findViewById(R.id.blog_catagoryname);
            mTxtviews = (TextView) itemView.findViewById(R.id.views);
            mBlogdetail = (RelativeLayout) itemView.findViewById(R.id.blogdetail);
            ad_icon_layout = itemView.findViewById(R.id.ad_icon_layout);
            like = itemView.findViewById(R.id.likebutton);
            likecount = itemView.findViewById(R.id.likecount);
            sharebutton = itemView.findViewById(R.id.sharebutton);
            mLaylike = (LinearLayout) itemView.findViewById(R.id.laylike);
            slider = itemView.findViewById(R.id.slider);
            //  ivicon1 = itemView.findViewById(R.id.ivicon1);
            addcomment = itemView.findViewById(R.id.addComment);
            blogdescription = (HtmlTextView) itemView.findViewById(R.id.blogdescription);
            blog_catagoryfollowers = itemView.findViewById(R.id.blog_catagoryfollowers);
            time_text = itemView.findViewById(R.id.time_text);
            link = itemView.findViewById(R.id.link);
            btnexestart = itemView.findViewById(R.id.btnexestart);
            catagoty_lay = itemView.findViewById(R.id.catagoty_lay);
        }
    }

    public class AdsHolder extends RecyclerView.ViewHolder {
        LinearLayout mRelativeFBAdmob;
        //   CardView mAdcard;

        AdsHolder(View itemView) {
            super(itemView);
            mRelativeFBAdmob = (LinearLayout) itemView.findViewById(R.id.adframe);
            //     mAdcard = (CardView) itemView.findViewById(R.id.adcard);
        }
    }

    NativeContentAdView nativeContentAdView;
    public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_content1, null);
                nativeContentAdView.setVisibility(View.VISIBLE);

                populateContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Log.e("error", "Failed to load native ad:: " + i);
                nativeContentAdView.setVisibility(View.GONE);
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("WrongConstant")
    public void populateContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setImageView(nativeContentAdView.findViewById(R.id.contentad_image));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        List images = nativeContentAd.getImages();
        if (images.size() > 0) {
            ((ImageView) nativeContentAdView.getImageView()).setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
        }
        com.google.android.gms.ads.formats.NativeAd.Image logo = nativeContentAd.getLogo();
        if (logo == null) {
            nativeContentAdView.getLogoView().setVisibility(4);
        } else {
            ((ImageView) nativeContentAdView.getLogoView()).setImageDrawable(logo.getDrawable());
            nativeContentAdView.getLogoView().setVisibility(0);
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
    }

    private void CallAddlike(String id, int pos) {
        //  String token = Constants.Authtoken;

        //   this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

        // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";

        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            JSONObject paramObject = new JSONObject();
            paramObject.put("update", "true");
            Call<AddLike> call = apiInterface.getLike(id, paramObject.toString(), "Bearer " + token);
            call.enqueue(new Callback<AddLike>() {
                @Override
                public void onResponse(Call<AddLike> call, Response<AddLike> response) {
                    Log.e("response", String.valueOf(response.code()));
                    AddLike addLike = response.body();
                    Log.e("likes", String.valueOf(addLike.getBlogLike()));
                    String likes = String.valueOf(addLike.getBlogLike());
                    Log.e("like", String.valueOf(likes));


                }

                @Override
                public void onFailure(Call<AddLike> call, Throwable t) {
                    Log.e("eroor", t.toString());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void CallAddunlike(String id, int pos) {

        //  String token = Constants.Authtoken;


        //   this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

        // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";

        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            JSONObject paramObject = new JSONObject();
            paramObject.put("update", "false");
            Call<AddLike> call = apiInterface.getLike(id, paramObject.toString(), "Bearer " + token);
            call.enqueue(new Callback<AddLike>() {
                @Override
                public void onResponse(Call<AddLike> call, Response<AddLike> response) {
                    Log.e("response", String.valueOf(response.body().toString()));

                    AddLike addLike = response.body();
                    Log.e("likes_unlike", String.valueOf(addLike.getBlogLike()));
                    String likes = String.valueOf(addLike.getBlogLike());

                }

                @Override
                public void onFailure(Call<AddLike> call, Throwable t) {
                    Log.e("eroor", t.toString());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private class SliderAdapter extends SliderViewAdapter<Adapter_Allblogs.SliderAdapter.SliderAdapterViewHolder> {
        private final List<String> mSliderItems;

        public SliderAdapter(Context context, List<String> sliderDataArrayList) {
            this.mSliderItems = sliderDataArrayList;
        }

        @Override
        public Adapter_Allblogs.SliderAdapter.SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
            View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
            return new Adapter_Allblogs.SliderAdapter.SliderAdapterViewHolder(inflate);
        }


        @Override
        public void onBindViewHolder(Adapter_Allblogs.SliderAdapter.SliderAdapterViewHolder viewHolder, final int position) {

            //  final Bloglist.Blog.Image sliderItem = mSliderItems.get(position);

            Glide.with(viewHolder.itemView)
                    .load(musicurl + "blog/" + mSliderItems.get(position))
                    .into(viewHolder.imageViewBackground);

            viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                        Log.e("share", "gfgdedd");
                        Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                        intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("id", blodid.get(position)/*.getId()*/);
                        intent.putExtra("position", position);
                        intent.putExtra("userid", userId);
                        intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                        intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                        String[] blogdata = new String[blodname.size()];
                        intent.putExtra("blogmodelList", String.valueOf(blogdata));
                        try {
                            if (!imagename.isEmpty()) {
                                intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                            }
                        } catch (Exception e) {

                        }

                        context.startActivity(intent);
                    } else {
                        //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        @Override
        public int getCount() {
            return mSliderItems.size();
        }

        class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
            //Adapter class for initializing the views of our slider view.
            View itemView;
            ImageView imageViewBackground;

            public SliderAdapterViewHolder(View itemView) {
                super(itemView);
                imageViewBackground = itemView.findViewById(R.id.myimage);
                this.itemView = itemView;
            }
        }
    }











}
