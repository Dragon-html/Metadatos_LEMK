package org.example;

public class Juego {

    private int ID_Game;
    private String Title;
    private String Developer;
    private String Publisher;
    private String Europe_PAL;
    private String Japan;
    private String North_America;


    public Juego(int ID_Game, String title, String developer, String publisher, String europe_PAL, String japan, String north_America) {
        this.ID_Game = ID_Game;
        Title = title;
        Developer = developer;
        Publisher = publisher;
        Europe_PAL = europe_PAL;
        Japan = japan;
        North_America = north_America;
    }

    public int getID_Game() {
        return ID_Game;
    }

    public void setID_Game(int ID_Game) {
        this.ID_Game = ID_Game;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDeveloper() {
        return Developer;
    }

    public void setDeveloper(String developer) {
        Developer = developer;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getEurope_PAL() {
        return Europe_PAL;
    }

    public void setEurope_PAL(String europe_PAL) {
        Europe_PAL = europe_PAL;
    }

    public String getJapan() {
        return Japan;
    }

    public void setJapan(String japan) {
        Japan = japan;
    }

    public String getNorth_America() {
        return North_America;
    }

    public void setNorth_America(String north_America) {
        North_America = north_America;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "ID_Game=" + ID_Game +
                ", Title='" + Title + '\'' +
                ", Developer='" + Developer + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Europe_PAL='" + Europe_PAL + '\'' +
                ", Japan='" + Japan + '\'' +
                ", North_America='" + North_America + '\'' +
                '}';
    }
}
