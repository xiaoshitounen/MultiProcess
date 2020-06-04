package swu.xl.messenger;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MessengerService extends Service {
    public static final String TAG = MessengerService.class.getSimpleName();

    //创建Messenger
    Messenger messenger = new Messenger(new CommunicationHandler());

    //创建Handler
    @SuppressLint("HandlerLeak")
    class CommunicationHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //处理消息
            switch (msg.what){
                case 0:
                    Log.d(TAG,msg.arg1+"---");
                    Toast.makeText(MessengerService.this, "数据："+msg.arg1, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + msg.what);
            }
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
