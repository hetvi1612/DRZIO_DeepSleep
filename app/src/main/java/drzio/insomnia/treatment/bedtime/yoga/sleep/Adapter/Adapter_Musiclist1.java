package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.AdapterListner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Adapter_Musiclist1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    AdapterListner1 adapterListner;
    ArrayList<File> listFiles1;
    int pos;
    private TinyDB tinyDB;
    String musicurl;
    public static String DIRECTORY_TO_SAVE_MEDIA_NOW = "/storage/emulated/0/Music/DrZio/";
    /* public Adapter_Musiclist1(Context context, AdapterListner adapterListner) {
         this.context = context;
         this.adapterListner = adapterListner;
     }
 */
    public Adapter_Musiclist1(Context context, ArrayList<File> listFiles, AdapterListner1 adapterListner) {
        this.context = context;
        this.listFiles1 = listFiles;
        this.adapterListner = adapterListner;
        tinyDB = new TinyDB(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musiclist1, parent, false);
       /* String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);*/
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        final MyViewHolder holder = (MyViewHolder) holders;
        final File file = (File) this.listFiles1.get(position);

      //  musicurl = tinyDB.getString(Constants.Yogaimageurl);
        Log.e("fileadapter1", String.valueOf(file));
        holder.mTvName.setText(file.getName());
     /*   Glide.with(FitnessApplication.getInstance())
                .load(songimgs[position])
                .into(holder.mIvimg);*/
        //    Uri mediaPath = Uri.parse("android.resource://" + context.getPackageName() +"/" + songids[position]);
        holder.mTvTime.setText(getDuration(Uri.fromFile(file)));
        holder.mLaymain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("position", String.valueOf(position));
                pos=position;
                adapterListner.onPlaymusic(pos);


            }
        });
    }


    @Override
    public int getItemCount() {
        return listFiles1.size();
    }
    String durationStr;
    private String getDuration(Uri path) {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(context, path);
            durationStr = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            assert durationStr != null;

        }catch (Exception e){

        }
        return formateMilliSeccond(Long.parseLong(durationStr));
    }

    public String formateMilliSeccond(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";
        String thirdString = "";

        int hours = (int) (milliseconds / (1000  *60  *60));
        int minutes = (int) (milliseconds % (1000  *60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60*  60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        if (minutes < 10) {
            thirdString = "0" + minutes;
        } else {
            thirdString = "" + minutes;
        }

        finalTimerString = finalTimerString + thirdString + ":" + secondsString;

        return finalTimerString;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        View rowView;
        LinearLayout mLaymain;
        TextView mTvName, mTvTime;
        ImageView mIvimg,mIvplaybtn;

        public MyViewHolder(View itemView) {
            super(itemView);
            rowView = itemView;
            mLaymain = (LinearLayout) itemView.findViewById(R.id.llaymain);
            mTvName = (TextView) itemView.findViewById(R.id.tvname);
            mTvTime = (TextView) itemView.findViewById(R.id.tvduration);
            mIvimg = (ImageView) itemView.findViewById(R.id.ivimg);
            mIvplaybtn = (ImageView) itemView.findViewById(R.id.ivplay);
        }
    }


}
