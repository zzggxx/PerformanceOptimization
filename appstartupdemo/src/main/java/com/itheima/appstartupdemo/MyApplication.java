package com.itheima.appstartupdemo;

import android.app.Application;
import android.os.Debug;
import android.os.SystemClock;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

/**
 * Created by itheima.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {

        // 如果没有办法手动操作监控，可以使用如下代码重点关注我们感兴趣的方法
        // 监控的结果会生成文件存储SDCard上
//        Debug.startMethodTracing("AppStartupDemo");// 文件的名称
        super.onCreate();

        InitService.start(this);

        SystemClock.sleep(1000);

//        Debug.stopMethodTracing();
    }
}
