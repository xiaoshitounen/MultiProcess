package swu.xl.messenger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MessengerService.class.getSimpleName();

    //全局化Messenger
    private Messenger messenger;

    //ServiceConnection
    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);

            Message message = Message.obtain();
            message.what = 0;
            message.arg1 = 10;
            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();

                Toast.makeText(MainActivity.this, "远程异常", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService(
                new Intent(this,MessengerService.class),
                serviceConnection,
                Context.BIND_AUTO_CREATE
        );
    }
}
