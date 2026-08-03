package org.purplebean.kmip.codec.json.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("UniqueIdentifier Json Serialization Tests")
class UniqueIdentifierJsonTest extends AbstractJsonSerializationTestSuite<UniqueIdentifier> {

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