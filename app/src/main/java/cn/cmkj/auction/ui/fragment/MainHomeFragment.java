package cn.cmkj.auction.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jude.rollviewpager.RollPagerView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import cn.cmkj.auction.R;
import cn.cmkj.auction.adapter.MainRecommendAdapter;
import cn.cmkj.auction.adapter.MainRollPagerAdapter;
import cn.cmkj.auction.data.http.MainRecommend;
import cn.cmkj.auction.data.http.RollAd;
import cn.cmkj.auction.widget.MyScrollView;
import cn.cmkj.auction.widget.TextScrollBanner;

/**
 * Created by cunguoyao on 2017/8/13.
 */
@ContentView(R.layout.fragment_main_home)
public class MainHomeFragment extends BaseFragment implements MyScrollView.ScrollViewListener,SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(R.id.swipeRefreshLayout)
    private SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.scrollView)
    private MyScrollView scrollView;
    @ViewInject(R.id.roll_view_pager)
    private RollPagerView rollPagerView;
    @ViewInject(R.id.ll_search)
    private RelativeLayout searchLayout;
    @ViewInject(R.id.text_scroll)
    private TextScrollBanner textScrollBanner;
    @ViewInject(R.id.main_content_layout)
    private LinearLayout contentLayout;

    private int height;
    private MainRollPagerAdapter mainRollPagerAdapter;
    private List<RollAd> rollAds;
    private List<RollAd> scrollTextData;
    private List<MainRecommend> mData;

    public static MainHomeFragment create() {
        MainHomeFragment f = new MainHomeFragment();
        Bundle args = new Bundle();
        //args.putInt("type", type);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rollAds = new ArrayList<>();
        scrollTextData = new ArrayList<>();
        mData = new ArrayList<>();
        mainRollPagerAdapter = new MainRollPagerAdapter(getActivity(), rollAds);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        //获取顶部图片高度后，设置滚动监听
        ViewTreeObserver vto = rollPagerView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rollPagerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height =   rollPagerView.getHeight();
                rollPagerView.getWidth();
                scrollView.setScrollViewListener(MainHomeFragment.this);
            }
        });
        rollPagerView.setAdapter(mainRollPagerAdapter);
        //为SwipeRefreshLayout设置监听事件
        swipeRefreshLayout.setOnRefreshListener(this);

        fetchRollData();
        fetchScrollTextData();
        fetchData();
        return view;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //结束后停止刷新
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }

    private void fetchRollData() {
        for(int i=1; i<=5; i++) {
            RollAd rollAd = new RollAd();
            rollAd.setId(i);
            rollAd.setAdTitle("我又要带耳机偷偷躲起来了哎");
            rollAd.setImageUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504089700372&di=9218adf350c165f3351726e4fa63f5c7&imgtype=0&src=http%3A%2F%2Fww1.sinaimg.cn%2Forj480%2F633f74f3jw1f6057jkmifj20k00bewga.jpg");
            rollAds.add(rollAd);
        }
        mainRollPagerAdapter.notifyDataSetChanged();
    }

    private void fetchScrollTextData() {
        for(int i=1; i<=5; i++) {
            RollAd rollAd = new RollAd();
            rollAd.setId(i);
            rollAd.setAdTitle("我又要带耳机偷偷躲起来了哎");
            scrollTextData.add(rollAd);
        }
        textScrollBanner.setList(scrollTextData);
        textScrollBanner.startScroll();
    }

    private void fetchData() {
        mData.clear();
        mData.addAll(MainRecommend.parseJsonArray(null));
        resolveData();
    }

    private void resolveData() {
        List<MainRecommend> recommendList1 = new ArrayList<>();
        List<MainRecommend> recommendList2 = new ArrayList<>();
        List<MainRecommend> recommendList3 = new ArrayList<>();
        List<MainRecommend> recommendList4 = new ArrayList<>();
        List<MainRecommend> recommendList5 = new ArrayList<>();
        for(MainRecommend bean : mData) {
            switch (bean.getMainPosition()) {
                case 1:
                    recommendList1.add(bean);
                    break;
                case 2:
                    recommendList2.add(bean);
                    break;
                case 3:
                    recommendList3.add(bean);
                    break;
                case 4:
                    recommendList4.add(bean);
                    break;
                case 5:
                    recommendList5.add(bean);
                    break;
            }

        }
        if(recommendList1.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            View remai = LayoutInflater.from(getActivity()).inflate(R.layout.view_main_item, null);
            RecyclerView remaiRecycleView = (RecyclerView)remai.findViewById(R.id.recyclerView);
            remaiRecycleView.setLayoutManager(linearLayoutManager);
            MainRecommendAdapter adapter = new MainRecommendAdapter(getActivity(), recommendList1);
            remaiRecycleView.setAdapter(adapter);
            contentLayout.addView(remai);
        }
        if(recommendList2.size() > 0) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            View remai = LayoutInflater.from(getActivity()).inflate(R.layout.view_main_item, null);
            RecyclerView remaiRecycleView = (RecyclerView)remai.findViewById(R.id.recyclerView);
            remaiRecycleView.setLayoutManager(linearLayoutManager);
            MainRecommendAdapter adapter = new MainRecommendAdapter(getActivity(), recommendList2);
            remaiRecycleView.setAdapter(adapter);
            contentLayout.addView(remai);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            setStatusBar(false, R.color.white);
        } else {

        }
    }

    @Override
    public void onScrollChanged(MyScrollView scrollView, int x, int y, int oldx, int oldy) {
        if(y<=height && y>=0){
            float scale =(float) y /height;
            float alpha =  (255 * scale);
            searchLayout.setBackgroundColor(Color.argb((int) alpha, 0xff, 0xff, 0xff));
        }
        else if(y > height){
            searchLayout.getBackground().setAlpha(255);
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
