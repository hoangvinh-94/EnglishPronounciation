package com.example.healer.englishpronounciation.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.healer.englishpronounciation.R;
import com.example.healer.englishpronounciation.adapter.MyDataBaseAdapter;
import com.example.healer.englishpronounciation.adapter.PracticeAdapter;
import com.example.healer.englishpronounciation.adapter.StudyAdapter;
import com.example.healer.englishpronounciation.model.Study;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;


public class AdvancedFragment extends Fragment  {

    private PracticeAdapter ptAdapter = null;
    MyDataBaseAdapter db = null;
    SQLiteDatabase myDataBase = null;
    ArrayList<Study> studies = null;
    public static TextView tx;
    public static ImageView check;
    int id;

    public static AdvancedFragment newInstance() {
        
        Bundle args = new Bundle();
        
        AdvancedFragment fragment = new AdvancedFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_advanced,container,false);
        ListView ls = (ListView)rootView.findViewById(R.id.lsPractice);
        tx = (TextView)rootView.findViewById(R.id.keyWord) ;
        check = (ImageView)rootView.findViewById(R.id.imgCheck);
        id = getArguments().getInt("id");
        db = new MyDataBaseAdapter(this.getActivity());
        db.open();
        myDataBase = db.getMyDatabase();
        studies = new ArrayList<>();
        studies = loadData();
        ArrayList<Study> A = new ArrayList<Study>();
        Random rand = new Random();
        int n = rand.nextInt(studies.size());
        Vector vector = new Vector();
        vector.add(n);
        A.add(studies.get(n));
        for(int i=0;i<3;){
            n = rand.nextInt(studies.size());
            if(!vector.contains(n)){
                vector.add(n);
                A.add(studies.get(n));
                i++;
            }
        }
        ptAdapter = new PracticeAdapter(this.getActivity(),R.layout.custom_practice,A);
        ls.setAdapter(ptAdapter);
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
        ptAdapter.onActivityResultPractice(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onAttach(this.getActivity());
        Log.d("ádfa","advanced");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
