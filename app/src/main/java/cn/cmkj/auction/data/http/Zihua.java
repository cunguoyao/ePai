package cn.cmkj.auction.data.http;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cunguoyao on 2017/9/11.
 */

public class Zihua {

    private long id;
    private String name;
    private String ages;
    private BigDecimal salePrice;
    private int length;
    private int width;
    private String faceUrl;

    private boolean checked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAges() {
        return ages;
    }

    public void setAges(String ages) {
        this.ages = ages;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public static Zihua parseJson(JSONObject jsonObject) {
        Zihua bean = new Zihua();
        return bean;
    }

    public static List<Zihua> parseJsonArray(JSONArray jsonArray, int page) {
        List<Zihua> list = new ArrayList<>();
        for(int i=6*(page-1);i<6*page;i++) {
            Zihua bean = new Zihua();
            bean.setId(i);
            bean.setName("Zihua");
            bean.setAges("2000年");
            bean.setWidth(100);
            bean.setLength(200);
            bean.setFaceUrl("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=346499351,263743510&fm=26&gp=0.jpg");
            bean.setSalePrice(new BigDecimal(100));
            list.add(bean);
        }
        return list;
    }
}
