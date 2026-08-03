package org.purpleBean.kmip.codec.ttlv.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.InteropIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("InteropIdentifier Ttlv Serialization Tests")
class InteropIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<InteropIdentifier> {

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