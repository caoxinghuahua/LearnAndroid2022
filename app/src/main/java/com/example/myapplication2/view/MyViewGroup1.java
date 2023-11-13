package com.example.myapplication2.view;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public class MyViewGroup1 extends ViewGroup {
    public MyViewGroup1(Context context) {
        super(context);
    }

    public MyViewGroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            LayoutParams layoutParams = childView.getLayoutParams();
            int childWMeasureSpec = getChildMeasureSpec(widthMeasureSpec, 0, layoutParams.width);
            int childHMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, layoutParams.height);
            childView.measure(childWMeasureSpec, childHMeasureSpec);
//            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
        }
//        int size=MeasureSpec.getSize(widthMeasureSpec);
//        int mode=MeasureSpec.getMode(widthMeasureSpec);
//        switch (layoutParams.width){
//
//            case LayoutParams.MATCH_PARENT:
//                if(mode==MeasureSpec.AT_MOST||mode==MeasureSpec.EXACTLY){
//                    resolveSize(size,MeasureSpec.EXACTLY);
//                }
//                break;
//            case LayoutParams.WRAP_CONTENT:
//                break;
//            default:
//
//        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        //https://blog.csdn.net/a553181867/article/details/54633151
        Parcelable parcelable=super.onSaveInstanceState();
        BaseSavedState baseSavedState=new BaseSavedState(parcelable);

        return parcelable;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }
}
