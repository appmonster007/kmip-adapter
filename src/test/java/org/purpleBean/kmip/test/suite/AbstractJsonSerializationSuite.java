package org.purpleBean.kmip.test.suite;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

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
    protected T createVariant() { return createDefault(); }

    protected ObjectMapper mapper() { return getJsonMapper(); }

    @Test
    @DisplayName("JSON: round-trip default instance")
    void json_roundTrip_default() {
        T original = createDefault();
        SerializationTestUtils.performJsonRoundTrip(mapper(), original, type());
    }

    @Test
    @DisplayName("JSON: round-trip variant instance")
    void json_roundTrip_variant() {
        T original = createVariant();
        SerializationTestUtils.performJsonRoundTrip(mapper(), original, type());
    }

    @Test
    @DisplayName("JSON: unsupported KMIP spec should fail serialize")
    void json_unsupportedSpec_failsSerialize() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> mapper().writeValueAsString(createDefault()))
                        .isInstanceOf(Exception.class));
    }
}
