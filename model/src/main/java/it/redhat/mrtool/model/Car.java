package it.redhat.mrtool.model;

public class Car {
    private String registryNumber;
    private float mileageRate;
    private String associateUid;

    public String getRegistryNumber() {
        return registryNumber;
    }

    public Car setRegistryNumber(String registryNumber) {
        this.registryNumber = registryNumber;
        return this;
    }

    public float getMileageRate() {
        return mileageRate;
    }

    public Car setMileageRate(float mileageRate) {
        this.mileageRate = mileageRate;
        return this;
    }

    public String getAssociateUid() {
        return associateUid;
    }

    public Car setAssociateUid(String uid) {
        this.associateUid = uid;
        return this;
    }

}
