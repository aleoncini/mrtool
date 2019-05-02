package it.redhat.mrtool;

import it.redhat.mrtool.core.persistence.AssociateHelper;
import it.redhat.mrtool.model.Associate;
import it.redhat.mrtool.model.Car;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDBTool {

    private final static String redHatId = "9999";

    @BeforeClass
    public static void prepare(){
        Car car = new Car()
                .setRegistryNumber("FB214ZM")
                .setMileageRate(0.89);
        Associate associate = new Associate()
                .setName("Andrea Leoncini")
                .setRedhatId(TestDBTool.redHatId)
                .setCostCenter("420")
                .setEmail("aleoncini@redhat.com")
                .setCar(car);
        new AssociateHelper().insertOrUpdate(associate);
    }

    @Test
    public void testInserted(){
        AssociateHelper helper = new AssociateHelper();

        Associate asso = helper.get(TestDBTool.redHatId);
        Assert.assertTrue(asso.getRedhatId().equals(TestDBTool.redHatId));
    }

    @Test
    public void testDelete(){
        AssociateHelper helper = new AssociateHelper();

        long result = new AssociateHelper().deleteByRedHatID(TestDBTool.redHatId);
        Assert.assertTrue(result > 0);
    }

}
