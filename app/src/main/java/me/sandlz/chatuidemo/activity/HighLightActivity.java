package me.sandlz.chatuidemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import me.sandlz.chatuidemo.R;
import me.sandlz.chatuidemo.adapter.HighLightAdapter;
import me.sandlz.chatuidemo.entity.SearchMsgEntity;
import me.sandlz.chatuidemo.manager.DataManager;

@ContentView(R.layout.activity_high_light)
public class HighLightActivity extends BaseActivity {

    private HighLightAdapter adapter;

    DataManager dataManager = new DataManager();


    @ViewInject(R.id.search_recycler)
    RecyclerView recyclerView;

    @ViewInject(R.id.search_edit)
    EditText et_text;

    @ViewInject(R.id.search_search)
    Button btn_search;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(this, 1);

        adapter = new HighLightAdapter(new ArrayList<SearchMsgEntity>(),"");
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @Event(R.id.search_search)
    private void search(View view) {
        // 提示框
        btn_search.setText("获取中...");
        Log.d("zliu","开始计算时间：- "+System.currentTimeMillis());
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    // 模拟网络请求
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<SearchMsgEntity> results = dataManager.getSearchReuslt(et_text.getText().toString());
                Message message = new Message();
                message.obj = results;
                mHandler.sendMessage(message);
            }
        };

        thread.start();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btn_search.setText("搜索");
            adapter.setNewData((List<SearchMsgEntity>)msg.obj);
            Log.d("zliu","设置完数据时间：- "+System.currentTimeMillis());
        }
    };



}
