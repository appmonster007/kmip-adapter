package org.purpleBean.kmip.test.suite;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.SerializationTestUtils;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Reusable XML serialization/deserialization test suite for KMIP objects.
 */
@DisplayName("Abstract XML Serialization Suite")
public abstract class AbstractXmlSerializationSuite<T> extends BaseKmipTest {

    protected abstract Class<T> type();

    protected abstract T createDefault();

    protected T createVariant() { return createDefault(); }

    protected XmlMapper mapper() { return getXmlMapper(); }

    @Test
    @DisplayName("XML: round-trip default instance")
    void xml_roundTrip_default() {
        T original = createDefault();
        SerializationTestUtils.performXmlRoundTrip(mapper(), original, type());
    }

    @Test
    @DisplayName("XML: round-trip variant instance")
    void xml_roundTrip_variant() {
        T original = createVariant();
        SerializationTestUtils.performXmlRoundTrip(mapper(), original, type());
    }

    @Test
    @DisplayName("XML: unsupported KMIP spec should fail serialize")
    void xml_unsupportedSpec_failsSerialize() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> assertThatThrownBy(() -> mapper().writeValueAsString(createDefault()))
                        .isInstanceOf(Exception.class));
    }
}
