package com.example.sic.Activity.Setting_Help;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.sic.DefaultActivity;
import com.example.sic.R;

public class Activity_Help_Detail extends DefaultActivity {

    FrameLayout btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_detail);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> {
            Intent i = new Intent(Activity_Help_Detail.this, Activity_Setting_Help.class);
            startActivity(i);
        });
    }
}