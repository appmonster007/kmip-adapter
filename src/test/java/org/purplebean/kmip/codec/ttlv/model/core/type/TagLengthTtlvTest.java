package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.TagLength;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("TagLength TTLV Serialization Tests")
class TagLengthTtlvTest extends AbstractTtlvSerializationTestSuite<TagLength> {

  @Override
  public Class<TagLength> type() {
    return TagLength.class;
  }

  @Override
  public TagLength createDefault() {
    return TagLength.of(128);
  }

  @Override
  public TagLength createVariant() {
    return TagLength.of(256);
  }
}