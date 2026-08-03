package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.AdjustmentType;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AdjustmentType Xml Serialization Tests")
class AdjustmentTypeXmlTest extends AbstractXmlSerializationTestSuite<AdjustmentType> {

  @Override
  public Class<AdjustmentType> type() {
    return AdjustmentType.class;
  }

  @Override
  public AdjustmentType createDefault() {
    return AdjustmentType.Standard.INCREMENT.inst();
  }

  @Override
  public AdjustmentType createVariant() {
    return AdjustmentType.Standard.DECREMENT.inst();
  }
}