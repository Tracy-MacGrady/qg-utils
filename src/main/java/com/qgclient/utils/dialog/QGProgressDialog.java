package com.qgclient.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.qgclient.utils.R;
import com.qgclient.utils.WindowUtil;
import com.qgclient.utils.base.BaseDialog;

/**
 * Created by Administrator on 2018/1/10.
 */

public class QGProgressDialog extends BaseDialog {
    private View view;

    public QGProgressDialog(Context context, View view) {
        this(context, R.style.DialogStyle_NoBG, view);
    }

    public QGProgressDialog(Context context, int themeResId, View view) {
        super(context, themeResId);
        this.view = view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (view == null) view = View.inflate(getContext(), R.layout.layout_qg_progress, null);
        setContentView(view, getWindowLayoutParams());
    }

    public WindowManager.LayoutParams getWindowLayoutParams() {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        int width = WindowUtil.getInstance(getContext()).getmScreenWidth();
        int height = WindowUtil.getInstance(getContext()).getmScreenHeight();
        if (width > height) {
            params.width = (int) (height * 0.6);
        } else params.width = (int) (width * 0.6);
        params.height = (int) (params.width * 0.4);
        params.gravity = Gravity.CENTER;
        getWindow().setAttributes(params);
        return getWindow().getAttributes();
    }
}
