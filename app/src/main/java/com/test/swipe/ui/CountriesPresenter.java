package com.test.swipe.ui;

import com.test.swipe.domain.model.Country;
import com.test.swipe.domain.usecase.CountryUseCase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CountriesPresenter implements CountryContract.Presenter {

    private CountryContract.View mView;
    private CountryUseCase mUseCase;
    private CompositeDisposable mDisposable;

    public CountriesPresenter(CountryContract.View mView, CountryUseCase useCase) {
        this.mView = mView;
        this.mUseCase = useCase;
        this.mDisposable = new CompositeDisposable();
        fetchCountries();
    }

    @Override
    public void destroy() {
        mDisposable.clear();
        mDisposable = null;
        mUseCase = null;
    }

    private void fetchCountries() {
        mDisposable.add(mUseCase.get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mView::setRecyclerViewList,
                        throwable -> mView.showError(throwable.getMessage())));
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
