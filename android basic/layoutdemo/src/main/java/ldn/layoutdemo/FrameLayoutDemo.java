package ldn.layoutdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class FrameLayoutDemo extends AppCompatActivity {

    int[] names = new int[]{R.id.view1, R.id.view2, R.id.view3, R.id.view3, R.id.view4, R.id.view5, R.id.view6};
    TextView[] textViews = new TextView[names.length];

    class MyHandler extends Handler{
        private WeakReference<FrameLayoutDemo> activity;
        public MyHandler(WeakReference<FrameLayoutDemo> activity){
            this.activity = activity;
        }
        private int currentColor = 0;
        int[] colors = new int[]{R.color.color1, R.color.color2, R.color.color3, R.color.color4, R.color.color5, R.color.color6};

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x123){
                for(int i=0; i<activity.get().names.length; i++){
                    activity.get().textViews[i].setBackgroundResource(colors[(i+currentColor)%colors.length]);
                }
                currentColor++;
            }
            super.handleMessage(msg);
        }
    }

    private Handler handler = new MyHandler(new WeakReference(this));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout_demo);
        for(int i=0; i<names.length; i++){
            textViews[i] = findViewById(names[i]);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        }, 0, 200);
    }
}
