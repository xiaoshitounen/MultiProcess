package swu.xl.messenger;

import android.app.Application;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //全局初始化操作

        //多个进程会多次进入该方法，造成资源浪费
        //可以通过判断进程来确定是否初始化
    }
}
