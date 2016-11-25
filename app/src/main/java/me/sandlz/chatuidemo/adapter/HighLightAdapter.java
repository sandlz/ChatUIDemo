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
public class HighLightAdapter extends BaseQuickAdapter<SearchMsgEntity, BaseViewHolder> {

    private String keyText;

    public HighLightAdapter(List<SearchMsgEntity> datas, String key) {
        super( R.layout.item_search_view, datas);
        this.keyText = key;
    }


    @Override
    protected void convert(BaseViewHolder holder, SearchMsgEntity item) {
        if (null == item.getNameStyle()) {
            holder.setText(R.id.search_name,item.getName());
        }else {
            holder.setText(R.id.search_name,item.getNameStyle());
        }

        if (null == item.getContentStyle()) {
            holder.setText(R.id.search_content,item.getContent());
        }else {
            holder.setText(R.id.search_content,item.getContentStyle());
        }

    }


}
