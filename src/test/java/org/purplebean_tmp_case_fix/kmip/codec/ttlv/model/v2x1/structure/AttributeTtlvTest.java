package org.purplebean.kmip.codec.ttlv.model.v2x1.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.v2x1.structure.Attribute;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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
        .vendorIdentification(org.purplebean.kmip.model.core.type.VendorIdentification.of("vendor"))
        .attributeName(org.purplebean.kmip.model.core.type.AttributeName.of("TestAttr"))
        .build();
  }

  @Override
  public Attribute createVariant() {
    return Attribute
        .builder()
        .vendorIdentification(org.purplebean.kmip.model.core.type.VendorIdentification.of("vendor"))
        .attributeName(org.purplebean.kmip.model.core.type.AttributeName.of("TestAttr"))
        .build();
  }
}