package com.example.fbpg;

public class Vaccine {
    private String place;
    private String date;
    public Vaccine(String place,String date){
        this.place=place;
        this.date=date;
    }
    public Vaccine(){
    }

    public String getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Vaccine{" +
                "place='" + place + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
