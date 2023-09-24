package com.example.zoohandlung12;


import java.util.Arrays;

public class ZoohandlungManager {
    private Zoohandlung zoo = new Zoohandlung();
    private static int sortierArt = 1;//0 = normal, 1 = alter, 2 = preis, 3 = name
    private static int suchArt = 0;//0 = alle, 1 = name, 2 = alter, 3 = preis, 4 = rasse
    private static Tier[] tiere = new Tier[6];
    private static Tier[] displayTiere;
    private static Pfleger[] pfleger = new Pfleger[3];
    private static Pfleger[] displayPfleger = pfleger.clone();
    private static char[] alphabet = "aäbcdefghijklmnoöpqrstuüvwxyz".toCharArray();

    public ZoohandlungManager(){


        tiere[0] = new Katze("Kiki",7,200,"Waldkatze");
        tiere[1] = new Katze("Coco",12,100,"Hauskatze");
        tiere[2] = new Hund("Keks",6,1000,"Australian Shepard");
        tiere[3] = new Hund("Kalle",11,1200,"Golden Retriever");
        tiere[4] = new Schlange("Fred",5,100,"Königs Python",12,false);
        tiere[5] = new Schlange("Klaus",5,100,"Klapperschlange",12,true);
        displayTiere = tiere.clone();

        pfleger[0] = new Pfleger("Hans",50,15,true);
        pfleger[1] = new Pfleger("Kristoph",40,13,true);
        pfleger[2] = new Pfleger("Heike",43,20,false);
        displayPfleger = pfleger.clone();


    }

    public static Tier[] getTiere() {
        return tiere.clone();
    }
    public static Tier[] getDisplayTiere() {
        return displayTiere.clone();
    }
    public static int getSortierArt() {return sortierArt;}
    public static void setSortierArt(int sortierArt) {ZoohandlungManager.sortierArt = sortierArt;}
    public static Pfleger[] getDiplayPfleger(){return displayPfleger.clone();}
    public static int getSuchArt() {return suchArt;}
    public static void setSuchArt(int suchArt) {ZoohandlungManager.suchArt = suchArt;}

    public static void addTiere(Tier tier){
        tiere = Arrays.copyOf(tiere, tiere.length+1);
        tiere[tiere.length-1] = tier;
        displayTiere = tiere.clone();
    }
    public static void addPfleger(Pfleger neuerPfleger){
        pfleger = Arrays.copyOf(pfleger, pfleger.length+1);
        pfleger[pfleger.length-1] = neuerPfleger;
        displayPfleger = pfleger.clone();
    }

    public static void removeTier(Tier tier){
        Tier[] tiereNeu = new Tier[tiere.length-1];
        int zaehler = 0;
        for(Tier tier2 : tiere){
            if(!tier2.equals(tier)) {
                tiereNeu[zaehler] = tier2;
                zaehler++;
            }
        }
        tiere = tiereNeu;
        displayTiere = tiere.clone();
    }

    public static void removePfleger(Pfleger pflegerweg){
        Pfleger[] pflegerNeu = new Pfleger[pfleger.length-1];
        int zaehler = 0;
        for(Pfleger pfleger2 : pfleger){
            if(!pfleger2.equals(pflegerweg)) {
                pflegerNeu[zaehler] = pfleger2;
                zaehler++;
            }
        }
        pfleger = pflegerNeu;
        displayPfleger = pfleger.clone();
    }

    public static void sortAlter(){
        sortOriginal();
        for(int i = 0;i<displayTiere.length;i++){
            int youngest = i;
            for(int j = i; j < displayTiere.length-1;j++) {
                if (displayTiere[youngest].getAlter() > displayTiere[j + 1].getAlter()) {
                    youngest = j + 1;
                }
            }
            tausche(i,youngest);
        }
    }

    public static void sortPreis(){
        sortOriginal();
        for(int i = 0;i<displayTiere.length;i++){
            int cheapest = i;
            for(int j = i; j < displayTiere.length-1;j++) {
                if (displayTiere[cheapest].getPreis() > displayTiere[j + 1].getPreis()) {
                    cheapest = j + 1;
                }
            }
            tausche(i,cheapest);
        }
    }

    public static void sortName(){
        sortOriginal();
        for(int i = 0;i<displayTiere.length;i++){
            int first = i;
            for(int j = i; j < displayTiere.length-1;j++) {
                if (findeAlphabet(displayTiere[first].getName().toLowerCase().charAt(0)) > findeAlphabet(tiere[j+1].getName().toLowerCase().charAt(0))) {
                    first = j + 1;
                }
            }
            tausche(i,first);
        }
    }

