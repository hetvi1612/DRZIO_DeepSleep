package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dinuscxj.progressbar.CircleProgressBar;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.AdapterListner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.MusicApi;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;
import static com.bumptech.glide.load.engine.DiskCacheStrategy.ALL;

public class Adapter_Musiclist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    AdapterListner adapterListner;
    // AdapterListner1 adapterListner1;
    AdapterListner1 adapterListner1;
    ArrayList<File> listFiles1;
    ArrayList<String> musicname = new ArrayList<>();
    List<MusicApi> loginData;
    public static String DIRECTORY_TO_SAVE_MEDIA_NOW = "/storage/emulated/0/Music/DrZio";
    RecyclerView mRvsonglist;

    private String extension1;
    private TinyDB tinyDB;
    private MediaPlayer mediaPlayer;
    private boolean clicked = false;
    private int pos;
    private MyViewHolder holder;
    private static final int REQUEST_STORAGE = 111;
    String musicurl = "https://drzio-android.s3.ap-south-1.amazonaws.com/";
    List<Object> allIWeHave = new ArrayList<>();

    ArrayList<String> _idarrayList1 = new ArrayList<>();
    ArrayList<String> namearrayList1 = new ArrayList<>();
    ArrayList<String> durationarrayList1 = new ArrayList<>();
    ArrayList<String> musicarrayList1 = new ArrayList<>();
    ArrayList<String> imagearrayList1 = new ArrayList<>();
    ArrayList<String> createdAtarrayList1 = new ArrayList<>();
    ArrayList<String> updatedAtAtarrayList1 = new ArrayList<>();
    private String BASEURL;

    public Adapter_Musiclist(Context context, AdapterListner1 adapterListner1,
                             ArrayList<String> idarrayList, ArrayList<String> namearrayList, ArrayList<String> durationarrayList,
                             ArrayList<String> musicarrayList, ArrayList<String> imagearrayList, ArrayList<String> createdAtarrayList,
                             ArrayList<String> updatedAtAtarrayList, AdapterListner adapterListner, RecyclerView onlinesonglist,
                             ArrayList<File> listFiles) {
        this.context = context;
        this._idarrayList1 = idarrayList;
        this.namearrayList1 = namearrayList;
        this.durationarrayList1 = durationarrayList;
        this.musicarrayList1 = musicarrayList;
        this.imagearrayList1 = imagearrayList;
        this.createdAtarrayList1 = createdAtarrayList;
        this.updatedAtAtarrayList1 = updatedAtAtarrayList;
        this.adapterListner = adapterListner;
        this.adapterListner1 = adapterListner1;
        this.mRvsonglist = onlinesonglist;
        this.listFiles1 = listFiles;
        tinyDB = new TinyDB(context);
    }

   /* public Adapter_Musiclist(RecyclerView onlinesonglist, ArrayList<String> idarrayList, ArrayList<String> durationarrayList, Context mainActivity, ArrayList<String> createdAtarrayList, ArrayList<String> updatedAtAtarrayList, AdapterListner adapterListner, ArrayList<String> namearrayList, ArrayList<File> listFiles, ArrayList<String> musicarrayList, AdapterListner1 adapterListner1, ArrayList<String> imagearrayList) {
        this.context = context;
        this._idarrayList1=idarrayList;
        this.namearrayList1=namearrayList;
        this.durationarrayList1=durationarrayList;
        this.musicarrayList1=musicarrayList;
        this.imagearrayList1=imagearrayList;
        this.createdAtarrayList1=createdAtarrayList;
        this.updatedAtAtarrayList1=updatedAtAtarrayList;
        this.adapterListner = adapterListner;
        this.adapterListner1 = adapterListner1;
        this.mRvsonglist = onlinesonglist;
        this.listFiles1 = listFiles;
        tinyDB = new TinyDB(context);



    }*/


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_musiclist, parent, false);

        return new MyViewHolder(itemView);
    }

    File file;

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        holder = (MyViewHolder) holders;
        BASEURL = tinyDB.getString(Constants.NewBaseUrl);

        holder.mTvName.setText(namearrayList1.get(position));
        //  musicurl = tinyDB.getString(Constants.Yogaimageurl);
        //musicname.add(loginData.get(position).getName());
        try {
            if (musicarrayList1.get(position).contains(".")) {
                extension1 = musicarrayList1.get(position).substring(musicarrayList1.get(position).lastIndexOf("."));
            }
            String path = Environment.getExternalStorageDirectory() + "/DrZio/" + namearrayList1.get(position) + extension1;
            //holder.mTvTime.setText(String.valueOf(getDuration(Uri.parse(path))));
            holder.mTvTime.setVisibility(View.GONE);

            //    file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/DrZio/" + namearrayList1.get(position) + extension1);;

            File file1 = context.getExternalFilesDir("Music");
            if (!file1.exists())
                file1.mkdir();


            file = new File(file1, namearrayList1.get(position) + extension1);


            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(ALL);
            Glide.with(context).load(musicurl + "music/" + imagearrayList1.get(position))
                    .apply(requestOptions).into(holder.mIvimg);
            Log.e("imagemusic", imagearrayList1.get(position));
        } catch (Exception e) {

        }

        holder.mIvplaybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("fdjfdf","fghughfj");
                adapterListner1.onPlaymusic(position);
              /*  if (file.getName().equals(namearrayList1.get(position))) {
                    clicked = false;
                    try {

                        //  adapterListner.onPlaymusic(position, file);
                        adapterListner1.onPlaymusic(position);
                        if (adapterListner1 != null) {
                            adapterListner1.notifyAll();
                        }
                        //  mediaPlayer.start();
                    } catch (Exception e) {

                    }
                }*/
            }
        });

        holder.ivdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean hasPermissionLocation = (ContextCompat.checkSelfPermission(context,
                        WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                if (!hasPermissionLocation) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{WRITE_EXTERNAL_STORAGE},
                            REQUEST_STORAGE);
                } else {
                    try {
                       /* if (file.getName().equals(namearrayList1.get(position))) {
                            clicked = false;
                            try {
                                pos = position;
                                //  adapterListner.onPlaymusic(position, file);
                                adapterListner1.onPlaymusic(pos);
                                //  mediaPlayer.start();
                            } catch (Exception e) {

                            }
                        } else {
*/
                        if (SDK_INT >= Build.VERSION_CODES.R) {
                            /* if (Environment.isExternalStorageManager()) {*/
                            clicked = true;
                            pos = position;
                                  /*  String path = Environment.getExternalStorageDirectory() + "/Music/Drzio";

                                    Log.e("tag", "path=" + path);
                                    File dir = new File(path);*/


                            new DownloadFileFromURL().execute(musicurl + "music/" + musicarrayList1.get(position), namearrayList1.get(position), "");
                            if (!mRvsonglist.isComputingLayout()) {
                                notifyItemChanged(position);
                            }
                            Log.e("positionmusic", String.valueOf(position));
                        } /*else {
                                    //request for the permission
                                    Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                                    intent.setData(uri);
                                    context.startActivity(intent);
                                }*/
                        /* } */
                        else {
                            clicked = true;
                            pos = position;
                             /*   String path = Environment.getExternalStorageDirectory() + "/Music/Drzio";

                                Log.e("tag", "path=" + path);
                                File dir = new File(path);
*/

                            new DownloadFileFromURL().execute(musicurl + "music/" + musicarrayList1.get(position), namearrayList1.get(position), "");
                            if (!mRvsonglist.isComputingLayout()) {
                                notifyItemChanged(position);
                            }
                            Log.e("positionmusic", String.valueOf(position));
                        }

                        /*  }*/
                    } catch (Exception e) {

                    }
                }
            }
        });

        if (clicked) {
            clicked = false;
            if (pos == position) {

                holder.ivdownload.setVisibility(View.GONE);
                holder.circleProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }


    class DownloadFileFromURL extends AsyncTask<String, String, String> {
        String extension;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                String filename = new String(f_url[1]);
                String postion = new String(f_url[2]);
                URLConnection connection = url.openConnection();
                connection.connect();

                if (f_url[0].contains(".")) {
                    extension = f_url[0].substring(f_url[0].lastIndexOf("."));
                }

                int lenghtOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());

                // Output stream


                File file = context.getExternalFilesDir("Music");
                if (!file.exists())
                    file.mkdir();

                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC) + "/" + "DrZio/";

                Log.e("tag", "path=" + path);


                OutputStream output = new FileOutputStream(file + "/" + filename + extension);
                //  OutputStream output = new FileOutputStream(path + "/" + filename + extension);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                // }

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }


        protected void onProgressUpdate(String... progress) {


            simulateProgress(progress[0]);
            if (progress[0].equals("100")) {
            } else {

            }
        }


        @Override
        protected void onPostExecute(String file_url) {
            if (file_url != null) {
                Toast.makeText(context, "Error", Toast.LENGTH_LONG).show();
            } else {
                holder.mIvplaybtn.setVisibility(View.VISIBLE);
                holder.circleProgressBar.setVisibility(View.GONE);
                Log.e("file_url", "file_url=" + file_url);

                adapterListner1.onPlaymusic(pos);
                Toast.makeText(context, "Download Completed", Toast.LENGTH_LONG).show();
            }

        }

    }


    private void simulateProgress(String progress) {
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //   int progress = (int) animation.getAnimatedValue();

                holder.circleProgressBar.setProgress(Integer.parseInt(progress));

            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(4000);
        animator.start();
    }

    @Override
    public int getItemCount() {
        return namearrayList1 == null ? 0 : namearrayList1.size();
    }

    private String getDuration(Uri path) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(context, path);
        String durationStr = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        assert durationStr != null;
        return formateMilliSeccond(Long.parseLong(durationStr));
    }

    public String formateMilliSeccond(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";
        String thirdString = "";

        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

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
        ImageView mIvimg, mIvplaybtn, ivdownload;
        CircleProgressBar circleProgressBar;


        public MyViewHolder(View itemView) {
            super(itemView);
            rowView = itemView;
            mLaymain = (LinearLayout) itemView.findViewById(R.id.llaymain);
            mTvName = (TextView) itemView.findViewById(R.id.tvname);
            mTvTime = (TextView) itemView.findViewById(R.id.tvduration);
            mIvimg = (ImageView) itemView.findViewById(R.id.ivimg);
            mIvplaybtn = (ImageView) itemView.findViewById(R.id.ivplay);
            ivdownload = (ImageView) itemView.findViewById(R.id.ivdownload);
            circleProgressBar = (CircleProgressBar) itemView.findViewById(R.id.circleprog);
        }
    }

    public interface AdapterListner {

        void onPlaymusic(int songnum, File file);

    }

}
