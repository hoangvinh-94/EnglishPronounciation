package com.example.healer.englishpronounciation;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.healer.englishpronounciation.adapter.HomeAdapter;
import com.example.healer.englishpronounciation.adapter.MyDataBaseAdapter;
import com.example.healer.englishpronounciation.fragment.ChooseTypeFragment;
import com.example.healer.englishpronounciation.model.Topic;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    HomeAdapter hAdapter = null;
    ArrayList<Topic> topics = null;
    MyDataBaseAdapter db = null;
    SQLiteDatabase myDataBase = null;
    public  static boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        topics = new ArrayList<Topic>();
        db = new MyDataBaseAdapter(this);
        db.open();
        myDataBase = db.getMyDatabase();
        loadData();
        hAdapter = new HomeAdapter(this, R.layout.custom_home, topics);
        ListView ls = (ListView)findViewById(R.id.lstHome);
        final RelativeLayout welcome = (RelativeLayout)findViewById(R.id.layout_welcome);
        if(!flag){
            CountDownTimer timer = new CountDownTimer(1000*6,1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                }
                @Override
                public void onFinish() {
                    getSupportActionBar().show();
                    welcome.setVisibility(View.INVISIBLE);
                }
            }.start();
        }
        else{
            getSupportActionBar().show();
            welcome.setVisibility(View.INVISIBLE);
        }


        ls.setAdapter(hAdapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                ChooseTypeFragment tf = new ChooseTypeFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("id", topics.get(arg2).getId());
                tf.setArguments(bundle);
                tf.show(getFragmentManager(), "");
            }
        });
        // Set up the action bar.
    }
    public void loadData(){
        Cursor c = myDataBase.rawQuery("SELECT * FROM TOPIC", null);
        c.moveToFirst();
        String data = "";
        while(c.isAfterLast() == false){
            topics.add(new Topic(c.getInt(0),c.getString(1),c.getString(2)));
            c.moveToNext();
        }
        c.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }
}
