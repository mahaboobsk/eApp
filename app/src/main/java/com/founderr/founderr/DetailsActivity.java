package com.founderr.founderr;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    ProgressBar progressBar;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Magazine magazine = (Magazine) getIntent().getSerializableExtra("MAGAZINE");

        assert magazine != null;
        Picasso.with(getApplicationContext())
                .load(magazine.getThumbImage())
                .placeholder(R.mipmap.ic_launcher)
                .into((ImageView)findViewById(R.id.side_image));

        ((TextView)findViewById(R.id.description)).setText(" "+magazine.getMagazineName()+"\n =======================\n\n"+magazine.getMagazineContent()+" \n"+magazine.getPublishedDate());

        findViewById(R.id.subscribe_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this, SubscriptionActivity.class));
            }
        });

        progressBar = findViewById(R.id.progress);
        //final VideoView videoView;
        videoView = findViewById(R.id.video_view);
        progressBar.setVisibility(View.VISIBLE);


        try {
            MediaController mediacontroller = new MediaController(this);
            mediacontroller.setAnchorView(videoView);
            videoView.setMediaController(mediacontroller);
            videoView.setVideoPath(magazine.getVideo());
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                DetailsActivity.this.progressBar.setVisibility(View.GONE);
                DetailsActivity.this.videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //finish();
            }
        });
    }
}
