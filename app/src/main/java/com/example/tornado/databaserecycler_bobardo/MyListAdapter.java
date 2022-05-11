package com.example.tornado.databaserecycler_bobardo;

/**
 * Created by tornado on 1/4/2019.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.tornado.databaserecycler_bobardo.SqliteDB.DatabaseHelper;
import com.example.tornado.databaserecycler_bobardo.model.Employee;

import java.util.List;

class MyListAdapter extends BaseAdapter {

    Context context;
    List<Employee> employees;

    MyListAdapter(Context context, List<Employee> employees){
        this.context = context;
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return employees.size();
    }

    @Override
    public Object getItem(int i) {
        return employees.get(i);
    }

    @Override
    public long getItemId(int i) {
        return employees.get(i).getId();
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        View rowView = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        TextView txtName = (TextView) rowView.findViewById(R.id.txt_employee_name);
        TextView txtPhone = (TextView) rowView.findViewById(R.id.txt_employee_phone);
        TextView txtAddress = (TextView) rowView.findViewById(R.id.txt_employee_address);
        Button btnEdit = (Button) rowView.findViewById(R.id.btn_edit);
        Button btnDelete = (Button) rowView.findViewById(R.id.btn_delete);

        txtName.setText(employees.get(position).getName());
        txtPhone.setText(employees.get(position).getPhone());
        txtAddress.setText(employees.get(position).getAddress());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) context).editEmployee(employees.get(position));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatabaseHelper(context).deleteEmployee(employees.get(position));
                employees.remove(position);
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}