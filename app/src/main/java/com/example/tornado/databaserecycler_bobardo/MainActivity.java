package com.example.tornado.databaserecycler_bobardo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;
    private Button btnCancel;
    private ListView listView;
    private MyListAdapter adapter;
    private EditText edtSearch, edtName, edtPhone, edtAddress;
    private int seletcedEmployeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = (Button) findViewById(R.id.btn_add);
        Button btnShowSearch = (Button) findViewById(R.id.btn_search);
        Button btnCloseSearch = (Button) findViewById(R.id.btn_close_search);
        Button btnDoSearch = (Button) findViewById(R.id.btn_do_search);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

        edtSearch = (EditText) findViewById(R.id.edt_search);
        edtName = (EditText) findViewById(R.id.edt_employee_name);
        edtPhone = (EditText) findViewById(R.id.edt_employee_phone);
        edtAddress = (EditText) findViewById(R.id.edt_employee_address);

        btnAdd.setOnClickListener(onAddClicked);
        btnShowSearch.setOnClickListener(onShowSearchClicked);
        btnCloseSearch.setOnClickListener(onCloseSearchClicked);
        btnDoSearch.setOnClickListener(onDoSearchClicked);
        btnCancel.setOnClickListener(onCancelbuttonClicked);

        listView = (ListView) findViewById(R.id.listView);
        refreshList();
    }

    private void refreshList() {
        List<Employee> employees = new DatabaseHelper(this).getListOfEmployees();
        adapter = new MyListAdapter(this, employees);
        listView.setAdapter(adapter);
    }

    public void editEmployee(Employee employee) {
        findViewById(R.id.lin_add_search).setVisibility(View.GONE);
        findViewById(R.id.add_edit_employee).setVisibility(View.VISIBLE);
        btnSubmit.setOnClickListener(onEditEmployeeSubmit);
        edtName.setText(employee.getName());
        edtPhone.setText(employee.getPhone());
        edtAddress.setText(employee.getAddress());
        seletcedEmployeeId = employee.getId();
    }

    private View.OnClickListener onAddClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            findViewById(R.id.lin_add_search).setVisibility(View.GONE);
            findViewById(R.id.add_edit_employee).setVisibility(View.VISIBLE);
            btnSubmit.setOnClickListener(onNewEmployeeSubmit);
        }
    };

    private View.OnClickListener onShowSearchClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            findViewById(R.id.lin_add_search).setVisibility(View.GONE);
            findViewById(R.id.search_bar).setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener onCloseSearchClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            findViewById(R.id.lin_add_search).setVisibility(View.VISIBLE);
            findViewById(R.id.search_bar).setVisibility(View.GONE);
            edtSearch.setText("");
            refreshList();
        }
    };

    private View.OnClickListener onDoSearchClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String input = edtSearch.getText().toString();
            if (input.length() == 0) {
                Toast.makeText(MainActivity.this, "لطفا قسمتی از نام کارمند را بنویسید", Toast.LENGTH_SHORT).show();
                return;
            }

            List<Employee> employees = new DatabaseHelper(MainActivity.this).searchByName(input);
            adapter = new MyListAdapter(MainActivity.this, employees);
            listView.setAdapter(adapter);
            Toast.makeText(MainActivity.this, employees.size() + " کارمند پیدا شد", Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener onCancelbuttonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            edtName.setText("");
            edtPhone.setText("");
            edtAddress.setText("");
            findViewById(R.id.lin_add_search).setVisibility(View.VISIBLE);
            findViewById(R.id.add_edit_employee).setVisibility(View.GONE);
        }
    };

    private View.OnClickListener onNewEmployeeSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (edtName.getText().length() == 0 || edtPhone.getText().length() == 0 || edtAddress.getText().length() == 0) {
                Toast.makeText(MainActivity.this, "لطفا اطلاعات را وارد کنید", Toast.LENGTH_SHORT).show();
                return;
            }
            Employee employee = new Employee();
            employee.setName(edtName.getText().toString());
            employee.setPhone(edtPhone.getText().toString());
            employee.setAddress(edtAddress.getText().toString());
            new DatabaseHelper(MainActivity.this).addNewEmployee(employee);
            refreshList();
            btnCancel.performClick();
        }
    };

    private View.OnClickListener onEditEmployeeSubmit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (edtName.getText().length() == 0 || edtPhone.getText().length() == 0 || edtAddress.getText().length() == 0) {
                Toast.makeText(MainActivity.this, "لطفا اطلاعات را وارد کنید", Toast.LENGTH_SHORT).show();
                return;
            }
            Employee employee = new Employee();
            employee.setId(seletcedEmployeeId);
            employee.setName(edtName.getText().toString());
            employee.setPhone(edtPhone.getText().toString());
            employee.setAddress(edtAddress.getText().toString());
            new DatabaseHelper(MainActivity.this).editEmployee(employee);
            refreshList();
            btnCancel.performClick();
        }
    };
}