package com.itheima.appstartupdemo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;


    private OnResponseListener<String> listener = new OnResponseListener<String>() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response<String> response) {
            recyclerView.setAdapter(new MyAdapter(response.get()));
            recyclerView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onFailed(int what, Response<String> response) {
        }

        @Override
        public void onFinish(int what) {
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

    // 使用Handler每个一段时间进行一次判断，如果初始化完成，则获取网络数据
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (InitService.isInit) {
                Request<String> request = NoHttp.createStringRequest("http://10.0.2.2:8080/GooglePlayServer/app?index=0", RequestMethod.GET);
                NoHttp.newRequestQueue().add(10, request, listener);
            } else {
                this.sendEmptyMessageDelayed(100, 10);
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.loading);
        recyclerView = (RecyclerView) findViewById(R.id.app_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.sendEmptyMessage(100);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // 界面加载完成，修改windows背景

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


        private final ArrayList<AppInfo> appList;

        public MyAdapter(String response) {

            Gson gson = new Gson();
            appList = gson.fromJson(response, new TypeToken<ArrayList<AppInfo>>() {
            }.getType());

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.app_item, null);
            view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, getResources().getDisplayMetrics())));
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.setData(position);
        }

        @Override
        public int getItemCount() {
            return appList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            ImageView icon;

            public MyViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.title);
                icon = (ImageView) itemView.findViewById(R.id.item_appinfo_iv_icon);
            }

            public void setData(int position) {
                title.setText(appList.get(position).name);
                String path = "http://10.0.2.2:8080/GooglePlayServer/image?name=" + appList.get(position).iconUrl;
                Picasso.with(MainActivity.this).load(path).into(icon);
            }
        }
    }
}
