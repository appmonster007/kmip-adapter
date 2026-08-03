package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("ResultMessage Domain Tests")
class ResultMessageTest extends AbstractKmipDataTypeTestSuite<ResultMessage> {

  @Override
  protected Class<ResultMessage> type() {
    return ResultMessage.class;
  }

  @Override
  protected ResultMessage createDefault() {
    return ResultMessage
        .builder()
        .value("Success")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}