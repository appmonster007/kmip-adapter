package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KeyMaterial;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.AttributeValueTextString;
import org.purpleBean.kmip.common.KeyMaterialByteString;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.common.structure.KeyValueStructure;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

import java.util.List;

@DisplayName("KeyValueStructure TTLV Serialization Tests")
class KeyValueStructureTtlvTest extends AbstractTtlvSerializationTestSuite<KeyValueStructure> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<KeyValueStructure> type() {
        return KeyValueStructure.class;
    }

    @Override
    protected KeyValueStructure createDefault() {
        KeyMaterial keyMaterial = KeyMaterialByteString.of(new byte[]{0x01, 0x02, 0x03});
        KmipAttribute attribute = CustomAttribute.of("x-test-attribute", AttributeValueTextString.of("test-value"));
        return KeyValueStructure.builder()
                .keyMaterial(keyMaterial)
                .attributes(List.of(attribute))
                .build();
    }

    @Override
    protected KeyValueStructure createVariant() {
        KeyMaterial keyMaterial = KeyMaterialByteString.of(new byte[]{0x04, 0x05, 0x06});
        KmipAttribute attribute = CustomAttribute.of("x-test-attribute-2", AttributeValueTextString.of("test-value"));
        return KeyValueStructure.builder()
                .keyMaterial(keyMaterial)
                .attributes(List.of(attribute))
                .build();
    }
}