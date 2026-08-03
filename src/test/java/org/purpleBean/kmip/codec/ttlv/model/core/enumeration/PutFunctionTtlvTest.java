package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.PutFunction;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PutFunction TTLV Serialization")
class PutFunctionTtlvTest extends AbstractTtlvSerializationTestSuite<PutFunction> {
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
