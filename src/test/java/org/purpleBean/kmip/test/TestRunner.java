package org.purpleBean.kmip.test;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

/**
 * Test runner that executes tests and generates reports.
 */
public class TestRunner {
    
    public static void main(String[] args) throws IOException {
        // Create a test execution listener to collect statistics
        TestExecutionStatsListener statsListener = new TestExecutionStatsListener();
        
        // Configure the test discovery request
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectPackage("org.purpleBean.kmip"))
                .build();
        
        // Create a launcher and register the listener
        Launcher launcher = LauncherFactory.create();
        launcher.registerTestExecutionListeners(statsListener);
        
        // Execute tests
        launcher.execute(request);
        
        // Generate and save the test report
        generateTestReport(statsListener);
    }
    
    private static void generateTestReport(TestExecutionStatsListener statsListener) throws IOException {
        // Create reports directory if it doesn't exist
        File reportsDir = new File("target/reports");
        if (!reportsDir.exists()) {
            reportsDir.mkdirs();
        }
        
        // Generate report filename with timestamp
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        String reportPath = "target/reports/test-report-" + timestamp + ".md";
        
        // Generate the report
        TestReportGenerator reportGenerator = new TestReportGenerator(statsListener, reportPath);
        reportGenerator.generateReport();
        
        System.out.println("Test report generated: " + new File(reportPath).getAbsolutePath());
    }
}
