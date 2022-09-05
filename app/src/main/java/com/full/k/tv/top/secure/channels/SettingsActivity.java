package com.full.k.tv.top.secure.channels;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.fulldive.startapppopups.PopupManager;
import com.fulldive.startapppopups.donation.DonationManager;

@SuppressWarnings("ALL")
public class SettingsActivity extends PreferenceActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar=findViewById(R.id.toolbar);
        setActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        getActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        addPreferencesFromResource(R.xml.preference);
        findPreference("action_about").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                LayoutInflater inflater = SettingsActivity.this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.bachors_apps, null);
                new AlertDialog.Builder(SettingsActivity.this)
                        .setCancelable(false)
                        .setNegativeButton("Ok", null)
                        .setView(dialogView)
                        .show();
                return true;
            }
        });
        findPreference("action_rate").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Uri uri = Uri.parse("https://github.com/FDweb0/Full-4k-TV");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            }
        });
        findPreference("action_share").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "https://github.com/FDweb0/Full-4k-TV");
                startActivity(Intent.createChooser(shareIntent, "Share link using"));
                return true;
            }
        });
        findPreference("support_us").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                DonationManager.INSTANCE.purchaseFromSettings(
                        SettingsActivity.this,
                        () -> {
                            return null;
                        },
                        () -> {
                            new PopupManager().showDonationSuccess(SettingsActivity.this);
                            return null;
                        }
                );
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
       finish();
    }
}