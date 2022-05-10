package com.example.tornado.databaserecycler_bobardo;

/**
 * Created by tornado on 1/4/2019.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private SQLiteDatabase mydb;

    public DatabaseHelper(Context context){
        mydb = new MyDatabase(context).getWritableDatabase();
    }

    public void addNewEmployee(Employee employee){
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("phone", employee.getPhone());
        values.put("address", employee.getAddress());

        mydb.insert(MyDatabase.tableEmployees, null, values);
        mydb.close();
    }

    public List<Employee> getListOfEmployees(){

        Cursor c = mydb.rawQuery("select * from " + MyDatabase.tableEmployees, null);
        List<Employee> employees = new ArrayList<>();

        while (c.moveToNext()){

            Employee em = new Employee();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.ID)));
            em.setName(c.getString(c.getColumnIndex(MyDatabase.NAME)));
            em.setPhone(c.getString(c.getColumnIndex(MyDatabase.PHONE)));
            em.setAddress(c.getString(c.getColumnIndex(MyDatabase.ADDRESS)));

            employees.add(em);
        }

        c.close();
        mydb.close();
        return employees;
    }

    public void editEmployee(Employee employee){
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("phone", employee.getPhone());
        values.put("address", employee.getAddress());

        mydb.update(MyDatabase.tableEmployees, values, "id = " + employee.getId(), null);
        mydb.close();
    }

    public void deleteEmployee(Employee employee){
        mydb.delete(MyDatabase.tableEmployees, "id = " + employee.getId(), null);
        mydb.close();
    }

    public List<Employee> searchByName(String name){

        Cursor c = mydb.rawQuery("select * from " + MyDatabase.tableEmployees + " where name like '%" + name + "%'", null);
        List<Employee> employees = new ArrayList<>();

        while (c.moveToNext()){

            Employee em = new Employee();
            em.setId(c.getInt(c.getColumnIndex(MyDatabase.ID)));
            em.setName(c.getString(c.getColumnIndex(MyDatabase.NAME)));
            em.setPhone(c.getString(c.getColumnIndex(MyDatabase.PHONE)));
            em.setAddress(c.getString(c.getColumnIndex(MyDatabase.ADDRESS)));

            employees.add(em);
        }

        c.close();
        mydb.close();
        return employees;
    }

}