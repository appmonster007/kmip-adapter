package org.purpleBean.kmip.codec.ttlv.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.ProcessingStage;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("ProcessingStage TTLV Serialization")
class ProcessingStageTtlvTest extends AbstractTtlvSerializationTestSuite<ProcessingStage> {
  @Override
  public Class<ProcessingStage> type() {
    return ProcessingStage.class;
  }

  @Override
  public ProcessingStage createDefault() {
    return ProcessingStage.Standard.SUBMITTED.inst();
  }

  @Override
  public ProcessingStage createVariant() {
    return ProcessingStage.Standard.IN_PROCESS.inst();
  }
}
