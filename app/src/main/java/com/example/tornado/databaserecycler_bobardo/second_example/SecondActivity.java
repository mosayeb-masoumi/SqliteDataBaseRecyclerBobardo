package com.example.tornado.databaserecycler_bobardo.second_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tornado.databaserecycler_bobardo.R;
import com.example.tornado.databaserecycler_bobardo.SqliteDB.DatabaseHelper;
import com.example.tornado.databaserecycler_bobardo.model.Employee;
import com.example.tornado.databaserecycler_bobardo.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {


    DatabaseHelper db;

    Button btn_add_list;
    Button btn_add_object;
    Button btn_get_list;
    Button btn_delete_db;
    Button btn_delete_list_item;
    Button btn_update_list_item;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        initView();
        db = new DatabaseHelper(this);

        List<UserModel> userList = new ArrayList<>();
        userList.add(new UserModel("ali", "rezai", "blue" , 0));
        userList.add(new UserModel("milad", "rezai", "red" ,1));
        userList.add(new UserModel("nabi", "hasani","green", 0));
        userList.add(new UserModel("gholi", "ahmadi","black" ,1));


        btn_add_list.setOnClickListener(view -> {

            // first delete db
            new DatabaseHelper(SecondActivity.this).deleteDBUser();

            for (int i = 0; i < userList.size() ; i++) {
                UserModel userModel = new UserModel();
                userModel.setName(userList.get(i).getName());
                userModel.setFamily(userList.get(i).getFamily());
                userModel.setColor(userList.get(i).getColor());
                userModel.setLiving_status(userList.get(i).getLiving_status());

                new DatabaseHelper(SecondActivity.this).insertUser(userModel);
                Log.i("TAG", "onCreate: ");
            }
        });


        btn_add_object.setOnClickListener(view -> {

            // first delete db
            new DatabaseHelper(SecondActivity.this).deleteDBUser();

            UserModel userModel = new UserModel();
            userModel.setName("ali");
            userModel.setFamily("alipour");
            userModel.setLiving_status(0);

            db.insertUser(userModel);
            Log.i("TAG", "onCreate: ");

        });


        btn_get_list.setOnClickListener(view -> {

            List<UserModel> list = new DatabaseHelper(SecondActivity.this).getListOfUsers();
            List<UserModel> list2 = new ArrayList<>();
            list2.addAll(list);

        });


        btn_delete_db.setOnClickListener(view -> {
            new DatabaseHelper(SecondActivity.this).deleteDBUser();
            List<UserModel> list = new DatabaseHelper(SecondActivity.this).getListOfUsers();
            Log.i("TAG", "onCreate: ");
        });



        btn_delete_list_item.setOnClickListener(view -> {
            List<UserModel> list = new DatabaseHelper(SecondActivity.this).getListOfUsers();
            for (int i = 0; i < list.size(); i++) {

                if(i == 1){   // delete second itm
                    new DatabaseHelper(SecondActivity.this).deleteUser(list.get(i));
                }
            }

            List<UserModel> list2 = new DatabaseHelper(SecondActivity.this).getListOfUsers();
            Log.i("TAG", "onCreate: ");

        });


        btn_update_list_item.setOnClickListener(view -> {
            List<UserModel> list = new DatabaseHelper(SecondActivity.this).getListOfUsers();
            for (int i = 0; i < list.size(); i++) {
                if(i == 1){
                    list.get(i).setName("sabzali");
                    new DatabaseHelper(SecondActivity.this).updateUser(list.get(i));
                }
            }

            List<UserModel> list2 = new DatabaseHelper(SecondActivity.this).getListOfUsers();
            Log.i("TAG", "onCreate: ");

        });




    }

    private void initView() {
        btn_add_object = findViewById(R.id.btn_add_object);
        btn_delete_db = findViewById(R.id.btn_delete_db);
        btn_add_list = findViewById(R.id.btn_add_list);
        btn_get_list = findViewById(R.id.btn_get_list);
        btn_delete_list_item = findViewById(R.id.btn_delete_list_item);
        btn_update_list_item = findViewById(R.id.btn_update_list_item);
    }
}