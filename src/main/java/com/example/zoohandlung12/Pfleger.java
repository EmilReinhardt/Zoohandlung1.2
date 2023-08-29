package com.example.zoohandlung12;

public class Pfleger {

    // start attributes
    private String name;
    private boolean maennlich;
    private Tier aktTier;
    // end attributes
    public Pfleger (String name, boolean maennlich){
        this.name = name;
        this.maennlich = maennlich;
    }

    // start methods
    public String getName() {
        return name;
    }

    public boolean getMaennlich() {
        return maennlich;
    }

    public void fuettern() {
        System.out.println(aktTier.name + " wurde von "+name+" gefüttert");

    }

    public Tier getAktTier() {
        return aktTier;
    }

    public void setAktTier(Tier aktTierNew) {
        aktTier = aktTierNew;
    }

    // end methods
}
