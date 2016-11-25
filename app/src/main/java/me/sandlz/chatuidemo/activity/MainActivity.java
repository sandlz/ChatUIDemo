package me.sandlz.chatuidemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

import me.sandlz.chatuidemo.R;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Event(R.id.main_clickme)
    private void clickMe(View view) {
        Intent intent = new Intent();
        intent.setClass(this,ChatActivity.class);
        startActivity(intent);
    }

    @Event(R.id.main_highlight)
    private void highLight(View view) {
        Intent intent = new Intent();
        intent.setClass(this,HighLightActivity.class);
        startActivity(intent);
    }

}
