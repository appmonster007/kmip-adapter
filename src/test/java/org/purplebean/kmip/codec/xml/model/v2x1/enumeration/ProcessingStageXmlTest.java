package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.ProcessingStage;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ProcessingStage XML Serialization")
class ProcessingStageXmlTest extends AbstractXmlSerializationTestSuite<ProcessingStage> {
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
