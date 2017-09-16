package cn.cmkj.auction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import cn.cmkj.auction.R;
import cn.cmkj.auction.data.http.MainRecommend;

/**
 * Created by cunguoyao on 2017/8/27.
 */

public class MainRecommendAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<MainRecommend> mData;
    private ImageOptions imageOptions;

    public MainRecommendAdapter(Context context, List<MainRecommend> mData) {
        this.context = context;
        this.mData = mData;
        this.imageOptions = new ImageOptions.Builder().setFadeIn(true).build(); //淡入效果
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext ()).inflate(R.layout.adapter_main_recommend, parent, false);
        return new MainRecommendViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainRecommend item = mData.get(position);
        x.image().bind(((MainRecommendViewHolder)holder).pic, item.getPicUrl(), imageOptions);
        ((MainRecommendViewHolder)holder).name.setText(item.getName());
        ((MainRecommendViewHolder)holder).price.setText("拍卖价格：" + item.getAuctionPrice());
    }

    class MainRecommendViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView pic;
        public TextView name;
        public TextView price;

        public MainRecommendViewHolder(View itemView) {
            super(itemView);
            this.pic = (ImageView) itemView.findViewById (R.id.recommend_pic);
            this.name = (TextView) itemView.findViewById (R.id.recommend_name);
            this.price = (TextView) itemView.findViewById (R.id.recommend_price);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
