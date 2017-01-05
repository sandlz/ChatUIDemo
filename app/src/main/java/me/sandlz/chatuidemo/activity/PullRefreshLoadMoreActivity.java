package me.sandlz.chatuidemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import me.sandlz.chatuidemo.R;
import me.sandlz.chatuidemo.adapter.PullAdapter;
import me.sandlz.chatuidemo.manager.DataCallBackListener;
import me.sandlz.chatuidemo.manager.DataManager;
import me.sandlz.chatuidemo.widget.SuperSwipeRefreshLayout;

@ContentView(R.layout.activity_pull_refresh_load_more)
public class PullRefreshLoadMoreActivity extends BaseActivity implements
        SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener ,DataCallBackListener{

    @ViewInject(R.id.pull_recycler)
    RecyclerView recyclerView;

    private PullAdapter adapter;
    DataManager dataManager = null;

    @ViewInject(R.id.pull_swipeLayout)
    SuperSwipeRefreshLayout refreshLayout;

    // headerView
    private ProgressBar header_progressBar;
    private TextView    header_textView;
    private ImageView   header_imageView;

    private int currentCount = 10;
    private int totalCount = 12;

    private int page = 1;
    private int pageCountSize = 10;
    private List<String> dataList = new ArrayList<>();
    private boolean refresh = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        adapter = new PullAdapter(dataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        // header refresh view
        refreshLayout.setHeaderViewBackgroundColor(Color.WHITE);
        refreshLayout.setHeaderView(createRefreshHeader());// add headerView
        dataManager = new DataManager();
        dataManager.setListener(this);
    }

    private View createRefreshHeader() {
        View view = LayoutInflater.from(refreshLayout.getContext())
                .inflate(R.layout.recyclerview_header_refresh, null);
        header_progressBar = (ProgressBar) view.findViewById(R.id.refresh_pb_view);
        header_textView = (TextView) view.findViewById(R.id.refresh_text_view);
        header_textView.setText("下拉刷新");
        header_imageView = (ImageView) view.findViewById(R.id.refresh_image_view);
        header_imageView.setVisibility(View.VISIBLE);
        header_imageView.setImageResource(R.mipmap.arrow_down);
        header_progressBar.setVisibility(View.GONE);
        return view;

    }

    private void initListener() {
        refreshLayout.setOnPullRefreshListener(this);
        adapter.setOnLoadMoreListener(this);
    }

    @Override
    public void onRefresh() {
        refresh = true;
        // 下拉刷新
        header_textView.setText("正在刷新");
        header_imageView.setVisibility(View.GONE);
        header_progressBar.setVisibility(View.VISIBLE);
        dataList.clear();
        adapter.setEnableLoadMore(false);
        page = 1;
        dataManager.getNamesByPage(page);
    }

    @Override
    public void onPullDistance(int distance) {

    }

    @Override
    public void onPullEnable(boolean enable) {
        header_textView.setText(enable ? "松开刷新" : "下拉刷新");
        header_imageView.setVisibility(View.VISIBLE);
        header_imageView.setRotation(enable ? 180 : 0);
    }

    @Override
    public void onLoadMoreRequested() {
        refresh = false;
        // 上拉加载更多
        refreshLayout.setEnabled(false);
        page ++;
        dataManager.getNamesByPage(page);
    }

    @Override
    public void callBackDataList(List<String> data) {
        Log.d("zliu","page: "+page + " 大小: "+data.size());
        if (refresh) {
            refreshLayout.setRefreshing(false);
            adapter.setEnableLoadMore(true);
            header_progressBar.setVisibility(View.GONE);
            dataList.addAll(data);
            adapter.setNewData(dataList);
        }else {
            if (null != data && data.size() > 0) {
                dataList.addAll(data);
                adapter.setNewData(dataList);
                if (data.size() < 10) {
                    adapter.loadMoreEnd();
//                    adapter.setEnableLoadMore(false);
                }else {
                    adapter.loadMoreComplete();
                }
            }else {
                adapter.loadMoreEnd();
            }
            refreshLayout.setEnabled(true);
        }

    }
}
