package com.hdd.androidreview;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hdd.androidreview.Patterm.PattermActivity;
import com.hdd.androidreview.utils.permissionUtil.PermissionUtils;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ListView mLV_Mian;
    private List<String> itemList;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "==onCreate()==");
        mLV_Mian = findViewById(R.id.mLV_Mian);
        itemList = new ArrayList<>();
        itemList.add("生命周期");
        itemList.add("启动模式");
        itemList.add("匹配规则");
        MyListMain myListMain = new MyListMain(this, itemList);
        mLV_Mian.setAdapter(myListMain);

        PermissionUtils.permission(Manifest.permission_group.STORAGE).request();
    }


    class MyListMain extends BaseAdapter {
        private Context context;
        private List<String> itemList;

        public MyListMain(Context context, List<String> itemList) {
            this.context = context;
            this.itemList = itemList;
        }

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyHolder myHolder = null;
            //判断是否有缓存布局
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
                myHolder = new MyHolder(convertView);
                convertView.setTag(position);
            } else {
                //得到缓存布局
                myHolder = (MyHolder) convertView.getTag();
            }
            final String textContent = itemList.get(position);
            myHolder.mTV_test1.setTextColor(Color.BLACK);
            myHolder.mTV_test1.setText(textContent);
            myHolder.mTV_test1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();

                    if ("生命周期".equals(textContent)) {
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setClass(context, CycleActivity.class);
                    } else if ("启动模式".equals(textContent)) {
                        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setClass(context, PattermActivity.class);

                    } else if ("匹配规则".equals(textContent)) {
                        //隐式跳转
                        intent.setAction("12345678");
                        //category非必须指定。如果要指定，一点要和清单文件中填写的一直。
                        //intent.addCategory("com.hdd.123456");
                        intent.setDataAndType(Uri.parse("http://www.sina.com"), "audio/mpeg");
//                        intent.setData(Uri.parse("http://www.baidu.com"));
//                        intent.setType("audio/mpeg");
                        //判断是否匹配成功

                        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
                        PackageManager packageManager=context.getPackageManager();
                        ResolveInfo resolveInfo = packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                        //packageManager的resolveActivity()方式
                        if (resolveInfo != null)
                            context.startActivity(intent);
                        else
                            Toast.makeText(context, "匹配不成功", Toast.LENGTH_SHORT).show();

                        //intent的resolveActivity()方式

//                        if (componentName != null)
//                            context.startActivity(intent);
//                        else
//                            Toast.makeText(context, "匹配不成功", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ComponentName component = intent.getComponent();
                    if (component == null) {
                        Toast.makeText(context, "ComponentName为空", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    context.startActivity(intent);

                }
            });
            return convertView;
        }

        class MyHolder {
            TextView mTV_test1;

            MyHolder(View view) {
                mTV_test1 = view.findViewById(android.R.id.text1);
            }


        }
    }

}
