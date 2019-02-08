package com.hdd.androidreview.customView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class TestCustomView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CircleView circleView = new CircleView(this);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.addView(circleView);

        setContentView(relativeLayout);
    }

}
