package cn.cmkj.auction.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import cn.cmkj.auction.data.http.RollAd;

/**
 * Created by cunguoyao on 2017/8/30.
 */

//适配器
public class MainRollPagerAdapter extends StaticPagerAdapter {

    private List<RollAd> imageUrls;
    private Context context;
    private ImageOptions imageOptions;

    public MainRollPagerAdapter(Context context, List<RollAd> imageUrls) {
        this.imageUrls = imageUrls;
        this.context = context;
        this.imageOptions = new ImageOptions.Builder().setFadeIn(true).build(); //淡入效果
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        if(imageUrls != null && imageUrls.size() > 0) {
            RollAd rollAd = imageUrls.get(position);
            x.image().bind(imageView, rollAd.getImageUrl(), imageOptions);
        }
        return imageView;
    }

    @Override
    public int getCount() {
        return imageUrls == null ? 0 : imageUrls.size();
    }
}
