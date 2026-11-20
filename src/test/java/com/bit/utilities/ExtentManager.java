 package com.bit.utilities;





import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.Date;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ExtentManager {

    private static ExtentReports extent;
    private static final Object LOCK = new Object();
    public static final ExtentReports extentReports = new ExtentReports();

    private ExtentManager() {
        // Prevent object creation
    }

    /**
     * Returns the singleton ExtentReports instance.
     */
    public static ExtentReports getInstance() {

        if (extent == null) {
            synchronized (LOCK) {
                if (extent == null) {
                    extent = createInstance();
                }
            }
        }

        return extent;
    }

     public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter extent = new ExtentSparkReporter("./reports/extent-reports/extent-report.html");
        extent.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(extent);
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
//        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("Author", "Balaji");
        extentReports.setSystemInfo("Report Date", new Date().toString());
        return extentReports;
    }
//}
    /**
     * Creates and configures the ExtentReports instance.
     */
    private static ExtentReports createInstance() {

        String reportDirectory = System.getProperty("user.dir")
                + "/TestReport";

        String reportFileName = "ExtentReports.html";

        Path reportPath = Paths.get(reportDirectory);

        try {
            Files.createDirectories(reportPath);
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Unable to create report directory: " + reportDirectory,
                    e
            );
        }

        String reportFileLocation = reportPath
                .resolve(reportFileName)
                .toString();

        System.out.println("Operating System: "
                + System.getProperty("os.name"));

        System.out.println("Extent Report Location: "
                + reportFileLocation);

        ExtentSparkReporter htmlReporter =
                new ExtentSparkReporter(reportFileLocation);

        // Report configuration
        htmlReporter.config().setDocumentTitle("Automation Test Report");
        htmlReporter.config().setReportName("Sample Extent Report");
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setEncoding("UTF-8");
        htmlReporter.config().setTimelineEnabled(true);

        ExtentReports extentReports = new ExtentReports();

        extentReports.attachReporter(htmlReporter);

        // System information
        extentReports.setSystemInfo(
                "Operating System",
                System.getProperty("os.name")
        );

        extentReports.setSystemInfo(
                "OS Architecture",
                System.getProperty("os.arch")
        );

        extentReports.setSystemInfo(
                "Java Version",
                System.getProperty("java.version")
        );

        extentReports.setSystemInfo(
                "User Name",
                System.getProperty("user.name")
        );

        extentReports.setSystemInfo(
                "Author",
                "Balaji"
        );

        extentReports.setSystemInfo(
                "Report Generated",
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(
                                "dd-MM-yyyy HH:mm:ss"
                        )
                )
        );

        return extentReports;
    }

    /**
     * Flushes the report to the HTML file.
     */
    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}





// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// public class ExtentManager {

//     public static ExtentReports extent;

//     public static ExtentReports getInstance() {

//         if (extent == null) {

//             ExtentSparkReporter reporter =
//                 new ExtentSparkReporter(
//                     "test-output/ExtentReport.html");

//             reporter.config().setDocumentTitle(
//                 "Automation Test Report");

//             reporter.config().setReportName(
//                 "Selenium Automation Results");

//             extent = new ExtentReports();

//             extent.attachReporter(reporter);

//             extent.setSystemInfo("Automation Tool", "Selenium");
//             extent.setSystemInfo("Language", "Java");
//             extent.setSystemInfo("Framework", "TestNG");
//             extent.setSystemInfo("Browser", "Chrome");
//         }

//         return extent;
//     }
// }





// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.reporter.ExtentSparkReporter;
// import com.aventstack.extentreports.reporter.configuration.Protocol;
// import com.aventstack.extentreports.reporter.configuration.Theme;
// import org.openqa.selenium.Platform;

// import java.io.File;
// import java.util.Date;

// public class ExtentManager {
//     public static final ExtentReports extentReports = new ExtentReports();

//     public synchronized static ExtentReports createExtentReports() {
//         ExtentSparkReporter extent = new ExtentSparkReporter("./reports/extent-reports/extent-report.html");
//         extent.config().setReportName("Sample Extent Report");
//         extentReports.attachReporter(extent);
//         extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
//         extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
// //        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
//         extentReports.setSystemInfo("Author", "Balaji");
//         extentReports.setSystemInfo("Report Date", new Date().toString());
//         return extentReports;
//     }
// //}

