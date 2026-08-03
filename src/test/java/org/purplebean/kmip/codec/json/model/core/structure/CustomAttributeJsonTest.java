package org.purplebean.kmip.codec.json.model.core.structure;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.CustomAttribute;
import org.purplebean.kmip.model.core.type.AttributeValue;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CustomAttribute JSON Serialization Tests")
class CustomAttributeJsonTest extends AbstractJsonSerializationTestSuite<CustomAttribute> {

  private static final OffsetDateTime FIXED_TIME =
      OffsetDateTime.of(2024, 1, 2, 3, 4, 5, 0, ZoneOffset.UTC);

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CustomAttribute> type() {
    return CustomAttribute.class;
  }

  @Override
  public CustomAttribute createDefault() {
    return CustomAttribute.of("x-custom-state", AttributeValue.ofInteger(1));
  }

  @Override
  public CustomAttribute createVariant() {
    return CustomAttribute.of("x-custom-date", AttributeValue.ofDateTime(FIXED_TIME));
  }
}
