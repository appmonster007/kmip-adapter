package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AsynchronousCorrelationValues Ttlv Serialization Tests")
class AsynchronousCorrelationValuesTtlvTest
    extends AbstractTtlvSerializationTestSuite<AsynchronousCorrelationValues> {

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