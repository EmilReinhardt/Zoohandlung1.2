package com.example.zoohandlung12;

public class Tier {
    // start attributes
    protected String name;
    protected int alter;
    protected double preis;
    // end attributes

    public Tier(){
    }

    public Tier(String name, int alter, double preis){
        this.name = name;
        this.alter = alter;
        this.preis = preis;
    }
    // start methods
    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alterNew) {
        alter = alterNew;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preisNew) {
        preis = preisNew;
    }

    public void gibtLaut() {
        System.out.println(name + " hat einen Laut gegeben");

    }

    public void seiWuetend() {
        System.out.println(name + " ist wütend");

    }

    public String getTyp(){
        return null;
    }

    public String getRasse(){return null;}

    // end methods
}
