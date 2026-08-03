package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.SplitKeyParts;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("SplitKeyParts JSON Serialization Tests")
class SplitKeyPartsJsonTest extends AbstractJsonSerializationTestSuite<SplitKeyParts> {

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