package com.budgettracking.pennywise.ui.reminders;

import android.os.Bundle;

import com.budgettracking.pennywise.R;
import com.budgettracking.pennywise.interfaces.IUserActionsMode;
import com.budgettracking.pennywise.ui.BaseActivity;

public class NewReminderActivity extends BaseActivity {


    //Creates the Reminder Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        @IUserActionsMode int mode = getIntent().getIntExtra(IUserActionsMode.MODE_TAG, IUserActionsMode.MODE_CREATE);
        String reminderId = getIntent().getStringExtra(NewReminderFragment.REMINDER_ID_KEY);
        replaceFragment(NewReminderFragment.newInstance(mode, reminderId), false);
    }

}
