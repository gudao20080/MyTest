package com.wk.customview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.wk.customview.view.RoundImageDrawable;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RoundBitmapActivity extends AppCompatActivity {

    @InjectView(R.id.roundView)
    ImageView roundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_bitmap);
        ButterKnife.inject(this);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b);
        RoundImageDrawable drawable = new RoundImageDrawable(bitmap);
        roundView.setBackgroundDrawable(drawable);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_round_bitmap, menu);
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

        return super.onOptionsItemSelected(item);
    }
}
