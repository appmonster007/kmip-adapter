package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KeyMaterial;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.structure.KeyValueStructure;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

import java.util.List;

@DisplayName("KeyValueStructure JSON Serialization Tests")
class KeyValueStructureJsonTest extends AbstractJsonSerializationTestSuite<KeyValueStructure> {

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
        KmipAttribute attribute = CustomAttribute.of("x-test-attribute", AttributeValue.ofTextString("test-value"));
        return KeyValueStructure.builder()
                .keyMaterial(keyMaterial)
                .attributes(List.of(attribute))
                .build();
    }

    @Override
    protected KeyValueStructure createVariant() {
        KeyMaterial keyMaterial = KeyMaterialByteString.of(new byte[]{0x04, 0x05, 0x06});
        KmipAttribute attribute = CustomAttribute.of("x-test-attribute-2", AttributeValue.ofTextString("test-value"));
        return KeyValueStructure.builder()
                .keyMaterial(keyMaterial)
                .attributes(List.of(attribute))
                .build();
    }
}