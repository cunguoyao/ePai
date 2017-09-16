package cn.cmkj.auction.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yanzhenjie.loading.LoadingView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.xutils.common.util.LogUtil;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.cmkj.auction.R;
import cn.cmkj.auction.adapter.MainCartAdapter;
import cn.cmkj.auction.data.http.Zihua;

/**
 * Created by cunguoyao on 2017/8/13.
 */
@ContentView(R.layout.fragment_main_cart)
public class MainCartFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, SwipeMenuRecyclerView.LoadMoreListener {

    @ViewInject(R.id.swipeRefreshLayout)
    private SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.swipeMenuRecyclerView)
    private SwipeMenuRecyclerView swipeMenuRecyclerView;
    @ViewInject(R.id.cart_checkbox)
    private CheckBox chooseCheckbox;
    @ViewInject(R.id.cart_count_text)
    private TextView chooseTextView;

    private List<Zihua> mData;
    private MainCartAdapter mainCartAdapter;
    private int page = 1;

    public static MainCartFragment create() {
        MainCartFragment f = new MainCartFragment();
        Bundle args = new Bundle();
        //args.putInt("type", type);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mData = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        //为SwipeRefreshLayout设置监听事件
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainCartAdapter = new MainCartAdapter(getActivity(), mData);
        //swipeMenuRecyclerView.setLongPressDragEnabled(true); // 开启拖拽。
        //swipeMenuRecyclerView.setItemViewSwipeEnabled(true); // 开启滑动删除。
        swipeMenuRecyclerView.setAutoLoadMore(true);
        // 自定义的核心就是DefineLoadMoreView类。
        DefineLoadMoreView loadMoreView = new DefineLoadMoreView(getActivity());
        swipeMenuRecyclerView.addFooterView(loadMoreView); // 添加为Footer。
        swipeMenuRecyclerView.setLoadMoreView(loadMoreView); // 设置LoadMoreView更新监听。
        swipeMenuRecyclerView.setLoadMoreListener(this); // 加载更多的监听。
        swipeMenuRecyclerView.setAdapter(mainCartAdapter);
        onRefresh();
        return view;
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            setStatusBar(false, R.color.white);
        } else {

        }
    }

    /**
     * 这是这个类的主角，如何自定义LoadMoreView。
     */
    static final class DefineLoadMoreView extends LinearLayout implements SwipeMenuRecyclerView.LoadMoreView, View.OnClickListener {

        private LoadingView mLoadingView;
        private TextView mTvMessage;

        private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener;

        public DefineLoadMoreView(Context context) {
            super(context);
            setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            setGravity(Gravity.CENTER);
            setVisibility(GONE);

            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

            int minHeight = (int) (displayMetrics.density * 60 + 0.5);
            setMinimumHeight(minHeight);

            inflate(context, R.layout.layout_fotter_loadmore, this);
            mLoadingView = (LoadingView) findViewById(R.id.loading_view);
            mTvMessage = (TextView) findViewById(R.id.tv_message);

            int color1 = getResources().getColor(R.color.colorPrimary);
            int color2 = getResources().getColor(R.color.colorPrimaryDark);
            int color3 = getResources().getColor(R.color.colorAccent);

            mLoadingView.setCircleColors(color1, color2, color3);
            setOnClickListener(this);
        }

        /**
         * 马上开始回调加载更多了，这里应该显示进度条。
         */
        @Override
        public void onLoading() {
            setVisibility(VISIBLE);
            mLoadingView.setVisibility(VISIBLE);
            mTvMessage.setVisibility(VISIBLE);
            mTvMessage.setText("正在努力加载，请稍后");
        }

        /**
         * 加载更多完成了。
         *
         * @param dataEmpty 是否请求到空数据。
         * @param hasMore   是否还有更多数据等待请求。
         */
        @Override
        public void onLoadFinish(boolean dataEmpty, boolean hasMore) {
            if (!hasMore) {
                setVisibility(VISIBLE);

                if (dataEmpty) {
                    mLoadingView.setVisibility(GONE);
                    mTvMessage.setVisibility(VISIBLE);
                    mTvMessage.setText("暂时没有数据");
                } else {
                    mLoadingView.setVisibility(GONE);
                    mTvMessage.setVisibility(VISIBLE);
                    mTvMessage.setText("没有更多数据啦");
                }
            } else {
                setVisibility(INVISIBLE);
            }
        }

        /**
         * 调用了setAutoLoadMore(false)后，在需要加载更多的时候，这个方法会被调用，并传入加载更多的listener。
         */
        @Override
        public void onWaitToLoadMore(SwipeMenuRecyclerView.LoadMoreListener loadMoreListener) {
            this.mLoadMoreListener = loadMoreListener;

            setVisibility(VISIBLE);
            mLoadingView.setVisibility(GONE);
            mTvMessage.setVisibility(VISIBLE);
            mTvMessage.setText("点我加载更多");
        }

        /**
         * 加载出错啦，下面的错误码和错误信息二选一。
         *
         * @param errorCode    错误码。
         * @param errorMessage 错误信息。
         */
        @Override
        public void onLoadError(int errorCode, String errorMessage) {
            setVisibility(VISIBLE);
            mLoadingView.setVisibility(GONE);
            mTvMessage.setVisibility(VISIBLE);

            // 这里要不直接设置错误信息，要不根据errorCode动态设置错误数据。
            mTvMessage.setText(errorMessage);
        }

        /**
         * 非自动加载更多时mLoadMoreListener才不为空。
         */
        @Override
        public void onClick(View v) {
            if (mLoadMoreListener != null) mLoadMoreListener.onLoadMore();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
