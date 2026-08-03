package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.PutFunction;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("PutFunction JSON Serialization")
class PutFunctionJsonTest extends AbstractJsonSerializationTestSuite<PutFunction> {
  @Override
  public Class<PutFunction> type() {
    return PutFunction.class;
  }

  @Override
  public PutFunction createDefault() {
    return PutFunction.Standard.NEW.inst();
  }

  @Override
  public PutFunction createVariant() {
    return PutFunction.Standard.REPLACE.inst();
  }
}
