package org.purplebean.kmip.codec.ttlv.model.v2x1.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.enumeration.AsynchronousIndicator;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AsynchronousIndicator Ttlv Serialization Tests")
class AsynchronousIndicatorTtlvTest
    extends AbstractTtlvSerializationTestSuite<AsynchronousIndicator> {

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