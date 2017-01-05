package me.sandlz.chatuidemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.sandlz.chatuidemo.R;

/**
 * Created by liuzhu on 2016/11/25.
 * Description :
 * Usage :
 */
public class PullAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PullAdapter(List<String> data) {
        super(R.layout.item_search_view, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {
        holder.setText(R.id.search_name,item);
    }
}
