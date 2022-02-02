package drzio.insomnia.treatment.bedtime.yoga.sleep.customs;

import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;


public class CountDownAnimation {

    private TextView mTextView;
    private Animation mAnimation;
    private int mStartCount;
    private int mCurrentCount;
    private CountDownListener mListener;

    private Handler mHandler = new Handler();

    private final Runnable mCountDown = new Runnable() {
        public void run() {
            if (mCurrentCount >= 0) {
                if (mCurrentCount <= 4) {
                    if (mCurrentCount == 1) {
                        if (tinydb.getBoolean(Constants.ISGUIDEON_KEY) && !tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                            fitnessApplication.speak("Go ");
                        }
						mTextView.setVisibility(View.VISIBLE);
                        mTextView.setText("Go");
                    } else if (mCurrentCount == 4){
                        if (tinydb.getBoolean(Constants.ISGUIDEON_KEY) && !tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                            fitnessApplication.speak(String.valueOf(3));
                        }
						mTextView.setVisibility(View.VISIBLE);
						mTextView.setText(3 + "");
                    }else if (mCurrentCount == 3){
						if (tinydb.getBoolean(Constants.ISGUIDEON_KEY) && !tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
							fitnessApplication.speak(String.valueOf(2));
						}
						mTextView.setVisibility(View.VISIBLE);
						mTextView.setText(2 + "");
					}else if (mCurrentCount == 2){
						if (tinydb.getBoolean(Constants.ISGUIDEON_KEY) && !tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
							fitnessApplication.speak(String.valueOf(1));
						}
						mTextView.setVisibility(View.VISIBLE);
						mTextView.setText(1 + "");
					}else {
						mTextView.setVisibility(View.GONE);
					}
                    mTextView.startAnimation(mAnimation);
                    mCurrentCount--;
                } else {
                    mTextView.setVisibility(View.GONE);
                    mCurrentCount--;
                }
            } else {
                mTextView.setVisibility(View.GONE);
                if (mListener != null)
                    mListener.onCountDownEnd(CountDownAnimation.this);
            }
        }
    };
    private TinyDB tinydb;
    private FitnessApplication fitnessApplication;


    public CountDownAnimation(TextView textView, int startCount, TinyDB tinyDB, FitnessApplication fitnessApplication) {
        this.mTextView = textView;
        this.mStartCount = startCount;
        this.tinydb = tinyDB;
        this.fitnessApplication = fitnessApplication;
        if (fitnessApplication.isSpeaking()) {
            fitnessApplication.stop();
        }
        mAnimation = new AlphaAnimation(1.0f, 0.0f);
        mAnimation.setDuration(1000);
    }


    public void start() {
        mHandler.removeCallbacks(mCountDown);


        mTextView.setText(mStartCount + "");
        mTextView.setVisibility(View.VISIBLE);

        mCurrentCount = mStartCount;

        mHandler.post(mCountDown);
        for (int i = 1; i <= mStartCount; i++) {
            mHandler.postDelayed(mCountDown, i * 1000);
        }
    }


    public void cancel() {
        mHandler.removeCallbacks(mCountDown);

        mTextView.setText("");
        mTextView.setVisibility(View.GONE);
    }


    public void setAnimation(Animation animation) {
        this.mAnimation = animation;
        if (mAnimation.getDuration() == 0)
            mAnimation.setDuration(1000);
    }


    public Animation getAnimation() {
        return mAnimation;
    }


    public void setStartCount(int startCount) {
        this.mStartCount = startCount;
    }


    public int getStartCount() {
        return mStartCount;
    }

    public void setCountDownListener(CountDownListener listener) {
        mListener = listener;
    }

    public static interface CountDownListener {
        void onCountDownEnd(CountDownAnimation animation);
    }
}
