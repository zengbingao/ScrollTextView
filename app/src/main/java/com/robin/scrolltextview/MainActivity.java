package com.robin.scrolltextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String[] values = {
            "android",
            "ios",
            "html",
            "c++",
            "都不会!",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        CustomTextSwitcher switcher = (CustomTextSwitcher) findViewById(R.id.tv_3);
        switcher.setvalues(values);
        switcher.setTime(2000);
    }
    
}
