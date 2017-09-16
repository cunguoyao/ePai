package cn.cmkj.auction.utils;

import android.support.v4.util.SparseArrayCompat;
import cn.cmkj.auction.ui.fragment.BaseFragment;
import cn.cmkj.auction.ui.fragment.MainCartFragment;
import cn.cmkj.auction.ui.fragment.MainHomeFragment;
import cn.cmkj.auction.ui.fragment.MainMineFragment;
import cn.cmkj.auction.ui.fragment.MainShuFragment;

/**
 * Created by cunguoyao on 2017/8/17.
 */

public class FragmentFactory {

    static SparseArrayCompat<BaseFragment> cachesFragment = new SparseArrayCompat<>();//
    static SparseArrayCompat<BaseFragment> cachesChartFragment = new SparseArrayCompat<>();//图表

    public static BaseFragment getChartFragment(int position) {
        BaseFragment fragment = null;
        BaseFragment tempFragment = cachesChartFragment.get(position);
        if (tempFragment != null) {
            fragment = tempFragment;
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = MainHomeFragment.create();
                break;
            case 1:
                fragment = MainShuFragment.create();
                break;
            case 2:
                fragment = MainCartFragment.create();
                break;
            case 3:
                fragment = MainMineFragment.create();
                break;
        }
        cachesChartFragment.put(position, fragment);
        return fragment;
    }

    //移除所有
    public static void removeAllFragment() {
        if (cachesFragment != null && cachesFragment.size() > 0) {
            cachesFragment.clear();
        }
    }

    //拿到列表
    public static SparseArrayCompat<BaseFragment> getListFragment() {
        if (cachesFragment != null && cachesFragment.size() > 0) {
            return cachesFragment;
        }
        return null;
    }

}
