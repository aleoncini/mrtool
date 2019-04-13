package it.redhat.mrtool.model;

import java.util.UUID;

public class Associate {
    private String uuid;
    private String name;
    private String email;
    private String costCenter;
    private String redhatId;

    public Associate(){
        this.uuid = UUID.randomUUID().toString();
    }

    public String getUuid() {
        return uuid;
    }

    public Associate setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getName() {
        return name;
    }

    public Associate setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Associate setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public Associate setCostCenter(String costCenter) {
        this.costCenter = costCenter;
        return this;
    }

    public String getRedhatId() {
        return redhatId;
    }

    public Associate setRedhatId(String id) {
        this.redhatId = id;
        return this;
    }

}
