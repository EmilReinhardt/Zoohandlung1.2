package com.example.zoohandlung12;

public class Schlange extends Tier {
    // start attributes
    private double länge;
    private String rasse;



    private boolean toetlich;
    // end attributes

    public Schlange(String name, int alter, double preis, String rasse, double länge, boolean toetlich) {
        super(name, alter, preis);
        this.länge = länge;
        this.rasse = rasse;
        this.toetlich = toetlich;
    }

    public void Schlange() {
        this.länge = 0;
        this.rasse = "";
    }

    // start methods
    public double getLänge() {
        return länge;
    }

    public String getRasse() {
        return rasse;
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

    public boolean getToetlich() {return toetlich;}

    public void setPreis(double preisNew) {
        preis = preisNew;
    }

    public void haeuten() {
        System.out.println(name+" hat sich gehäutet");

    }

    public String getTyp(){return "Schlange";}

    // end methods
}
