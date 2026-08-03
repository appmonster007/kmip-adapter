package org.purpleBean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("SplitKeyParts XML Serialization Tests")
class SplitKeyPartsXmlTest extends AbstractXmlSerializationTestSuite<SplitKeyParts> {

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