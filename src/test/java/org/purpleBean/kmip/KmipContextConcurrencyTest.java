package org.purpleBean.kmip;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("KmipContext Concurrency Tests")
@Execution(ExecutionMode.SAME_THREAD) // Ensure thread safety tests run sequentially
class KmipContextConcurrencyTest extends BaseKmipTest {

    @Nested
    @DisplayName("Basic Operations")
    class BasicOperations {

        @Test
        @DisplayName("Should have default V1.2 version")
        void shouldHaveDefaultUnknownVersion() {
            // When
            KmipSpec spec = KmipContext.getSpec();

            // Then
            assertThat(spec).isEqualTo(KmipSpec.UnknownVersion);
        }

        @Test
        @DisplayName("Should set and get spec correctly")
        void shouldSetAndGetSpecCorrectly() {
            // Given
            KmipSpec expectedSpec = KmipSpec.V1_2;

            // When
            KmipContext.setSpec(expectedSpec);
            KmipSpec actualSpec = KmipContext.getSpec();

            // Then
            assertThat(actualSpec).isEqualTo(expectedSpec);
        }

        @Test
        @DisplayName("Should clear context to default")
        void shouldClearContextToDefault() {
            // Given
            KmipContext.setSpec(KmipSpec.V1_2);

            // When
            KmipContext.clear();
            KmipSpec spec = KmipContext.getSpec();

            // Then
            assertThat(spec).isEqualTo(KmipSpec.UnknownVersion);
        }

        @Test
        @DisplayName("Should handle null spec gracefully")
        void shouldHandleNullSpecGracefully() {
            // When & Then
            assertThatCode(() -> KmipContext.setSpec(null)).doesNotThrowAnyException();
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
                KmipContext.clear();

                CompletableFuture<KmipSpec> thread1 =
                        CompletableFuture.supplyAsync(
                                () -> {
                                    KmipContext.setSpec(KmipSpec.V1_2);
                                    return KmipContext.getSpec();
                                },
                                executor);

                CompletableFuture<KmipSpec> thread2 =
                        CompletableFuture.supplyAsync(
                                () -> {
                                    KmipContext.setSpec(KmipSpec.UnknownVersion);
                                    return KmipContext.getSpec();
                                },
                                executor);

                CompletableFuture<KmipSpec> mainThread =
                        CompletableFuture.supplyAsync(KmipContext::getSpec);

                // Then - Each thread should maintain its own context
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
                    futures[i] =
                            CompletableFuture.runAsync(
                                    () -> {
                                        KmipSpec specToSet =
                                                (threadId % 2 == 0) ? KmipSpec.V1_2 : KmipSpec.UnknownVersion;
                                        KmipContext.setSpec(specToSet);

                                        // Verify the spec is what we set
                                        KmipSpec retrievedSpec = KmipContext.getSpec();
                                        assertThat(retrievedSpec).isEqualTo(specToSet);

                                        // Clear and verify
                                        KmipContext.clear();
                                        assertThat(KmipContext.getSpec()).isEqualTo(KmipSpec.UnknownVersion);
                                    },
                                    executor);
                }

                // Then - All operations should complete without exceptions
                CompletableFuture.allOf(futures).get(5, TimeUnit.SECONDS);

            } finally {
                executor.shutdown();
                executor.awaitTermination(2, TimeUnit.SECONDS);
            }
        }
    }
}
