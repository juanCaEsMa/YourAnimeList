package org.insbaixcamp.animelist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.insbaixcamp.animelist.R;
import org.insbaixcamp.animelist.dataclass.DataAnime;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.ViewHolder> {

    Context context;
    ArrayList<DataAnime> animeList;

    public AnimeListAdapter(ArrayList<DataAnime> animeList, Context context){
        this.animeList = animeList;
        this.context = context;
    }
    @Override
    public AnimeListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_anime_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnimeListAdapter.ViewHolder holder, int position) {
        holder.animeNameTextView.setText(animeList.get(position).title);
        Picasso.get().load(animeList.get(position).imageUrl).resize(500,700).into(holder.animeImageView);

    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView animeNameTextView;
        public ImageView animeImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            animeNameTextView = itemView.findViewById(R.id.tv_anime);
            animeImageView = itemView.findViewById(R.id.iv_anime);
        }

        public TextView getAnimeNameTextView() {
            return animeNameTextView;
        }

        public ImageView getAnimeImageView() {
            return animeImageView;
        }
    }

}
