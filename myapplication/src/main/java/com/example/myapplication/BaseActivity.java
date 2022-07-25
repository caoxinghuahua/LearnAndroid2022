package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;

public class BaseActivity extends Activity {
    protected Context mContext;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources resources=LoadUtil.getResource(getApplication());
        mContext=new ContextThemeWrapper(getBaseContext(),0);

        Class<? extends Context> cls=mContext.getClass();

        try {
            Field mResourceField=cls.getDeclaredField("mResource");
            mResourceField.setAccessible(true);
            mResourceField.set(mContext,resources);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
