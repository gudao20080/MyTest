package com.wk.beautifulgir;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    @InjectView(R.id.btn_load)
    Button btnLoad;
    @InjectView(R.id.iv_image)
    ImageView ivImage;
    List<Pic> pics = new ArrayList<>();
    private String mString;


    @InjectView(R.id.RecyclerView)
    RecyclerView recyclerView;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1)
                recyclerView.setAdapter(new MyRecyclerAdapter(MainActivity.this, pics));

            return false;
        }

    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Picasso.with(this).load("http://img0.bdstatic.com/img/image/shouye/bizhi0525.jpg").into(ivImage);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        HandlerThread handlerThread = new HandlerThread("handThread");
        Looper looper = handlerThread.getLooper();
        Handler handler = new Handler(looper, new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {

                return false;
            }
        });
        Log.d(TAG, "onCreate() called with " + "savedInstanceState = [" + savedInstanceState + "]");
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    @OnClick(R.id.btn_load)
    public void loadPics() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(15000);
        client.setResponseTimeout(15000);
        RequestParams params = new RequestParams("num", "20");
        client.addHeader("apikey", " 578bcd5a718e185b2ff73ebc4fce6e99");
        final long start = System.currentTimeMillis();
        client.get("http://apis.baidu.com/txapi/mvtp/meinv", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                long end = System.currentTimeMillis();
                Toast.makeText(MainActivity.this, "" + (end - start), Toast.LENGTH_SHORT).show();
                Gson gson = new Gson();
                for (int i = 0; i < response.length(); i++) {
                    boolean has = response.has(String.valueOf(i));
                    if (has) {
                        String value = null;
                        try {
                            value = response.getString(String.valueOf(i));
                            Pic pic = gson.fromJson(value, Pic.class);
                            pics.add(pic);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                handler.sendEmptyMessage(1);

            }
        });
    }


}
