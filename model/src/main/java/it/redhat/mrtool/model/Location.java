package it.redhat.mrtool.model;

public class Location {
    private String destination;
    private String purpose;
    private int distance;
    private String associateUid;

    public String getDestination() {
        return destination;
    }

    public Location setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public String getPurpose() {
        return purpose;
    }

    public Location setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }

    public int getDistance() {
        return distance;
    }

    public Location setDistance(int distance) {
        this.distance = distance;
        return this;
    }

    public String getAssociateUid() {
        return associateUid;
    }

    public Location setAssociateUid(String uid) {
        this.associateUid = uid;
        return this;
    }

}
