package drzio.insomnia.treatment.bedtime.yoga.sleep.customs.direct_select;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class AdvancedExampleCountryAdapter extends ArrayAdapter<AdvancedExampleCountryPOJO> {
    private List<AdvancedExampleCountryPOJO> items;
    private Context context;
    private TinyDB tinyDB;

    public AdvancedExampleCountryAdapter(@NonNull Context context, int resource, @NonNull List<AdvancedExampleCountryPOJO> objects) {
        super(context, resource, objects);
        this.items = objects;
        this.context = context;
        tinyDB = new TinyDB(context);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AdvancedExampleCountryAdapter.ViewHolder holder;

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);

        if (null == convertView) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert vi != null;
            convertView = vi.inflate(R.layout.advanced_example_country_list_item, parent, false);
            holder = new AdvancedExampleCountryAdapter.ViewHolder();
            holder.text = convertView.findViewById(R.id.custom_cell_text);
            convertView.setTag(holder);
        } else {
            holder = (AdvancedExampleCountryAdapter.ViewHolder) convertView.getTag();
        }

        if (null != holder) {
            holder.text.setText(items.get(position).getTitle());
        }
        return convertView;
    }

    private class ViewHolder {
        TextView text;
    }

}
