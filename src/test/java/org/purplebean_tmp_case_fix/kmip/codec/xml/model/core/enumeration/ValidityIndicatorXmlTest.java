package org.purplebean.kmip.codec.xml.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.ValidityIndicator;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ValidityIndicator XML Serialization")
class ValidityIndicatorXmlTest extends AbstractXmlSerializationTestSuite<ValidityIndicator> {
  @Override
  public Class<ValidityIndicator> type() {
    return ValidityIndicator.class;
  }

  @Override
  public ValidityIndicator createDefault() {
    return ValidityIndicator.Standard.VALID.inst();
  }

  @Override
  public ValidityIndicator createVariant() {
    return ValidityIndicator.Standard.INVALID.inst();
  }
}
