package cn.cmkj.auction.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import cn.cmkj.auction.R;
import cn.cmkj.auction.app.BaseActivity;
import cn.cmkj.auction.ui.fragment.BaseFragment;
import cn.cmkj.auction.utils.FragmentFactory;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.viewpager)
    private ViewPager mViewPager;
    @ViewInject(R.id.rg_tab)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.b1)
    private RadioButton radio1;
    @ViewInject(R.id.b2)
    private RadioButton radio2;
    @ViewInject(R.id.b3)
    private RadioButton radio3;
    @ViewInject(R.id.b4)
    private RadioButton radio4;
    @ViewInject(R.id.iv_red1)
    private ImageView ivRed1;
    @ViewInject(R.id.iv_red2)
    private ImageView ivRed2;
    @ViewInject(R.id.iv_red3)
    private ImageView ivRed3;
    @ViewInject(R.id.iv_red4)
    private ImageView ivRed4;

    private MainPageAdapter pagerAdapter;
    private MainOnPageChangeListener pageChangeListener;
    private MainRadioGroupListener radioGroupListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.b1:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.b2:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.b3:
                        mViewPager.setCurrentItem(3);
                        break;
                    case R.id.b4:
                        mViewPager.setCurrentItem(4);
                        break;
                }
            }
        });
        pagerAdapter = new MainPageAdapter(getSupportFragmentManager());
        pageChangeListener = new MainOnPageChangeListener();
        radioGroupListener = new MainRadioGroupListener();
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOnPageChangeListener(pageChangeListener);
        mViewPager.setOffscreenPageLimit(0);
        mRadioGroup.setOnCheckedChangeListener(radioGroupListener);
    }

    private class MainRadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.b1:
                    mViewPager.setCurrentItem(0);
                    break;
                case R.id.b2:
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.b3:
                    mViewPager.setCurrentItem(2);
                    break;
                case R.id.b4:
                    mViewPager.setCurrentItem(3);
                    break;
            }
        }
    }

    private class MainPageAdapter extends FragmentStatePagerAdapter {

        public MainPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment fragment = FragmentFactory.getChartFragment(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    private class MainOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    mRadioGroup.check(R.id.b1);
                    break;
                case 1:
                    mRadioGroup.check(R.id.b2);
                    break;
                case 2:
                    mRadioGroup.check(R.id.b3);
                    break;
                case 3:
                    mRadioGroup.check(R.id.b4);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
