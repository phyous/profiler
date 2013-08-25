package com.phyous.profiler.contact;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.phyous.profiler.R;
import com.phyous.profiler.contact.task.ListProfileTask;

import java.util.List;

/**
 * Created by pyoussef on 8/24/13.
 */
public class ProfileFragment extends ListFragment {
    private ProfileAdapter mAdapter;
    private List pairList = null;
    private LayoutInflater mInflater;
    public boolean taskRun;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mInflater = (LayoutInflater)
                getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (!taskRun) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ListProfileTask task = new ListProfileTask(getActivity(), ft);
            task.execute();
        }
        taskRun = true;
        mAdapter = new ProfileAdapter(getActivity(), R.layout.list_item, R.id.key, pairList);
        mAdapter.setInflater(mInflater);
        mAdapter.setLayout(R.layout.list_item);
        setListAdapter(mAdapter);
        getListView().invalidate();
    }

    public void setDataList(List result) {
        pairList = result;
        Activity act = getActivity();

        if (act != null) {
            mInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            mAdapter = new ProfileAdapter(getActivity(), R.layout.list_item, R.id.key, result);
            mAdapter.setLayout(R.layout.list_item);
            mAdapter.setInflater(mInflater);
            setListAdapter(mAdapter);
            getListView().invalidate();
        }
    }

}