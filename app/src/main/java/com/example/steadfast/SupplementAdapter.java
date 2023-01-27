package com.example.steadfast;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import java.util.List;

public class SupplementAdapter extends BaseAdapter {
    private List<Supplement> mSupplements;
    private Context mContext;

    public SupplementAdapter(Context context, List<Supplement> supplements) {
        mContext = context;
        mSupplements = supplements;
    }

    @Override
    public int getCount() {
        return mSupplements.size();
    }

    @Override
    public Object getItem(int position) {
        return mSupplements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SupplementView view;
        if (convertView == null) {
            view = new SupplementView(mContext);
        } else {
            view = (SupplementView) convertView;
        }
        view.bind(mSupplements.get(position));

        CheckBox checkBox = (CheckBox) view.findViewById(R.id.supplement_taken);
        checkBox.setChecked(mSupplements.get(position).isTaken());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSupplements.get(position).setTaken(isChecked);
            }
        });
        return view;
    }}


