package com.test.swipe.ui;

import android.support.annotation.NonNull;

import com.test.swipe.domain.model.Country;

import java.util.List;

public interface CountryContract {

    interface View {
        void setRecyclerViewList(@NonNull List<Country> countries);
        void showError(String error);
    }

    interface Presenter {
        void destroy();
    }

}
