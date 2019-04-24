package com.ikem.nwodo.cryptonyte.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.ikem.nwodo.cryptonyte.Coin;
import com.ikem.nwodo.cryptonyte.CryptoAdapter;
import com.ikem.nwodo.cryptonyte.CryptoService;
import com.ikem.nwodo.cryptonyte.Data;
import com.ikem.nwodo.cryptonyte.History;
import com.ikem.nwodo.cryptonyte.R;
import com.ikem.nwodo.cryptonyte.Result;
import com.ikem.nwodo.cryptonyte.di.CoinComponent;
import com.ikem.nwodo.cryptonyte.di.DaggerCoinComponent;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    CryptoService cryptoService;
    private TextView mTextMessage;

    static final String TAG = MainActivity.class.getSimpleName();

    Disposable coinDisposable;

    ArrayList<Coin> coins;
    ArrayList<History> coinHistories;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fetch();
        prepareData(coins);
    }

    private void fetch() {
        CoinComponent coinComponent = DaggerCoinComponent.builder().build();
        cryptoService = coinComponent.getCryptoService();
        Single<Result> cryptoCurrencies = cryptoService.getCryptoCurrencies();
        cryptoCurrencies
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Result>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        coinDisposable = d;
                    }

                    @Override
                    public void onSuccess(Result result) {
                        coins = result.getData().getCoins();
                    }

                    @Override
                    public void onError(Throwable e) {
                        }
                });

        Single<Data> cryptoHistories = cryptoService.getCoinHistory24h(1);
        cryptoHistories
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Data data) {
                        coinHistories = data.getCoinHistory();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void prepareData(ArrayList<Coin> coins) {
        CryptoAdapter adapter = new CryptoAdapter(this, getApplicationContext(), coins);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        if (coinDisposable != null){
            coinDisposable.dispose();
        }
        super.onDestroy();
    }

}
