package it.redhat.mrtool;

import it.redhat.mrtool.core.pdf.Report;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class TestReport {

    @Test
    public void testMakeReport(){
        File file = new Report("9053", 2019, 1).make().getReportFile();
        Assert.assertTrue(file.exists());
    }
}
