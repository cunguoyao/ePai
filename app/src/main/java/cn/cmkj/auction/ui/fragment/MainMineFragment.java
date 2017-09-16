package cn.cmkj.auction.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.cmkj.auction.R;
import cn.cmkj.auction.ui.LoginActivity;
import cn.cmkj.auction.ui.UserAddressActivity;
import cn.cmkj.auction.ui.UserInfoActivity;
import cn.cmkj.auction.widget.AlertDialog;

/**
 * Created by cunguoyao on 2017/8/13.
 */
@ContentView(R.layout.fragment_main_mine)
public class MainMineFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    @ViewInject(R.id.swipeRefreshLayout)
    private SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.scrollView)
    private ScrollView scrollView;
    @ViewInject(R.id.account_image_frame)
    private RelativeLayout accountImageFrame;
    @ViewInject(R.id.mine_address)
    private RelativeLayout mineAddress;

    public static MainMineFragment create() {
        MainMineFragment f = new MainMineFragment();
        Bundle args = new Bundle();
        //args.putInt("type", type);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = x.view().inject(this, inflater, container);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new  ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                swipeRefreshLayout.setEnabled(scrollView.getScrollY()==0);
            }
        });
        //为SwipeRefreshLayout设置监听事件
        swipeRefreshLayout.setOnRefreshListener(this);
        accountImageFrame.setOnClickListener(this);
        mineAddress.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account_image_frame:
                jump(UserInfoActivity.class);
                break;
            case R.id.mine_address:
                startActivity(new Intent(getActivity(), UserAddressActivity.class));
                break;
        }
    }

    private void jump(Class c) {
        if(getAccount() == null) {
            AlertDialog loginDialog = new AlertDialog(getActivity());
            loginDialog.builder().setTitle("提示").setMsg("需要登录才能使用此功能")
                    .setCancelable(true).setNegativeButton("取消", null)
                    .setPositiveButton("立即登录", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivityForResult(intent, LoginActivity.REQ_LOGIN);
                        }
                    }).show();
        }else {
            Intent intent = new Intent(getActivity(), c);
            startActivity(intent);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()) {
            setStatusBar(false, R.color.status_bar_fragment_mine);
        } else {

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LoginActivity.REQ_LOGIN && resultCode == Activity.RESULT_OK) {
            Toast.makeText(getActivity(), "登录成功了", Toast.LENGTH_SHORT).show();
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
