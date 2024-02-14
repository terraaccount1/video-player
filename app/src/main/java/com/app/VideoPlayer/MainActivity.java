package com.app.VideoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView video = findViewById(R.id.video);
        String path = "android.resource://com.app.VideoPlayer/" + R.raw.file;
        Uri uri = Uri.parse(path);

        video.setVideoURI(uri);
        video.requestFocus();

        MediaController mediaController = new MediaController(getApplicationContext());
        video.setMediaController(mediaController);
    }
}

