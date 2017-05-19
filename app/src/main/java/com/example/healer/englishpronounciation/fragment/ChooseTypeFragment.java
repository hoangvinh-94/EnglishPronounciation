package com.example.healer.englishpronounciation.fragment;

import java.util.ArrayList;

import com.example.healer.englishpronounciation.R;
import com.example.healer.englishpronounciation.StudyMainActivity;
import com.example.healer.englishpronounciation.adapter.ChooseTypeAdapter;
import com.example.healer.englishpronounciation.adapter.MyDataBaseAdapter;
import com.example.healer.englishpronounciation.model.Study;
import com.example.healer.englishpronounciation.model.Topic;
import com.example.healer.englishpronounciation.model.Pronounce;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class ChooseTypeFragment extends DialogFragment {
	
	ChooseTypeAdapter ctAdapter = null;
	public static ArrayList<Pronounce> pronounces = null;
	MyDataBaseAdapter db = null;
	SQLiteDatabase myDataBase = null;
	int id;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView =inflater.inflate(R.layout.fragment_choosetype,container,false);
		getDialog().setTitle("Your Choice");
		pronounces = new ArrayList<Pronounce>();
		id = getArguments().getInt("id");
		db = new MyDataBaseAdapter(this.getActivity());
		db.open();
		myDataBase = db.getMyDatabase();
		pronounces = loadData();
		ctAdapter = new ChooseTypeAdapter(this.getActivity(),R.layout.custom_fragment_choosetype, pronounces);
		GridView gv = (GridView) rootView.findViewById(R.id.gridview);
		gv.setAdapter(ctAdapter);
		gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent intent = new Intent(getActivity(), StudyMainActivity.class);
				intent.putExtra("id",pronounces.get(i).getId());
				startActivity(intent);
			}
		});
		return rootView;
		
	}

	public ArrayList<Pronounce>  loadData(){
		ArrayList<Pronounce> temp = new ArrayList<Pronounce>();
		Cursor c = myDataBase.rawQuery("SELECT * FROM PRONOUNCE WHERE topicId = '"+id+"'", null);
		c.moveToFirst();
		String data = "";
		while(c.isAfterLast() == false){
			temp.add(new Pronounce(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5)));
			c.moveToNext();
		}
		c.close();
		return temp;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
}
