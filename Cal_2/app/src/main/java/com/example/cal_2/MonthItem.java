package com.example.cal_2;

public class MonthItem {
    private int dayValue;
    private int MonthValue;

    MonthItem(int dayValue){
        this.dayValue = dayValue;
    }

    public int getDay(){
        return dayValue;
    }
    public int getMonth() {return MonthValue;}
}
