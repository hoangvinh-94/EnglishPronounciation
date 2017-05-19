package com.example.healer.englishpronounciation.adapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.healer.englishpronounciation.R;

import com.example.healer.englishpronounciation.StudyMainActivity;
import com.example.healer.englishpronounciation.model.Pronounce;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class ChooseTypeAdapter extends ArrayAdapter<Pronounce> {

	Activity context ;
	int layoutId;
	ArrayList<Pronounce> arr = null;

	public ChooseTypeAdapter(Activity context, int resource, ArrayList<Pronounce> objects) {
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
		 	Pronounce proType = arr.get(position);
	        ImageView icon = (ImageView)convertView.findViewById(R.id.imageP);
			//icon.setImageResource(proType.getImage());
	        //icon.setImageURI(Uri.parse(proType.getImage()));
	    	AssetManager mngr = this.context.getAssets();
			InputStream is = null;
			try {
				is = mngr.open(proType.getImageSymbol());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Bitmap bmImg = BitmapFactory.decodeStream(is);
			icon.setImageBitmap(bmImg);
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
/*
			icon.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(context,StudyMainActivity.class);
					v.getContext().startActivity(intent);
				}
			});*/
	        return convertView;
	}
	
	

}
