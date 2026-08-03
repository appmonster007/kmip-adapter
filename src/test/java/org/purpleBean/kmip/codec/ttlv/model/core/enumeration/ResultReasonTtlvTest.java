package org.purplebean.kmip.codec.ttlv.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ResultReason;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResultReason TTLV Serialization")
class ResultReasonTtlvTest extends AbstractTtlvSerializationTestSuite<ResultReason> {
  @Override
  public Class<ResultReason> type() {
    return ResultReason.class;
  }

  @Override
  public ResultReason createDefault() {
    return ResultReason.Standard.ITEM_NOT_FOUND.inst();
  }

  @Override
  public ResultReason createVariant() {
    return ResultReason.Standard.RESPONSE_TOO_LARGE.inst();
  }
}
