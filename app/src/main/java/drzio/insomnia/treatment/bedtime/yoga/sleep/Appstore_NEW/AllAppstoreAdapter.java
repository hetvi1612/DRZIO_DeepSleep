package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AllAppstoreAdapter extends RecyclerView.Adapter<AllAppstoreAdapter.CategoryViewholder> {
    private List<AppstoreList.Related> categoryitems;
    private Context context;
    private int selected = 0;
    Appclicklistner appclicklistner;
    TinyDB tinyDB;
    String BASEURL;
    private String musicurl;

    public interface Appclicklistner {
        void onClick(String apppackage, String coins, String app_number);
    }
    /*  public AllAppstoreAdapter(Context context, ArrayList<String> categoryitems) {
          this.categoryitems = categoryitems;
          this.context = context;
      }*/
    public AllAppstoreAdapter(AllApp_Activity context, List<AppstoreList.Related> related, Appclicklistner appclicklistner) {
        this.categoryitems = related;
        this.context = context;
        this.appclicklistner = appclicklistner;
        tinyDB = new TinyDB(context);
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    @NonNull
    @Override
    public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_allaplist,
                viewGroup, false);
        CategoryViewholder viewHolder = new CategoryViewholder(view);
        viewHolder.mCardclick = (RelativeLayout) view.findViewById(R.id.appsdetails);
        viewHolder.cattitles = (TextView) view
                .findViewById(R.id.tvappname);
        viewHolder.tvcoin = (TextView) view
                .findViewById(R.id.tvcoin);
        viewHolder.banner_image = (ImageView) view.findViewById(R.id.banner_image);
        viewHolder.ivappicon = (ImageView) view.findViewById(R.id.ivappicon);
        viewHolder.tvapprating = (TextView) view
                .findViewById(R.id.tvapprating);
        viewHolder.laycoin = (LinearLayout) view.findViewById(R.id.laycoin1);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final CategoryViewholder categoryViewholder, final int position) {
        //     final DietCateData.Datalist catitems = categoryitems.get(position);
       /* categoryViewholder.cattitles.setText(categoryitems.get(position));
        categoryViewholder.mCardclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/
        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
            categoryViewholder.laycoin.setVisibility(View.VISIBLE);
        } else {
            categoryViewholder.laycoin.setVisibility(View.GONE);
        }
        musicurl = tinyDB.getString(Constants.Backimageurl);
        BASEURL=tinyDB.getString(Constants.NewBaseUrl);
        try {

            categoryViewholder.cattitles.setText(categoryitems.get(position).getName());
           // categoryViewholder.tvcoin.setText(String.valueOf(categoryitems.get(position).getCoin()));
            categoryViewholder.tvapprating.setText(String.valueOf(categoryitems.get(selected).getRating())+"★");
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(musicurl + "apps/" + categoryitems.get(position).getBannerImage())
                    .apply(requestOptions).into(categoryViewholder.banner_image);

            Glide.with(context).load(musicurl + "apps/" + categoryitems.get(position).getImage())
                    .apply(requestOptions).into(categoryViewholder.ivappicon);
            categoryViewholder.mCardclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 //   appclicklistner.onClick(categoryitems.get(position).getLink(), String.valueOf(categoryitems.get(position).getCoin()), String.valueOf(categoryitems.get(position).getNumber()));
                    try {
                        context.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(categoryitems.get(position).getLink())));
                    } catch (android.content.ActivityNotFoundException anfe) {

                    }
                    Appstore_Click(categoryitems.get(position).getNumber());
                }
            });
        }catch (Exception e){
        }
    }
    public void Appstore_Click(Integer number) {
        try {
            String token = tinyDB.getString(Constants.Authtoken);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            OkHttpClient client = httpClient.build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("number", number);
                Call<Appstore_Addclick> call = apiInterface.addappstoreclick(paramObject.toString(), "Bearer " + token);
                call.enqueue(new Callback<Appstore_Addclick>() {
                    @Override
                    public void onResponse(Call<Appstore_Addclick> call, Response<Appstore_Addclick> response) {
                        try {
                            // Log.e("response", String.valueOf(response.body().toString()));
                            Appstore_Addclick appstore_addclick = response.body();
                            String app_name = appstore_addclick.getName();
                            // Log.e("app_name", app_name);
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<Appstore_Addclick> call, Throwable t) {
                        //  Log.e("erooraddtime", t.toString());
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
    class CategoryViewholder extends RecyclerView.ViewHolder {
        RelativeLayout mCardclick;
        TextView cattitles, tvcoin, tvapprating;
        ImageView banner_image, ivappicon;
        LinearLayout laycoin;
        public CategoryViewholder(View itemView) {
            super(itemView);
        }
    }
}