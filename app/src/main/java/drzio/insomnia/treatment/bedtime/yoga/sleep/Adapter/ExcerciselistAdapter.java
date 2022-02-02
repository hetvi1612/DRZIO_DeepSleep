package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class ExcerciselistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Daymodals> excerciseDataList = new ArrayList<>();
    Activity excerciselist;
    private final Context context;
    private Dialog mBottomSheetDialog;
    private LinearLayout mPreviouslayout;
    private LinearLayout mCurrentlayout;
    private LinearLayout mNextlayout;
    private int mPosition;
    private TextView mCurrentpos;
    private final int REST = 0, EXERCISE = 1;
    ArrayList<String> roundnames = new ArrayList<>();
    private int REST_TAG;
    int mLevel;
    private VideoView mPrevVidview;
    private VideoView mCurVidview;
    private VideoView mNextVidview;
    TinyDB tinyDB;
    public ExcerciselistAdapter(Context context, List<Daymodals> excerciseDataList, Activity excerciselist, ArrayList<String> roundnames, int mLevel) {
        this.context = context;
        this.excerciseDataList = excerciseDataList;
        this.excerciselist = excerciselist;
        this.roundnames = roundnames;
        this.mLevel = mLevel;
       /* if (mLevel == 1 || mLevel == 2){*/
            REST_TAG = 6;

        /*}else {
            REST_TAG = 5;

        }*/
        tinyDB = new TinyDB(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);

        switch (viewType) {
            case REST:
                View v1 = inflater.inflate(R.layout.item_roundnames, parent, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case EXERCISE:
                View v2 = inflater.inflate(R.layout.item_excerciselist, parent, false);
                viewHolder = new ExcerciseviewHolder(v2);
                break;
            default:
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, final int position) {
        switch (holders.getItemViewType()) {
            case REST:
                ViewHolder1 vh1 = (ViewHolder1) holders;
                configureViewHolder1(vh1, position / REST_TAG);
                break;
            case EXERCISE:
                ExcerciseviewHolder vh2 = (ExcerciseviewHolder) holders;
                configureViewHolder2(vh2, position);
                break;
            default:
                break;
        }

    }

    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        try {
            vh1.name.setText(roundnames.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void configureViewHolder2(final ExcerciseviewHolder vh2, final int position) {
        final int index = position - (position / REST_TAG) - 1;
        Daymodals excerciseData = excerciseDataList.get(index);

        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(excerciseData.getExethumbs())
                .apply(requestOptions).into(vh2.mExeimage);
        vh2.mExcerName.setText(excerciseData.getExecercisename().toUpperCase());
        vh2.mExcerTime.setText(excerciseData.getExecerciseduration());
        vh2.mExcerciselayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBottomSheet(index);
            }
        });

    }


    @SuppressLint("BASEURL")
    public void openBottomSheet(int pos) {
        mPosition = pos;
        final View view = this.excerciselist.getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        mPreviouslayout = (LinearLayout) view.findViewById(R.id.previousview);
        mCurrentlayout = (LinearLayout) view.findViewById(R.id.currentview);
        mNextlayout = (LinearLayout) view.findViewById(R.id.nextview);
        ImageView mBtnclose = (ImageView) view.findViewById(R.id.imgclose);
        final ImageView mBtnprev = (ImageView) view.findViewById(R.id.prevexcer);
        final ImageView mBtnnext = (ImageView) view.findViewById(R.id.nextexcer);
        mCurrentpos = (TextView) view.findViewById(R.id.currentpos);
        TextView mTotalworkout = (TextView) view.findViewById(R.id.totalno);
        mTotalworkout.setText(String.valueOf(excerciseDataList.size()));
        int temp = mPosition + 1;
        mCurrentpos.setText(String.valueOf(temp));
        if (mPosition == 0) {
            mBtnprev.setImageAlpha(60);
        }
        if (mPosition == excerciseDataList.size() - 1) {
            mBtnnext.setImageAlpha(60);
        }
        mBtnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {
                if (mPosition == 0) {
                    mBtnprev.setAlpha(60);
                } else {
                    if (mPosition == 1) {
                        mBtnprev.setAlpha(60);
                    }
                    mPreviouslayout.setVisibility(View.VISIBLE);
                    mCurrentlayout.setVisibility(View.GONE);
                    mNextlayout.setVisibility(View.GONE);
                    if (mPrevVidview != null) {
                        mPrevVidview.setVisibility(View.VISIBLE);
                    }
                    if (mCurVidview != null) {
                        mCurVidview.setVisibility(View.GONE);
                    }
                    if (mNextVidview != null) {
                        mNextVidview.setVisibility(View.GONE);
                    }
                    mBtnnext.setAlpha(255);
                    mPosition--;
                    int temp = mPosition + 1;
                    mCurrentpos.setText(String.valueOf(temp));
                    Previousviewlayout(view, mPosition);
                }
            }
        });

        mBtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View views) {
                if (mPosition == excerciseDataList.size() - 1) {
                    mBtnnext.setAlpha(60);
                } else {
                    if (mPosition == excerciseDataList.size() - 2) {
                        mBtnnext.setAlpha(60);
                    }
                    mPreviouslayout.setVisibility(View.GONE);
                    mCurrentlayout.setVisibility(View.GONE);
                    mNextlayout.setVisibility(View.VISIBLE);

                    if (mPrevVidview != null) {
                        mPrevVidview.setVisibility(View.GONE);
                    }
                    if (mCurVidview != null) {
                        mCurVidview.setVisibility(View.GONE);
                    }
                    if (mNextVidview != null) {
                        mNextVidview.setVisibility(View.VISIBLE);
                    }
                    mBtnprev.setAlpha(255);
                    mPosition++;
                    int temp = mPosition + 1;
                    mCurrentpos.setText(String.valueOf(temp));
                    Nextviewlayout(view, mPosition);
                }

            }
        });
        Currentviewlayout(view, mPosition);
        mBottomSheetDialog = new Dialog(context, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, 1400);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        mBottomSheetDialog.show();
        mBtnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetDialog.dismiss();
            }
        });

    }


    public void Previousviewlayout(View view, int pos) {
        TextView mPworkname = (TextView) view.findViewById(R.id.pworkoutname);
        TextView mPworkdesc = (TextView) view.findViewById(R.id.pexcerdesc);
        mPrevVidview = (VideoView) view.findViewById(R.id.pvidviewvid);
        mPworkname.setText(excerciseDataList.get(pos).getExecercisename());
      //  mPworkdesc.setText(excerciseDataList.get(pos).getExedescription());

        Log.e("getExedescription", excerciseDataList.get(pos).getExedescription());

        String languages = tinyDB.getString(Constants.Language);
        Log.e("languages",languages);
        TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT, languages,
                // Language.HINDI,
                excerciseDataList.get(pos).getExedescription());

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                Log.d("TAG", "onSuccess: " + translatedText);
                //  textView.setText(translatedText);
                mPworkdesc.setText(translatedText);
            /*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*/
            }

            @Override
            public void onFailure(String ErrorText) {
                Log.d("TAG", "onFailure: " + ErrorText);
            }
        });
        if (mPrevVidview.isPlaying()) {
            mPrevVidview.pause();
            mPrevVidview.requestFocus();
        }
        Uri video = Uri.parse(excerciseDataList.get(pos).getExecerciseimaga());
        mPrevVidview.setVideoURI(video);
        mPrevVidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mPrevVidview.start();
            }
        });
    }

    public void Currentviewlayout(View view, int pos) {
        TextView mCworkname = (TextView) view.findViewById(R.id.cworkoutname);
        TextView mCworkdesc = (TextView) view.findViewById(R.id.cexcerdesc);
        mCurVidview = (VideoView) view.findViewById(R.id.cvidviewvid);
        mCworkname.setText(excerciseDataList.get(pos).getExecercisename());
       // mCworkdesc.setText(excerciseDataList.get(pos).getExedescription());

        Log.e("getExedescription", excerciseDataList.get(pos).getExedescription());

        String languages = tinyDB.getString(Constants.Language);
        TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT, languages,
                // Language.HINDI,
                excerciseDataList.get(pos).getExedescription());

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                Log.d("TAG", "onSuccess: " + translatedText);
                //  textView.setText(translatedText);
                mCworkdesc.setText(translatedText);
            /*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*/
            }

            @Override
            public void onFailure(String ErrorText) {
                Log.d("TAG", "onFailure: " + ErrorText);
            }
        });
        if (mCurVidview.isPlaying()) {
            mCurVidview.pause();
            mCurVidview.requestFocus();
        }
        Uri video = Uri.parse(excerciseDataList.get(pos).getExecerciseimaga());
        mCurVidview.setVideoURI(video);
        mCurVidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mCurVidview.start();
            }
        });
    }

    private void Nextviewlayout(View view, int pos) {
        TextView mNworkname = (TextView) view.findViewById(R.id.nworkoutname);
        TextView mNworkdesc = (TextView) view.findViewById(R.id.nexcerdesc);
        mNextVidview = (VideoView) view.findViewById(R.id.nvidviewvid);
        mNworkname.setText(excerciseDataList.get(pos).getExecercisename());
       // mNworkdesc.setText(excerciseDataList.get(pos).getExedescription());


        String languages = tinyDB.getString(Constants.Language);
        Log.e("languages",languages);
        TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT, languages,
                // Language.HINDI,
                excerciseDataList.get(pos).getExedescription());

        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                Log.d("TAG", "onSuccess: " + translatedText);
                //  textView.setText(translatedText);
                mNworkdesc.setText(translatedText);
            /*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*/
            }

            @Override
            public void onFailure(String ErrorText) {
                Log.d("TAG", "onFailure: " + ErrorText);
            }
        });
        if (mNextVidview.isPlaying()) {
            mNextVidview.pause();
            mNextVidview.requestFocus();
        }
        Uri video = Uri.parse(excerciseDataList.get(pos).getExecerciseimaga());
        mNextVidview.setVideoURI(video);
        mNextVidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mNextVidview.start();
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return (position % REST_TAG == 0) ? REST
                : EXERCISE;
    }

    @Override
    public int getItemCount() {
        return excerciseDataList.size() + roundnames.size();
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder1(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.roundtxt);
        }
    }

    class ExcerciseviewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mExcerciselayout;
        TextView mExcerName, mExcerTime;
        ImageView mExeimage;

        public ExcerciseviewHolder(View itemView) {
            super(itemView);
            mExcerciselayout = (RelativeLayout) itemView.findViewById(R.id.excerlayout);
            mExcerName = (TextView) itemView.findViewById(R.id.excetitle);
            mExcerTime = (TextView) itemView.findViewById(R.id.excetime);
            mExeimage = (ImageView) itemView.findViewById(R.id.exceimage);

        }
    }


}
