package pl.edu.wat.wel.bogdanski_kleszko.music_player;

import android.Manifest;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class SongList extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST = 1;

    public List<SongInfo> lista;

    RecyclerView recyclerView = findViewById(R.id.listView); //po zamianie listview na recyclerview w layoucie

    //zmienne do polaczenia z usluga
    private PlayerService playerService;
    private Intent playIntent;
    private boolean playerBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_list_activity);

        if (ContextCompat.checkSelfPermission(SongList.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SongList.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(SongList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);

            } else {
                ActivityCompat.requestPermissions(SongList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
            }
        } else {
            Cool();
        }

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(playIntent == null)
        {
            playIntent = new Intent(this, PlayerService.class);
            bindService(playIntent, playerConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }

    private SongListAdapter sAdapter;

    public void Cool(){


        //?????????????????????????? Nie wiem czy zadziała w ten sposób, jutro zobaczymy, tam na dole filepathlist już nie będzie
        recyclerView = findViewById(R.id.listView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getMusic();

        sAdapter = new SongListAdapter(lista);
        recyclerView.setAdapter(sAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }



    public void getMusic() {
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

        SongInfo songinfo = new SongInfo();

        if(songCursor != null && songCursor.moveToFirst()) {
            int tytul = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int wykonawca = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int filepath = songCursor.getColumnIndex(MediaStore.MediaColumns.DATA); //pobiera dane o file path piosenki


            do {
                String currentTitle = songCursor.getString(tytul);
                String currentArtist = songCursor.getString(wykonawca);
                String currentFilePath = songCursor.getString(filepath);
                songinfo.autor = currentArtist;
                songinfo.tytul = currentTitle;
                songinfo.info = currentFilePath;//zapisuje na liscie dane o filepath

            } while (songCursor.moveToNext());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST: {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(SongList.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();

                        Cool();
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }
        }

    }

    private ServiceConnection playerConnection = new ServiceConnection()
    {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            PlayerService.PlayerBinder binder = (PlayerService.PlayerBinder)service;

            playerService = binder.getService();

            playerService.setFilePath(filePathList);
            playerBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            playerBound = false;
        }
    };
}