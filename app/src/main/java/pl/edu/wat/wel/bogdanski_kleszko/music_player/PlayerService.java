package pl.edu.wat.wel.bogdanski_kleszko.music_player;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;


import java.io.IOException;
import java.util.ArrayList;

public class PlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener
{

    private MediaPlayer musicPlayer;
    private ArrayList<String> filePathList;
    private int songId;
    private final IBinder playerBind = new PlayerBinder();

    @Override
    public void onCreate()
    {

        super.onCreate();
        songId = 0;

        musicPlayer = new MediaPlayer();
        prepareMusicPlayer();
    }


    public void prepareMusicPlayer()
    {
        //musicPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        musicPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        musicPlayer.setOnPreparedListener(this);
        musicPlayer.setOnCompletionListener(this);
        musicPlayer.setOnErrorListener(this);


    }

    public void setFilePath(ArrayList<String> FilePathList)
    {
        filePathList = FilePathList;
    }

    @Override
    public void onPrepared(MediaPlayer  musicPlayer)
    {
        musicPlayer.start();
    }

    public void setSongId(int songIndex)
    {
        songId = songIndex;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra)
    {
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {

    }

    public class PlayerBinder extends Binder
    {
        PlayerService getService()
        {
            return PlayerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
            return playerBind;
    }

    @Override
    public boolean onUnbind(Intent intent)
    {
        musicPlayer.stop();
        musicPlayer.release();
        return super.onUnbind(intent);
    }


    public void playSong()
    {
        musicPlayer.reset();
        try
        {
            musicPlayer.setDataSource(filePathList.get(songId));
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        musicPlayer.prepareAsync();
    }



}
