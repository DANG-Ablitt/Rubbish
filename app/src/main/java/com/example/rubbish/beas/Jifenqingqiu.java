package com.example.rubbish.beas;

import java.io.Serializable;

public class Jifenqingqiu implements Serializable
{
    private int hao1;
    private int hao2;
    private int hao3;
    private int hao4;
    private int hao5;
    private int jintian;

    //默认构造函数
    public Jifenqingqiu(){}

    public Jifenqingqiu(int hao1, int hao2, int hao3, int hao4, int hao5, int jintian)
    {
        this.hao1 = hao1;
        this.hao2 = hao2;
        this.hao3 = hao3;
        this.hao4 = hao4;
        this.hao5 = hao5;
        this.jintian = jintian;
    }

    //x
    public void setHao1(int hao1)
    {
        this.hao1 = hao1;
    }

    public int getHao1()
    {
        return hao1;
    }
   //xx
    public void setHao2(int hao2)
    {
        this.hao2 = hao2;
    }

    public int getHao2()
    {
        return hao2;
    }
    //xxx

    public void setHao3(int hao3)
    {
        this.hao3 = hao3;
    }

    public int getHao3()
    {
        return hao3;
    }
   //xxxx
    public void setHao4(int hao4)
    {
        this.hao4 = hao4;
    }

    public int getHao4()
    {
        return hao4;
    }
   //xxxxx
    public void setHao5(int hao5)
    {
        this.hao5 = hao5;
    }

    public int getHao5()
    {
        return hao5;
    }

    //xxxxxx
    public void setJintian(int jintian)
    {
        this.jintian = jintian;
    }

    public int getJintian()
    {
        return jintian;
    }
}
