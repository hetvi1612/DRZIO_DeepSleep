package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes;

import android.widget.TextView;

public class R_ViewHolder {
    public TextView text;

    public R_ViewHolder(TextView textView) {
        this.text = textView;
    }

    public TextView getText() {
        return this.text;
    }

    public void setText(TextView textView) {
        this.text = textView;
    }
}
