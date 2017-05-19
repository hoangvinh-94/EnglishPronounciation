package com.example.healer.englishpronounciation.adapter;

import java.util.ArrayList;

import com.example.healer.englishpronounciation.R;
import com.example.healer.englishpronounciation.model.Topic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeAdapter extends ArrayAdapter<Topic> {

	
	Activity context = null;
	int layoutId;
	ArrayList<Topic> arr = null;
	
	public HomeAdapter(Activity context, int resource, ArrayList<Topic> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layoutId = resource;
		this.arr = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		 if(convertView==null){
			 LayoutInflater inflater = context.getLayoutInflater();
	            convertView = inflater.inflate(layoutId, null);
	        }
		 	Topic pro = arr.get(position);
	        ImageView icon = (ImageView)convertView.findViewById(R.id.icon);
	        TextView title = (TextView)convertView.findViewById(R.id.txtTitle);
	        TextView signature = (TextView)convertView.findViewById(R.id.txtSignature);
	        title.setText(pro.getTitle());
			signature.setText(pro.getSignature());

	        return convertView;
	}
	
	

}
