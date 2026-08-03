package org.purpleBean.kmip.model.v2x1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("InteropIdentifier Domain Tests")
class InteropIdentifierTest extends AbstractKmipDataTypeTestSuite<InteropIdentifier> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<InteropIdentifier> type() {
    return InteropIdentifier.class;
  }

  @Override
  public InteropIdentifier createDefault() {
    return InteropIdentifier.of("default-string");
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}