package com.example.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UnitAdapter extends BaseAdapter {
    private Context context;
    private List<Unit> unitList;
    public UnitAdapter(Context context, List<Unit> unitList) {
        this.context = context;
        this.unitList = unitList;
    }
    @Override
    public int getCount() {
        return unitList.size();
    }

    @Override
    public Object getItem(int position) {
        return unitList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return unitList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.unit_item, parent, false);
        }

        TextView txtUnitName = convertView.findViewById(R.id.txtUnitName);
        TextView txtUnitEmail = convertView.findViewById(R.id.txtUnitEmail);

        Unit unit = unitList.get(position);
        txtUnitName.setText(unit.getUnitName());
        txtUnitEmail.setText(unit.getEmail());

        return convertView;
    }
}
