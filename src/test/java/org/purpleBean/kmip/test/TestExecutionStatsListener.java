package org.purpleBean.kmip.test;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Collects execution statistics for all tests.
 */
public class TestExecutionStatsListener implements TestExecutionListener {
    private final Map<String, TestStats> testStats = new ConcurrentHashMap<>();
    private final Map<String, Long> startTimes = new ConcurrentHashMap<>();

    @Override
    public void testPlanExecutionStarted(TestPlan testPlan) {
        // Initialize test plan
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            startTimes.put(testIdentifier.getUniqueId(), System.currentTimeMillis());
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        if (testIdentifier.isTest()) {
            long duration = System.currentTimeMillis() - startTimes.get(testIdentifier.getUniqueId());
            testStats.computeIfAbsent(testIdentifier.getDisplayName(), k -> new TestStats())
                   .recordExecution(duration, testExecutionResult.getStatus());
        }
    }

    public Map<String, TestStats> getTestStats() {
        return testStats;
    }

    public static class TestStats {
        private final AtomicInteger executionCount = new AtomicInteger();
        private final AtomicInteger successCount = new AtomicInteger();
        private final AtomicInteger failureCount = new AtomicInteger();
        private final AtomicInteger abortedCount = new AtomicInteger();
        private final AtomicLong totalDuration = new AtomicLong();
        private volatile long minDuration = Long.MAX_VALUE;
        private volatile long maxDuration = 0;
        
        public int getExecutionCount() { return executionCount.get(); }
        public int getSuccessCount() { return successCount.get(); }
        public int getFailureCount() { return failureCount.get(); }
        public int getAbortedCount() { return abortedCount.get(); }
        public long getTotalDuration() { return totalDuration.get(); }
        public long getMinDuration() { return minDuration; }
        public long getMaxDuration() { return maxDuration; }

        public void recordExecution(long duration, TestExecutionResult.Status status) {
            executionCount.incrementAndGet();
            totalDuration.addAndGet(duration);
            minDuration = Math.min(minDuration, duration);
            maxDuration = Math.max(maxDuration, duration);

            switch (status) {
                case SUCCESSFUL -> successCount.incrementAndGet();
                case FAILED -> failureCount.incrementAndGet();
                case ABORTED -> abortedCount.incrementAndGet();
            }
        }

        public double getAverageDuration() {
            return executionCount.get() > 0 ? (double) totalDuration.get() / executionCount.get() : 0;
        }

        @Override
        public String toString() {
            return String.format("Runs: %d, Success: %d, Fail: %d, Aborted: %d, " +
                              "Avg: %.2fms, Min: %dms, Max: %dms",
                    executionCount.get(), successCount.get(), failureCount.get(), 
                    abortedCount.get(), getAverageDuration(), minDuration, maxDuration);
        }
    }
}
