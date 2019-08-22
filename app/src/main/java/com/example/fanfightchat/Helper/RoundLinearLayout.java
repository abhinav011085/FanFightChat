package com.example.fanfightchat.Helper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.fanfightchat.R;

public class RoundLinearLayout extends LinearLayout {
    Context context;

    public RoundLinearLayout(Context context) {
        super(context);
        this.context = context;
        initBackground();
    }

    public RoundLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initBackground();
    }

    public RoundLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initBackground();
    }

    private void initBackground() {
        setBackground(Utility.generateBackgroundWithShadow(this, R.color.white,
                Utility.dp2px(context, 50), R.color.shadow, Utility.dp2px(context, 10), Gravity.BOTTOM));
    }
}
