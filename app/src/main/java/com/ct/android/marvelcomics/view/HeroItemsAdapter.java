package com.ct.android.marvelcomics.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ct.android.marvelcomics.R;
import com.ct.android.marvelcomics.model.HeroItemViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeroItemsAdapter extends RecyclerView.Adapter<HeroItemsAdapter.ViewHolder> {
    private Context context;
    private List<HeroItemViewModel> items;

    public HeroItemsAdapter(Context context, List<HeroItemViewModel> items) {
        this.context = context;
        this.items = items;
    }

    public void changeList(List<HeroItemViewModel> items) {
        this.items = items;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView title;
        TextView description;

        ViewHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }

    @Override
    public HeroItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View heroItemView = LayoutInflater.from(context)
                .inflate(R.layout.resource_list_content, parent, false);
        return new HeroItemsAdapter.ViewHolder(heroItemView);
    }

    @Override
    public void onBindViewHolder(HeroItemsAdapter.ViewHolder holder, int position) {
        final HeroItemViewModel item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.description.setText(item.getDescription());

        String thumbnailUrl = item.getThumbnail().getPath() + "." + item.getThumbnail().getExtension();
        Picasso.get()
                .load(thumbnailUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(android.R.drawable.ic_dialog_alert)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
