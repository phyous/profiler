package com.phyous.profiler.contact;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.phyous.profiler.R;
import com.phyous.profiler.contact.data.Pair;

import java.util.List;

/**
 * Created by pyoussef on 8/24/13.
 */
class ProfileAdapter extends ArrayAdapter {
    private static String TAG = ProfileAdapter.class.getName();
    private LayoutInflater inflator = null;
    List pairList = null;
    private int layout;

    public class ViewHolder {
        TextView key;
        TextView value;
    }

    public ProfileAdapter(Context context, int resource,
                          int textViewResourceId, List objects) {
        super(context, resource, textViewResourceId, objects);
        this.pairList = objects;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        try {
            if (convertView == null) {
                convertView = this.inflator.inflate(layout, null);
                holder = new ViewHolder();
                holder.key = (TextView) convertView.findViewById(R.id.key);
                holder.value = (TextView) convertView.findViewById(R.id.value);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            Pair pair = (Pair) getItem(position);
            String key = pair.key;
            String value = pair.value;
            holder.key.setText(key);
            holder.value.setText(value);
        } catch (Exception e) {
            Log.e(TAG, e.toString(), e);
        }
        return convertView;
    }

    public void setInflater(LayoutInflater mInflater) {
        this.inflator = mInflater;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public void setDataList( List result) {

    }
}