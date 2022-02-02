package drzio.insomnia.treatment.bedtime.yoga.sleep.customs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatSeekBar;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.models.ProgressItem;

public class CustomSeekBar extends AppCompatSeekBar {
    private ArrayList<ProgressItem> mProgressItemsList;
    int offset = 1;

    public CustomSeekBar(Context context) {
        super(context);
    }

    public CustomSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initData(ArrayList<ProgressItem> arrayList) {
        this.mProgressItemsList = arrayList;
    }

    public void onDraw(Canvas canvas) {
        if (this.mProgressItemsList.size() > 0) {
            int width = getWidth();
            int height = getHeight();
            int thumbOffset = getThumbOffset();
            int i = 0;
            int i2 = 0;
            while (i < this.mProgressItemsList.size()) {
                ProgressItem progressItem = (ProgressItem) this.mProgressItemsList.get(i);
                Paint paint = new Paint();
                paint.setColor(getResources().getColor(progressItem.color));
                int i3 = ((int) ((progressItem.progressItemPercentage * ((float) width)) / 100.0f)) + i2;
                if (i == this.mProgressItemsList.size() - 1 && i3 != width) {
                    i3 = width;
                }
                RectF rect = new RectF(offset, // left
                        offset, // top
                        canvas.getWidth() - offset, // right
                        canvas.getHeight() - offset);
                int i4 = thumbOffset / 2;
                int cornersRadius = 1;

                rect.set(i2, i4, i3, height - i4);
                canvas.drawRoundRect( rect, // rect
                        cornersRadius, // rx
                        cornersRadius, paint);
                i++;
                i2 = i3;
            }
            super.onDraw(canvas);
        }
    }

    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }
}
