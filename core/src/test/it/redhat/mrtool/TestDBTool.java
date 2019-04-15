package it.redhat.mrtool;

import it.redhat.mrtool.core.helpers.AssociateHelper;
import it.redhat.mrtool.model.Associate;
import org.junit.Assert;
import org.junit.Test;

public class TestDBTool {

    @Test
    public void testInsert(){
        Associate associate = new Associate()
                .setName("Andrea Leoncini")
                .setRedhatId("9999")
                .setCostCenter("420")
                .setEmail("aleoncini@redhat.com");
        AssociateHelper helper = new AssociateHelper();
        int result = helper.insert(associate);
        Assert.assertTrue(result >= 0);

        associate = helper.getByRedHatID("9999");
        Assert.assertTrue(associate.getRedhatId().equals("9999"));

        result = new AssociateHelper().deleteByRedHatID("9999");
        Assert.assertTrue(result > 0);
    }

}
