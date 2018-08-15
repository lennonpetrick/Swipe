package com.test.swipe;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountriesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        mRecyclerView = findViewById(R.id.rvCountries);
        setUpRecycleView();
        setUpActionBar();

        List<Country> countries = getMockedCountries();
        mRecyclerView.setAdapter(new CountriesAdapter(countries));
    }

    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setUpRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }

    private List<Country> getMockedCountries() {
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            final int identifier = (i + 1);

            Country country = new Country();
            country.setName("Country " + identifier);
            country.setMainCurrency("Currency " + identifier);
            country.setMainLanguage("Language " + identifier);
            countries.add(country);
        }

        return countries;
    }
}

