package com.phyous.profiler.contact;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.phyous.profiler.R;
import com.phyous.profiler.contact.task.ListContactTask;

import java.util.LinkedList;
import java.util.List;

public class ContactListFragment extends ListFragment {
    private List contactItemList = new LinkedList<ContactItem>();
    private LayoutInflater mInflater;
    private ContactAdapter mAdapter;
    public boolean taskRun = false;
    long currentID = 0;
    long currentContactID = 0;

    public ContactListFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(!taskRun){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ListContactTask task= new ListContactTask(getActivity(),ft);
            task.execute();
        }
        taskRun = true;
        mAdapter = new ContactAdapter(getActivity(), R.layout.list_item,R.id.key, contactItemList);
        mAdapter .setInflater(mInflater);
        mAdapter.setLayout(R.layout.list_item);
        setListAdapter(mAdapter );
        ListView listView = getListView();
        getListView().invalidate();
    }

    public void setDataList(List list) {
        Activity act = getActivity();
        this.contactItemList = list;
        if (act != null) {
            mAdapter = new ContactAdapter(act, R.layout.list_item, R.id.key, list);
            mAdapter.setInflater(mInflater);
            mAdapter.setLayout(R.layout.list_item);
            setListAdapter(mAdapter);
            getListView().invalidate();
        }
    }


}