package org.purplebean.kmip.codec.xml.model.core.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KeyMaterial;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.CustomAttribute;
import org.purplebean.kmip.model.core.structure.KeyValueStructure;
import org.purplebean.kmip.model.core.type.ActivationDate;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.model.core.type.KeyMaterialByteString;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("KeyValueStructure XML Serialization Tests")
class KeyValueStructureXmlTest extends AbstractXmlSerializationTestSuite<KeyValueStructure> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

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
    KmipAttribute attribute1 =
        CustomAttribute.of("x-test-attribute", AttributeValue.ofTextString("test-value"));
    KmipAttribute attribute2 = ActivationDate.of(FIXED_TIME);
    return KeyValueStructure
        .builder()
        .keyMaterial(keyMaterial)
        .attributes(List.of(attribute1, attribute2))
        .build();
  }

  @Override
  public KeyValueStructure createVariant() {
    KeyMaterial keyMaterial = KeyMaterialByteString.of(new byte[] {0x04, 0x05, 0x06});
    KmipAttribute attribute1 =
        CustomAttribute.of("x-test-attribute-2", AttributeValue.ofTextString("test-value-2"));
    KmipAttribute attribute2 = ActivationDate.of(FIXED_TIME.plusDays(10));
    return KeyValueStructure
        .builder()
        .keyMaterial(keyMaterial)
        .attributes(List.of(attribute1, attribute2))
        .build();
  }
}