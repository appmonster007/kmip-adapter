package org.purpleBean.kmip.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KeyMaterial;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.common.AttributeValueTextString;
import org.purpleBean.kmip.common.KeyMaterialByteString;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KeyValueStructure Domain Tests")
class KeyValueStructureTest extends AbstractKmipStructureSuite<KeyValueStructure> {

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
    protected EncodingType expectedEncodingType() {
        return EncodingType.STRUCTURE;
    }

    @Override
    protected int expectedMinComponentCount() {
        return 2;
    }

    @Override
    protected void validateComponents(List<KmipDataType> values) {
        assertThat(values.get(0).getEncodingType()).isEqualTo(EncodingType.BYTE_STRING);
        assertThat(values.get(1).getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
    }
}