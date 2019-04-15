package it.redhat.mrtool;

import it.redhat.mrtool.core.helpers.PageFormatter;
import it.redhat.mrtool.core.pdf.Report;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class TestReport {

    @Test
    public void testTemplateExists(){
        Assert.assertTrue(new PageFormatter().checkTemplate());
    }

    @Test
    public void testMakeReport(){
        File file = new Report("pippo", 2019, 3, "/tmp/leo.pdf").make(false).getReportFile();
        Assert.assertTrue(file.exists());
    }
}
