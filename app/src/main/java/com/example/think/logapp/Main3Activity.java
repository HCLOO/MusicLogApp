package com.example.think.logapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main3Activity extends AppCompatActivity {
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btn=(Button)findViewById(R.id.goBack);
        tv=(TextView) findViewById(R.id.logcontext);
        //获取当前日期时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ");
        Date curDate = new Date(System.currentTimeMillis());
        String showLogTime = formatter.format(curDate);

        Bundle bundle=getIntent().getExtras();
        String showLogName=bundle.getString("logName");
        String showLogContext=bundle.getString("logContext");
        tv.setText("标题"+"\n"+showLogName+"\n"+"\n"+"时间"+"\n"+showLogTime+"\n"+"\n"+"内容"+"\n"+showLogContext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main3Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
