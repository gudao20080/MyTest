package com.wk.beautifulgir;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Button;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private String TAG = getClass().getSimpleName();
    @InjectView(R.id.btn_load)
    Button btnLoad;

    List<Pic> pics = new ArrayList<>();
    private String mString;



    @InjectView(R.id.RecyclerView)
    RecyclerView recyclerView;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            recyclerView.setAdapter(new MyRecyclerAdapter(MainActivity.this, pics));

            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Fresco.initialize(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        DisplayMetrics displayMetrics1 = displayMetrics;
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics1);
        int widthPixels = displayMetrics1.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        Log.d(TAG, "widthPixels: " + widthPixels + "  heightPixels:" + heightPixels);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration();
        HandlerThread handlerThread = new HandlerThread("handThread");
        Looper looper = handlerThread.getLooper();
//        Handler handler = new Handler(looper, new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//
//                return false;
//            }
//        });
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

    @OnClick(R.id.btn_activity2)
    public void goActivity2() {
        startActivity(new Intent(this, RoundActivity.class));
    }

    @OnClick(R.id.btn_fresco_demo)
    public void goActivityFrescoDemo() {
        startActivity(new Intent(this, FrescoDemoActivity.class));
    }
    @OnClick(R.id.btn_load)
    public void loadWithOkHttp() {
        String url = "http://apis.baidu.com/txapi/mvtp/meinv?num=20";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().addHeader("apikey", "578bcd5a718e185b2ff73ebc4fce6e99")
            .url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String content = response.body().string();
                InputStream inputStream = response.body().byteStream();

                Gson gson = new Gson();
                pics = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(content);
                    for (int i = 0; i < jsonObject.length(); i++) {
                        if (jsonObject.has(String.valueOf(i))) {

                            JSONObject jsonDetail = jsonObject.getJSONObject(String.valueOf(i));
                            String s = jsonObject.optString(String.valueOf(i), "");
                            Pic pic = gson.fromJson(s, Pic.class);
                            pics.add(pic);
                        }
                    }
                    mHandler.sendEmptyMessage(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void loadWithPost() {

        RequestBody.create(MediaType.parse(""), "");

    }


}
