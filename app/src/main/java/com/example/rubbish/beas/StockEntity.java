package com.example.rubbish.beas;

public class StockEntity
{
    private String name;
    private String price;
    private String flag;
    private String gross;

    public StockEntity(String name, String price, String flag, String gross) {
        this.name = name;
        this.price = price;
        this.flag = flag;
        this.gross = gross;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getFlag() {
        return flag;
    }

    public String getGross() {
        return gross;
    }
}
