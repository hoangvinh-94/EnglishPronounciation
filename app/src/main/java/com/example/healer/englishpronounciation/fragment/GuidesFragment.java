package com.example.healer.englishpronounciation.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healer.englishpronounciation.R;
import com.example.healer.englishpronounciation.adapter.ChooseTypeAdapter;
import com.example.healer.englishpronounciation.adapter.MyDataBaseAdapter;
import com.example.healer.englishpronounciation.model.Pronounce;
import com.example.healer.englishpronounciation.model.Study;
import com.example.healer.englishpronounciation.service.MyService;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class GuidesFragment extends Fragment {

    MyDataBaseAdapter db = null;
    SQLiteDatabase myDataBase = null;
    ArrayList<Pronounce> pronounces = null;
    int id;

    public static GuidesFragment newInstance() {
        
        Bundle args = new Bundle();
        
        GuidesFragment fragment = new GuidesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_guides, container, false);
        id = getArguments().getInt("id");

        db = new MyDataBaseAdapter(this.getActivity());
        db.open();
        myDataBase = db.getMyDatabase();
        pronounces = new ArrayList<Pronounce>();
        pronounces = ChooseTypeFragment.pronounces;

        ImageView imgPronounce = (ImageView)rootView.findViewById(R.id.imageG);
        final ImageButton imgListen = (ImageButton)rootView.findViewById(R.id.imgListen);
        TextView textGuide = (TextView)rootView.findViewById(R.id.txtGuides);

        int k = 0;
        for(int i = 0; i < pronounces.size(); i++) {
            Log.d("pathImage",pronounces.get(i).getImagePronounce());
            if (pronounces.get(i).getId() == id) {

                textGuide.setText(pronounces.get(i).getDescription());

                imgPronounce.setImageBitmap(loadImageAsset(pronounces.get(i).getImagePronounce()));
                k = i;
                break;
            }
        }
        final String pathSound = pronounces.get(k).getSound();
        imgListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyService.class);
                if(pathSound.equals("")){
                    Toast.makeText(getActivity(),"Not found path sound",Toast.LENGTH_SHORT).show();
                }
                else {
                    intent.putExtra("pathSound", pathSound);
                    getActivity().startService(intent);
                }
            }
        });
        return rootView;
    }
    public Bitmap loadImageAsset(String path){
        AssetManager mngr = this.getActivity().getAssets();
        InputStream is = null;
        try {
            is = mngr.open(path);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Bitmap bmImg = BitmapFactory.decodeStream(is);
        try {
            is.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bmImg;
    }
    public Pronounce loadData(){
        Pronounce temp = null;
        Cursor c = myDataBase.rawQuery("SELECT * FROM PRONOUNCE WHERE id = '"+id+"'", null);
        c.moveToFirst();
        String data = "";
        while(c.isAfterLast() == false){
            temp = new Pronounce(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5));
            c.moveToNext();
        }
        c.close();
        return temp;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ádfa","guide");
    }
}
