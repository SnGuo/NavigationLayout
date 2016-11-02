package com.sag.navigationlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SAG on 2016/11/1 0001.
 */

public class NavigationLayout extends ViewGroup {

    private float height;
    private int mCurrentButton;

    public NavigationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NavigationLayout);
        height = ta.getDimension(R.styleable.NavigationLayout_navigation_height, 0f);
        mCurrentButton = ta.getInt(R.styleable.NavigationLayout_navigation_def_button, 0);
        ta.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            getY();
            getChildAt(0).layout(0, 0, r, (int) (b - height));
            int total = getChildCount(), left = 0, top = (int) (b - height), width = r / (total - 1);
            for (int i = 1; i < total; i++) {
                View child = getChildAt(i);
                LayoutParams vlp = child.getLayoutParams();
                if (vlp.height >= 0) {
                    child.layout(left + (i - 1) * width, b - vlp.height, left + i * width, b);
                } else {
                    child.layout(left + (i - 1) * width, top, left + i * width, b);
                }
            }
            getChildAt(mCurrentButton + 1).setSelected(true);
        }
    }

    public interface NavigationListener {
        void onButtonClick(int position, View v);
    }

    public void setNavigationListener(final NavigationListener listener) {
        int total = getChildCount();
        for (int i = 1; i < total; i++) {
            final int position = i - 1;
            getChildAt(i).setOnClickListener(v -> {
               if (mCurrentButton != position) {
                   listener.onButtonClick(position, v);
                   getChildAt(position + 1).setSelected(true);
                   getChildAt(mCurrentButton + 1).setSelected(false);
                   mCurrentButton = position;
               }
            });
        }
    }

}
