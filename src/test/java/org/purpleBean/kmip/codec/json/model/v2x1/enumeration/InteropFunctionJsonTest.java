package org.purplebean.kmip.codec.json.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.InteropFunction;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("InteropFunction JSON Serialization")
class InteropFunctionJsonTest extends AbstractJsonSerializationTestSuite<InteropFunction> {
  @Override
  public Class<InteropFunction> type() {
    return InteropFunction.class;
  }

  @Override
  public InteropFunction createDefault() {
    return InteropFunction.Standard.BEGIN.inst();
  }

  @Override
  public InteropFunction createVariant() {
    return InteropFunction.Standard.END.inst();
  }
}
