package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes.R_ListViewItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes.R_ViewHolder;
import drzio.insomnia.treatment.bedtime.yoga.sleep.inter_face.Reminder_Interface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class R_ListAdapter extends ArrayAdapter {
    private R_ListViewItem[] objects;
    private Reminder_Interface reminderInterface;
    private TinyDB tinyDB;

    public int getViewTypeCount() {
        return 5;
    }

    public R_ListAdapter(Context context, int i, R_ListViewItem[] r_ListViewItemArr, Reminder_Interface reminder_Interface) {
        super(context, i, r_ListViewItemArr);
        this.objects = r_ListViewItemArr;
        this.reminderInterface = reminder_Interface;

        tinyDB = new TinyDB(context);
   }

    public int getItemViewType(int i) {
        return this.objects[i].getType();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        R_ListViewItem r_ListViewItem = this.objects[i];

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
        switch (getItemViewType(i)) {
            case 0:
                view = LayoutInflater.from(getContext()).inflate(R.layout.reminder_name_layout, null);
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        R_ListAdapter.this.reminderInterface.OnReminderItemClickListener(0);
                    }
                });
                break;
            case 1:
                view = LayoutInflater.from(getContext()).inflate(R.layout.date_layout, null);
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        R_ListAdapter.this.reminderInterface.OnReminderItemClickListener(1);
                    }
                });
                break;
            case 2:
                view = LayoutInflater.from(getContext()).inflate(R.layout.repeat_layout, null);
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        R_ListAdapter.this.reminderInterface.OnReminderItemClickListener(2);
                    }
                });
                break;
            case 3:
                view = LayoutInflater.from(getContext()).inflate(R.layout.reminder_type_layout, null);
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        R_ListAdapter.this.reminderInterface.OnReminderItemClickListener(3);
                    }
                });
                break;
            case 4:
                view = LayoutInflater.from(getContext()).inflate(R.layout.reportas_layout, null);
                view.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        R_ListAdapter.this.reminderInterface.OnReminderItemClickListener(4);
                    }
                });
                break;
        }
        TextView textView = (TextView) view.findViewById(R.id.name);
        R_ViewHolder r_ViewHolder = new R_ViewHolder((TextView) view.findViewById(R.id.text));
        view.setTag(r_ViewHolder);
        r_ViewHolder.getText().setText(r_ListViewItem.getText());
        return view;
    }
}
