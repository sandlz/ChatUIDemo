package me.sandlz.chatuidemo.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.sandlz.chatuidemo.entity.ChatMutiItems;

/**
 * Created by liuzhu on 2016/11/22.
 * Description :
 * Usage :
 */
public class DataManager {

    private List times = new ArrayList();

    public DataManager() {
        initChatTimelist();
    }

    public List<ChatMutiItems> getMutiItems() {
        Random random=new Random();

        List<ChatMutiItems> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            ChatMutiItems items = null;
            if (i % 2 == 0) {
                // 我发的文字
                items = new ChatMutiItems(1, 1);
                items.setUserHeadUrl("http://7xsap2.com1.z0.glb.clouddn.com/favicon200.png");
                items.setUserName("zliu");
                items.setContent("我是第"+ i +"条内容");
            } else if (i % 3 == 0) {
                // 别人发的文字
                items = new ChatMutiItems(2, 1);
                items.setUserHeadUrl("http://up.qqjia.com/z/25/tu32703_10.png");
                items.setUserName("ywang");
                items.setContent("我是第"+ i +"条内容");
            } else if (i % 5 == 0) {
                // 我发的图片
                items = new ChatMutiItems(3, 1);
                int pos = random.nextInt(10);
                String url = getRandomImageUrl(pos);
                items.setImageUrl(url);
                items.setUserHeadUrl("http://7xsap2.com1.z0.glb.clouddn.com/favicon200.png");
                items.setUserName("zliu");
            } else {
                // 别人发的图片
                items = new ChatMutiItems(4, 1);
                int pos = random.nextInt(10);
                String url = getRandomImageUrl(pos);
                items.setImageUrl(url);
                items.setUserHeadUrl("http://up.qqjia.com/z/25/tu32703_10.png");
                items.setUserName("ywang");
            }
            data.add(items);
        }
        return data;
    }

    private void initChatTimelist() {
        long sTime = Long.parseLong("1479669923387");// 2016-11-21 03:25:23
        for (int i = 0; i < 50; i++) {
            long tempTime = sTime + 1000 * 60 * 15;
            times.add(tempTime);
        }
    }

    private String getRandomImageUrl(int pos) {
        List<String> dataList = new ArrayList<>();
        dataList.add("http://img1.niutuku.com/design/1207/2312/ntk-2312-38pogdiczbqu1.jpg");
        dataList.add("http://img3.duitang.com/uploads/item/201507/30/20150730163111_YZT5S.thumb.700_0.jpeg");
        dataList.add("http://sc.jb51.net/uploads/allimg/150703/14-150F3164339355.jpg");
        dataList.add("http://t1.niutuku.com/960/10/10-202370.jpg");
        dataList.add("http://h.hiphotos.baidu.com/zhidao/pic/item/6d81800a19d8bc3ed69473cb848ba61ea8d34516.jpg");
        dataList.add("http://img1.niutuku.com/design/1207/2312/ntk-2312-38pogdiczbqu1.jpg");
        dataList.add("http://a.hiphotos.baidu.com/zhidao/pic/item/f9dcd100baa1cd11aa2ca018bf12c8fcc3ce2d74.jpg");
        dataList.add("http://h.hiphotos.baidu.com/zhidao/pic/item/0eb30f2442a7d9334f268ca9a84bd11372f00159.jpg");
        dataList.add("http://t-1.tuzhan.com/3e1771aeb810/c-2/l/2013/07/10/01/403e940d4d20464282ba291fad64a49c.jpg");
        dataList.add("http://img.wadongxi.net/shop/15/46/2502566dcf9f146.jpg");

        return dataList.get(pos);
    }
}
