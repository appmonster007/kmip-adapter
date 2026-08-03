package org.purpleBean.kmip.codec.json.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.RotateNameType;
import org.purpleBean.kmip.model.v2x1.structure.RotateName;
import org.purpleBean.kmip.model.v2x1.type.RotateNameValue;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RotateName Json Serialization Tests")
class RotateNameJsonTest extends AbstractJsonSerializationTestSuite<RotateName> {

  @Override
  public Class<RotateName> type() {
    return RotateName.class;
  }

  @Override
  public RotateName createDefault() {
    return RotateName.of(
        RotateNameValue.of("default"),
        RotateNameType.Standard.UNINTERPRETED_TEXT_STRING.inst());
  }

  @Override
  public RotateName createVariant() {
    return RotateName.of(
        RotateNameValue.of("variant"),
        RotateNameType.Standard.URI.inst());
  }
}