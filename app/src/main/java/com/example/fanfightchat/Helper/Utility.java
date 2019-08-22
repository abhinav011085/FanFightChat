package com.example.fanfightchat.Helper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.core.content.ContextCompat;

import static android.view.View.LAYER_TYPE_SOFTWARE;

public class Utility {


    public static int dp2px(Context context, float dip) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
    }

    public static Drawable generateBackgroundWithShadow(View view, @ColorRes int backgroundColor,
                                                        @DimenRes int cornerRadius,
                                                        @ColorRes int shadowColor,
                                                        @DimenRes int elevation,
                                                        int shadowGravity) {
        int shadowColorValue = ContextCompat.getColor(view.getContext(), shadowColor);
        int backgroundColorValue = ContextCompat.getColor(view.getContext(), backgroundColor);

        float[] outerRadius = {(float) cornerRadius, (float) cornerRadius, (float) cornerRadius,
                (float) cornerRadius, (float) cornerRadius, (float) cornerRadius, (float) cornerRadius,
                (float) cornerRadius};

        Paint backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setShadowLayer((float) cornerRadius, 0, 0, 0);

        Rect shapeDrawablePadding = new Rect();
        shapeDrawablePadding.left = elevation;
        shapeDrawablePadding.right = elevation;

        int DY;
        switch (shadowGravity) {
            case Gravity.CENTER:
                shapeDrawablePadding.top = elevation;
                shapeDrawablePadding.bottom = elevation;
                DY = 0;
                break;
            case Gravity.TOP:
                shapeDrawablePadding.top = elevation * 2;
                shapeDrawablePadding.bottom = elevation;
                DY = -1 * elevation / 3;
                break;
            default:
            case Gravity.BOTTOM:
                shapeDrawablePadding.top = elevation;
                shapeDrawablePadding.bottom = elevation * 2;
                DY = elevation / 3;
                break;
        }

        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setPadding(shapeDrawablePadding);

        shapeDrawable.getPaint().setColor(backgroundColorValue);
        shapeDrawable.getPaint().setShadowLayer((float) cornerRadius / 5, 0, DY, shadowColorValue);

        view.setLayerType(LAYER_TYPE_SOFTWARE, shapeDrawable.getPaint());

        shapeDrawable.setShape(new RoundRectShape(outerRadius, null, null));

        LayerDrawable drawable = new LayerDrawable(new Drawable[]{shapeDrawable});
        drawable.setLayerInset(0, elevation, elevation, elevation, elevation * 2);

        return drawable;

    }

}
