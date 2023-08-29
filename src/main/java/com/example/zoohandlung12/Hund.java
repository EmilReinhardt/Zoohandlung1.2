package com.example.zoohandlung12;

public class Hund extends Tier {
    // start attributes
    private String rasse;
    // end attributes

    public Hund(){
        super();
    }

    public Hund(String name, int alter, double preis,String rasse) {
        super(name, alter, preis);
        this.rasse = rasse;
    }

    // start methods
    public String getRasse() {
        return rasse;
    }

    public void setRasse(String rasseNew) {
        rasse = rasseNew;
    }

    public void platz() {
        System.out.println(name+" hat platz gemacht");

    }

    public String getTyp(){return "Hund";}
    // end methods
}
