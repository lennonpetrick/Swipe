package com.test.swipe;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvName;
        private TextView mTvCurrency;
        private TextView mTvLanguage;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.tvName);
            mTvCurrency = itemView.findViewById(R.id.tvCurrency);
            mTvLanguage = itemView.findViewById(R.id.tvLanguage);
        }
    }
}
