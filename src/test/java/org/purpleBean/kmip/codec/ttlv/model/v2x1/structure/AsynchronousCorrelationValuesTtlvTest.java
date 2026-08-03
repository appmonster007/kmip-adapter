package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.AsynchronousCorrelationValues;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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