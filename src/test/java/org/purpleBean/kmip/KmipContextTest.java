package org.purpleBean.kmip;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for {@link KmipContext} class.
 */
@Execution(ExecutionMode.SAME_THREAD) // Ensure tests run in sequence for thread-local tests
class KmipContextTest {

    @BeforeEach
    @AfterEach
    void clearContext() {
        KmipContext.clear();
    }

    @Test
    @DisplayName("getSpec should return UnknownVersion by default")
    void getSpec_shouldReturnUnknownVersionByDefault() {
        // When
        KmipSpec spec = KmipContext.getSpec();

        // Then
        assertThat(spec).isSameAs(KmipSpec.UnknownVersion);
    }

    @Test
    @DisplayName("setSpec should set the spec for current thread")
    void setSpec_shouldSetSpecForCurrentThread() {
        // Given
        KmipSpec testSpec = KmipSpec.V1_2;

        // When
        KmipContext.setSpec(testSpec);

        // Then
        assertThat(KmipContext.getSpec()).isSameAs(testSpec);
    }

    @Test
    @DisplayName("setSpec with null should clear the current spec")
    void setSpec_withNull_shouldClearCurrentSpec() {
        // Given
        KmipContext.setSpec(KmipSpec.V1_2);

        // When
        KmipContext.setSpec(null);

        // Then
        assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.UnknownVersion);
    }

    @Test
    @DisplayName("clear should reset to UnknownVersion")
    void clear_shouldResetToUnknownVersion() {
        // Given
        KmipContext.setSpec(KmipSpec.V1_2);

        // When
        KmipContext.clear();

        // Then
        assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.UnknownVersion);
    }

    @Test
    @DisplayName("withSpec should execute runnable with specified spec")
    void withSpec_shouldExecuteWithSpecifiedSpec() {
        // Given
        KmipSpec testSpec = KmipSpec.V1_2;
        boolean[] executed = {false};

        // When
        KmipContext.withSpec(
                testSpec,
                () -> {
                    assertThat(KmipContext.getSpec()).isSameAs(testSpec);
                    executed[0] = true;
                });

        // Then
        assertThat(executed[0]).isTrue();
        assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.UnknownVersion);
    }

    @Test
    @DisplayName("withSpec should restore previous spec after execution")
    void withSpec_shouldRestorePreviousSpec() {
        // Given
        KmipSpec originalSpec = KmipSpec.V1_2;
        KmipContext.setSpec(originalSpec);

        // When
        KmipContext.withSpec(
                KmipSpec.V1_2,
                () -> {
                    // Do nothing, just verify the spec is set inside the block
                    assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.V1_2);
                });

        // Then
        assertThat(KmipContext.getSpec()).isSameAs(originalSpec);
    }

    @Test
    @DisplayName("withSpec should handle null previous spec")
    void withSpec_shouldHandleNullPreviousSpec() {
        // Given - Clear any existing spec
        KmipContext.clear();

        // When
        KmipContext.withSpec(
                KmipSpec.V1_2,
                () -> {
                    assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.V1_2);
                });

        // Then - Should revert to UnknownVersion
        assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.UnknownVersion);
    }

    @Test
    @DisplayName("withSpec should restore spec even if runnable throws")
    void withSpec_shouldRestoreSpecOnException() {
        // Given
        KmipSpec originalSpec = KmipSpec.V1_2;
        KmipContext.setSpec(originalSpec);

        // When
        assertThatThrownBy(
                () ->
                        KmipContext.withSpec(
                                KmipSpec.V1_2,
                                () -> {
                                    throw new RuntimeException("Test exception");
                                }))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Test exception");

        // Then
        assertThat(KmipContext.getSpec()).isSameAs(originalSpec);
    }

    @Test
    @DisplayName("Thread isolation: Spec should be thread-local")
    void spec_shouldBeThreadLocal() throws InterruptedException {
        // Given
        KmipContext.setSpec(KmipSpec.V1_2);
        final KmipSpec[] threadSpec = {null};

        // When
        Thread thread =
                new Thread(
                        () -> {
                            // This thread should have the default UnknownVersion
                            threadSpec[0] = KmipContext.getSpec();
                        });

        thread.start();
        thread.join();

        // Then
        assertThat(threadSpec[0]).isSameAs(KmipSpec.UnknownVersion);
        assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.V1_2);
    }

    @Test
    @DisplayName("Multiple clears should not cause issues")
    void multipleClears_shouldNotCauseIssues() {
        // When
        KmipContext.clear();
        KmipContext.clear(); // Second clear

        // Then
        assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.UnknownVersion);
    }

    @Test
    @DisplayName("setSpec with UnknownVersion should work as expected")
    void setSpec_withUnknownVersion_shouldWork() {
        // When
        KmipContext.setSpec(KmipSpec.UnknownVersion);

        // Then
        assertThat(KmipContext.getSpec()).isSameAs(KmipSpec.UnknownVersion);
    }
}
