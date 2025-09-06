package org.purpleBean.kmip.codec;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

@DisplayName("KmipCodecContext Tests")
@Execution(ExecutionMode.SAME_THREAD) // Ensure thread safety tests run sequentially
class KmipCodecContextTest extends BaseKmipTest {

    @AfterEach
    void cleanupContext() {
        KmipCodecContext.clear();
    }

    @Nested
    @DisplayName("Basic Operations")
    class BasicOperations {

        @Test
        @DisplayName("Should have default V1.2 version")
        void shouldHaveDefaultV1_2Version() {
            // When
            KmipSpec spec = KmipCodecContext.getSpec();
            
            // Then
            assertThat(spec).isEqualTo(KmipSpec.V1_2);
        }

        @Test
        @DisplayName("Should set and get spec correctly")
        void shouldSetAndGetSpecCorrectly() {
            // Given
            KmipSpec expectedSpec = KmipSpec.V1_2;
            
            // When
            KmipCodecContext.setSpec(expectedSpec);
            KmipSpec actualSpec = KmipCodecContext.getSpec();
            
            // Then
            assertThat(actualSpec).isEqualTo(expectedSpec);
        }

        @Test
        @DisplayName("Should clear context to default")
        void shouldClearContextToDefault() {
            // Given
            KmipCodecContext.setSpec(KmipSpec.V1_2);
            
            // When
            KmipCodecContext.clear();
            KmipSpec spec = KmipCodecContext.getSpec();
            
            // Then
            assertThat(spec).isEqualTo(KmipSpec.UnknownVersion);
        }

        @Test
        @DisplayName("Should handle null spec gracefully")
        void shouldHandleNullSpecGracefully() {
            // When & Then
            assertThatCode(() -> KmipCodecContext.setSpec(null))
                .doesNotThrowAnyException();
            
            // The behavior with null depends on ThreadLocal implementation
            // but it should not crash
        }
    }

    @Nested
    @DisplayName("Thread Safety")
    class ThreadSafety {

        @Test
        @DisplayName("Should maintain thread-local isolation")
        void shouldMaintainThreadLocalIsolation() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(3);
            
            try {
                // When - Set different specs in different threads
                CompletableFuture<KmipSpec> thread1 = CompletableFuture.supplyAsync(() -> {
                    KmipCodecContext.setSpec(KmipSpec.V1_2);
                    return KmipCodecContext.getSpec();
                }, executor);
                
                CompletableFuture<KmipSpec> thread2 = CompletableFuture.supplyAsync(() -> {
                    KmipCodecContext.setSpec(KmipSpec.UnknownVersion);
                    return KmipCodecContext.getSpec();
                }, executor);
                
                CompletableFuture<KmipSpec> mainThread = CompletableFuture.supplyAsync(() -> {
                    // Main thread should still have default
                    return KmipCodecContext.getSpec();
                });
                
                // Then
                assertThat(thread1.get(1, TimeUnit.SECONDS)).isEqualTo(KmipSpec.V1_2);
                assertThat(thread2.get(1, TimeUnit.SECONDS)).isEqualTo(KmipSpec.UnknownVersion);
                assertThat(mainThread.get(1, TimeUnit.SECONDS)).isEqualTo(KmipSpec.UnknownVersion);
                
            } finally {
                executor.shutdown();
                executor.awaitTermination(2, TimeUnit.SECONDS);
            }
        }

        @Test
        @DisplayName("Should handle concurrent access safely")
        void shouldHandleConcurrentAccessSafely() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(10);
            int numberOfThreads = 100;
            
