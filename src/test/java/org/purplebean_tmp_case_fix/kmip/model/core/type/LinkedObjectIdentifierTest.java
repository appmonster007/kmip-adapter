package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("LinkedObjectIdentifier Domain Tests")
class LinkedObjectIdentifierTest extends AbstractKmipDataTypeTestSuite<LinkedObjectIdentifier> {

  @Override
  protected Class<LinkedObjectIdentifier> type() {
    return LinkedObjectIdentifier.class;
  }

  @Override
  protected LinkedObjectIdentifier createDefault() {
    return LinkedObjectIdentifier
        .builder()
        .value("test-linked-id")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}