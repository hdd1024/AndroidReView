package com.hdd.androidreview.memory;

import android.content.Context;

public class MemorySingle {
    private static Context context;
    private MemorySingle() {
    }

    public static MemorySingle getInstance(Context context) {
        MemorySingle.context = context.getApplicationContext();
        return Menory.single;
    }
    static class Menory {
        private static final MemorySingle single = new MemorySingle();
    }
}
