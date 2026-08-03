package org.purplebean.kmip.codec.xml.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.AsynchronousIndicator;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AsynchronousIndicator Xml Serialization Tests")
class AsynchronousIndicatorXmlTest
    extends AbstractXmlSerializationTestSuite<AsynchronousIndicator> {

  @Override
  public Class<AsynchronousIndicator> type() {
    return AsynchronousIndicator.class;
  }

  @Override
  public AsynchronousIndicator createDefault() {
    return AsynchronousIndicator.Standard.values()[0].inst();
  }

  @Override
  public AsynchronousIndicator createVariant() {
    return AsynchronousIndicator.Standard.values()[1].inst();
  }
}