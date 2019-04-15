package it.redhat.mrtool;

import it.redhat.mrtool.core.pdf.Report;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class TestReport {
    @Test
    public void testNotNull(){
        Report report = new Report("leo", 2019, 3, "/tmp/report.pdf");
        report.makeTemplate();
        File reportFile = report.getReportFile();
        Assert.assertNotNull(reportFile);
    }
}
