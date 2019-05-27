package com.atproj.movielib.ui.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class InfiniteScrollListener extends RecyclerView.OnScrollListener {

    private static final int visible_threshold = 2;

    private LinearLayoutManager layoutManager;
    private OnInfiniteScrollListener listener;

    private boolean loading = true;
    private int previousTotal = 0;

    public InfiniteScrollListener(LinearLayoutManager layoutManager, OnInfiniteScrollListener listener) {
        this.layoutManager = layoutManager;
        this.listener = listener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {
            int visibleItemCount = recyclerView.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItem = getFirstVisibleItem();

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }

            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visible_threshold)) {
                loading = true;
                listener.onLoadNext();
            }
        }
    }

    public void clear() {
        previousTotal = 0;
    }

    public int getFirstVisibleItem() {
        return layoutManager.findFirstVisibleItemPosition();
    }

    public interface OnInfiniteScrollListener {
        void onLoadNext();
    }
}
