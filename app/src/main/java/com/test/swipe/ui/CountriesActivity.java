package com.test.swipe.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.test.swipe.R;
import com.test.swipe.data.datasource.CloudCountryDataSource;
import com.test.swipe.data.repository.CountryRepositoryImpl;
import com.test.swipe.domain.model.Country;
import com.test.swipe.domain.usecase.CountryUseCaseImpl;
import com.test.swipe.ui.adapter.CountriesAdapter;
import com.test.swipe.ui.adapter.CustomItemTouchHelper;
import com.test.swipe.utils.MessageUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesActivity extends AppCompatActivity implements CountryContract.View {

    @BindView(R.id.rvCountries) RecyclerView mRecyclerView;

    private CountryContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);
        ButterKnife.bind(this);
        setUpRecycleView();
        setUpActionBar();

        mPresenter = new CountriesPresenter(this,
                new CountryUseCaseImpl(
                        new CountryRepositoryImpl(
                                new CloudCountryDataSource())));
    }

    @Override
    protected void onDestroy() {
        mPresenter.destroy();
        mPresenter = null;
        super.onDestroy();
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

        new ItemTouchHelper(new CustomItemTouchHelper(0, ItemTouchHelper.LEFT))
                .attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void setRecyclerViewList(@NonNull List<Country> countries) {
        mRecyclerView.setAdapter(new CountriesAdapter(countries));
    }

    @Override
    public void showError(String error) {
        MessageUtils.showError(this, error);
    }

}

