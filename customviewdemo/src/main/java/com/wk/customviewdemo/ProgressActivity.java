package com.wk.customviewdemo;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wk.customviewdemo.view.RoundProgress;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ProgressActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    @InjectView(R.id.roundProgress)
    RoundProgress mRoundProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        ButterKnife.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_progress, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

//        Object systemService = getSystemService(ACTIVITY_SERVICE);
//        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        boolean b = am.clearApplicationUserData();
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.showProgress)
    public void showProgress(View view) {

        new CountDownTimer(360 * 10, 10){

            @Override
            public void onTick(long millisUntilFinished) {
                int i = (int) (millisUntilFinished / 10);
                mRoundProgress.setProgress(i);
                Log.d(TAG, "onTick " + i);
                int progress = mRoundProgress.getProgress();
//                int i1 = mRoundProgress.generateViewId();  //自动生成id

//                Log.d(TAG, "getProgress " + progress);
            }

            @Override
            public void onFinish() {

            }
        }.start();


    }
}
