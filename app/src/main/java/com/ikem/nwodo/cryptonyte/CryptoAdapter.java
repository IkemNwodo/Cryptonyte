package com.ikem.nwodo.cryptonyte;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.EntryXComparator;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.ikem.nwodo.cryptonyte.di.CoinComponent;
import com.ikem.nwodo.cryptonyte.di.DaggerCoinComponent;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoViewHolder> {

    private static final String TAG = "mpAndroidChart";
    private ArrayList<Coin> _coins;
    private ArrayList<History> coinHistory;
    private Context context;
    private Activity activity;
    private Disposable disposable;

    public CryptoAdapter(Activity activity, Context context, ArrayList<Coin> coins) {
        _coins = coins;
        //this.coinHistory = coinHistory;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public CryptoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        return new CryptoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoViewHolder holder, final int i) {
        final Coin coin = _coins.get(i);

        // Set drawable for coin
        //setDrawable(holder.coinAvatar, coin.getColor());

        GlideToVectorYou.justLoadImage(activity, Uri.parse(coin.getIconUrl()), holder.coinAvatar);


        //setupCoinChart(holder.coinGraph);

        holder.coinName.setText(coin.getName());
        holder.coinSymbol.setText(coin.getSymbol());
        holder.coinPrice.setText(coin.getPrice());
        holder.coinChange.setText(String.valueOf(coin.getChange()));
    }

    private void setupCoinChart(LineChart coinGraph) {
        coinGraph.getDescription().setEnabled(false);

        // enable touch gestures
        coinGraph.setTouchEnabled(true);

        coinGraph.setDragDecelerationFrictionCoef(0.9f);

        // enable scaling and dragging
        coinGraph.setDragEnabled(true);
        coinGraph.setScaleEnabled(true);
        coinGraph.setDrawGridBackground(false);
        coinGraph.setHighlightPerDragEnabled(true);


        coinGraph.setBackgroundColor(Color.WHITE);
        coinGraph.setViewPortOffsets(0f, 0f, 0f, 0f);

        Legend l = coinGraph.getLegend();
        l.setEnabled(false);

        XAxis xAxis = coinGraph.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.rgb(255, 192, 56));
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularity(1f); // one hour
        xAxis.setLabelCount(3);
        xAxis.setValueFormatter(new MyXAxisValueFormatter());

        YAxis leftAxis = coinGraph.getAxisLeft();
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setGranularity(500f);
        leftAxis.setLabelCount(3);
        leftAxis.setYOffset(-9f);
        leftAxis.setTextColor(Color.rgb(255, 192, 56));
        leftAxis.setValueFormatter(new MyYAxisValueFormatter());

        YAxis rightAxis = coinGraph.getAxisRight();
        rightAxis.setEnabled(false);

        coinGraph.setData(getData());

    }

    private LineData getData() {
        ArrayList<History> histories = coinHistory;

        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < histories.size(); i++) {
            History history = histories.get(i);
            float minute = Long.parseLong(String.valueOf(history.getTimestamp()));
            int historyPrice = Integer.parseInt(history.getPrice());
            float price = (float) historyPrice;
            entries.add(new Entry(minute, price));
            Log.i(TAG, "X: " + minute + " " + "Y: " + price);
        }

        Collections.sort(entries, new EntryXComparator());

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(entries, "DataSet 1");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setValueTextColor(ColorTemplate.getHoloBlue());
        set1.setLineWidth(1.5f);
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);

        // create a data object with the data sets
        LineData data = new LineData(set1);
        data.setValueTextColor(Color.WHITE);
        data.setValueTextSize(9f);

        return data;
    }

    private void fetchCoinHistory(Integer id) {
        CoinComponent coinComponent = DaggerCoinComponent.create();
        CryptoService service = coinComponent.getCryptoService();
        Single<Data> coin = service.getCoinHistory24h(id);
        coin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Data>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onSuccess(Data data) {
                        coinHistory = data.getCoinHistory();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
        disposable.dispose();
    }

    @Override
    public int getItemCount() {
        return _coins.size();

    }

    /**
     * This method is just to modify Drawable's color
     *
     * @param coinAvatar
     * @param color
     */
    private void setDrawable(ImageView coinAvatar, String color) {
        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.coin_icon_drawable);

        GradientDrawable gradientDrawable = (GradientDrawable) drawable;
        assert gradientDrawable != null;

        if (color.length() == 4 || color == null) {
            gradientDrawable.setStroke(4, Color.parseColor("#000000"));
            gradientDrawable.setColor(Color.parseColor("#000000"));
        } else {
            gradientDrawable.setStroke(4, Color.parseColor(color));
            gradientDrawable.setColor(Color.parseColor(color));
        }


        coinAvatar.setBackground(gradientDrawable);
    }

    class CryptoViewHolder extends RecyclerView.ViewHolder {

        ImageView coinAvatar;
        LineChart coinGraph;

        TextView coinName;
        TextView coinSymbol;
        TextView coinPrice;
        TextView coinChange;

        CryptoViewHolder(@NonNull View itemView) {
            super(itemView);

            coinName = itemView.findViewById(R.id.coin_name);
            coinSymbol = itemView.findViewById(R.id.coin_symbol);
            coinPrice = itemView.findViewById(R.id.coin_price);
            coinChange = itemView.findViewById(R.id.coin_change);
            coinAvatar = itemView.findViewById(R.id.coin_avatar);

            coinGraph = itemView.findViewById(R.id.coin_graph);
        }
    }

}
