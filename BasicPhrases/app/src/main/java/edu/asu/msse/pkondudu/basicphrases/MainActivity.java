package edu.asu.msse.pkondudu.basicphrases;

import android.media.MediaPlayer;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void buttonTapped(View view) {

        int id = view.getId();
        String myId = "";

        myId = view.getResources().getResourceEntryName(id);

        int resourceId = getResources().getIdentifier(myId, "raw", "edu.asu.msse.pkondudu.basicphrases");

        MediaPlayer mediaPlayer = MediaPlayer.create(this, resourceId);
        mediaPlayer.start();

        Log.i("Button Tapped", myId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
