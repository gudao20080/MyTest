package com.administrator.beautifulgir;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;

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
    @InjectView(R.id.btn_load)
    Button btnLoad;
    @InjectView(R.id.iv_image)
    ImageView ivImage;
    private String TAG = getClass().getSimpleName();
    List<Pic> pics = new ArrayList<>();


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
        client.get("http://apis.baidu.com/txapi/mvtp/meinv", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
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
