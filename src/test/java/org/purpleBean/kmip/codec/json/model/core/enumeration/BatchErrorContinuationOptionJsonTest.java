package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.BatchErrorContinuationOption;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("BatchErrorContinuationOption JSON Serialization")
class BatchErrorContinuationOptionJsonTest
    extends AbstractJsonSerializationTestSuite<BatchErrorContinuationOption> {
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
