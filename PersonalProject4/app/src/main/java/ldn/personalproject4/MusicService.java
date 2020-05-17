package ldn.personalproject4;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;

public class MusicService extends Service {

    public final IBinder binder = new MyBinder();
    private MediaPlayer mp =  new MediaPlayer();
    private int isFinish = -1;
    private final int PLAY_CODE = 1, STOP_CODE = 2, SEEK_CODE = 3, NEWMUSIC_CODE = 4, CURRENTDURATION_CODE = 5, TOTALDURATION = 6;
//    public MusicService() {
//    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        try {
            mp.setDataSource(Environment.getExternalStorageDirectory() + "/data/山高水长.mp3");
            mp.prepare();
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    isFinish = 1;
                }
            });
        } catch (IOException e) {
            Log.e("prepare error", "getService: " + e.toString());
        }
        return binder;
    }

    public class MyBinder extends Binder{
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            switch (code) {
                //service solve
                case PLAY_CODE:
                    play_pause();
                    break;
                case STOP_CODE:
                    stop();
                    break;
                case SEEK_CODE:
                    mp.seekTo(data.readInt());
                    break;
                case NEWMUSIC_CODE:
                    newMusic(Uri.parse(data.readString()));
                    reply.writeInt(mp.getDuration());
                case TOTALDURATION:
                    reply.writeInt(mp.getDuration());
                    break;
                case CURRENTDURATION_CODE:
                    reply.writeInt(mp.getCurrentPosition());
                    reply.writeInt(isFinish);
                    break;
            }
            return super.onTransact(code, data, reply, flags);
        }
    }

    public void play_pause() {
        if (mp.isPlaying()) {
            mp.pause();
        } else {
            mp.start();
            isFinish = -1;
        }
    }
    public void stop() {
        if (mp != null) {
            mp.stop();
            try {
                mp.prepare();
                mp.seekTo(0);
            } catch (Exception e) {
                Log.d("stop", "stop: " + e.toString());
            }
        }
    }
    public void newMusic(Uri uri){
        try{
            mp.reset();
            mp.setDataSource(this, uri);
            mp.prepare();
        }
        catch (Exception e){
            Log.d("New Music", "new music: " + e.toString());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mp!= null){
            mp.reset();//将MediaPlayer恢复到为初始化的状态
            mp.release();//释放资源
        }
    }
}
