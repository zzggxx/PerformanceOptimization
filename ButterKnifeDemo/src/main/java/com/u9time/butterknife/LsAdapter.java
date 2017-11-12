package com.u9time.butterknife;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 2017/11/12.
 */

class LsAdapter extends BaseAdapter {
    private Context context;
    private String[] data;

    public LsAdapter(Context context, String[] data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.ls_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.setData(data[position]);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv)
        TextView tv;
        private String data;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setData(String data) {
            this.data = data;

            tv.setText(data);
        }
    }
}
