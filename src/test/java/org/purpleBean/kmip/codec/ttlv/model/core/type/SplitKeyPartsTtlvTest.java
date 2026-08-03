package org.purpleBean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("SplitKeyParts TTLV Serialization Tests")
class SplitKeyPartsTtlvTest extends AbstractTtlvSerializationTestSuite<SplitKeyParts> {

  @Override
  public Class<SplitKeyParts> type() {
    return SplitKeyParts.class;
  }

  @Override
  public SplitKeyParts createDefault() {
    return SplitKeyParts
        .builder()
        .value(2)
        .build();
  }

  @Override
  public SplitKeyParts createVariant() {
    return SplitKeyParts
        .builder()
        .value(3)
        .build();
  }
}