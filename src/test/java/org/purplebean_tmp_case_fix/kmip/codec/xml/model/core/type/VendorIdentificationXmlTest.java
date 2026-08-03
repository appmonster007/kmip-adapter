package org.purplebean.kmip.codec.xml.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("VendorIdentification XML Serialization Tests")
class VendorIdentificationXmlTest extends AbstractXmlSerializationTestSuite<VendorIdentification> {

  @Override
  public Class<VendorIdentification> type() {
    return VendorIdentification.class;
  }

  @Override
  public VendorIdentification createDefault() {
    return VendorIdentification
        .builder()
        .value("test-vendor")
        .build();
  }

  @Override
  public VendorIdentification createVariant() {
    return VendorIdentification
        .builder()
        .value("another-vendor")
        .build();
  }
}