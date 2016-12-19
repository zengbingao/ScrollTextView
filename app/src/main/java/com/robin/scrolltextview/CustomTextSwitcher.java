package com.robin.scrolltextview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

/** vertical scroll textview's text
 * Created by robin on 2016/12/19.
 */

class CustomTextSwitcher extends TextSwitcher implements ViewSwitcher.ViewFactory {
    private static final String TAG = "CustomTextSwitcher";
    private Context context;
    private Timer timer;
    private int index = -2;
    private String[] values = {};
    private int textSize=60;

    public CustomTextSwitcher(Context context) {
        super(context);
        init(context);
    }

    public CustomTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        if (timer == null) timer = new Timer();
        this.setFactory(this);
        Log.i(TAG, "init: textSize=="+textSize);
        setInAnim();
        setOutAnim();
    }

    @Override
    public View makeView() {
        TextView tv =new TextView(context);
        tv.setTextSize(textSize);
        tv.setGravity(Gravity.CENTER);
        tv.setTextColor(Color.BLACK);
        return tv;
    }

    public void setvalues(String[] values) {
        this.values = values;
    }

    public void setTime(long time) {
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    updateUI();
                }
            }, 1, time);
        }
    }

    private void updateUI() {
        index+=1;
        if (index > values.length - 1) {
            index = index - values.length;
        }
        this.postDelayed(new Runnable() {
            @Override
            public void run() {
                setText(values[index]);
            }
        },100);
    }

    private void setInAnim() {
        TranslateAnimation taIn = new TranslateAnimation(0, 0, dp2Px(textSize), 0);
        taIn.setDuration(1000);
        this.setInAnimation(taIn);
    }

    private void setOutAnim() {
        TranslateAnimation taOut = new TranslateAnimation(0, 0, 0,-dp2Px(textSize));
        taOut.setDuration(1000);
        this.setOutAnimation(taOut);
    }
    public  int dp2Px(int dp) {
        try {
            final float metric = this.getResources().getDisplayMetrics().density;
            Log.i(TAG, "dp2Px: "+metric);
            return (int) (dp * metric+ 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
            return dp;
        }
    }
}
