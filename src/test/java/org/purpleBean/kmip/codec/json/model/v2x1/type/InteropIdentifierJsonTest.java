package org.purpleBean.kmip.codec.json.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.type.InteropIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("InteropIdentifier Json Serialization Tests")
class InteropIdentifierJsonTest extends AbstractJsonSerializationTestSuite<InteropIdentifier> {

  @Override
  public Class<InteropIdentifier> type() {
    return InteropIdentifier.class;
  }

  @Override
  public InteropIdentifier createDefault() {
    return InteropIdentifier.of("default-string");
  }

  @Override
  public InteropIdentifier createVariant() {
    return InteropIdentifier.of("variant-string");
  }
}