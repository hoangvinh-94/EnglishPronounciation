package com.example.healer.englishpronounciation.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.healer.englishpronounciation.R;
import com.example.healer.englishpronounciation.adapter.MyDataBaseAdapter;
import com.example.healer.englishpronounciation.adapter.StudyAdapter;
import com.example.healer.englishpronounciation.model.Pronounce;
import com.example.healer.englishpronounciation.model.Study;
import com.example.healer.englishpronounciation.service.MyService;

import java.util.ArrayList;


public class BasicFragment extends Fragment {

    private StudyAdapter sdAdapter = null;
    MyDataBaseAdapter db = null;
    SQLiteDatabase myDataBase = null;
    ArrayList<Study> studies = null;
    int id;

    public static BasicFragment newInstance() {

        Bundle args = new Bundle();

        BasicFragment fragment = new BasicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmentR
        View rootView = inflater.inflate(R.layout.fragment_basic,container,false);
       TabHost mTabHost = (TabHost) ((View) container.getParent().getParent()).findViewById(android.R.id.tabhost);
        ListView lv = (ListView) rootView.findViewById(R.id.lvItem);
        id = getArguments().getInt("id");
        db = new MyDataBaseAdapter(this.getActivity());
        db.open();
        myDataBase = db.getMyDatabase();
        studies = new ArrayList<>();
        studies = loadData();
        sdAdapter = new StudyAdapter(this.getActivity(),R.layout.custom_basic,studies);
        lv.setAdapter(sdAdapter);
        return rootView;
    }
    public ArrayList<Study> loadData(){
        ArrayList<Study> temp = new ArrayList<Study>();
        Cursor c = myDataBase.rawQuery("SELECT * FROM BASIC WHERE pronounceId = '"+id+"'", null);
        c.moveToFirst();
        while(c.isAfterLast() == false){
            temp.add(new Study(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5)));
            c.moveToNext();
        }
        c.close();
        return temp;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        sdAdapter.onActivityResultStudy(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ádfa","Basic");
    }
}
