package com.example.think.logapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public MyHelper myDatebaseHelper;
    List<DataStore> logList ;
    private String path="b.mp3";
    private Intent intent;
    private myConn conn;
    MusicService.MyBinder binder;
    Button btn_play;
    Button btn_pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btn_play=(Button)findViewById(R.id.button_play);
        btn_pause=(Button)findViewById(R.id.button_pause);

        btn_play.setOnClickListener(this);
        btn_pause.setOnClickListener(this);
        conn=new myConn();
        intent=new Intent(MainActivity.this,MusicService.class);
        bindService(intent,conn,BIND_AUTO_CREATE);

        myDatebaseHelper=new MyHelper(this);
        logList=new LinkedList<DataStore>();
        SQLiteDatabase db = myDatebaseHelper.getWritableDatabase();
        //Cursor cursor = db.rawQuery("select * from student",null);
        Cursor cursor = db.query("LogInformation", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String logName = cursor.getString(cursor.getColumnIndex("logName"));
            String logContext = cursor.getString(cursor.getColumnIndex("logContext"));
            DataStore logInfo = new DataStore(logName,logContext);
            logList.add(logInfo);
        }
        cursor.close();
        db.close();
        //拿到listveiw对象
        ListView lv = (ListView) findViewById(R.id.listView);
        //设置适配器
        lv.setAdapter(new MyAapter());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        //ListView点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataStore info=logList.get(i);
                Bundle bundle=new Bundle();
                bundle.putString("logName",info.getLogName());
                bundle.putString("logContext",info.getLogContext());
                Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        File SDpath= Environment.getExternalStorageDirectory();
        File file=new File(SDpath,path);
        String allPath=file.getAbsolutePath();
        switch (view.getId()) {
            case R.id.button_play:
                if(file.exists()&&file.length()>0) {
                    binder.play(allPath);
                } else {
                    Toast.makeText(this, "找不到音乐文件", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_pause:
                binder.pause();
                break;
        }
    }

    protected void onDistroy() {
        unbindService(conn);
        super.onDestroy();
    }

    private class myConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder=(MusicService.MyBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addLog) {
            Intent intent=new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    //适配器类
    class MyAapter extends BaseAdapter {

        //获取集合中有多少条元素,由系统调用
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return logList.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }
        //由系统调用，返回一个view对象作为listview的条目

        // position：本次getView方法调用所返回的view对象在listView中处于第几个条目，position的值就为多少

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(MainActivity.this);
            tv.setTextSize(18);
            //获取集合中的元素
            DataStore logInfo = logList.get(position);
            tv.setText(logInfo.toString());
            return tv;
        }
    }
}
