package com.test.swipe.ui.adapter;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.test.swipe.ui.adapter.CountriesAdapter.ViewHolder;

public class CustomItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private OnSwipeListener mListener;
    private float mAnchorPoint;

    private RecyclerView.ViewHolder selectedHolder;

    public CustomItemTouchHelper(int dragDirs, int swipeDirs,
                                 float anchorPoint,  OnSwipeListener listener) {
        super(dragDirs, swipeDirs);
        mListener = listener;
        mAnchorPoint = anchorPoint;
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
            if (selectedHolder != null) {
                swipeViewBack(((ViewHolder) selectedHolder).mForegroundView);
            }

            selectedHolder = viewHolder;
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
                            RecyclerView.ViewHolder viewHolder, final float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {

        View foreground = ((ViewHolder) viewHolder).mForegroundView;
        View itemView = ((ViewHolder) viewHolder).itemView;

        float auxDx;
        float oldDx = 0.0f;
        final float width = recyclerView.getWidth();
        final float threshold = width * mAnchorPoint;

        Object tag = foreground.getTag();
        if (tag != null) {
            oldDx = (float) tag;
        }

        // It's bigger than threshold and user releases the touch
        if (Math.abs(oldDx) >= threshold && !isCurrentlyActive) {
            auxDx = Math.max(threshold, Math.abs(dX)) * (dX <= 0 ? -1 : 1);
        } else {
            auxDx = dX;
        }

        oldDx = auxDx;
        foreground.setTag(oldDx);

        getDefaultUIUtil()
                .onDraw(c, recyclerView, foreground,
                        auxDx, dY, actionState, isCurrentlyActive);

        float alpha = (1 - (Math.abs(auxDx * 1.2f) / width));
        itemView.setAlpha(alpha);
    }

    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return .75f;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mListener != null) {
            mListener.onSwipe(viewHolder.getAdapterPosition());
        }
    }

    private void swipeViewBack(final View view) {
        TranslateAnimation animation = new TranslateAnimation(
                0.0f, view.getTranslationX() * -1,
                view.getTranslationY(), view.getTranslationY());
        animation.setDuration(150);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setFillEnabled(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                getDefaultUIUtil().clearView(view);
                view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        view.startAnimation(animation);
    }

    public interface OnSwipeListener {
        void onSwipe(int itemPosition);
    }
}
