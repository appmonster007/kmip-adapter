package org.purpleBean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2x1.enumeration.RotateNameType;
import org.purpleBean.kmip.model.v2x1.structure.RotateName;
import org.purpleBean.kmip.model.v2x1.type.RotateNameValue;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RotateName Ttlv Serialization Tests")
class RotateNameTtlvTest extends AbstractTtlvSerializationTestSuite<RotateName> {

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