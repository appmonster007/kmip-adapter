package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("UniqueIdentifier Xml Serialization Tests")
class UniqueIdentifierXmlTest extends AbstractXmlSerializationTestSuite<UniqueIdentifier> {

  @Override
  public Class<UniqueIdentifier> type() {
    return UniqueIdentifier.class;
  }

  @Override
  public UniqueIdentifier createDefault() {
    return UniqueIdentifier.Standard.values()[0].inst();
  }

  @Override
  public UniqueIdentifier createVariant() {
    return UniqueIdentifier.Standard.values()[1].inst();
  }
}