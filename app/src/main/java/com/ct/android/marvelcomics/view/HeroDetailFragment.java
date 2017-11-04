package com.ct.android.marvelcomics.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ct.android.marvelcomics.R;
import com.ct.android.marvelcomics.model.Comic;
import com.ct.android.marvelcomics.model.HeroEvent;
import com.ct.android.marvelcomics.model.HeroItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class HeroDetailFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView emptyListPlaceholder;
    private HeroItemsAdapter adapter;

    public HeroDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Activity activity = this.getActivity();
        recyclerView = (RecyclerView) activity.findViewById(R.id.resources_list);
        emptyListPlaceholder = (TextView) activity.findViewById(R.id.resources_list_empty);

        adapter = new HeroItemsAdapter(activity, new ArrayList<HeroItemViewModel>());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.resource_list, container, false);
    }

    public void showComics(List<Comic> comics) {
        if ( adapter != null ) {
            adapter.changeList(comicsToHeroItemViewModel(comics));
            adapter.notifyDataSetChanged();
            int emptyPlaceholderVisibility = comics.size() > 0 ? View.GONE : View.VISIBLE;
            emptyListPlaceholder.setVisibility(emptyPlaceholderVisibility);
        }
    }

    public void showHeroEvents(List<HeroEvent> events) {
        if ( adapter != null ) {
            adapter.changeList(eventsToHeroItemViewModel(events));
            adapter.notifyDataSetChanged();
            int emptyPlaceholderVisibility = events.size() > 0 ? View.GONE : View.VISIBLE;
            emptyListPlaceholder.setVisibility(emptyPlaceholderVisibility);
        }
    }

    private List<HeroItemViewModel> comicsToHeroItemViewModel(List<Comic> comics) {
        List<HeroItemViewModel> items = new ArrayList<>();
        for (Comic comic :
                comics) {
            HeroItemViewModel item = new HeroItemViewModel();
            item.setId( comic.getId() );
            item.setTitle( comic.getTitle() );
            item.setDescription( comic.getDescription() );
            item.setThumbnail( comic.getThumbnail() );
            items.add(item);
        }

        return items;
    }

    private List<HeroItemViewModel> eventsToHeroItemViewModel(List<HeroEvent> events) {
        List<HeroItemViewModel> items = new ArrayList<>();
        for (HeroEvent event :
                events) {
            HeroItemViewModel item = new HeroItemViewModel();
            item.setId( event.getId() );
            item.setTitle( event.getTitle() );
            item.setDescription( event.getDescription() );
            item.setThumbnail( event.getThumbnail() );
            items.add(item);
        }

        return items;
    }
}
