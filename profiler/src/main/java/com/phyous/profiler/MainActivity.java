package com.phyous.profiler;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

import com.phyous.profiler.R;
import com.phyous.profiler.contact.ContactListFragment;

import java.util.List;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        TabListener tabListener = new TabListener(this);

        // Profile tab
        Tab profileTab = actionBar.newTab().setText("Profile").setTabListener(tabListener);
        actionBar.addTab(profileTab);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_USE_LOGO);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Contacts Tab
        Tab contactTab = actionBar.newTab().setText("Contacts").setTabListener(tabListener);
        actionBar.addTab(contactTab);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");

    }

    public void addContactListFragment(FragmentTransaction ft, List result) {
        ContactListFragment contactListFragment = (ContactListFragment) getFragmentManager().
                findFragmentByTag("ContactList");
        if(contactListFragment == null){
            contactListFragment = new ContactListFragment();
        }
        ft.replace(R.id.fragment_container, contactListFragment);
        ft.commit();
        contactListFragment.setDataList(result);
        contactListFragment.taskRun = true;
    }

}
