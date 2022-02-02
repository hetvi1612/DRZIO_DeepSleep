package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class AppstoreAdapter extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;
    public int postion;
    ArrayList<String> namearraylist = new ArrayList<String>();
    ArrayList<Integer> ratearraylist = new ArrayList<Integer>();
    ArrayList<String> imagearraylist = new ArrayList<String>();
    ArrayList<String> linkarraylist = new ArrayList<String>();
    ArrayList<Integer> coinarraylist = new ArrayList<Integer>();
    ArrayList<String> bannerimagearraylist = new ArrayList<String>();
    //   Appclicklistner appclicklistner;


    ImageView image1, image2, image3, image4, image5, image6, image7;
    String badge;
    List<Appstore_new> relateds1;

    ImageView banner_image, app_images;
    TextView app_name, rate, app_coins, likecount, addComment, blog_catagoryfollowers, btnexestart;
    RelativeLayout mCatagorydetail;

    RelativeLayout catagoty_lay;
    private String musicurl;
    private TinyDB tinyDB;

    public AppstoreAdapter(AppstoreActivity appstoreActivity, ArrayList<String> namearraylist, ArrayList<Integer> ratearraylist, ArrayList<String> imagearraylist, ArrayList<String> bannerimagearraylist, ArrayList<String> linkarraylist, ArrayList<Integer> coinarraylist) {

        this.context = appstoreActivity;
        this.namearraylist = namearraylist;
        this.ratearraylist = ratearraylist;
        this.imagearraylist = imagearraylist;
        this.bannerimagearraylist = bannerimagearraylist;
        this.linkarraylist = linkarraylist;
        this.coinarraylist = coinarraylist;
        //  inflter = (LayoutInflater.from(appstoreActivity));
    }

    public AppstoreAdapter(Context appstoreActivity, List<Appstore_new> relateds) {
        this.context = appstoreActivity;
        this.relateds1 = relateds;
            inflter = (LayoutInflater.from(appstoreActivity));

    }

    @Override
    public int getCount() {
        return relateds1.size();
    }

    @Override
    public Integer getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflter.inflate(R.layout.custom_appstore1, null);
        tinyDB=new TinyDB(context);
        app_name = (TextView) view.findViewById(R.id.app_names);
        rate = (TextView) view.findViewById(R.id.rates);
        app_coins = (TextView) view.findViewById(R.id.coins);
        mCatagorydetail = view.findViewById(R.id.mCatagorydetail);
        banner_image = (ImageView) view.findViewById(R.id.banner_image);
        app_images = (ImageView) view.findViewById(R.id.app_images);
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(context).load(musicurl + "apps/" + relateds1.get(i).getImage())
                .apply(requestOptions).into(app_images);
        Log.e("app_name", musicurl + "apps/" + relateds1.get(i).getImage());
        app_name.setText(relateds1.get(i).getName());
        musicurl = tinyDB.getString(Constants.Backimageurl);
        Integer rates = relateds1.get(i).getCoin();
        rate.setText(String.valueOf(rates + "★"));
        Integer coin = relateds1.get(i).getCoin();
        app_coins.setText(String.valueOf(coin));
           /* mCatagorydetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPackagename = name.replace("https://play.google.com/store/apps/details?id=","");
                    mAppinstallicon = Integer.parseInt(coins);
                    Rateus(name);
                }
            });*/
        try {


        } catch (Exception exception) {

        }

        return view;
    }

  /*  private static class ViewHolder {
        ImageView banner_image, app_images;
        TextView app_name, rate, app_coins, likecount, addComment, blog_catagoryfollowers, btnexestart;
        RelativeLayout mCatagorydetail;

        RelativeLayout catagoty_lay;

        public ViewHolder(@NonNull View view) {

            app_name = (TextView) view.findViewById(R.id.app_names);
            rate = (TextView) view.findViewById(R.id.rates);
            app_coins = (TextView) view.findViewById(R.id.coins);
            mCatagorydetail = view.findViewById(R.id.mCatagorydetail);
            banner_image = (ImageView) view.findViewById(R.id.banner_image);
            app_images = (ImageView) view.findViewById(R.id.app_images);
        }*/
   // }

}
