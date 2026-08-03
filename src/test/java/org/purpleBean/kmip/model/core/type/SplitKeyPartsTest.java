package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("SplitKeyParts Domain Tests")
class SplitKeyPartsTest extends AbstractKmipDataTypeTestSuite<SplitKeyParts> {

  @Override
  protected Class<SplitKeyParts> type() {
    return SplitKeyParts.class;
  }

  @Override
  protected SplitKeyParts createDefault() {
    return SplitKeyParts
        .builder()
        .value(2)
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.INTEGER;
  }
}