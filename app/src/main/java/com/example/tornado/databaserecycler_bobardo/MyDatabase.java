package com.example.tornado.databaserecycler_bobardo;

/**
 * Created by tornado on 1/4/2019.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "database";
    private final static int DB_VERSION = 1;

    public static final String tableEmployees = "employees";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {

        mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ tableEmployees + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                PHONE + " TEXT, " +
                ADDRESS + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb,
                          int oldVersion, int newVersion) {

    }
}