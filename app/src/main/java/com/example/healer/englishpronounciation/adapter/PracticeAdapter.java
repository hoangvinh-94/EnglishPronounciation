package com.example.healer.englishpronounciation.adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
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
import com.example.healer.englishpronounciation.fragment.AdvancedFragment;
import com.example.healer.englishpronounciation.model.Study;
import com.example.healer.englishpronounciation.service.MyService;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Healer on 15-May-17.
 */

public class PracticeAdapter extends ArrayAdapter<Study>{
    Activity context;
    ArrayList<Study> list;
    int layoutId;
    String rootWord;
    private int pos;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    public PracticeAdapter(Activity context, int resource, ArrayList<Study> objects) {
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
        ImageButton listen = (ImageButton)convertView.findViewById(R.id.img_Listen);
        ImageButton voice = (ImageButton)convertView.findViewById(R.id.img_Voice);
        rootWord = pro.getWord();
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
        voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos = position;
                promptSpeechInputPractice();

            }
        });
        return convertView;
    }
    private void promptSpeechInputPractice() {
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
    public void onActivityResultPractice(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    AdvancedFragment.tx.setText(result.get(0).toString());
                    if(getItem(pos).getWord().trim().toString().toLowerCase().equals(result.get(0).trim().toString().toLowerCase())){
                        AdvancedFragment.check.setImageResource(R.drawable.correct2);
                    }
                    else{
                        AdvancedFragment.check.setImageResource(R.drawable.error2);;
                    }
                }
                break;
            }

        }
    }
}
