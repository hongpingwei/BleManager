package com.zzc.blemanager.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zzc.blemanager.common.ItemClickListener;
import com.zzc.blemanager.databinding.ItemLogBinding;
import com.zzc.blemanager.model.LogModel;

import java.util.List;

/**
 * Created : zzc
 * Time : 2017/7/26
 * Email : zzc1259@163.com
 * Description : ${desc}
 */

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {
    private List<LogModel> mList;
    private int layoutId;
    private LayoutInflater mInflater;
    private ItemClickListener<LogModel> mListener;

    public LogAdapter(List<LogModel> list, int layoutId, Context context) {
        mList = list;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(context);
    }

    public void setListener(ItemClickListener<LogModel> listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(layoutId, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final LogModel logModel = mList.get(position);
        holder.binding.setModle(logModel);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.itemClick(position, logModel);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mListener != null) {
                    return mListener.itemLongClick(position, logModel);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemLogBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
