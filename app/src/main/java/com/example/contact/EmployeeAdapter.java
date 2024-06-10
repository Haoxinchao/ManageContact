package com.example.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private Context context;
    private List<Employee> employeeList;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return employeeList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.employee_item, parent, false);
        }

        TextView txtEmployeeName = convertView.findViewById(R.id.txtEmployeeName);
        TextView txtEmployeeEmail = convertView.findViewById(R.id.txtEmployeeEmail);

        Employee employee = employeeList.get(position);
        txtEmployeeName.setText(employee.getName());
        txtEmployeeEmail.setText(employee.getEmail());

        return convertView;
    }
}
