package com.example.rubbish.beas;

public class ShangpinEntity {

    private String url;
    private String name;
    private String price;
    private String count;
    private String intro;
    private String id;
    public boolean isCheck;
    public int carNum;
    public ShangpinEntity() {
        super();
        // TODO Auto-generated constructor stub
    }
    public ShangpinEntity(String url, String name, String price, String count,
                          String intro, String id, int carNum) {
        super();
        this.url = url;
        this.name = name;
        this.price = price;
        this.count = count;
        this.intro = intro;
        this.id=id;
        this.carNum=carNum;
    }
    public int getCarNum() {
        return carNum;
    }
    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getIntro() {
        return intro;
    }
    public void setIntro(String intro) {
        this.intro = intro;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


}

