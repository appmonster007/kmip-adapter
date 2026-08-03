package org.purplebean.kmip.codec.json.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.AttributeReference;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttributeReference Json Serialization Tests")
class AttributeReferenceJsonTest extends AbstractJsonSerializationTestSuite<AttributeReference> {

  @Override
  public Class<AttributeReference> type() {
    return AttributeReference.class;
  }

  @Override
  public AttributeReference createDefault() {
    return AttributeReference
        .builder()
        .build();
  }

  @Override
  public AttributeReference createVariant() {
    return AttributeReference
        .builder()
        .build();
  }
}