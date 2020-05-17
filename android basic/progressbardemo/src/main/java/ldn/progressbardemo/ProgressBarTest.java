package ldn.progressbardemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

public class ProgressBarTest extends AppCompatActivity {

    private int[] data = new int[100];
    private int hasData = 0;
    int status = 0;
    private ProgressBar bar;
    private ProgressBar bar2;

    static class MyHandler extends Handler{
        private WeakReference<ProgressBarTest> activity;
        MyHandler(WeakReference<ProgressBarTest> activity){
            this.activity = activity;
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x111){
                activity.get().bar.setProgress(activity.get().status);
                activity.get().bar2.setProgress(activity.get().status);
            }
        }
    }

    MyHandler mHandler = new MyHandler(new WeakReference<ProgressBarTest>(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_test);
        bar = findViewById(R.id.bar);
        bar2 = findViewById(R.id.bar2);
        new Thread(){
            @Override
            public void run() {
                while (status < 100){
                    status = doWork();
                    mHandler.sendEmptyMessage(0x111);
                }
            }
        }.start();
    }

    private int doWork() {
        data[hasData++] = (int)(Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}
