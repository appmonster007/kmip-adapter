package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KeyMaterial;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.KeyMaterialByteString;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("KeyValueStructure Domain Tests")
class KeyValueStructureTest extends AbstractKmipStructureTestSuite<KeyValueStructure> {

  @Override
  protected Class<KeyValueStructure> type() {
    return KeyValueStructure.class;
  }

  @Override
  protected KeyValueStructure createDefault() {
    KeyMaterial keyMaterial = KeyMaterialByteString.of(new byte[] {0x01, 0x02, 0x03});
    KmipAttribute attribute =
        CustomAttribute.of("x-test-attribute", AttributeValue.ofTextString("test-value"));
    return KeyValueStructure
        .builder()
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
    assertThat(values
        .get(0)
        .getEncodingType()).isEqualTo(EncodingType.BYTE_STRING);
    assertThat(values
        .get(1)
        .getEncodingType()).isEqualTo(EncodingType.STRUCTURE);
  }
}