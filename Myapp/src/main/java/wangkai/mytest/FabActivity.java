package wangkai.mytest;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FabActivity extends AppCompatActivity {
    private View contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        contentLayout = findViewById(R.id.main);
        ButterKnife.inject(this);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("标题1"));
//        tabLayout.addTab(tabLayout.newTab().setText("标题2"));
//        tabLayout.addTab(tabLayout.newTab().setText("标题3"));
//        tabLayout.addTab(tabLayout.newTab().setText("标题4"));

//        CollapsingToolbarLayout
    }


    @OnClick(R.id.fab)
    public void showSnackBar() {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.main), "fab is onClick!", Snackbar.LENGTH_SHORT);
        snackbar.setAction("action", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        View view = snackbar.getView();
        TextView textView1 = (TextView) view.findViewById(R.id.snackbar_text);
        TextView textView2 = (TextView) view.findViewById(R.id.snackbar_action);
        textView1.setTextColor(Color.RED);
        textView2.setTextColor(Color.GREEN);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        textView1.setCompoundDrawables(drawable, null, null, null);
        snackbar.show(); //AA

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fab, menu);
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
