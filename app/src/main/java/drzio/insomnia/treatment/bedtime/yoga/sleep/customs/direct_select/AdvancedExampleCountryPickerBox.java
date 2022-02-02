package drzio.insomnia.treatment.bedtime.yoga.sleep.customs.direct_select;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ramotion.directselect.DSAbstractPickerBox;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class AdvancedExampleCountryPickerBox extends DSAbstractPickerBox<AdvancedExampleCountryPOJO> {

    private final TinyDB tinydb;
    private TextView text;
    private View cellRoot;

    public AdvancedExampleCountryPickerBox(@NonNull Context context) {
        this(context, null);
    }

    public AdvancedExampleCountryPickerBox(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdvancedExampleCountryPickerBox(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        tinydb = new TinyDB(context);
        init(context);
    }

    private void init(@NonNull Context context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert mInflater != null;
        mInflater.inflate(R.layout.advanced_example_country_picker_box, this, true);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(context, languages);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.text = findViewById(R.id.custom_cell_text);
        this.cellRoot = findViewById(R.id.custom_cell_root);
    }

    @Override
    public void onSelect(AdvancedExampleCountryPOJO selectedItem, int selectedIndex) {
        tinydb.putInt("selected",selectedIndex);
        this.text.setText(selectedItem.getTitle());
        tinydb.putInt("goalday",selectedItem.getValue());
    }

    @Override
    public View getCellRoot() {
        return this.cellRoot;
    }
}
