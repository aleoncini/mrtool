package it.redhat.mrtool.model;

import java.util.UUID;

public class Report {

    private String uuid;
    private String associateUid;
    private int year;
    private int month;

    public Report(){
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public Report setUid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getAssociateUid() {
        return associateUid;
    }

    public Report setAssociateUid(String associateUid) {
        this.associateUid = associateUid;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Report setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public Report setMonth(int month) {
        this.month = month;
        return this;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("{ ");
        buffer.append("\"uuid\": \"").append(uuid).append("\"");

        if (associateUid != null){
            buffer.append(", \"associateUid\": \"").append(associateUid).append("\"");
        }
        buffer.append(", \"year\": ").append(this.year);
        buffer.append(", \"month\": ").append(this.month);
        buffer.append(" }");
        return buffer.toString();
    }
}