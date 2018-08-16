package com.test.swipe.ui.adapter;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.test.swipe.ui.adapter.CountriesAdapter.ViewHolder;

public class CustomItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    public CustomItemTouchHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView,
                          RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        getDefaultUIUtil()
                .clearView(((ViewHolder) viewHolder).mForegroundView);
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            getDefaultUIUtil()
                    .onSelected(((ViewHolder) viewHolder).mForegroundView);
        }
    }

    @Override
    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        getDefaultUIUtil()
                .onDrawOver(canvas, recyclerView, ((ViewHolder) viewHolder).mForegroundView,
                        dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {

        getDefaultUIUtil()
                .onDraw(c, recyclerView, ((ViewHolder) viewHolder).mForegroundView,
                        dX, dY, actionState, isCurrentlyActive);
        Log.d("Swipe", "dX = " + dX);
    }

    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return .75f;
    }

    @Override
    public float getSwipeVelocityThreshold(float defaultValue) {
        return defaultValue;
    }

    private boolean param = true;

    @Override
    public boolean isItemViewSwipeEnabled() {
        return param;
    }



    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
