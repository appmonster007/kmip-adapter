package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.LinkedObjectIdentifier;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("LinkedObjectIdentifier XML Serialization Tests")
class LinkedObjectIdentifierXmlTest
    extends AbstractXmlSerializationTestSuite<LinkedObjectIdentifier> {

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