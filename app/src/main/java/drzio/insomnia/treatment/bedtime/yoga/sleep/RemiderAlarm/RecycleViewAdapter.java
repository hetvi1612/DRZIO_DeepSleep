package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes.M_ListViewItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {
    public Context context;
    private ReminderMainActivity reminderMainActivity;
    private ArrayList<M_ListViewItem> reminderList;
    private final ArrayList<Integer> selected = new ArrayList<>();
    private TinyDB tinyDB;

    public class RecycleViewHolder extends RecyclerView.ViewHolder implements OnClickListener, OnLongClickListener {
        Switch alarm_on_off;
        TextView date;
        ReminderMainActivity reminderMainActivity;
        RelativeLayout relative_container;
        TextView repeat;
        TextView time;
        TextView time_am_pm;
        TextView time_diff;
        TextView title;

        RecycleViewHolder(View view, final ReminderMainActivity reminderMainActivity2) {
            super(view);

            this.title = (TextView) view.findViewById(R.id.textViewName);
            this.date = (TextView) view.findViewById(R.id.textViewDate);
            this.time = (TextView) view.findViewById(R.id.textViewTime);
            this.repeat = (TextView) view.findViewById(R.id.textViewRepeat);
            this.time_diff = (TextView) view.findViewById(R.id.time_diff);
            this.alarm_on_off = (Switch) view.findViewById(R.id.on_off_switch);
            this.time_am_pm = (TextView) view.findViewById(R.id.time_am_pm);
            this.relative_container = (RelativeLayout) view.findViewById(R.id.relative_container);
            this.reminderMainActivity = reminderMainActivity2;
            this.relative_container.setOnClickListener(this);
            this.relative_container.setOnLongClickListener(this);
            this.alarm_on_off.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @SuppressLint("WrongConstant")
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (z) {
                        RecycleViewHolder.this.alarm_on_off.setChecked(true);
                        RecycleViewHolder.this.time.setTextColor(Color.parseColor("#ffffff"));
                        RecycleViewHolder.this.time_am_pm.setTextColor(Color.parseColor("#ffffff"));
                        RecycleViewHolder.this.date.setTextColor(Color.parseColor("#ffffff"));
                        RecycleViewHolder.this.repeat.setTextColor(Color.parseColor("#ffffff"));
                        RecycleViewHolder.this.title.setTextColor(Color.parseColor("#ffffff"));
                        RecycleViewHolder.this.time_diff.setVisibility(0);
                    } else {
                        RecycleViewHolder.this.alarm_on_off.setChecked(false);
                        RecycleViewHolder.this.time.setTextColor(Color.parseColor("#808080"));
                        RecycleViewHolder.this.time_am_pm.setTextColor(Color.parseColor("#808080"));
                        RecycleViewHolder.this.date.setTextColor(Color.parseColor("#808080"));
                        RecycleViewHolder.this.repeat.setTextColor(Color.parseColor("#808080"));
                        RecycleViewHolder.this.title.setTextColor(Color.parseColor("#808080"));
                        RecycleViewHolder.this.time_diff.setVisibility(8);
                    }
                    reminderMainActivity2.AlarmSwitch_on_off(z, RecycleViewHolder.this.getAdapterPosition());
                }
            });
        }

        public boolean onLongClick(View view) {
            int color = RecycleViewAdapter.this.context.getResources().getColor(R.color.tbtncolor);
            if (RecycleViewAdapter.this.selected.contains(getAdapterPosition())) {
                RecycleViewAdapter.this.selected.remove((Integer) getAdapterPosition());
            } else {
                RecycleViewAdapter.this.selected.add(getAdapterPosition());
                color = RecycleViewAdapter.this.context.getResources().getColor(R.color.tbtncolor);
            }
            tinyDB.remove("alarmtime");
            RecycleViewAdapter.this.GradientDrawable(color, 10, 50, this.relative_container);
            this.reminderMainActivity.LongClickListener(view, getAdapterPosition());
            return true;
        }

        public void onClick(View view) {
            int color = RecycleViewAdapter.this.context.getResources().getColor(R.color.recycleviewBackground);
            if (RecycleViewAdapter.this.selected.contains(getAdapterPosition())) {
                RecycleViewAdapter.this.selected.remove(Integer.valueOf(getAdapterPosition()));
            } else {
                RecycleViewAdapter.this.selected.add(getAdapterPosition());
                color = Color.TRANSPARENT;
            }
            RecycleViewAdapter.this.GradientDrawable(color, 10, 50, this.relative_container);
            this.reminderMainActivity.PrepareSelection(view, getAdapterPosition());
        }
    }

    public RecycleViewAdapter(Context context2, ArrayList<M_ListViewItem> arrayList) {
        this.context = context2;
        this.reminderList = arrayList;
        this.reminderMainActivity = (ReminderMainActivity) context2;
    }

    @NonNull
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        tinyDB = new TinyDB(context);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        return new RecycleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_layout, viewGroup, false), this.reminderMainActivity);
    }

    @SuppressLint("WrongConstant")
    public void onBindViewHolder(@NonNull RecycleViewHolder recycleViewHolder, int i) {
        M_ListViewItem m_ListViewItem = (M_ListViewItem) this.reminderList.get(i);
        if (m_ListViewItem.getTitle().trim().isEmpty()) {
            recycleViewHolder.title.setVisibility(8);
        } else {
            recycleViewHolder.title.setText(m_ListViewItem.getTitle());
        }
        recycleViewHolder.date.setText(m_ListViewItem.getDate());
        recycleViewHolder.time.setText(m_ListViewItem.getTime());
        recycleViewHolder.repeat.setText(m_ListViewItem.getRepeat());
        recycleViewHolder.time_diff.setText(m_ListViewItem.getTime_diff());
        recycleViewHolder.time_am_pm.setText(m_ListViewItem.getTime_am_pm());
        recycleViewHolder.alarm_on_off.setChecked(m_ListViewItem.getSwitch_state());
    }

    public int getItemCount() {
        return this.reminderList.size();
    }

    public void GradientDrawable(int i, int i2, int i3, RelativeLayout relativeLayout) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadius((float) i2);
        gradientDrawable.setStroke(i3, i);
        relativeLayout.setBackground(gradientDrawable);
    }
}
