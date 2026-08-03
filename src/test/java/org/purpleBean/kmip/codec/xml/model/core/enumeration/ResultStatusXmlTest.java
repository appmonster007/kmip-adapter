package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.ResultStatus;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ResultStatus XML Serialization")
class ResultStatusXmlTest extends AbstractXmlSerializationTestSuite<ResultStatus> {
  @Override
  public Class<ResultStatus> type() {
    return ResultStatus.class;
  }

  @Override
  public ResultStatus createDefault() {
    return ResultStatus.Standard.SUCCESS.inst();
  }

  @Override
  public ResultStatus createVariant() {
    return ResultStatus.Standard.OPERATION_FAILED.inst();
  }
}
