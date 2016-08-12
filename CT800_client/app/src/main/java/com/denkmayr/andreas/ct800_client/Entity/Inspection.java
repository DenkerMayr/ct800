package com.denkmayr.andreas.ct800_client.Entity;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Inspection {
    private GregorianCalendar date;
    private Farmer farmer;
    private List<Cow> cows;

    public Inspection() {
        date = new GregorianCalendar();
        date.setTimeInMillis(System.currentTimeMillis());
    }

    public String getDateString() {
        return date.get(Calendar.DAY_OF_MONTH)
                + "-" + (date.get(Calendar.MONTH)+1) //Month starts with 0???
                + "-" + date.get(Calendar.YEAR);
    }

    public void setDateString(String dateString) {
        date = new GregorianCalendar();
        String[] dateValues = dateString.split("-");
        date.set(Integer.parseInt(dateValues[2]),Integer.parseInt(dateValues[1])-1,Integer.parseInt(dateValues[0]));
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public List<Cow> getCows() {
        return cows;
    }

    public void setCows(List<Cow> cows) {
        this.cows = cows;
    }
}
