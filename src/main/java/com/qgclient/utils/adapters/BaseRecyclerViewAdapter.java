package com.qgclient.utils.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qgclient.utils.view.BottomLoadMoreVeiw;
import com.qgclient.utils.viewholder.BaseRecyclerviewViewholder;

/**
 * Created by 李健健 on 2017/6/28.
 */

public abstract class BaseRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    public int TYPE_BOTTOM = -1, TYPE_CENTER = 0, TYPE_HEADER = 1;
    public BottomLoadMoreVeiw bottomLoadMoreVeiw;

    public abstract void setLoadMode(boolean isLoading);

    public class ViewHolderBottom extends BaseRecyclerviewViewholder {
        public ViewHolderBottom(View itemView) {
            super(itemView);
        }
    }
}
