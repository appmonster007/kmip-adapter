package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KeyMaterial;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CustomAttribute;
import org.purpleBean.kmip.model.core.structure.KeyValueStructure;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.KeyMaterialByteString;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("KeyValueStructure TTLV Serialization Tests")
class KeyValueStructureTtlvTest extends AbstractTtlvSerializationTestSuite<KeyValueStructure> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<KeyValueStructure> type() {
    return KeyValueStructure.class;
  }

  @Override
  public KeyValueStructure createDefault() {
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
  public KeyValueStructure createVariant() {
    KeyMaterial keyMaterial = KeyMaterialByteString.of(new byte[] {0x04, 0x05, 0x06});
    KmipAttribute attribute =
        CustomAttribute.of("x-test-attribute-2", AttributeValue.ofTextString("test-value"));
    return KeyValueStructure
        .builder()
        .keyMaterial(keyMaterial)
        .attributes(List.of(attribute))
        .build();
  }
}