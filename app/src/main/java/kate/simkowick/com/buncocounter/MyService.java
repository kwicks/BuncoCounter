package kate.simkowick.com.buncocounter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.support.annotation.IntDef;


/**
 * Created by kate on 10/10/17.
 */

public class MyService extends Service {

    private static final String TAG = "com.example.kate.applebacon";

    //constructor
    public MyService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++){
                    long futureTime = System.currentTimeMillis() +5000;
                    while(System.currentTimeMillis() < futureTime) {
                        synchronized (this) {
                            try {
                                wait(futureTime - System.currentTimeMillis());
                            } catch (Exception e) {

                            }

                        }
                    }
                }
            }
        };

        Thread katesThread = new Thread(r);
        katesThread.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
