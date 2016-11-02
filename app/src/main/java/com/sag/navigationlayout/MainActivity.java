package com.sag.navigationlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sag.navigationlibrary.NavigationLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationLayout layout = (NavigationLayout) findViewById(R.id.navigation);
        layout.setNavigationListener(new NavigationLayout.NavigationListener() {
            @Override
            public void onButtonClick(int position, View v) {
                Log.d("tiaoshi", "sss" + position);
            }
        });
    }

}
