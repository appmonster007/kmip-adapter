package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("UniqueIdentifier XML Serialization Tests")
class UniqueIdentifierXmlTest extends AbstractXmlSerializationTestSuite<UniqueIdentifier> {

  @Override
  public Class<UniqueIdentifier> type() {
    return UniqueIdentifier.class;
  }

  @Override
  public UniqueIdentifier createDefault() {
    return UniqueIdentifier
        .builder()
        .value("FIXED_STRING")
        .build();
  }

  @Override
  public UniqueIdentifier createVariant() {
    return UniqueIdentifier
        .builder()
        .value("VARIANT_STRING")
        .build();
  }
}
