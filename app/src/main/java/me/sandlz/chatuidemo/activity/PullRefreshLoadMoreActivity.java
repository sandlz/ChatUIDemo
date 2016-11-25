package me.sandlz.chatuidemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import me.sandlz.chatuidemo.R;
import me.sandlz.chatuidemo.adapter.PullAdapter;
import me.sandlz.chatuidemo.manager.DataManager;
import me.sandlz.chatuidemo.wiget.SuperSwipeRefreshLayout;

@ContentView(R.layout.activity_pull_refresh_load_more)
public class PullRefreshLoadMoreActivity extends BaseActivity implements
        SuperSwipeRefreshLayout.OnPullRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    @ViewInject(R.id.pull_recycler)
    RecyclerView recyclerView;

    private PullAdapter adapter;
    DataManager dataManager = new DataManager();

    @ViewInject(R.id.pull_swipeLayout)
    SuperSwipeRefreshLayout refreshLayout;

    // headerView
    private ProgressBar header_progressBar;
    private TextView    header_textView;
    private ImageView   header_imageView;

    private int currentCount = 10;
    private int totalCount = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        adapter = new PullAdapter(dataManager.getFakeData(10));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        // header refresh view
        refreshLayout.setHeaderViewBackgroundColor(Color.WHITE);
        refreshLayout.setHeaderView(createRefreshHeader());// add headerView
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
        // 下拉刷新
        header_textView.setText("正在刷新");
        header_imageView.setVisibility(View.GONE);
        header_progressBar.setVisibility(View.VISIBLE);

        adapter.setEnableLoadMore(false);
        currentCount = 10;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(dataManager.getFakeData(10));
                refreshLayout.setRefreshing(false);
                header_progressBar.setVisibility(View.GONE);
                if (currentCount >= totalCount) {
                    adapter.setEnableLoadMore(false);
                }else {
                    adapter.setEnableLoadMore(true);
                }
            }
        }, 1000);

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
        // 上拉加载更多
        refreshLayout.setEnabled(false);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (currentCount < totalCount) {
                    adapter.setNewData(dataManager.getFakeData(12));
                    currentCount = totalCount;
                }else {
                    adapter.loadMoreEnd();
                }
                refreshLayout.setEnabled(true);
            }
        }, 1000);

    }
}
