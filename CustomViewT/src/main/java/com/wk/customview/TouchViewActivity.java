package com.wk.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class TouchViewActivity extends AppCompatActivity {

    @InjectView(R.id.btn_1)
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_view);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.btn_1)
    public void onClickBtn_1() {
        Toast.makeText(TouchViewActivity.this, "onClick Button_1", Toast.LENGTH_SHORT).show();
    }

}
