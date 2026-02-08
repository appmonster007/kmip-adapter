package org.purpleBean.kmip.test.suite;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.api.KmipSpec;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Interface defining the test contract for KMIP serialization (JSON, XML, TTLV).
 * Provides default test methods for round-trip serialization and error handling.
 *
 * @param <T> The specific KMIP type being tested.
 * @param <M> The type of the Mapper used for serialization.
 * @param <S> The type of the serialized value (e.g., String, ByteBuffer).
 */
public interface KmipSerializationTestSuite<T, M, S> {

    /**
     * @return The class of the KMIP type being tested.
     */
    Class<T> type();

    /**
     * @return A default instance of the type for testing.
     */
    T createDefault();

    /**
     * @return An alternative instance of the type for testing (can be same as default).
     */
    default T createVariant() {
        return createDefault();
    }

    /**
     * @return The Mapper to use for serialization.
     */
    M getMapper();

    /**
     * Serializes the object using the mapper.
     *
     * @param object The object to serialize.
     * @return The serialized form.
     * @throws Exception If serialization fails.
     */
    S serialize(T object) throws Exception;

    /**
     * Deserializes the serialized form back to an object.
     *
     * @param serialized The serialized form.
     * @return The deserialized object.
     * @throws Exception If deserialization fails.
     */
    T deserialize(S serialized) throws Exception;

    /**
     * @return True if serialization should fail under KmipSpec.UnsupportedVersion.
     * Defaults to true.
     */
    default boolean unsupportedSpecShouldFailSerialize() {
        return true;
    }

    /**
     * Helper method to execute an operation within a specific KMIP spec context.
     * This should be implemented by the test class or a base class that manages the context.
     */
    void withKmipSpec(KmipSpec spec, Runnable operation);

    @Test
    @DisplayName("Serialization: round-trip default instance")
    default void serialization_roundTrip_default() {
        T original = createDefault();
        performRoundTrip(original);
    }

    @Test
    @DisplayName("Serialization: round-trip variant instance")
    default void serialization_roundTrip_variant() {
        T original = createVariant();
        performRoundTrip(original);
    }

    @Test
    @DisplayName("Serialization: unsupported KMIP spec should fail serialize")
    default void serialization_unsupportedSpec_failsSerialize() {
        if (unsupportedSpecShouldFailSerialize()) {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(() -> performSerialize(createDefault()))
                            .isInstanceOf(Exception.class));
        }
    }

    default void performRoundTrip(T original) {
        try {
            S serialized = serialize(original);
            T restored = deserialize(serialized);
            assertThat(original).isEqualTo(restored);
        } catch (Exception e) {
            throw new AssertionError("Round-trip serialization failed", e);
        }
    }

    default void performSerialize(T original) throws Exception {
        serialize(original);
    }
}
