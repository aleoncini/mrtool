package it.redhat.mrtool.model;

public class Trip {
    private String uid;
    private String associateUid;
    private DateOfTrip date;
    private Location location;

    public String getUid() {
        return uid;
    }

    public Trip setUid(String uid) {
        this.uid = uid;
        return this;
    }

    public String getAssociateUid() {
        return associateUid;
    }

    public Trip setAssociateUid(String uid) {
        this.associateUid = uid;
        return this;
    }

    public DateOfTrip getDate() {
        return date;
    }

    public Trip setDate(DateOfTrip date) {
        this.date = date;
        return this;
    }

    public Location getLocation() {
        return location;
    }

    public Trip setLocation(Location location) {
        this.location = location;
        return this;
    }
}
