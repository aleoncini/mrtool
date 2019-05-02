package it.redhat.mrtool.core.io;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class ReportDirectory {

    private static final Logger logger = LoggerFactory.getLogger("it.redhat.mrtool");
    private String DEFAULT_WORKING_DIRECTORY = "/etc/mrtool";
    private File workingDirectory;

    public ReportDirectory(){
        // first try to get system wide system variable
        // set this variable when launching the system
        // using: java -Dmrtool.work.dir=<your.report.dir> -jar mrtool-thorntail.jar

        String dirName = System.getenv("mrtool.work.dir");
        if ( dirName == null ) {
            // variable not set, let's switch on default dir
            dirName = DEFAULT_WORKING_DIRECTORY;
        }

        if (! checkWorkingDirectory(dirName)){
            // we are unable to use the specified directory (or default dir),
            // let's switch on "<user.home>/.mrtool" directory
            dirName = System.getProperty("user.home") + "/.mrtool";
        }

        if (checkWorkingDirectory(dirName)){
            workingDirectory = new File(dirName);
        }

        if (workingDirectory != null){
            // if working dir is available we check that two subdirs exists:
            // resources (this dir contains files like the banner image),
            // and reports (the dir that actually contains reports generated)
            logger.info("[ReportDirectory] working dir = " + workingDirectory.getAbsolutePath());
            File resources = new File(workingDirectory.getAbsolutePath() + "/resources");
            resources.mkdir();
            File reports = new File(workingDirectory.getAbsolutePath() + "/reports");
            reports.mkdir();
        }
    }

    private boolean checkWorkingDirectory(String dirName) {
        logger.info("[ReportDirectory] checking " + dirName);
        boolean created = false;
        File dir = new File(dirName);
        if (! dir.exists()){
            dir.mkdirs();
        }
        if (Files.isWritable(Paths.get(dirName))){
            created = true;
        }
        return created;
    }

    public File getWorkingDirectory(){
        return workingDirectory;
    }

    public Path getWorkingDirectoryPath(){
        return (workingDirectory == null) ? null : Paths.get(workingDirectory.getAbsolutePath());
    }

    public Path getReportsDirectoryPath(){
        return (workingDirectory == null) ? null : Paths.get(workingDirectory.getAbsolutePath() + "/reports");
    }

    public Path getResourcesDirectoryPath(){
        return (workingDirectory == null) ? null : Paths.get(workingDirectory.getAbsolutePath() + "/resources");
    }

    public File getLatestReport(String associateId){
        if (workingDirectory == null){
            return null;
        }
        File reportsDir = new File(workingDirectory, "reports");
        FileFilter fileFilter = new WildcardFileFilter(associateId + "*.pdf", IOCase.INSENSITIVE);
        File[] listOfFiles = reportsDir.listFiles(fileFilter);
        int year = 0;
        int month = 0;
        for (File file : listOfFiles) {
            StringTokenizer st = new StringTokenizer(file.getName(), ".");
            if (! st.hasMoreElements()){
                return null;
            }
            StringTokenizer tokenizer = new StringTokenizer(st.nextToken(), "_");
            if (! tokenizer.hasMoreElements()){
                return null;
            }
            tokenizer.nextToken(); //we can skip this, it is the associateId
            int y = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());
            if (y > year){
                year = y;
            }
            if (m > month){
                month = m;
            }
        }
        String latestName = associateId + "_" + year + "_" + month + ".pdf";
        return new File(reportsDir, latestName);
    }

}
