package com.phyous.profiler.contact.task;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.phyous.profiler.MainActivity;
import com.phyous.profiler.contact.ContactItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pyoussef on 8/24/13.
 */
public class ListContactTask extends AsyncTask<Void, Void, List<ContactItem>> {
    private FragmentTransaction ft;
    private Activity activity;

    public ListContactTask(Activity activity, FragmentTransaction ft) {
        this.activity = activity;
        this.ft = ft;
    }

    public ListContactTask() {
    }

    @Override
    protected List doInBackground(Void... params) {
        Cursor c = activity.getContentResolver().query(Data.CONTENT_URI,
                new String[]{Data._ID, Data.DISPLAY_NAME, Phone.NUMBER, Data.CONTACT_ID, Phone.TYPE, Phone.LABEL},
                Data.MIMETYPE + "='" + Phone.CONTENT_ITEM_TYPE + "'",
                null,
                Data.DISPLAY_NAME);

        int count = c.getCount();
        boolean b = c.moveToFirst();
        String[] columnNames = c.getColumnNames();
        int displayNameColIndex = c.getColumnIndex("display_name");
        int idColIndex = c.getColumnIndex("_id");
        int col2Index = c.getColumnIndex(columnNames[2]);
        int col3Index = c.getColumnIndex(columnNames[3]);
        int col4Index = c.getColumnIndex(columnNames[4]);

        List contactItemList = new LinkedList();
        for (int i = 0; i < count; i++) {
            String displayName = c.getString(displayNameColIndex);
            String phoneNumber = c.getString(col2Index);
            int contactId = c.getInt(col3Index);
            String phoneType = c.getString(col4Index);

            long _id = c.getLong(idColIndex);
            ContactItem contactItem = new ContactItem();
            contactItem.mId = _id;
            contactItem.mContactId = contactId;
            contactItem.mDisplayName = displayName;
            contactItem.mPhone = phoneNumber;
            contactItemList.add(contactItem);
            boolean b2 = c.moveToNext();
        }
        c.close();
        return contactItemList;
    }

    protected void onPostExecute(List result) {
        ((MainActivity) activity).addContactListFragment(ft, result);
    }
}