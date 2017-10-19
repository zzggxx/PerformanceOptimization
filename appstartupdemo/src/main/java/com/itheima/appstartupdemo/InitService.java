package com.itheima.appstartupdemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

/**
 * 将MyApplication中onCreate方法内容耗时的初始化工作移动到该类中
 */

public class InitService extends IntentService {

    // 问题：由于将NoHttp的初始化工作移动到了子线程，当主线程使用NoHttp发现没有初始化完成，报异常了。

    // 方案一：使用boolean值进行初始化工作的标记，如果完成boolean为true，可以在使用该工具的地方每隔一个时间段判断一下。
    // 方案二：当初始化工作完成后，发出一个通知，如果有观察者，则进行后续工作的处理

    public static boolean isInit=false;// 标记是否初始化完成

    public InitService() {
        super("init");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // 耗时操作

        Logger.setTag("NoHttp");
        Logger.setDebug(true);

        NoHttp.initialize(this, new NoHttp.Config()
                .setConnectTimeout(30 * 1000)
                .setReadTimeout(30 * 1000)
        );

        isInit=true;
    }

    /**
     * 启动service
     * @param myApplication
     */
    public static void start(MyApplication myApplication) {
        Intent intent = new Intent(myApplication, InitService.class);
        myApplication.startService(intent);
    }
}
