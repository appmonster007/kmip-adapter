package org.purplebean.kmip.codec.json.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.RotateNameType;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateNameType JSON Serialization")
class RotateNameTypeJsonTest extends AbstractJsonSerializationTestSuite<RotateNameType> {
  @Override
  public Class<RotateNameType> type() {
    return RotateNameType.class;
  }

  @Override
  public RotateNameType createDefault() {
    return RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst();
  }

  @Override
  public RotateNameType createVariant() {
    return RotateNameType.Standard.URI.inst();
  }
}
