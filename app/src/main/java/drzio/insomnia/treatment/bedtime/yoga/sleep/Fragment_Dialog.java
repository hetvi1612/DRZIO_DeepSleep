package drzio.insomnia.treatment.bedtime.yoga.sleep;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Fragment_Dialog extends DialogFragment {

    public static final String TAG = "Fragment_Dialog";
    private Dialog dialog;
    private TinyDB tinydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        tinydb = new TinyDB(getContext());
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);

    }

    @Override
    public void onStart() {
        super.onStart();
        dialog = getDialog();

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_ads, parent, false);
      /*  RelativeLayout mainlay = (RelativeLayout) view.findViewById(R.id.mailalyh);
        LottieAnimationView mLottie = (LottieAnimationView) view.findViewById(R.id.lotti);
        mLottie.setAnimation("celebrate.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        LinearLayout mNativecontainer = view.findViewById(R.id.adplace);
        showGOOGLEAdvance(getContext(), mNativecontainer);
        ImageView mClose = (ImageView) view.findViewById(R.id.img_close);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        CardView mAdcard = (CardView) view.findViewById(R.id.adcard);
        mAdcard.setVisibility(View.GONE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    Animation slide_down = AnimationUtils.loadAnimation(getContext(),
                            R.anim.adzoom_in);
                    mAdcard.setAnimation(slide_down);
                    mAdcard.setVisibility(View.VISIBLE);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },2000);*/


        return view;
    }


}