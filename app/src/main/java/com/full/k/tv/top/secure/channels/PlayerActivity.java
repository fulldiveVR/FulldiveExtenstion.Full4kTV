package com.full.k.tv.top.secure.channels;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;


import com.khizar1556.mkvideoplayer.MKPlayer;

public class PlayerActivity extends AppCompatActivity  {

    MKPlayer mkplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Bundle b = getIntent().getExtras();
        final String name = b.getString("name");
        final String url = b.getString("url");

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mkplayer = new MKPlayer(this);
        mkplayer.play(url);
        mkplayer.setTitle(name);
        mkplayer.setPlayerCallbacks(new MKPlayer.playerCallbacks() {
            @Override
            public void onNextClick() {

            }

            @Override
            public void onPreviousClick() {

            }
        });
        mkplayer.setFullScreenOnly(true);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mkplayer != null) {
            mkplayer.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mkplayer != null) {
            mkplayer.onResume();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mkplayer != null) {
            mkplayer.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mkplayer != null) {
            mkplayer.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (mkplayer != null && mkplayer.onBackPressed()) {
            return;
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    startActivity(new Intent(this, SettingsActivity.class));
                }
        return super.onOptionsItemSelected(item);
        }
    }

}
