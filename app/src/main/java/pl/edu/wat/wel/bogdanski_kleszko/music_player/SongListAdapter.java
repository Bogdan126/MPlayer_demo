package pl.edu.wat.wel.bogdanski_kleszko.music_player;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;


public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private List<SongList> piosenki;
    private Context context;

    public SongListAdapter(Context context, List<SongList> piosenki) {

        this.context = context;
        this.piosenki = piosenki;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(context)
                .inflate(R.layout.song_list_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //TODO Jak juz mamy listę piosenek (patrz wyżej), tu ustawiamy metodą setText() nazwę i autora

        holder.
        holder.
    }

    @Override
    public int getItemCount() {
        return this.piosenki.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView ; //z holdera wyciągamy tu te same zmienne, których używamy przy piosenkach (nazwa, autor)
        private TextView ;

        public ViewHolder(@NonNull View view) {
            super(view);

        }
    }
}