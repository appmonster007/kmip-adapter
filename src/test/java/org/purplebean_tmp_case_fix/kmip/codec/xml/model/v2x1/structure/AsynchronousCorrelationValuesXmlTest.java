package org.purplebean.kmip.codec.xml.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AsynchronousCorrelationValues Xml Serialization Tests")
class AsynchronousCorrelationValuesXmlTest
    extends AbstractXmlSerializationTestSuite<AsynchronousCorrelationValues> {

  @Override
  public Class<AsynchronousCorrelationValues> type() {
    return AsynchronousCorrelationValues.class;
  }

  @Override
  public AsynchronousCorrelationValues createDefault() {
    return AsynchronousCorrelationValues
        .builder()
        .build();
  }

  @Override
  public AsynchronousCorrelationValues createVariant() {
    return AsynchronousCorrelationValues
        .builder()
        .build();
  }
}