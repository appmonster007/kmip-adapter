package org.purpleBean.kmip.codec.xml.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.KeyMaterial;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.AttributeValueTextString;
import org.purpleBean.kmip.common.KeyMaterialByteString;
import org.purpleBean.kmip.common.structure.CustomAttribute;
import org.purpleBean.kmip.common.structure.KeyValueStructure;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationSuite;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@DisplayName("KeyValueStructure XML Serialization Tests")
class KeyValueStructureXmlTest extends AbstractXmlSerializationSuite<KeyValueStructure> {

    private static final OffsetDateTime FIXED_TIME = OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

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
        KmipAttribute attribute1 = CustomAttribute.of("x-test-attribute", AttributeValueTextString.of("test-value"));
        KmipAttribute attribute2 = ActivationDate.of(FIXED_TIME);
        return KeyValueStructure.builder()
                .keyMaterial(keyMaterial)
                .attributes(List.of(attribute1, attribute2))
                .build();
    }

    @Override
    protected KeyValueStructure createVariant() {
        KeyMaterial keyMaterial = KeyMaterialByteString.of(new byte[]{0x04, 0x05, 0x06});
        KmipAttribute attribute1 = CustomAttribute.of("x-test-attribute-2", AttributeValueTextString.of("test-value-2"));
        KmipAttribute attribute2 = ActivationDate.of(FIXED_TIME.plusDays(10));
        return KeyValueStructure.builder()
                .keyMaterial(keyMaterial)
                .attributes(List.of(attribute1, attribute2))
                .build();
    }
}