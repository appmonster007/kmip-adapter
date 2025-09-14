package org.purpleBean.kmip.codec.json;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.test.BaseKmipTest;
import org.purpleBean.kmip.test.KmipTestDataFactory;
import org.purpleBean.kmip.test.SerializationTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("ProtocolVersion JSON Tests")
class ProtocolVersionJsonTest extends BaseKmipTest {

    @Test
    @DisplayName("Round-trip: serialize and deserialize ProtocolVersion")
    void roundTrip() {
        ProtocolVersion original = KmipTestDataFactory.createProtocolVersion();
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, original, ProtocolVersion.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,0", "1,0", "1,2", "2,1", "99,99"})
    @DisplayName("Round-trip: various protocol versions")
    void roundTrip_variousVersions(String versionPair) {
        String[] parts = versionPair.split(",");
        ProtocolVersion version = ProtocolVersion.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        SerializationTestUtils.performJsonRoundTrip(jsonMapper, version, ProtocolVersion.class);
    }

    @Test
    @DisplayName("JSON: serialization succeeds but deserialization fails under UnsupportedVersion context")
    void json_behavior_underUnsupportedVersionContext() {
        withKmipSpec(
                KmipSpec.UnsupportedVersion,
                () -> {
                    ProtocolVersion original = KmipTestDataFactory.createProtocolVersion();
                    // Serialize should succeed
                    String json;
                    try {
                        json = jsonMapper.writeValueAsString(original);
                    } catch (Exception e) {
                        throw new RuntimeException("JSON serialization failed unexpectedly", e);
                    }
                    // Deserialize should fail due to tag lookup filtering by UnsupportedVersion
                    final String finalJson = json;
                    org.assertj.core.api.Assertions.assertThatThrownBy(
                                    () -> jsonMapper.readValue(finalJson, ProtocolVersion.class))
                            .isInstanceOf(Exception.class);
                });
    }

    @Test
    @DisplayName("Structure: expected JSON fields present")
    void structure_expectFields() {
        ProtocolVersion version = ProtocolVersion.of(1, 2);
        SerializationTestUtils.testJsonSerialization(
                jsonMapper,
                version,
                json -> {
                    SerializationTestUtils.validateJsonStructure(json, "tag", "type", "value");
                    assertThat(json).contains("\"ProtocolVersion\"");
                    assertThat(json).contains("\"ProtocolVersionMajor\"");
                    assertThat(json).contains("\"ProtocolVersionMinor\"");
                    assertThat(json).contains("\"value\":1");
                    assertThat(json).contains("\"value\":2");
                });
    }
}
