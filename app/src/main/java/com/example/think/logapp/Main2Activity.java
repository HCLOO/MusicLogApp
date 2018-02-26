package com.example.think.logapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    EditText et1;
    EditText et2;
    Button btn1;
    Button btn2;
    public MyHelper myDatebaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et1=(EditText)findViewById(R.id.title);
        et2=(EditText)findViewById(R.id.context);
        btn1=(Button)findViewById(R.id.submit);
        btn2=(Button)findViewById(R.id.queryAll);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit:
                String logName=et1.getText().toString();
                String logContext=et2.getText().toString();

                myDatebaseHelper=new MyHelper(this);
                SQLiteDatabase db = myDatebaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("logName", logName);
                values.put("logContext",logContext);
                db.insert("LogInformation", null, values);
                Toast.makeText(this,"日志添加成功",Toast.LENGTH_SHORT).show();
                db.close();
                Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.queryAll:
                Intent intent1=new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
