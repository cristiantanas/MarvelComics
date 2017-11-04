package com.ct.android.marvelcomics.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ct.android.marvelcomics.R;
import com.ct.android.marvelcomics.model.MarvelHero;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MarvelHeroesAdapter extends RecyclerView.Adapter<MarvelHeroesAdapter.ViewHolder> {

    private List<MarvelHero> marvelHeroes;
    private final OnItemClickListener listener;
    private Context context;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView name;
        TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }

    public MarvelHeroesAdapter(Context context, List<MarvelHero> items, OnItemClickListener listener) {
        this.context = context;
        this.marvelHeroes = items;
        this.listener = listener;
    }

    public void changeList(List<MarvelHero> items) {
        this.marvelHeroes = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View heroItemView = LayoutInflater.from(context)
                .inflate(R.layout.hero_list_content, parent, false);
        return new ViewHolder(heroItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MarvelHero item = marvelHeroes.get(position);
        holder.name.setText(item.getName());
        holder.description.setText(item.getDescription());

        String thumbnailUrl = item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension();
        Picasso.with(context)
                .load(thumbnailUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(android.R.drawable.ic_dialog_alert)
                .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return marvelHeroes.size();
    }

    public interface OnItemClickListener {
        void onItemClick(MarvelHero hero);
    }
}
