package com.ikholopov.yamblz.weather.weathermobilization.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.ikholopov.yamblz.weather.weathermobilization.R;
import com.ikholopov.yamblz.weather.weathermobilization.WeatherApplication;
import com.ikholopov.yamblz.weather.weathermobilization.data.CityAutoComplete;
import com.ikholopov.yamblz.weather.weathermobilization.data.dto.city.CityShortInfo;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.CitySelectComponent;
import com.ikholopov.yamblz.weather.weathermobilization.di.component.DaggerCitySelectComponent;
import com.ikholopov.yamblz.weather.weathermobilization.presenter.CitySelectPresenter;
import com.ikholopov.yamblz.weather.weathermobilization.ui.views.CitySelectView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by turist on 28.07.2017.
 */

public class CitySelectActivity extends AppCompatActivity implements CitySelectView {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.city_edit) EditText cityEdit;
    @BindView(R.id.cities_list) ListView citiesList;

    @Inject CitySelectPresenter presenter;

    private CitySelectComponent selectComponent;
    private ArrayAdapter<CityShortInfo> adapter;

    public CitySelectComponent getViewComponent() {
        if(selectComponent == null) {
            selectComponent = DaggerCitySelectComponent.builder()
                    .applicationComponent(WeatherApplication.get(this).getComponent())
                    .build();
        }
        return selectComponent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewComponent().inject(this);

        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        citiesList.setAdapter(adapter);

        presenter.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unbind();
    }

    @Override
    public Observable<CharSequence> textChanges() {
        return RxTextView.textChanges(cityEdit).skipInitialValue();
    }

    @Override
    public Observable<CityShortInfo> selection() {
        return RxAdapterView.itemClicks(citiesList).map(new Function<Integer, CityShortInfo>() {
            @Override
            public CityShortInfo apply(@NonNull Integer integer) throws Exception {
                return adapter.getItem(integer);
            }
        });
    }

    @Override
    public void onSelected() {
        finish();
    }

    @Override
    public void onError() {
        Snackbar.make(citiesList, R.string.error_has_been, BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void accept(CityAutoComplete cityAutoComplete) throws Exception {
        adapter.clear();
        adapter.addAll(cityAutoComplete.predictions);
    }
}