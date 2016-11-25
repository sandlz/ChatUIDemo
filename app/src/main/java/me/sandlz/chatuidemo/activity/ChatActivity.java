package me.sandlz.chatuidemo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.List;

import me.sandlz.chatuidemo.R;
import me.sandlz.chatuidemo.adapter.ChatAdapter;
import me.sandlz.chatuidemo.entity.ChatMutiItems;
import me.sandlz.chatuidemo.manager.DataManager;


@ContentView(R.layout.activity_chat)
public class ChatActivity extends BaseActivity {

    @ViewInject(R.id.chat_recyclerview)
    private RecyclerView recyclerView;

    @ViewInject(R.id.chat_edittext)
    private EditText editText;

    private ChatAdapter chatAdapter;

    private List<ChatMutiItems> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        final GridLayoutManager manager = new GridLayoutManager(this, 1);
//        manager.setOrientation();
        DataManager dataManager = new DataManager();
        // 构造数据
        data = dataManager.getMutiItems();
        chatAdapter = new ChatAdapter(this,data);

        recyclerView.setLayoutManager(manager);
        chatAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(chatAdapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Log.d("zliu","SimpleOnItemClick - " + view.getId());
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                super.onItemChildClick(adapter, view, position);
                Log.d("zliu","onItemChildClick- id - "+view.getId());
            }
        });

    }

    // 局部刷新

    @Event(R.id.chat_mesend)
    private void clickMe(View view) {
        String c = editText.getText().toString();
        ChatMutiItems item = new ChatMutiItems(1,1,c);
        item.setUserHeadUrl("http://7xsap2.com1.z0.glb.clouddn.com/favicon200.png");
        item.setUserName("zliu");
        item.setReceiverTime(System.currentTimeMillis());
        chatAdapter.addData(item);
        // 滚动至底部
        recyclerView.scrollToPosition(chatAdapter.getData().size()-1);
    }

    @Event(R.id.chat_othersend)
    private void clickOther(View view) {
        String c = editText.getText().toString();
        ChatMutiItems item = new ChatMutiItems(2,1,c);
        item.setReceiverTime(System.currentTimeMillis());
        item.setUserHeadUrl("http://up.qqjia.com/z/25/tu32703_10.png");
        item.setUserName("ywang");
        chatAdapter.addData(item);
        // 滚动至底部
        recyclerView.scrollToPosition(chatAdapter.getData().size()-1);
    }

}
