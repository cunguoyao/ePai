package cn.cmkj.auction.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.util.LogUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import cn.cmkj.auction.R;
import cn.cmkj.auction.data.http.Zihua;

/**
 * Created by cunguoyao on 2017/9/11.
 */

public class MainCartAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Zihua> mData;
    private ImageOptions imageOptions;

    public MainCartAdapter(Context context, List<Zihua> mData) {
        this.context = context;
        this.mData = mData;
        this.imageOptions = new ImageOptions.Builder().setFadeIn(true).build(); //淡入效果
    }

    @Override
    public int getItemCount() {
        int size = mData != null ? mData.size() : 0;
        return size;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext ()).inflate(R.layout.adapter_main_cart, parent, false);
        return new MainCartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Zihua item = mData.get(position);
        ((MainCartViewHolder)holder).checkBox.setChecked(item.isChecked());
        x.image().bind(((MainCartViewHolder)holder).image, item.getFaceUrl(), imageOptions);
        ((MainCartViewHolder)holder).textName.setText(item.getName());
        ((MainCartViewHolder)holder).textPrice.setText("￥：" + item.getSalePrice());
        ((MainCartViewHolder)holder).textAges.setText("年代：" + item.getAges());
        ((MainCartViewHolder)holder).textSize.setText("尺寸：" + item.getWidth() + "*" + item.getLength() + "cm");
    }

    class MainCartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public CheckBox checkBox;
        public ImageView image;
        public TextView textName;
        public TextView textPrice;
        public TextView textAges;
        public TextView textSize;

        public MainCartViewHolder(View itemView) {
            super(itemView);
            this.checkBox = (CheckBox) itemView.findViewById (R.id.cart_checkbox);
            this.image = (ImageView) itemView.findViewById (R.id.cart_image);
            this.textName = (TextView) itemView.findViewById (R.id.cart_text_name);
            this.textPrice = (TextView) itemView.findViewById (R.id.cart_text_price);
            this.textAges = (TextView) itemView.findViewById (R.id.cart_text_ages);
            this.textSize = (TextView) itemView.findViewById (R.id.cart_text_size);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
