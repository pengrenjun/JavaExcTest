package com.ronglian.fssc.webapp.UtilsTest;

public class City {

    private int peopleNum;
    private int popularity;
    private String cityName;

    public City(int peopleNum, int popularity, String cityName) {
        this.peopleNum = peopleNum;
        this.popularity = popularity;
        this.cityName = cityName;
    }

    public City() {

    }

    public int getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
