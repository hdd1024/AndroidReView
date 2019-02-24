package com.hdd.androidreview.customView.evnent;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.hdd.androidreview.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class HorizonalscrollViewExActivity extends AppCompatActivity {
    private HorizonalScrollViewEx mHVE_horizonalAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizonalscroll_view_ex);
        mHVE_horizonalAct = findViewById(R.id.mHVE_horizonalAct);

        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(
                    R.layout.content_layout,
                    mHVE_horizonalAct, false);
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            creatList(layout);
            mHVE_horizonalAct.addView(layout);
        }

    }

    private void creatList(ViewGroup viewGroup) {
        ListView mLv_horizonalAtc = viewGroup.findViewById(R.id.mLv_horizonalAtc);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            datas.add("time：" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.test_list_item,
                android.R.id.text1, datas);


        mLv_horizonalAtc.setAdapter(adapter);
    }
}
