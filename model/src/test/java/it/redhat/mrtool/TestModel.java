package it.redhat.mrtool;

import it.redhat.mrtool.model.Associate;
import it.redhat.mrtool.model.Car;
import org.junit.Assert;
import org.junit.Test;

public class TestModel {

    private Associate associate = new Associate()
            .setName("Leo")
            .setCostCenter("420")
            .setEmail("aleoncin@redhat.com")
            .setRedhatId("9053");
    private Car car = new Car()
            .setRegistryNumber("FB214ZM")
            .setMileageRate(0.89f)
            .setAssociateUid(associate.getUuid());

    @Test
    public void testAssociate(){
        Assert.assertNotNull(associate);
    }
    @Test
    public void testCar(){
        Assert.assertNotNull(car);
    }
}
