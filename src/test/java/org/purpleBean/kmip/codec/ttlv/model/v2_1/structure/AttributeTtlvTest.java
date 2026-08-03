package org.purpleBean.kmip.codec.ttlv.model.v2_1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.structure.Attribute;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Attribute Ttlv Serialization Tests")
class AttributeTtlvTest extends AbstractTtlvSerializationTestSuite<Attribute> {

  @Override
  public Class<Attribute> type() {
    return Attribute.class;
  }

  @Override
  public Attribute createDefault() {
    return Attribute
        .builder()
        .vendorIdentification(org.purpleBean.kmip.model.core.type.VendorIdentification.of("vendor"))
        .attributeName(org.purpleBean.kmip.model.core.type.AttributeName.of("TestAttr"))
        .build();
  }

  @Override
  public Attribute createVariant() {
    return Attribute
        .builder()
        .vendorIdentification(org.purpleBean.kmip.model.core.type.VendorIdentification.of("vendor"))
        .attributeName(org.purpleBean.kmip.model.core.type.AttributeName.of("TestAttr"))
        .build();
  }
}