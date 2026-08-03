package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.ResultMessage;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResultMessage XML Serialization Tests")
class ResultMessageXmlTest extends AbstractXmlSerializationTestSuite<ResultMessage> {

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