package com.pop.sean.androidtown.view.activity;

import android.os.Bundle;
import android.view.Menu;

import com.pop.sean.androidtown.R;

public class EditorsChoiceActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editors_choice);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

}
