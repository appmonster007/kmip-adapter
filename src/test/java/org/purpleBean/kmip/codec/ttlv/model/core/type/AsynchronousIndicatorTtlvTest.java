package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.AsynchronousIndicator;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AsynchronousIndicator TTLV Serialization Tests")
class AsynchronousIndicatorTtlvTest
    extends AbstractTtlvSerializationTestSuite<AsynchronousIndicator> {

  @Override
  public Class<AsynchronousIndicator> type() {
    return AsynchronousIndicator.class;
  }

  @Override
  public AsynchronousIndicator createDefault() {
    return AsynchronousIndicator
        .builder()
        .value(true)
        .build();
  }

  @Override
  public AsynchronousIndicator createVariant() {
    return AsynchronousIndicator
        .builder()
        .value(false)
        .build();
  }
}