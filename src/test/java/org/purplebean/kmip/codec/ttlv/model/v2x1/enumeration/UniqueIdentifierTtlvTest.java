package org.purplebean.kmip.codec.ttlv.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("UniqueIdentifier Ttlv Serialization Tests")
class UniqueIdentifierTtlvTest extends AbstractTtlvSerializationTestSuite<UniqueIdentifier> {

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