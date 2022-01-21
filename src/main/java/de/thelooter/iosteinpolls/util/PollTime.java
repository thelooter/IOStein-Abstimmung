package de.thelooter.iosteinpolls.util;

public enum PollTime {

    TEN_MINUTES(12000,"10 Minuten"), //1
    FIVETEEN_MINUTES(18000,"15 Minuten"), //2
    THIRTY_MINUTES(36000,"30 Minuten"), //3
    FOURTYFIVE_MINUTES(54000,"45 Minuten"), //4
    ONE_HOUR(72000,"1 Stunde"), //5
    ONE_HOUR_THIRTY_MINUTES(108000,"1 Stunde 30 Minuten"), //6
    TWO_HOURS(142000,"2 Stunden"); //7


    public final int ticks;
    public final String loreString;

    PollTime(int ticks, String loreString) {
        this.ticks = ticks;
        this.loreString = loreString;
    }

    public int getTicks() {
        return ticks;
    }

    public String getLoreString() {
        return loreString;
    }
}
