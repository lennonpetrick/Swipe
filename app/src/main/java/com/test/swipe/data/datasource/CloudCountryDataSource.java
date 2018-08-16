package com.test.swipe.data.datasource;

import com.android.volley.toolbox.JsonArrayRequest;
import com.test.swipe.application.AppController;
import com.test.swipe.data.entity.CountryEntity;
import com.test.swipe.data.entity.mapper.JsonMapper;
import com.test.swipe.utils.EndpointUtils;

import java.util.List;

import io.reactivex.Single;

public class CloudCountryDataSource implements CountryDataSource {

    @Override
    public Single<List<CountryEntity>> get() {
        return Single.create(e -> {
            final String url = EndpointUtils.getUrl("all");
            JsonArrayRequest request = new JsonArrayRequest(url,
                    response -> e.onSuccess(JsonMapper
                            .transformCountryEntities(response.toString())),
                    e::tryOnError);

            AppController.getInstance().addToRequestQueue(request);
        });
    }

}
