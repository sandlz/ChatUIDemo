package me.sandlz.chatuidemo.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by liuzhu on 2016/11/22.
 * Description :
 * Usage :
 */
public class ChatMutiItems implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int TEXT_O = 2;
    public static final int IMAGE = 3;
    public static final int IMAGE_O = 4;


    // 公用
    private String userName;
    private String userHeadUrl;
    private long   receiverTime;

    // 文本
    private String content;
    // 图片
    private String imageUrl;


    private int itemType;
    private int spanSize;

    public ChatMutiItems(int itemType, int spanSize, String content) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
    }

    public ChatMutiItems(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeadUrl() {
        return userHeadUrl;
    }

    public void setUserHeadUrl(String userHeadUrl) {
        this.userHeadUrl = userHeadUrl;
    }

    public long getReceiverTime() {
        return receiverTime;
    }

    public void setReceiverTime(long receiverTime) {
        this.receiverTime = receiverTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    @Override
    public int getItemType() {
        return itemType;
    }


}
