package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ResultStatus;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("ResultStatus JSON Serialization")
class ResultStatusJsonTest extends AbstractJsonSerializationTestSuite<ResultStatus> {
  @Override
  public Class<ResultStatus> type() {
    return ResultStatus.class;
  }

  @Override
  public ResultStatus createDefault() {
    return ResultStatus.Standard.OPERATION_FAILED.inst();
  }

  @Override
  public ResultStatus createVariant() {
    return ResultStatus.Standard.OPERATION_PENDING.inst();
  }
}
