package com.example.tornado.databaserecycler_bobardo.SqliteDB;

/**
 * Created by tornado on 1/4/2019.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String tableUser = "user";
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


        String createUserTable = "CREATE TABLE IF NOT EXISTS "+ tableUser + " (" +
                USER_ID + " INTEGER PRIMARY KEY, " +
                USER_NAME + " TEXT NOT NULL, " +
                USER_FAMILY + " TEXT NOT NULL, " +
                USER_COLOR + " TEXT, " +
                USER_LIVING_STATUS + " INTEGER DEFAULT 0);";          // boolean



        mydb.execSQL(createEmployeesTable);
        mydb.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb, int oldVersion, int newVersion) {

    }
}