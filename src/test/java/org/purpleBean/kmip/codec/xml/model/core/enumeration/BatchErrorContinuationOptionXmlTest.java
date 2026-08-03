package org.purpleBean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("BatchErrorContinuationOption XML Serialization")
class BatchErrorContinuationOptionXmlTest
    extends AbstractXmlSerializationTestSuite<BatchErrorContinuationOption> {
  @Override
  public Class<BatchErrorContinuationOption> type() {
    return BatchErrorContinuationOption.class;
  }

  @Override
  public BatchErrorContinuationOption createDefault() {
    return BatchErrorContinuationOption.Standard.CONTINUE.inst();
  }

  @Override
  public BatchErrorContinuationOption createVariant() {
    return BatchErrorContinuationOption.Standard.STOP.inst();
  }
}
