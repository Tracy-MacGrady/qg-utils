package com.qgclient.utils.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qgclient.utils.R;
import com.qgclient.utils.interfaces.TitleBarClickListenerInterface;

/**
 * Created by Administrator on 2018/1/10.
 */

public class TitleBarView extends RelativeLayout implements View.OnClickListener {
    private TextView titleView, leftTextView, rightTextView;
    private ImageView leftImageView, rightImageView;
    private TitleBarClickListenerInterface listener;
    ColorStateList titleColor, leftTextColor, rightTextColor;
    float titleTextSize, leftTextSize, rightTextSize;
    String titleTextVal, leftTextVal, rightTextVal;
    boolean leftTextShow, rightTextShow;
    int leftImgSrcRes, rightImgSrcRes;

    public TitleBarView(Context context) {
        super(context);
        initView();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBarView, defStyleAttr, 0);
        titleColor = typedArray.getColorStateList(R.styleable.TitleBarView_title_textview_textcolor);
        titleTextSize = typedArray.getDimension(R.styleable.TitleBarView_title_textview_textsize, -1);
        titleTextVal = typedArray.getString(R.styleable.TitleBarView_title_textview_textval);
        if (titleColor != null) titleView.setTextColor(titleColor);
        if (titleTextSize > -1) titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        titleView.setText(titleTextVal);
        leftTextShow = typedArray.getBoolean(R.styleable.TitleBarView_left_show_textview, false);
        rightTextShow = typedArray.getBoolean(R.styleable.TitleBarView_right_show_textview, false);
        if (leftTextShow) {
            leftTextVal = typedArray.getString(R.styleable.TitleBarView_lefttextview_textval);
            leftTextColor = typedArray.getColorStateList(R.styleable.TitleBarView_lefttextview_textcolor);
            leftTextSize = typedArray.getDimension(R.styleable.TitleBarView_lefttextview_textsize, -1);
            leftTextView.setVisibility(VISIBLE);
            leftTextView.setText(leftTextVal);
            if (leftTextColor != null) leftTextView.setTextColor(leftTextColor);
            if (leftTextSize > -1)
                leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
        } else {
            leftImgSrcRes = typedArray.getResourceId(R.styleable.TitleBarView_leftimage_src, 0);
            leftImageView.setVisibility(VISIBLE);
            leftImageView.setImageResource(leftImgSrcRes);
        }
        if (rightTextShow) {
            rightTextVal = typedArray.getString(R.styleable.TitleBarView_righttextview_textval);
            rightTextColor = typedArray.getColorStateList(R.styleable.TitleBarView_righttextview_textcolor);
            rightTextSize = typedArray.getDimension(R.styleable.TitleBarView_righttextview_textsize, -1);
            rightTextView.setVisibility(VISIBLE);
            rightTextView.setText(rightTextVal);
            if (rightTextColor != null) rightTextView.setTextColor(rightTextColor);
            if (rightTextSize > -1)
                rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
        } else {
            rightImgSrcRes = typedArray.getResourceId(R.styleable.TitleBarView_rightimage_src, 0);
            rightImageView.setVisibility(VISIBLE);
            rightImageView.setImageResource(rightImgSrcRes);
        }
        typedArray.recycle();
    }

    public void initView() {
        View.inflate(getContext(), R.layout.layout_titlebar_view, this);
        titleView = findViewById(R.id.titleview);
        leftTextView = findViewById(R.id.left_textview);
        rightTextView = findViewById(R.id.right_textview);
        leftImageView = findViewById(R.id.left_imageview);
        rightImageView = findViewById(R.id.right_imageview);

        leftTextView.setOnClickListener(this);
        rightTextView.setOnClickListener(this);
        leftImageView.setOnClickListener(this);
        rightImageView.setOnClickListener(this);
    }

    public void setListener(TitleBarClickListenerInterface listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener == null) return;
        int id = v.getId();
        if (id == R.id.left_textview) {
            listener.onTitlebarClickLeft();
        } else if (id == R.id.left_imageview) {
            listener.onTitlebarClickLeft();
        } else if (id == R.id.right_textview) {
            listener.onTitlebarClickRight();
        } else if (id == R.id.right_imageview) {
            listener.onTitlebarClickRight();
        }
    }

    public void setTitleColor(ColorStateList titleColor) {
        this.titleColor = titleColor;
        this.titleView.setTextColor(this.titleColor);
    }

    public void setLeftTextColor(ColorStateList leftTextColor) {
        this.leftTextColor = leftTextColor;
        this.leftTextView.setTextColor(this.leftTextColor);
    }

    public void setRightTextColor(ColorStateList rightTextColor) {
        this.rightTextColor = rightTextColor;
        this.rightTextView.setTextColor(this.rightTextColor);
    }

    public void setTitleTextSize(float titleTextSize) {
        this.titleTextSize = titleTextSize;
        this.titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.titleTextSize);
    }

    public void setLeftTextSize(float leftTextSize) {
        this.leftTextSize = leftTextSize;
        this.leftTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.leftTextSize);
    }

    public void setRightTextSize(float rightTextSize) {
        this.rightTextSize = rightTextSize;
        this.rightTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.rightTextSize);
    }

    public void setTitleTextVal(String titleTextVal) {
        this.titleTextVal = titleTextVal;
        this.titleView.setText(this.titleTextVal);
    }

    public void setLeftTextVal(String leftTextVal) {
        this.leftTextVal = leftTextVal;
        this.leftTextView.setText(this.leftTextVal);
    }

    public void setRightTextVal(String rightTextVal) {
        this.rightTextVal = rightTextVal;
        this.rightTextView.setText(this.rightTextVal);
    }

    public void setLeftTextShow(boolean leftTextShow) {
        this.leftTextShow = leftTextShow;
        this.leftTextView.setVisibility(this.leftTextShow ? VISIBLE : GONE);
    }

    public void setRightTextShow(boolean rightTextShow) {
        this.rightTextShow = rightTextShow;
        this.rightTextView.setVisibility(this.rightTextShow ? VISIBLE : GONE);
    }

    public void setLeftImgSrcRes(int leftImgSrcRes) {
        this.leftImgSrcRes = leftImgSrcRes;
        this.leftImageView.setImageResource(this.leftImgSrcRes);
    }

    public void setRightImgSrcRes(int rightImgSrcRes) {
        this.rightImgSrcRes = rightImgSrcRes;
        this.rightImageView.setImageResource(this.rightImgSrcRes);
    }
}
