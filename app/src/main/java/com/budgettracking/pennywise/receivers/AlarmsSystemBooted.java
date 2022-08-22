package com.budgettracking.pennywise.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.budgettracking.pennywise.entities.Reminder;

public class AlarmsSystemBooted extends BroadcastReceiver {

    //Intent for alarm system
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            for (Reminder reminder : Reminder.getReminders()) {
                if (reminder.isState()) {
                    Reminder.updateReminder(reminder, reminder.isState());
                }
            }
        }
    }

}