            try {
                // When - Multiple threads set and get specs concurrently
                CompletableFuture<Void>[] futures = new CompletableFuture[numberOfThreads];
                
                for (int i = 0; i < numberOfThreads; i++) {
                    final int threadId = i;
                    futures[i] = CompletableFuture.runAsync(() -> {
                        KmipSpec specToSet = (threadId % 2 == 0) ? KmipSpec.V1_2 : KmipSpec.UnknownVersion;
                        KmipCodecContext.setSpec(specToSet);
                        
                        // Verify the spec is what we set
                        KmipSpec retrievedSpec = KmipCodecContext.getSpec();
                        assertThat(retrievedSpec).isEqualTo(specToSet);
                        
                        // Clear and verify
                        KmipCodecContext.clear();
                        assertThat(KmipCodecContext.getSpec()).isEqualTo(KmipSpec.UnknownVersion);
                    }, executor);
                }
                
                // Then - All operations should complete without exceptions
                CompletableFuture.allOf(futures).get(5, TimeUnit.SECONDS);
                
            } finally {
                executor.shutdown();
                executor.awaitTermination(2, TimeUnit.SECONDS);
            }
        }

        @Test
        @DisplayName("Should not leak between threads")
        void shouldNotLeakBetweenThreads() throws Exception {
            // Given
            ExecutorService executor = Executors.newFixedThreadPool(2);
            
            try {
                // When - Set spec in one thread, check in another
                CompletableFuture<Void> setterThread = CompletableFuture.runAsync(() -> {
                    KmipCodecContext.setSpec(KmipSpec.V1_2);
                }, executor);
                
                setterThread.get(1, TimeUnit.SECONDS);
                
                CompletableFuture<KmipSpec> getterThread = CompletableFuture.supplyAsync(() -> {
                    return KmipCodecContext.getSpec();
                }, executor);
                
                // Then - Getter thread should have default, not the set value
                assertThat(getterThread.get(1, TimeUnit.SECONDS)).isEqualTo(KmipSpec.UnknownVersion);
                
            } finally {
                executor.shutdown();
                executor.awaitTermination(2, TimeUnit.SECONDS);
            }
        }
    }

    @Nested
    @DisplayName("Context Lifecycle")
    class ContextLifecycle {

        @Test
        @DisplayName("Should maintain state within thread")
        void shouldMaintainStateWithinThread() {
            // Given
            KmipSpec initialSpec = KmipSpec.V1_2;
            
            // When
            KmipCodecContext.setSpec(initialSpec);
            
            // Perform multiple operations
            KmipSpec spec1 = KmipCodecContext.getSpec();
            KmipSpec spec2 = KmipCodecContext.getSpec();
            
            // Then
            assertThat(spec1).isEqualTo(initialSpec);
            assertThat(spec2).isEqualTo(initialSpec);
            assertThat(spec1).isEqualTo(spec2);
        }

        @Test
        @DisplayName("Should handle multiple set operations")
        void shouldHandleMultipleSetOperations() {
            // Given
            KmipSpec firstSpec = KmipSpec.V1_2;
            KmipSpec secondSpec = KmipSpec.UnknownVersion;
            
            // When
            KmipCodecContext.setSpec(firstSpec);
            assertThat(KmipCodecContext.getSpec()).isEqualTo(firstSpec);
            
            KmipCodecContext.setSpec(secondSpec);
            assertThat(KmipCodecContext.getSpec()).isEqualTo(secondSpec);
            
            // Then - Latest set value should be active
            assertThat(KmipCodecContext.getSpec()).isEqualTo(secondSpec);
        }

        @Test
        @DisplayName("Should handle clear after set")
        void shouldHandleClearAfterSet() {
            // Given
            KmipCodecContext.setSpec(KmipSpec.V1_2);
            assertThat(KmipCodecContext.getSpec()).isEqualTo(KmipSpec.V1_2);
            
            // When
            KmipCodecContext.clear();
            
            // Then
            assertThat(KmipCodecContext.getSpec()).isEqualTo(KmipSpec.UnknownVersion);
        }

        @Test
        @DisplayName("Should handle multiple clear operations")
        void shouldHandleMultipleClearOperations() {
            // Given
            KmipCodecContext.setSpec(KmipSpec.V1_2);
            
            // When
            KmipCodecContext.clear();
            KmipCodecContext.clear(); // Second clear
            
            // Then
            assertThat(KmipCodecContext.getSpec()).isEqualTo(KmipSpec.UnknownVersion);
        }
    }

    @Nested
    @DisplayName("Integration with Base Test")
    class IntegrationWithBaseTest {

        @Test
        @DisplayName("Should work with BaseKmipTest setup")
        void shouldWorkWithBaseKmipTestSetup() {
            // Given - BaseKmipTest sets up context in @BeforeEach
            // The context should already be set to V1_2 by BaseKmipTest
            
            // When
            KmipSpec currentSpec = KmipCodecContext.getSpec();
            
            // Then
            assertThat(currentSpec).isEqualTo(KmipSpec.V1_2);
        }

        @Test
        @DisplayName("Should support withKmipSpec pattern")
        void shouldSupportWithKmipSpecPattern() {
            // Given
            KmipSpec originalSpec = KmipCodecContext.getSpec();
            KmipSpec testSpec = KmipSpec.UnknownVersion;
            
            // When
            withKmipSpec(testSpec, () -> {
                assertThat(KmipCodecContext.getSpec()).isEqualTo(testSpec);
            });
            
            // Then - Should restore original spec
            assertThat(KmipCodecContext.getSpec()).isEqualTo(originalSpec);
        }
    }
}
