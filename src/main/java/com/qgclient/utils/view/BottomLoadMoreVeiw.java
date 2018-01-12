package com.qgclient.utils.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qgclient.utils.R;
import com.qgclient.utils.interfaces.BottomLoadMoreInterface;


/**
 * Created by 李健健 on 2017/4/6.
 */

public class BottomLoadMoreVeiw extends FrameLayout implements View.OnClickListener {
    private static final int LOADING = 1;
    private static final int TO_LOAD = 0;
    private int loadMode = TO_LOAD;
    private TextView loadTextView;
    private BottomLoadMoreInterface loadMoreListener;

    public BottomLoadMoreVeiw(Context context, BottomLoadMoreInterface listener) {
        super(context);
        init();
        setLoadMoreListener(listener);
    }


    public BottomLoadMoreVeiw(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomLoadMoreVeiw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        this.setLayoutParams(params);
        View.inflate(getContext(), R.layout.layout_bottom_loadmore_view, this);
        loadTextView = (TextView) findViewById(R.id.loadmore_view);
        loadTextView.setText("加载更多...");
        loadTextView.setOnClickListener(this);
    }

    public void setLoadMoreListener(BottomLoadMoreInterface listener) {
        this.loadMoreListener = listener;
    }

    public void setLoadMode(boolean isLoading) {
        if (isLoading) {
            loadMode = LOADING;
            loadTextView.setText("正在加载");
        } else {
            loadMode = TO_LOAD;
            loadTextView.setText("加载更多...");
        }
    }

    public boolean isLoading() {
        if (loadMode == LOADING) return true;
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loadmore_view) {
            if (loadMode == LOADING) return;
            if (loadMoreListener != null) {
                setLoadMode(true);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreListener.loadMore();
                    }
                }, 1000);
            }

        }
    }
}
