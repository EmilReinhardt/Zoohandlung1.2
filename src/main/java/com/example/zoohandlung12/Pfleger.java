package com.example.zoohandlung12;

public class Pfleger {

    // start attributes
    private String name;
    private int alter;
    private double gehalt;
    private boolean maennlich;
    private Tier aktTier;
    // end attributes
    public Pfleger (String name, int alter, double gehalt, boolean maennlich){
        this.name = name;
        this.alter = alter;
        this.gehalt = gehalt;
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
    public double getGehalt(){return gehalt;}
    public int getAlter(){return alter;}
    public void setAktTier(Tier aktTierNew) {
        aktTier = aktTierNew;
    }

    // end methods
}
