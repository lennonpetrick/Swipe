package com.test.swipe.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.swipe.R;
import com.test.swipe.domain.model.Country;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {

    private List<Country> mCountries;

    public CountriesAdapter(List<Country> countries) {
        this.mCountries = countries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_countries_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Country country = mCountries.get(position);
        holder.mTvName.setText(country.getName());
        holder.mTvCurrency.setText(country.getMainCurrency());
        holder.mTvLanguage.setText(country.getMainLanguage());
    }

    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    public Country getItem(int position) {
        return mCountries.get(position);
    }

    public void remoteItem(int position) {
        mCountries.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvName) TextView mTvName;
        @BindView(R.id.tvCurrency) TextView mTvCurrency;
        @BindView(R.id.tvLanguage) TextView mTvLanguage;
        @BindView(R.id.container_background) View mBackgroundView;
        @BindView(R.id.container_foreground) View mForegroundView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
