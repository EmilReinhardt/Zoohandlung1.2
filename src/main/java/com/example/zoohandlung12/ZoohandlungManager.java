package com.example.zoohandlung12;


import java.util.ArrayList;
import java.util.Arrays;

public class ZoohandlungManager {
    private Zoohandlung zoo = new Zoohandlung();



    private static Tier[] tiere = new Tier[6];
    private Pfleger[] pfleger = new Pfleger[1];

    public ZoohandlungManager(){


        tiere[0] = new Katze("Kiki",7,200,"Waldkatze");
        tiere[1] = new Katze("Coco",12,100,"Hauskatze");
        tiere[2] = new Hund("Keks",6,1000,"Australian Shepard");
        tiere[3] = new Hund("Kalle",11,1200,"Golden Retriever");
        tiere[4] = new Schlange("Fred",5,100,"Königs Python",12,false);
        tiere[5] = new Schlange("Klaus",5,100,"Klapperschlange",12,true);

        pfleger[0] = new Pfleger("Hans",true);


    }

    public static Tier[] getTiere() {
        return tiere.clone();
    }

    public static void addTiere(Tier tier){
        tiere = Arrays.copyOf(tiere, tiere.length+1);
        tiere[tiere.length-1] = tier;
    }

    public static void removeTier(Tier tier){
        Tier[] tiereNeu = new Tier[tiere.length-1];
        int zaehler = 6;
        for(Tier tier2 : tiere){
            if(!tier2.equals(tier)){
                tiereNeu[zaehler] = tier2;
                zaehler++;
            }
        }
    }
}
