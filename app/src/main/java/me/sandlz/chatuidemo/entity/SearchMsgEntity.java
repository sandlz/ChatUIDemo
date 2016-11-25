package me.sandlz.chatuidemo.entity;

import android.text.SpannableStringBuilder;

/**
 * Created by liuzhu on 2016/11/25.
 * Description :
 * Usage :
 */
public class SearchMsgEntity {

    private String headUrl;

    private String name;

    private SpannableStringBuilder nameStyle;

    private String content;

    private SpannableStringBuilder contentStyle;



    public SearchMsgEntity(String head, String name, String content) {
        this.headUrl = head;
        this.name = name;
        this.content = content;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SpannableStringBuilder getNameStyle() {
        return nameStyle;
    }

    public void setNameStyle(SpannableStringBuilder nameStyle) {
        this.nameStyle = nameStyle;
    }

    public SpannableStringBuilder getContentStyle() {
        return contentStyle;
    }

    public void setContentStyle(SpannableStringBuilder contentStyle) {
        this.contentStyle = contentStyle;
    }
}
