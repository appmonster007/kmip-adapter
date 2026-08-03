package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.ResultMessage;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ResultMessage TTLV Serialization Tests")
class ResultMessageTtlvTest extends AbstractTtlvSerializationTestSuite<ResultMessage> {

  @Override
  public Class<ResultMessage> type() {
    return ResultMessage.class;
  }

  @Override
  public ResultMessage createDefault() {
    return ResultMessage
        .builder()
        .value("Success")
        .build();
  }

  @Override
  public ResultMessage createVariant() {
    return ResultMessage
        .builder()
        .value("Failure")
        .build();
  }
}