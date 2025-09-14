package org.purpleBean.kmip.codec.xml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

@DisplayName("ProtocolVersion XML Tests")
class ProtocolVersionXmlTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize ProtocolVersion")
    void roundTrip() {
        ProtocolVersion original = KmipTestDataFactory.createProtocolVersion();
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, ProtocolVersion.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,0", "1,0", "1,2", "2,1", "99,99"})
    @DisplayName("Round-trip: various protocol versions")
    void roundTrip_variousVersions(String versionPair) {
        String[] parts = versionPair.split(",");
        ProtocolVersion version = ProtocolVersion.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        SerializationTestUtils.performXmlRoundTrip(xmlMapper, version, ProtocolVersion.class);
    }

    @Test
    @DisplayName("Round-trip succeeds under UnsupportedVersion context")
    void roundTrip_underUnsupportedVersionContext() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> {
                    ProtocolVersion original = KmipTestDataFactory.createProtocolVersion();
                    SerializationTestUtils.performXmlRoundTrip(xmlMapper, original, ProtocolVersion.class);
                });
    }

    @Test
    @DisplayName("Error handling: malformed XML should fail deserialization")
    void errorHandling_malformedXml_shouldFail() {
        String malformedXml = "<ProtocolVersion><ProtocolVersionMajor><value>not_a_number</value></ProtocolVersionMajor>";
        org.assertj.core.api.Assertions.assertThatThrownBy(
                        () -> xmlMapper.readValue(malformedXml, ProtocolVersion.class))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Error handling: missing required elements should fail deserialization")
    void errorHandling_missingElements_shouldFail() {
        String incompleteXml = "<ProtocolVersion><ProtocolVersionMajor><value>1</value></ProtocolVersionMajor></ProtocolVersion>";
        org.assertj.core.api.Assertions.assertThatThrownBy(
                        () -> xmlMapper.readValue(incompleteXml, ProtocolVersion.class))
                .isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("Structure: expected XML elements present for ProtocolVersion")
    void structure_expectedElements() {
        ProtocolVersion version = ProtocolVersion.of(1, 2);
        SerializationTestUtils.testXmlSerialization(
                xmlMapper,
                version,
                xml -> {
                    org.assertj.core.api.Assertions.assertThat(xml).contains("<ProtocolVersion>");
                    org.assertj.core.api.Assertions.assertThat(xml).contains("<ProtocolVersionMajor");
                    org.assertj.core.api.Assertions.assertThat(xml).contains("<ProtocolVersionMinor");
                }
        );
    }
}
