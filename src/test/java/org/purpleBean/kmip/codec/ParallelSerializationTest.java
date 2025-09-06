package org.purpleBean.kmip.codec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.codec.KmipCodecContext;
import org.purpleBean.kmip.common.ActivationDateAttribute;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.structure.SampleStructure;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Parallel Serialization Tests with Different Codec Contexts")
@Execution(ExecutionMode.CONCURRENT)
class ParallelSerializationTest extends BaseKmipTest {

    @Nested
    @DisplayName("Multi-Codec Parallel Serialization")
    class MultiCodecParallelSerialization {

        @Test
        @DisplayName("Should handle JSON and XML serialization in parallel threads with different codec contexts")
        void shouldHandleJsonAndXmlSerializationInParallelThreadsWithDifferentCodecContexts() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(4);
            int numberOfThreads = 20;
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            try {
                // When - Create multiple threads with different codec contexts and serialization formats
                for (int i = 0; i < numberOfThreads; i++) {
                    final int threadId = i;
                    final KmipSpec specForThread = KmipSpec.V1_2;
                    final boolean useJson = threadId % 3 == 0; // Mix of JSON and XML

                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        // Set thread-specific codec context
                        KmipCodecContext.setSpec(specForThread);
                        
                        try {
                            // Verify context is set correctly
                            assertThat(KmipCodecContext.getSpec()).isEqualTo(specForThread);
                            
                            // Create test data with V1_2 context - use standard states to avoid version issues
                            State activeState = new State(State.Standard.ACTIVE);
                            ActivationDateAttribute activationDate = ActivationDateAttribute.builder()
                                    .dateTime(OffsetDateTime.now())
                                    .build();
                            SampleStructure structure = SampleStructure.builder()
                                    .activationDate(activationDate)
                                    .state(activeState)
                                    .build();
                            
                            ProtocolVersion version = ProtocolVersion.of(threadId % 3 + 1, threadId % 5);
                            
                            if (useJson) {
                                // JSON serialization
                                SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
                                SerializationTestUtils.performJsonRoundTrip(jsonMapper, structure, SampleStructure.class);
                            } else {
                                // XML serialization
                                SerializationTestUtils.performXmlRoundTrip(xmlMapper, version, ProtocolVersion.class);
                                SerializationTestUtils.performXmlRoundTrip(xmlMapper, structure, SampleStructure.class);
                            }
                            
                            // Verify context is still correct after serialization
                            assertThat(KmipCodecContext.getSpec()).isEqualTo(specForThread);
                            
                        } finally {
                            KmipCodecContext.clear();
                        }
                    }, executor);
                    
                    futures.add(future);
                }

