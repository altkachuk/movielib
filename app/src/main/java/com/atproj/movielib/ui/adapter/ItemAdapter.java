package com.atproj.movielib.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.atproj.movielib.ui.viewholder.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter<Item, T extends ItemViewHolder<Item>> extends RecyclerView.Adapter<T> {

    private List<Item> items;

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        Item item = items.get(position);
        if (item != null) {
            holder.setItem(item);
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addRangeItems(List<Item> rangeItems) {
        if (items == null) {
            items = new ArrayList<>();
        }

        if (rangeItems != null) {
            int pos = items.size();
            items.addAll(rangeItems);
            notifyItemRangeInserted(pos, items.size());
        }
    }

    public void clearItems() {
        if (items != null && items.size() > 0) {
            int oldSize = items.size();
            items.clear();
            notifyItemRangeRemoved(0, oldSize);
        }
    }
}
