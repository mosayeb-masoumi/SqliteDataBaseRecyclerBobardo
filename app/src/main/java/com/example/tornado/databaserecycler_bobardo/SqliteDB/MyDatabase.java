package com.example.tornado.databaserecycler_bobardo.SqliteDB;

/**
 * Created by tornado on 1/4/2019.
 */
import static android.graphics.PorterDuff.Mode.ADD;
import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "database";
    private final static int DB_VERSION = 1;

    /************************* employees ************************************/
    public static final String tableEmployees = "employees";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";

    /************************* user ************************************/
    public static final String user = "user";
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_FAMILY = "user_family";
    public static final String USER_COLOR = "user_color";
    public static final String USER_LIVING_STATUS = "user_living_status";




    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {

        String createEmployeesTable = "CREATE TABLE IF NOT EXISTS "+ tableEmployees + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                PHONE + " TEXT, " +
                ADDRESS + " TEXT);";


//        String createUserTable = "CREATE TABLE IF NOT EXISTS "+ user + " (" +
//                USER_ID + " INTEGER PRIMARY KEY, " +
//                USER_NAME + " TEXT NOT NULL, " +
//                USER_FAMILY + " TEXT NOT NULL, " +
//                USER_LIVING_STATUS + " INTEGER DEFAULT 0);";          // boolean


        String createUserTable ="CREATE TABLE IF NOT EXISTS user ("
                + "user_id integer primary key, user_name text, user_family text, user_living_status integer);";

        mydb.execSQL(createEmployeesTable);
        mydb.execSQL(createUserTable);


    }




    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldVersion, int newVersion) {


        if (newVersion > oldVersion) {
            /************* first option   add a column***********************/
//            mydb.execSQL("ALTER TABLE " + user_table + " ADD COLUMN " + "user_color" + " text");




            Log.i("TAG", "onUpgrade: ");
            /********************************second option is to delete the last table and create new table with new columns  and copy the former to new one *********************************/


//            // here we added color column to new table
            mydb.execSQL("CREATE TABLE IF NOT EXISTS user_tmp ("
                    + "user_id integer, user_name text, user_family text, user_color text,user_living_status integer);");
            mydb.execSQL("INSERT INTO user_tmp(user_id,user_name,user_family,user_living_status) SELECT * FROM user");
            mydb.execSQL("drop table user;");
            mydb.execSQL("ALTER TABLE user_tmp RENAME TO user");



        }


    }
}