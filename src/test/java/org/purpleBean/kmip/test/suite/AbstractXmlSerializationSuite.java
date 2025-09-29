package org.purpleBean.kmip.test.suite;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.BaseKmipTest;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Reusable XML serialization/deserialization test suite for KMIP objects.
 */
@DisplayName("Abstract XML Serialization Suite")
public abstract class AbstractXmlSerializationSuite<T> extends BaseKmipTest {

    protected abstract Class<T> type();

    protected abstract T createDefault();

    protected T createVariant() {
        return createDefault();
    }

    protected XmlMapper mapper() {
        return getXmlMapper();
    }

    /**
     * Override when the type is allowed to serialize even under KmipSpec.UnsupportedVersion.
     * Default is true (negative test expects failure).
     */
    protected boolean unsupportedSpecShouldFailSerialize() {
        return true;
    }

    @Test
    @DisplayName("XML: round-trip default instance")
    void xml_roundTrip_default() {
        T original = createDefault();
        try {
            String xml = mapper().writeValueAsString(original);
            System.out.println("XML: \n" + xml);
            T restored = mapper().readValue(xml, type());
            assertThat(equalsRelaxed(original, restored)).isTrue();
        } catch (Exception e) {
            throw new AssertionError("XML round-trip failed", e);
        }
    }

    @Test
    @DisplayName("XML: round-trip variant instance")
    void xml_roundTrip_variant() {
        T original = createVariant();
        try {
            String xml = mapper().writeValueAsString(original);
            System.out.println("XML: \n" + xml);
            T restored = mapper().readValue(xml, type());
            assertThat(equalsRelaxed(original, restored)).isTrue();
        } catch (Exception e) {
            throw new AssertionError("XML round-trip failed", e);
        }
    }

    /**
     * Hook for subclasses to relax equality comparison on round-trips.
     * Default uses Objects.equals (delegates to equals()).
     */
    protected boolean equalsRelaxed(T a, T b) {
        return Objects.equals(a, b);
    }

    @Test
    @DisplayName("XML: unsupported KMIP spec should fail serialize")
    void xml_unsupportedSpec_failsSerialize() {
        if (unsupportedSpecShouldFailSerialize()) {
            withKmipSpec(
                    KmipSpec.UnsupportedVersion,
                    () -> assertThatThrownBy(() -> mapper().writeValueAsString(createDefault()))
                            .isInstanceOf(Exception.class));
        }
    }
}
