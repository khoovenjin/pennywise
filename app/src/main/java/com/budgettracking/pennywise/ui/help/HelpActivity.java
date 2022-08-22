package com.budgettracking.pennywise.ui.help;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.budgettracking.pennywise.R;
import com.budgettracking.pennywise.ui.BaseActivity;

public class HelpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(toolbar);
    }

}
