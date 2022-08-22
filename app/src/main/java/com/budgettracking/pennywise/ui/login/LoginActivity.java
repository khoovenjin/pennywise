package com.budgettracking.pennywise.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.budgettracking.pennywise.PennywiseApp;
import com.budgettracking.pennywise.R;
import com.budgettracking.pennywise.ui.BaseActivity;
import com.budgettracking.pennywise.ui.MainActivity;


//Unused Component as Firebase Requires Newer SDK. Tested by AK
public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boolean logged = PreferenceManager.getDefaultSharedPreferences(PennywiseApp.getContext()).getBoolean(getString(R.string.already_accepted_user_key), false);
        if (logged) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            finish();
        }
    }

}