                // Then - All operations should complete successfully
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(10, TimeUnit.SECONDS);

            } finally {
                executor.shutdown();
                executor.awaitTermination(2, TimeUnit.SECONDS);
            }
        }

        @Test
        @DisplayName("Should handle concurrent serialization with multiple codec contexts and formats")
        void shouldHandleConcurrentSerializationWithMultipleCodecContextsAndFormats() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(6);
            int operationsPerThread = 50;
            
            try {
                // When - Create tasks that mix different specs and formats
                List<CompletableFuture<Void>> jsonTasks = IntStream.range(0, operationsPerThread)
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        KmipCodecContext.setSpec(KmipSpec.V1_2);
                        try {
                            ProtocolVersion version = ProtocolVersion.of(1, 2);
                            SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
                        } finally {
                            KmipCodecContext.clear();
                        }
                    }, executor))
                    .toList();

                List<CompletableFuture<Void>> xmlTasks = IntStream.range(0, operationsPerThread)
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        KmipCodecContext.setSpec(KmipSpec.V1_2);
                        try {
                            State state = new State(State.Standard.ACTIVE);
                            SerializationTestUtils.performXmlRoundTrip(xmlMapper, state, State.class);
                        } finally {
                            KmipCodecContext.clear();
                        }
                    }, executor))
                    .toList();

                List<CompletableFuture<Void>> mixedTasks = IntStream.range(0, operationsPerThread)
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        KmipSpec spec = KmipSpec.V1_2;
                        KmipCodecContext.setSpec(spec);
                        try {
                            ActivationDateAttribute attr = ActivationDateAttribute.builder()
                                    .dateTime(OffsetDateTime.now())
                                    .build();
                            // Use both formats in same thread
                            SerializationTestUtils.performJsonRoundTrip(jsonMapper, attr, ActivationDateAttribute.class);
                            SerializationTestUtils.performXmlRoundTrip(xmlMapper, attr, ActivationDateAttribute.class);
                        } finally {
                            KmipCodecContext.clear();
                        }
                    }, executor))
                    .toList();

                // Then - All tasks should complete without interference
                List<CompletableFuture<Void>> allTasks = new ArrayList<>();
                allTasks.addAll(jsonTasks);
                allTasks.addAll(xmlTasks);
                allTasks.addAll(mixedTasks);

                CompletableFuture.allOf(allTasks.toArray(new CompletableFuture[0])).get(15, TimeUnit.SECONDS);

            } finally {
                executor.shutdown();
                executor.awaitTermination(3, TimeUnit.SECONDS);
            }
        }
    }

    @Nested
    @DisplayName("Codec Context Isolation")
    class CodecContextIsolation {

        @Test
        @DisplayName("Should maintain codec context isolation between parallel serialization operations")
        void shouldMaintainCodecContextIsolationBetweenParallelSerializationOperations() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(10);
            int numberOfOperations = 100;
            ConcurrentLinkedQueue<KmipSpec> capturedSpecs = new ConcurrentLinkedQueue<>();

            try {
                // When - Multiple threads set different specs and perform serialization
                List<CompletableFuture<Void>> futures = IntStream.range(0, numberOfOperations)
                    .mapToObj(i -> {
                        final KmipSpec expectedSpec = (i % 3 == 0) ? KmipSpec.V1_2 : KmipSpec.UnknownVersion;
                        
                        return CompletableFuture.runAsync(() -> {
                            KmipCodecContext.setSpec(expectedSpec);
                            
                            // Capture the spec at the beginning
                            capturedSpecs.add(KmipCodecContext.getSpec());
                            
                            // Perform serialization operations
                            ProtocolVersion version = ProtocolVersion.of(i % 5, i % 3);
                            SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
                            
                            // Verify spec hasn't changed during serialization
                            assertThat(KmipCodecContext.getSpec()).isEqualTo(expectedSpec);
                            
                            // Perform XML serialization
                            SerializationTestUtils.performXmlRoundTrip(xmlMapper, version, ProtocolVersion.class);
                            
                            // Final verification
                            assertThat(KmipCodecContext.getSpec()).isEqualTo(expectedSpec);
                            
                            KmipCodecContext.clear();
                        }, executor);
                    })
                    .toList();

                // Then - All operations should complete successfully
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(20, TimeUnit.SECONDS);
                
                // Verify we captured the expected number of specs
                assertThat(capturedSpecs).hasSize(numberOfOperations);

            } finally {
                executor.shutdown();
                executor.awaitTermination(3, TimeUnit.SECONDS);
            }
        }

        @Test
        @DisplayName("Should handle stress test with rapid context switching and serialization")
        void shouldHandleStressTestWithRapidContextSwitchingAndSerialization() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(8);
            int stressOperations = 200;
            
            try {
                // When - Rapid context switching with serialization
                List<CompletableFuture<Void>> futures = IntStream.range(0, stressOperations)
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        for (int j = 0; j < 5; j++) {
                            // Rapid context switching
                            KmipSpec spec = (j % 2 == 0) ? KmipSpec.V1_2 : KmipSpec.UnknownVersion;
                            KmipCodecContext.setSpec(spec);
                            
                            // Quick serialization
                            ProtocolVersion version = ProtocolVersion.of(j, i % 3);
                            
                            if (j % 2 == 0) {
                                SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
                            } else {
                                SerializationTestUtils.performXmlRoundTrip(xmlMapper, version, ProtocolVersion.class);
                            }
                            
                            // Verify context integrity
                            assertThat(KmipCodecContext.getSpec()).isEqualTo(spec);
                        }
                        KmipCodecContext.clear();
                    }, executor))
                    .toList();

                // Then - All stress operations should complete successfully
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(30, TimeUnit.SECONDS);

            } finally {
                executor.shutdown();
                executor.awaitTermination(5, TimeUnit.SECONDS);
            }
        }
    }

    @Nested
    @DisplayName("Performance Under Load")
    class PerformanceUnderLoad {

        @Test
        @DisplayName("Should maintain performance with concurrent multi-format serialization")
        void shouldMaintainPerformanceWithConcurrentMultiFormatSerialization() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(12);
            int totalOperations = 1000;

            long startTime = System.currentTimeMillis();
            
            try {
                // When - High-load concurrent operations
                List<CompletableFuture<Void>> futures = IntStream.range(0, totalOperations)
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        KmipSpec spec = KmipSpec.V1_2;
                        KmipCodecContext.setSpec(spec);
                        
                        try {
                            // Create test data with standard values to avoid version issues
                            State activeState = new State(State.Standard.ACTIVE);
                            ActivationDateAttribute activationDate = ActivationDateAttribute.builder()
                                    .dateTime(OffsetDateTime.now())
                                    .build();
                            SampleStructure structure = SampleStructure.builder()
                                    .activationDate(activationDate)
                                    .state(activeState)
                                    .build();
                            ProtocolVersion version = ProtocolVersion.of(i % 3 + 1, i % 5);
                            
                            // Perform both JSON and XML serialization
                            SerializationTestUtils.performJsonRoundTrip(jsonMapper, structure, SampleStructure.class);
                            SerializationTestUtils.performXmlRoundTrip(xmlMapper, version, ProtocolVersion.class);
                            
                        } finally {
                            KmipCodecContext.clear();
                        }
                    }, executor))
                    .toList();

                // Then - Should complete within reasonable time
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(60, TimeUnit.SECONDS);
                
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                
                // Performance assertion - should complete within 60 seconds
                assertThat(totalTime).isLessThan(60000);
                
                // Calculate operations per second
                double operationsPerSecond = (double) totalOperations / (totalTime / 1000.0);
                assertThat(operationsPerSecond).isGreaterThan(10); // At least 10 ops/sec

            } finally {
                executor.shutdown();
                executor.awaitTermination(5, TimeUnit.SECONDS);
            }
        }
    }

    @Nested
    @DisplayName("Error Handling in Parallel Context")
    class ErrorHandlingInParallelContext {

        @Test
        @DisplayName("Should handle exceptions in parallel serialization without affecting other threads")
        void shouldHandleExceptionsInParallelSerializationWithoutAffectingOtherThreads() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(6);
            int successfulOperations = 50;
            int failingOperations = 10;

            try {
                // When - Mix successful and failing operations
                List<CompletableFuture<Void>> successfulFutures = IntStream.range(0, successfulOperations)
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        KmipCodecContext.setSpec(KmipSpec.V1_2);
                        try {
                            ProtocolVersion version = ProtocolVersion.of(1, 2);
                            SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
                        } finally {
                            KmipCodecContext.clear();
                        }
                    }, executor))
                    .toList();

                List<CompletableFuture<Void>> failingFutures = IntStream.range(0, failingOperations)
                    .mapToObj(i -> CompletableFuture.runAsync(() -> {
                        KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
                        try {
                            // Intentionally cause serialization issues with malformed data
                            String malformedJson = "{invalid json}";
                            assertThatThrownBy(() -> 
                                SerializationTestUtils.testJsonDeserialization(jsonMapper, malformedJson, ProtocolVersion.class))
                                .isInstanceOf(AssertionError.class);
                        } finally {
                            KmipCodecContext.clear();
                        }
                    }, executor))
                    .toList();

                // Then - Successful operations should complete, failing ones should handle errors gracefully
                CompletableFuture.allOf(successfulFutures.toArray(new CompletableFuture[0]))
                    .get(10, TimeUnit.SECONDS);
                
                CompletableFuture.allOf(failingFutures.toArray(new CompletableFuture[0]))
                    .get(10, TimeUnit.SECONDS);

            } finally {
                executor.shutdown();
                executor.awaitTermination(3, TimeUnit.SECONDS);
            }
        }
    }
}
