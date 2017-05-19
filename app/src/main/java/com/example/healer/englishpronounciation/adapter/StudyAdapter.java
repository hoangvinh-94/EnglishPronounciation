package com.example.healer.englishpronounciation.adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healer.englishpronounciation.R;
import com.example.healer.englishpronounciation.model.Study;
import com.example.healer.englishpronounciation.service.MyService;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Healer on 13-May-17.
 */

public class StudyAdapter extends ArrayAdapter<Study>{
    Activity context;
    ArrayList<Study> list;
    int layoutId;
    TextView word;
    private int pos;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    public StudyAdapter(Activity context, int resource, ArrayList<Study> objects) {
        super(context, resource, objects);
        this.context = context;
        this.list = objects;
        this.layoutId = resource;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutId, null);
        }
        final Study pro = list.get(position);
        word = (TextView)convertView.findViewById(R.id.textWord);
        TextView mean = (TextView)convertView.findViewById(R.id.txtMean);
        TextView pronounce = (TextView)convertView.findViewById(R.id.txtSpell) ;
        ImageButton listen = (ImageButton)convertView.findViewById(R.id.imageSound);
        ImageButton record = (ImageButton)convertView.findViewById(R.id.imageVoice);
        word.setText(pro.getWord());
        mean.setText(pro.getMean());
        pronounce.setText(pro.getPronounce());
        final String pathSound = pro.getSound();
        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MyService.class);
                if(pathSound.equals("")){
                    Toast.makeText(context,"Not found path sound",Toast.LENGTH_SHORT).show();
                }
                else{
                    intent.putExtra("pathSound",pathSound);
                    view.getContext().startService(intent);
                }

            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = position;
                promptSpeechInputStudy();
            }
        });
        return convertView;
    }
    private void promptSpeechInputStudy() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH.US);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now ...");
        try {
            this.context.startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(context.getApplicationContext(),"Sorry! Your device doesn\\'t support speech input",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void onActivityResultStudy(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data  ) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if(result.get(0).trim().toString().toLowerCase().equals(getItem(pos).getWord().trim().toString().toLowerCase())){
                        Intent intent = new Intent(context, MyService.class);
                        intent.putExtra("pathSound","raw/matched");
                        context.startService(intent);
                    }
                    else{
                        Toast.makeText(context.getApplication(),result.get(0).toString(),Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }

        }
    }

}
