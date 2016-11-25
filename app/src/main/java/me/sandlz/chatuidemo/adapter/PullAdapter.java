package me.sandlz.chatuidemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.sandlz.chatuidemo.R;
import me.sandlz.chatuidemo.entity.SearchMsgEntity;

/**
 * Created by liuzhu on 2016/11/25.
 * Description :
 * Usage :
 */
public class PullAdapter extends BaseQuickAdapter<SearchMsgEntity, BaseViewHolder> {

    public PullAdapter(List<SearchMsgEntity> data) {
        super(R.layout.item_search_view, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, SearchMsgEntity item) {
        holder.setText(R.id.search_name,item.getName())
                .setText(R.id.search_content,item.getContent());
    }
}
