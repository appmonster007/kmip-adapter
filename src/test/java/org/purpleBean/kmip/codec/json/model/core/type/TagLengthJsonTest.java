package org.purplebean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.TagLength;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("TagLength JSON Serialization Tests")
class TagLengthJsonTest extends AbstractJsonSerializationTestSuite<TagLength> {

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