package me.sandlz.chatuidemo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import me.sandlz.chatuidemo.R;
import me.sandlz.chatuidemo.entity.ChatMutiItems;
import me.sandlz.chatuidemo.utils.TimeUtil;


/**
 * Created by liuzhu on 2016/11/22.
 * Description :
 * Usage :
 */
public class ChatAdapter extends BaseMultiItemQuickAdapter<ChatMutiItems,BaseViewHolder> {

    private Context mContext = null;
    private int timeRange = 10000;// 10s

    public ChatAdapter(Context context, List<ChatMutiItems> data) {
        super(data);
        mContext = context;
        // 设定可能需要展示的ItemType
        addItemType(ChatMutiItems.TEXT, R.layout.item_text_view);
        addItemType(ChatMutiItems.TEXT_O, R.layout.item_text_view_o);
        addItemType(ChatMutiItems.IMAGE, R.layout.item_img_view);
        addItemType(ChatMutiItems.IMAGE_O, R.layout.item_img_view_o);
    }

    // 设置Item数据、点击事件、回调处理
    @Override
    protected void convert(BaseViewHolder holder, ChatMutiItems item) {
        switch (holder.getItemViewType()) {
            case ChatMutiItems.TEXT:
                setTextMessageMine(holder,item);
                break;
            case ChatMutiItems.TEXT_O:
                setTextMessageOther(holder,item);
                break;
            case ChatMutiItems.IMAGE:
                setImageMessageMine(holder,item);
                break;
            case ChatMutiItems.IMAGE_O:
                setImageMessageOther(holder,item);
                break;
        }
    }

    // 我发的文本消息
    private void setTextMessageMine(BaseViewHolder holder, ChatMutiItems item) {
        // 头像
        if (null != item.getUserHeadUrl()) {
            Glide.with(mContext)
                    .load(item.getUserHeadUrl())
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .into((ImageView) holder.getView(R.id.item_text_headurl));
        }
        // 姓名
        if (null != item.getUserName()) {
            holder.setText(R.id.item_text_username,item.getUserName());
        }
        int position = holder.getAdapterPosition();
        // 内容
        holder.setText(R.id.item_text_content, item.getContent());
        if (position == 0) {
            // 第一条
            holder.getView(R.id.item_text_time).setVisibility(View.VISIBLE);
            holder.setText(R.id.item_text_time,getTime(item.getReceiverTime()));
        }else if ((item.getReceiverTime() - getItem(position-1).getReceiverTime()) > timeRange) {
            // 大于10s
            holder.getView(R.id.item_text_time).setVisibility(View.VISIBLE);
            holder.setText(R.id.item_text_time,getTime(item.getReceiverTime()));
        }else {
            holder.getView(R.id.item_text_time).setVisibility(View.GONE);
        }
        // 添加点击事件
        holder.addOnClickListener(R.id.item_text_content);
    }

    // 别人发的文本消息
    private void setTextMessageOther(BaseViewHolder holder, ChatMutiItems item) {
        // 头像
        if (null != item.getUserHeadUrl()) {
            Glide.with(mContext)
                    .load(item.getUserHeadUrl())
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .into((ImageView) holder.getView(R.id.item_text_headurl_o));
        }
        // 姓名
        if (null != item.getUserName()) {
            holder.setText(R.id.item_text_username_o,item.getUserName());
        }
        int position = holder.getAdapterPosition();

        // 内容
        holder.setText(R.id.item_text_content_o, item.getContent());
        if (position == 0) {
            // 第一条
            holder.getView(R.id.item_text_time_o).setVisibility(View.VISIBLE);
            holder.setText(R.id.item_text_time_o,getTime(item.getReceiverTime()));
        }else if ((item.getReceiverTime() - getItem(position-1).getReceiverTime()) > timeRange) {
            // 大于10s
            holder.getView(R.id.item_text_time_o).setVisibility(View.VISIBLE);
            holder.setText(R.id.item_text_time_o,getTime(item.getReceiverTime()));
        }else {
            holder.getView(R.id.item_text_time_o).setVisibility(View.GONE);
        }
    }

    private void setImageMessageMine(BaseViewHolder holder, ChatMutiItems item) {
        // 头像
        if (null != item.getUserHeadUrl()) {
            Glide.with(mContext)
                    .load(item.getUserHeadUrl())
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .into((ImageView) holder.getView(R.id.item_img_head));
        }
        // 姓名
        if (null != item.getUserName()) {
            holder.setText(R.id.item_img_username,item.getUserName());
        }
        // 内容
        if (null != item.getImageUrl()) {
            Glide.with(mContext)
                    .load(item.getImageUrl())
                    .into((ImageView) holder.getView(R.id.item_img_content));
        }
    }

    private void setImageMessageOther(BaseViewHolder holder, ChatMutiItems item) {
        // 头像
        if (null != item.getUserHeadUrl()) {
            Glide.with(mContext)
                    .load(item.getUserHeadUrl())
                    .bitmapTransform(new CropCircleTransformation(mContext))
                    .into((ImageView) holder.getView(R.id.item_img_img_o));
        }
        // 姓名
        if (null != item.getUserName()) {
            holder.setText(R.id.item_img_username_o,item.getUserName());
        }
        // 内容
        if (null != item.getImageUrl()) {
            Glide.with(mContext)
                    .load(item.getImageUrl())
                    .into((ImageView) holder.getView(R.id.item_img_content_o));
        }
    }

    private void setVoiceMessageMine(BaseViewHolder holder, ChatMutiItems item) {

    }

    private void setVoiceMessageOther(BaseViewHolder holder, ChatMutiItems item) {

    }

    private void setVideoMessageMine(BaseViewHolder holder, ChatMutiItems item) {

    }

    private void setVideoMessageOther(BaseViewHolder holder, ChatMutiItems item) {

    }

    // ...

    private String getTime(long time) {
        // item时间
        Date oldTime = new Date(time);
        // 今天开始时间
        List todayTime = TimeUtil.getTodayStartAndEndTime();
        List yesterdayTime = TimeUtil.getYesterdayStartAndEndTime();

        long todayStart = (long)todayTime.get(0);
        long todayEnd = (long)todayTime.get(1);
        long yesterdayStart = (long)yesterdayTime.get(0);
        long yesterdayEnd = (long)yesterdayTime.get(1);

        SimpleDateFormat sdf = null;
        String dateStr = "";

        if (time > todayStart) {
            // 今天 直接显示时间
            sdf = new SimpleDateFormat("HH:mm:ss");
            dateStr = sdf.format(oldTime);
        }else if (time < todayStart && time > yesterdayStart) {
            // 昨天 显示昨天+时分秒
            sdf = new SimpleDateFormat("HH:mm");
            dateStr = sdf.format(oldTime);
            dateStr = "昨天"+dateStr;
        }else {
            // TODO 完善
            // 更早 显示月日+时分
            sdf = new SimpleDateFormat("MM-dd HH:mm");
            dateStr = sdf.format(oldTime);
        }

        return dateStr;
    }

}
