package com.example.zoohandlung12;

import com.example.zoohandlung12.Hund;
import com.example.zoohandlung12.Katze;

public class Zoohandlung {
    // start attributes
    private double oeffnung;
    private double schliessung;
    private boolean offen = false;
    private double geld;
    // end attributes





    public Zoohandlung(){

    }

    public Zoohandlung(double oeffnung,double schliessung){
        this.oeffnung = oeffnung;
        this.schliessung = schliessung;
    }

    public void auf(){
        offen = true;
        System.out.println("com.example.zoohandlung12.Zoohandlung ist jetzt offen");
    }

    public void zu(){
        offen = false;
        System.out.println("com.example.zoohandlung12.Zoohandlung ist jetzt zu");
    }

    public double getOeffnung() {
        return oeffnung;
    }

    public void setOeffnung(double oeffnungNew) {
        oeffnung = oeffnungNew;
    }

    public void setSchliessung(double schliessungNew) {
        schliessung = schliessungNew;
    }

    public double getSchliessung() {
        return schliessung;
    }

    public boolean getOffen() {
        return offen;
    }

    public void setOffen(boolean offenNew) {
        offen = offenNew;
    }

    public void setGeld(double geld){
        this.geld = geld;
    }

    public double getGeld(){
        return geld;
    }
    // end methods
}
