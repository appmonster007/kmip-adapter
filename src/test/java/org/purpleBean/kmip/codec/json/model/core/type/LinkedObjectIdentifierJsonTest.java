package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("LinkedObjectIdentifier JSON Serialization Tests")
class LinkedObjectIdentifierJsonTest
    extends AbstractJsonSerializationTestSuite<LinkedObjectIdentifier> {

  @Override
  public Class<LinkedObjectIdentifier> type() {
    return LinkedObjectIdentifier.class;
  }

  @Override
  public LinkedObjectIdentifier createDefault() {
    return LinkedObjectIdentifier
        .builder()
        .value("test-linked-id")
        .build();
  }

  @Override
  public LinkedObjectIdentifier createVariant() {
    return LinkedObjectIdentifier
        .builder()
        .value("another-linked-id")
        .build();
  }
}