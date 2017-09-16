package cn.cmkj.auction.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.cmkj.auction.R;
import cn.cmkj.auction.adapter.MainCartAdapter;
import cn.cmkj.auction.app.BaseActivity;
import cn.cmkj.auction.data.http.Zihua;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

/**
 * Created by cunguoyao on 2017/8/30.
 */
@ContentView(R.layout.activity_user_address)
public class UserAddressActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener, SwipeMenuRecyclerView.LoadMoreListener {

    @ViewInject(R.id.swipeRefreshLayout)
    private SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.swipeMenuRecyclerView)
    private SwipeMenuRecyclerView swipeMenuRecyclerView;

    private List<Zihua> mData;
    private MainCartAdapter mainCartAdapter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        mData = new ArrayList<>();
        mainCartAdapter = new MainCartAdapter(this, mData);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //swipeMenuRecyclerView.setLongPressDragEnabled(true); // 开启拖拽。
        //swipeMenuRecyclerView.setItemViewSwipeEnabled(true); // 开启滑动删除。
        swipeMenuRecyclerView.setAutoLoadMore(true);
        // 自定义的核心就是DefineLoadMoreView类。
        swipeMenuRecyclerView.useDefaultLoadMore();
        swipeMenuRecyclerView.setLoadMoreListener(this); // 加载更多的监听。
        swipeMenuRecyclerView.setAdapter(mainCartAdapter);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
        mData.clear();
        mData.addAll(Zihua.parseJsonArray(null, page));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //结束后停止刷新
                mainCartAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        LogUtil.e("--onLoadMore:page=" + page);
        mData.addAll(Zihua.parseJsonArray(null, page));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //结束后停止刷新
                mainCartAdapter.notifyDataSetChanged();
                swipeMenuRecyclerView.loadMoreFinish(false, true);
            }
        }, 3000);
    }
}
