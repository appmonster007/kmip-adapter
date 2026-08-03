package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CancellationResult;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CancellationResult XML Serialization")
class CancellationResultXmlTest extends AbstractXmlSerializationTestSuite<CancellationResult> {
  @Override
  public Class<CancellationResult> type() {
    return CancellationResult.class;
  }

  @Override
  public CancellationResult createDefault() {
    return CancellationResult.Standard.CANCELED.inst();
  }

  @Override
  public CancellationResult createVariant() {
    return CancellationResult.Standard.UNABLE_TO_CANCEL.inst();
  }
}
