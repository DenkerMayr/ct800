package com.denkmayr.andreas.ct800_client.Entity;

public class Cow {

    private String eartag;
    private String name;
    private Farmer farmer;

    public Cow() {
    }

    public Cow(String eartag) {
        this.eartag = eartag;
    }

    public Cow(String eartag, String name) {
        this(eartag);
        this.name = name;
    }

    public Cow(String eartag, String name, Farmer farmer) {
        this(eartag,name);
        this.farmer = farmer;
    }

    public String getEartag() {
        return eartag;
    }

    public void setEartag(String eartag) {
        this.eartag = eartag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
}
