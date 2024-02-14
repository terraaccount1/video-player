package com.app.VideoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final String VIDEO_POSITION = "video_position";

    private VideoView video;
    private Uri uri;
    private int videoPosition;


    public void createPortrait() {
        Button play = findViewById(R.id.playButton);
        Button replay = findViewById(R.id.replayButton);

        play.setText(R.string.pause);

        View.OnClickListener playListener = v -> {
            if (video.isPlaying()) {
                play.setText(R.string.play);
                video.pause();
                return;
            }
            video.start();
            play.setText(R.string.pause);
        };

        View.OnClickListener replayListener = v -> {
            video.setVideoURI(uri);
            video.requestFocus();
            video.start();
            play.setText(R.string.pause);
        };

        play.setOnClickListener(playListener);
        replay.setOnClickListener(replayListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int currentOrientation = this.getResources().getConfiguration().orientation;
        if(currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_main);
            createPortrait();
        } else {
            setContentView(R.layout.landscape_main);
        }

        video = findViewById(R.id.video);
        String path = "android.resource://com.app.VideoPlayer/" + R.raw.file;
        uri = Uri.parse(path);

        video.setVideoURI(uri);
        video.requestFocus();

        MediaController mediaController = new MediaController(getApplicationContext());
        video.setMediaController(mediaController);


        if (savedInstanceState != null) {
            videoPosition = savedInstanceState.getInt(VIDEO_POSITION);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        video.seekTo(videoPosition);
        video.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPosition = video.getCurrentPosition();
        video.pause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current video playback position
        outState.putInt(VIDEO_POSITION, videoPosition);
    }
}

