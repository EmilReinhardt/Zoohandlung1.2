package com.example.zoohandlung12;

public class Katze extends Tier {
    private String rasse;

    public Katze(){
        super();
    }

    public Katze(String name, int alter, double preis,String rasse) {
        super(name, alter, preis);
        this.rasse = rasse;
    }

    public String getRasse() {
        return rasse;
    }

    public void setRasse(String rasseNew) {
        rasse = rasseNew;
    }

    public String getTyp(){return "Katze";}

}
