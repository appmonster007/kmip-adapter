package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.AlternativeNameType;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AlternativeNameType TTLV Serialization")
class AlternativeNameTypeTtlvTest extends AbstractTtlvSerializationTestSuite<AlternativeNameType> {
  @Override
  public Class<AlternativeNameType> type() {
    return AlternativeNameType.class;
  }

  @Override
  public AlternativeNameType createDefault() {
    return AlternativeNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
  }

  @Override
  public AlternativeNameType createVariant() {
    return AlternativeNameType.Standard.URI.inst();
  }
}
