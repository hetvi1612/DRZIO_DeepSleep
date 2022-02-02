package drzio.insomnia.treatment.bedtime.yoga.sleep.customs;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

public class ProgressBarAnimation extends Animation {
    private RoundCornerProgressBar mProgressBar;
    private int mTo;
    private int mFrom;
    private long mStepDuration;

    /**
     * @param fullDuration - time required to fill progress from 0% to 100%
     */
    public ProgressBarAnimation(RoundCornerProgressBar progressBar, long fullDuration) {
        super();
        mProgressBar = progressBar;
        mStepDuration = (long) (fullDuration / progressBar.getMax());
    }


    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        }

        if (progress > mProgressBar.getMax()) {
            progress = (int) mProgressBar.getMax();
        }

        mTo = progress;

        mFrom = (int) mProgressBar.getProgress();
        setDuration(Math.abs(mTo - mFrom) * mStepDuration);
        mProgressBar.startAnimation(this);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float value = mFrom + (mTo - mFrom) * interpolatedTime;
        mProgressBar.setProgress((int) value);
    }
}