// //public class ExtentManager {
//     public static ExtentReports extent;
//     private static Platform platform;
//     private static String reportFileName = "ExtentReports.html";
//     private static String macPath = System.getProperty("user.dir")+ "/TestReport";
//     private static String windowsPath = System.getProperty("user.dir")+ "\\TestReport";
//     private static String macReportFileLoc = macPath + "/" + reportFileName;
//     private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

//     private static String linuxPath = System.getProperty("user.dir")+ "/target";

//     private static String linuxReportFileLoc = linuxPath + "/" + reportFileName;

//     public static ExtentReports getInstance() {
//         if (extent == null)
//             createInstance();
//         return extent;
//     }
//     //Create an extent report instance
//     public static ExtentReports createInstance() {
//         platform = getCurrentPlatform();
//         System.out.println("Platform: "+platform);
//         String fileName = getReportFileLocation(platform);
//         ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
// //        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
// //        htmlReporter.config().setChartVisibilityOnOpen(true);
//         htmlReporter.config().setCss("css-string");
//         htmlReporter.config().setDocumentTitle("page title");
//         htmlReporter.config().setTimelineEnabled(true);
//         htmlReporter.config().setEncoding("utf-8");
//         htmlReporter.config().setJs("js-string");
//         htmlReporter.config().setProtocol(Protocol.HTTPS);
//         htmlReporter.config().setReportName("build name");
//         htmlReporter.config().setTheme(Theme.DARK);

// //        htmlReporter.Config.DocumentTitle = "page title";
// //        htmlReporter.Config.EnableTimeline = true;
// //        htmlReporter.Config.Encoding = "utf-8";
// //        htmlReporter.Config.JS = "js-string";
// //        htmlReporter.Config.Protocol = Protocol.HTTPS;
// //        htmlReporter.Config.ReportName = "build name";
// //        htmlReporter.Config.Theme = Theme.DARK;


//         htmlReporter.config().setTheme(Theme.STANDARD);
//         htmlReporter.config().setDocumentTitle(fileName);
//         htmlReporter.config().setEncoding("utf-8");
//         htmlReporter.config().setReportName(fileName);
//         extent = new ExtentReports();
//         extent.attachReporter(htmlReporter);
//         return extent;
//     }
//     //Select the extent report file location based on platform
//     private static String getReportFileLocation (Platform platform) {
//         String reportFileLocation = null;
//         switch (platform) {
//             case MAC:
//                 reportFileLocation = macReportFileLoc;
//                 createReportPath(macPath);
//                 System.out.println("ExtentReport Path for MAC: " + macPath + "\n");
//                 break;
//             case WINDOWS:
//                 reportFileLocation = winReportFileLoc;
//                 createReportPath(windowsPath);
//                 System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
//                 break;
//             case LINUX:
//                 reportFileLocation = linuxReportFileLoc;
//                 createReportPath(linuxPath);
//                 System.out.println("ExtentReport Path for LINUX: " + linuxPath + "\n");
//                 break;
//             default:
//                 System.out.println("ExtentReport path has not been set! There is a problem!\n");
//                 break;
//         }
//         return reportFileLocation;
//     }
//     //Create the report path if it does not exist
//     private static void createReportPath (String path) {
//         File testDirectory = new File(path);
//         if (!testDirectory.exists()) {
//             if (testDirectory.mkdir()) {
//                 System.out.println("Directory: " + path + " is created!" );
//             } else {
//                 System.out.println("Failed to create directory: " + path);
//             }
//         } else {
//             System.out.println("Directory already exists: " + path);
//         }
//     }
//     //Get current platform
//     private static Platform getCurrentPlatform () {
//         if (platform == null) {
//             String operSys = System.getProperty("os.name").toLowerCase();
//             if (operSys.contains("win")) {
//                 platform = Platform.WINDOWS;
//             } else if (operSys.contains("nix") || operSys.contains("nux")
//                     || operSys.contains("aix")) {
//                 platform = Platform.LINUX;
//             } else if (operSys.contains("mac")) {
//                 platform = Platform.MAC;
//             }
//         }
//         return platform;
//     }
// }


