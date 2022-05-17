package com.example.tornado.databaserecycler_bobardo.SqliteDB;

/**
 * Created by tornado on 1/4/2019.
 */
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tornado.databaserecycler_bobardo.model.Employee;
import com.example.tornado.databaserecycler_bobardo.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

    private SQLiteDatabase mydb;

    public DatabaseHelper(Context context){
        mydb = new MyDatabase(context).getWritableDatabase();
    }




    /******************************************************  employees ********************************************************/
    public void addNewEmployee(Employee employee){
        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("phone", employee.getPhone());
        values.put("address", employee.getAddress());

        mydb.insert(MyDatabase.tableEmployees, null, values);
        mydb.close();
    }

    @SuppressLint("Range")
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

    @SuppressLint("Range")
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





    /******************************************************  users ********************************************************/


    public void insertUser(UserModel user){
        ContentValues values = new ContentValues();
        values.put("user_name" , user.getName());
        values.put("user_family" , user.getFamily());
//        values.put("user_color" , user.getColor());
        values.put("user_living_status" , user.getLiving_status());
        mydb.insert(MyDatabase.user , null , values);
        mydb.close();
    }



    public void updateUser(UserModel user){
        ContentValues values = new ContentValues();
        values.put("user_name" , user.getName());
        values.put("user_family" , user.getFamily());
//        values.put("user_color" , user.getColor());
        values.put("user_living_status" , user.getLiving_status());
        mydb.update(MyDatabase.user , values , "user_id = " + user.getId() , null);
        mydb.close();
    }




    // delete item
    public void deleteUser(UserModel user){
        mydb.delete(MyDatabase.user, "user_id = " + user.getId(), null);
        mydb.close();
    }


    // delete all table
    public void deleteDBUser() {
        mydb.delete(MyDatabase.user, null, null);
        mydb.close();
    }


    @SuppressLint("Range")
    public List<UserModel> getListOfUsers(){

        Cursor c = mydb.rawQuery("select * from " + MyDatabase.user, null);
        List<UserModel> users = new ArrayList<>();

        while (c.moveToNext()){

            UserModel user = new UserModel();
            user.setId(c.getInt(c.getColumnIndex(MyDatabase.USER_ID)));
            user.setName(c.getString(c.getColumnIndex(MyDatabase.USER_NAME)));
            user.setFamily(c.getString(c.getColumnIndex(MyDatabase.USER_FAMILY)));
//            user.setColor(c.getString(c.getColumnIndex(MyDatabase.USER_COLOR)));
            user.setLiving_status(c.getInt(c.getColumnIndex(MyDatabase.USER_LIVING_STATUS)));

            users.add(user);
        }

        c.close();
        mydb.close();
        return users;
    }


}