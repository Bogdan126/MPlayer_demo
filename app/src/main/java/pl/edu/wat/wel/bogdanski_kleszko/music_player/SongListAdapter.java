package pl.edu.wat.wel.bogdanski_kleszko.music_player;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.io.Console;
import java.util.ArrayList;
import java.util.List;


public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private List<SongInfo> piosenki;


    public SongListAdapter(List<SongInfo> piosenki) {
        this.piosenki = piosenki;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View rowView = inflater.inflate(R.layout.song_info,parent, false);
        SongListAdapter.ViewHolder viewHolder = new SongListAdapter.ViewHolder(rowView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SongListAdapter.ViewHolder holder, int position) {

        holder.title.setText(piosenki.get(position).getTytul());
        holder.author.setText(piosenki.get(position).getAutor());
        holder.info.setText(piosenki.get(position).getInfo());
        //TUTAJ JEST ONCLICK
        holder.title.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Player.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return this.piosenki.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title; //??????????????????????????????
        public TextView author;
        public TextView info;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tytulpiosenki); //?????????????????????????
            author = itemView.findViewById(R.id.autorpiosenki);
            info = itemView.findViewById(R.id.informacje);
        }
    }
}