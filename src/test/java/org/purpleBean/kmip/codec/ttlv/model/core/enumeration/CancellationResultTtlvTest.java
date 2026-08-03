package org.purpleBean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CancellationResult TTLV Serialization")
class CancellationResultTtlvTest extends AbstractTtlvSerializationTestSuite<CancellationResult> {
  @Override
  public Class<CancellationResult> type() {
    return CancellationResult.class;
  }

  @Override
  public CancellationResult createDefault() {
    return CancellationResult.Standard.CANCELED.inst();
  }

  @Override
  public CancellationResult createVariant() {
    return CancellationResult.Standard.UNABLE_TO_CANCEL.inst();
  }
}
