package pl.edu.wat.wel.bogdanski_kleszko.music_player;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


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

        View rowView = inflater.inflate(R.layout.song_list_activity,parent, false);
        SongListAdapter.ViewHolder viewHolder = new SongListAdapter.ViewHolder(rowView);

        return viewHolder;
    }

    public void setSongs(List<SongInfo> piosenki){
        this.piosenki = piosenki;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SongListAdapter.ViewHolder holder, int position) {

        holder.title.setText(piosenki.get(position).tytul);
        holder.author.setText(piosenki.get(position).autor);
        holder.info.setText(piosenki.get(position).info);
    }

    @Override
    public int getItemCount() {
        return this.piosenki.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView author;
        public TextView info;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tytul);
            author = itemView.findViewById(R.id.autor);

        }
    }
}