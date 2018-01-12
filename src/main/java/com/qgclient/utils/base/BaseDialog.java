package com.qgclient.utils.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.qgclient.utils.R;
import com.qgclient.utils.WindowUtil;

/**
 * Created by Administrator on 2018/1/10.
 */

public class BaseDialog extends Dialog {
    public BaseDialog(Context context) {
        this(context, R.style.DialogStyle_NoBG);
    }

    public BaseDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(View.inflate(getContext(), layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view, null);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        if (params == null) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            int width = WindowUtil.getInstance(getContext()).getmScreenWidth();
            int height = WindowUtil.getInstance(getContext()).getmScreenHeight();
            if (width > height) {
                layoutParams.width = (int) (height * 0.6);
            } else layoutParams.width = (int) (width * 0.6);
            layoutParams.height = layoutParams.width;
            layoutParams.gravity = Gravity.CENTER;
            getWindow().setAttributes(layoutParams);
            params = getWindow().getAttributes();
        }
        super.setContentView(view, params);
    }
}
