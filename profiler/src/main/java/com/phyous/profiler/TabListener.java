package com.phyous.profiler;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.util.Log;

import com.phyous.profiler.contact.task.ListContactTask;
import com.phyous.profiler.contact.task.ListProfileTask;

/**
 * Created by pyoussef on 8/24/13.
 */
public class TabListener implements ActionBar.TabListener {
    private final String TAG = TabListener.class.getName();
    String lastTab = null;
    private Activity activity;

    public TabListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        CharSequence tabText = tab.getText();
        Log.i(TAG, tabText.toString());
        if(tabText.equals("Profile")) {
            ListProfileTask task = new ListProfileTask(activity,ft);
            task.execute();
        } else if (tabText.equals("Contacts")) {
            ListContactTask task = new ListContactTask(activity, ft);
            task.execute();
        }
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {

    }
}