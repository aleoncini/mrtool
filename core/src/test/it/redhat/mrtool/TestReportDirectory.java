package it.redhat.mrtool;

import it.redhat.mrtool.core.io.ReportDirectory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Files;

public class TestReportDirectory {

    @BeforeClass
    public static void prepare(){
    }

    @Test
    public void testWDIsAvailable(){
        Assert.assertNotNull(new ReportDirectory().getWorkingDirectory());
    }

    @Test
    public void testWDIsWritable(){
        Assert.assertTrue(Files.isWritable(new ReportDirectory().getWorkingDirectoryPath()));
    }

}
