package org.purpleBean.kmip.codec.xml.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.ObjectTypes;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("ObjectTypes Xml Serialization Tests")
class ObjectTypesXmlTest extends AbstractXmlSerializationTestSuite<ObjectTypes> {

  @Override
  public Class<ObjectTypes> type() {
    return ObjectTypes.class;
  }

  @Override
  public ObjectTypes createDefault() {
    return ObjectTypes
        .builder()
        .build();
  }

  @Override
  public ObjectTypes createVariant() {
    return ObjectTypes
        .builder()
        .build();
  }
}