    public static void sortOriginal(){
        displayTiere = tiere.clone();
    }//sortiert wie anfangs

    public static void suchen(String x){//sucht abhängig von Suchart
        Tier[] sucheTiere = new Tier[0];
        if(suchArt==0){
            for(int i = 0; i < tiere.length; i++) {
                if (Integer.toString(tiere[i].getAlter()).equals(x)) {
                    sucheTiere = Arrays.copyOf(sucheTiere, sucheTiere.length + 1);
                    sucheTiere[sucheTiere.length - 1] = tiere[i];
                } else if (Integer.toString((int) tiere[i].getPreis()).equals(x)) {
                    sucheTiere = Arrays.copyOf(sucheTiere, sucheTiere.length + 1);
                    sucheTiere[sucheTiere.length - 1] = tiere[i];
                } else if (tiere[i].getName().toLowerCase().contains(x.toLowerCase())) {
                    sucheTiere = Arrays.copyOf(sucheTiere, sucheTiere.length + 1);
                    sucheTiere[sucheTiere.length - 1] = tiere[i];
                } else if (tiere[i].getRasse().toLowerCase().contains(x.toLowerCase())) {
                    sucheTiere = Arrays.copyOf(sucheTiere, sucheTiere.length + 1);
                    sucheTiere[sucheTiere.length - 1] = tiere[i];
                } else if (tiere[i].getTyp().toLowerCase().contains(x.toLowerCase())) {
                    sucheTiere = Arrays.copyOf(sucheTiere, sucheTiere.length + 1);
                    sucheTiere[sucheTiere.length - 1] = tiere[i];
                }
            }
        }else if(suchArt == 1){
            for(int i = 0; i < tiere.length; i++) {
                if (tiere[i].getName().toLowerCase().contains(x.toLowerCase())) {
                    sucheTiere = Arrays.copyOf(sucheTiere, sucheTiere.length + 1);
                    sucheTiere[sucheTiere.length - 1] = tiere[i];
                }
            }
        }else if(suchArt == 2){
            try {
                sortAlter();
                int u = 0;
                int o = displayTiere.length - 1;
                int m;
                int gefunden = -1;
                // wiederhole solange u >= o
                for (int i = 0; o >= u; i++) {
                    m = (o + u) / 2;
                    if (displayTiere[m].getAlter() == Integer.parseInt(x)) {
                        gefunden = m;
                        break;
                    } else if (displayTiere[m].getAlter() < Integer.parseInt(x)) {
                        u = m + 1;
                    } else if (displayTiere[m].getAlter() > Integer.parseInt(x)) {
                        o = m - 1;
                    }
                }
                if (gefunden != -1) {
                    sucheTiere = new Tier[1];
                    sucheTiere[0] = displayTiere[gefunden];

                }
            }catch(NumberFormatException e){}

        }else if(suchArt == 3){
            try {
                sortPreis();
                int u = 0;
                int o = displayTiere.length - 1;
                int m;
                int gefunden = -1;
                for (int i = 0; o >= u; i++) {
                    m = (o + u) / 2;
                    if (displayTiere[m].getPreis() == Integer.parseInt(x)) {
                        gefunden = m;
                        break;
                    } else if (displayTiere[m].getPreis() < Integer.parseInt(x)) {
                        u = m + 1;
                    } else if (displayTiere[m].getPreis() > Integer.parseInt(x)) {
                        o = m - 1;
                    }
                }
                if (gefunden != -1) {
                    sucheTiere = new Tier[1];
                    sucheTiere[0] = displayTiere[gefunden];

                }
            }catch(NumberFormatException e){}

        }else if(suchArt == 4){
            for(int i = 0; i < tiere.length; i++) {
                if (tiere[i].getRasse().toLowerCase().contains(x.toLowerCase())) {
                    sucheTiere = Arrays.copyOf(sucheTiere, sucheTiere.length + 1);
                    sucheTiere[sucheTiere.length - 1] = tiere[i];
                }
            }
        }
        displayTiere = sucheTiere.clone();
    }

    public static Tier getDisplayTier(int x){
        return displayTiere[x];
    }
    public static Pfleger getDisplayPflegerIn(int x){
        return displayPfleger[x];
    }
    private static void tausche(int i, int j){
        Tier zwischen = displayTiere[i];
        displayTiere[i] = displayTiere[j];
        displayTiere[j] = zwischen;
    }

    private static int findeAlphabet(char a){//dindet index von char in alphabet
        for(int i = 0; i < alphabet.length;i++){
            if(a==alphabet[i]) {
                return i;
            }
        }
        return -1;
    }

}
