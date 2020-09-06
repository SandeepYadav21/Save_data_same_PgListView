package com.sandeep.task.savesqlitedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //initial var
    EditText etText;
    Button btAdd;
    ListView listView;
    DatabaseHelper databaseHelper;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Var
        etText = findViewById(R.id.et_text);
        btAdd = findViewById(R.id.bt_add);
        listView=findViewById(R.id.list_view);

        //Initialize DataBaseHelper
        databaseHelper=new DatabaseHelper(MainActivity.this);

        //Add Database Values to ArrayLIST
        arrayList = databaseHelper.getAllText();

        //Initialize ArrayAdapter
        arrayAdapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,arrayList);

        //Set ArrayAdapter to ListView
        listView.setAdapter(arrayAdapter);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get text from EditText
                String text = etText.getText().toString();
                if (!text.isEmpty()){
                    if (databaseHelper.addText(text)){
                        etText.setText("");//clear EditText
                        //Display Toast Message
                        Toast.makeText(getApplicationContext(),"Data Inserted..."
                        ,Toast.LENGTH_SHORT).show();
                        //clear ArrayList
                        arrayList.clear();
                        arrayList.addAll(databaseHelper.getAllText());
                        //Refresh ListView Data
                        arrayAdapter.notifyDataSetChanged();
                        listView.invalidateViews();
                        listView.refreshDrawableState();
                    }
                }
            }
        });
    }
}
