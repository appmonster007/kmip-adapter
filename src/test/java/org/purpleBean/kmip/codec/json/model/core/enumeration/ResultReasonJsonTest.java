package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ResultReason;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResultReason JSON Serialization")
class ResultReasonJsonTest extends AbstractJsonSerializationTestSuite<ResultReason> {
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
