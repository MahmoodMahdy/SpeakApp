package com.mahdy.codes.speakapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txt_Speak ;
    Button btn_Speak ;
    TextToSpeech textToSpeech ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result =textToSpeech.setLanguage(Locale.ENGLISH) ;
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(MainActivity.this, "This Language not support", Toast.LENGTH_SHORT).show();
                    }else  {
                        btn_Speak.setEnabled(true);
                        textToSpeech.setPitch(0.6f) ;
                        textToSpeech.setSpeechRate(1.0f) ;
                        speak() ;
                    }
                }
            }
        });

        txt_Speak = (EditText) findViewById(R.id.edt_speak) ;
        btn_Speak = (Button) findViewById(R.id.btn_speak) ;
        btn_Speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });



    }

    private void speak() {
        String text = txt_Speak.getText().toString() ;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null,null) ;

        }else {
            textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null) ;
        }
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null){

            textToSpeech.stop() ;
        }
        super.onDestroy();
    }
}
