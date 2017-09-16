package cn.cmkj.auction.data.http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cunguoyao on 2017/8/27.
 */

public class MainRecommend {

    private long id;
    private int mainPosition;//1今日拍卖 2精品推荐 3特惠专场 4一折 5免费
    private String name;
    private String picUrl;
    private double auctionPrice;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMainPosition() {
        return mainPosition;
    }

    public void setMainPosition(int mainPosition) {
        this.mainPosition = mainPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(double auctionPrice) {
        this.auctionPrice = auctionPrice;
    }

    public static MainRecommend parseJson(JSONObject jsonObject) {
        MainRecommend bean = new MainRecommend();
        return bean;
    }

    public static List<MainRecommend> parseJsonArray(JSONArray jsonArray) {
        List<MainRecommend> list = new ArrayList<>();
        /*if(jsonArray != null && jsonArray.length() > 0) {
            for(int i=0;i<jsonArray.length();i++) {
                MainRecommend bean = parseJson(jsonArray.optJSONObject(i));
                list.add(bean);
            }
        }*/
        for(int i=0;i<6;i++) {
            MainRecommend bean = new MainRecommend();
            bean.setId(i);
            bean.setMainPosition(1);
            bean.setName("《四僧书画》");
            bean.setPicUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=346499351,263743510&fm=26&gp=0.jpg");
            bean.setAuctionPrice(5000);
            list.add(bean);
        }
        for(int i=0;i<6;i++) {
            MainRecommend bean = new MainRecommend();
            bean.setId(i);
            bean.setMainPosition(2);
            bean.setName("《四僧书画》");
            bean.setPicUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=346499351,263743510&fm=26&gp=0.jpg");
            bean.setAuctionPrice(5000);
            list.add(bean);
        }
        return list;
    }
}
