package org.purpleBean.kmip.test.suite;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Reusable JSON serialization/deserialization test suite for KMIP objects.
 * Extend this class and implement factory methods to cover a specific type.
 */
@DisplayName("Abstract JSON Serialization Suite")
public abstract class AbstractJsonSerializationSuite<T> extends BaseKmipTest {

    protected abstract Class<T> type();

    /**
     * Provide a canonical instance for round-trip tests.
     */
    protected abstract T createDefault();

    /**
     * Optionally provide an alternative instance (can be same as default).
     */
    protected T createVariant() {
        return createDefault();
    }

    protected ObjectMapper mapper() {
        return getJsonMapper();
    }

    /**
     * Override when the type is allowed to serialize even under KmipSpec.UnsupportedVersion.
     * Default is true (negative test expects failure).
     */
    protected boolean unsupportedSpecShouldFailSerialize() {
        return true;
    }

    @Test
    @DisplayName("JSON: round-trip default instance")
    void json_roundTrip_default() {
        T original = createDefault();
        try {
            String json = mapper().writeValueAsString(original);
            System.out.println("JSON: \n" + json);
            T restored = mapper().readValue(json, type());
            assertThat(equalsRelaxed(original, restored)).isTrue();
        } catch (Exception e) {
            throw new AssertionError("JSON round-trip failed", e);
        }
    }

    @Test
    @DisplayName("JSON: round-trip variant instance")
    void json_roundTrip_variant() {
        T original = createVariant();
        try {
            String json = mapper().writeValueAsString(original);
            System.out.println("JSON: \n" + json);
            T restored = mapper().readValue(json, type());
            assertThat(equalsRelaxed(original, restored)).isTrue();
        } catch (Exception e) {
            throw new AssertionError("JSON round-trip failed", e);
        }
    }

    @Test
    @DisplayName("JSON: unsupported KMIP spec should fail serialize")
    void json_unsupportedSpec_failsSerialize() {
        if (unsupportedSpecShouldFailSerialize()) {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(() -> mapper().writeValueAsString(createDefault()))
                            .isInstanceOf(Exception.class));
        }
    }

    /**
     * Hook for subclasses to relax equality comparison on round-trips.
     * Default uses Objects.equals (delegates to equals()).
     */
    protected boolean equalsRelaxed(T a, T b) {
        return Objects.equals(a, b);
    }
}
