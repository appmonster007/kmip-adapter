package org.purpleBean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("VendorIdentification Domain Tests")
class VendorIdentificationTest extends AbstractKmipDataTypeTestSuite<VendorIdentification> {

  @Override
  protected Class<VendorIdentification> type() {
    return VendorIdentification.class;
  }

  @Override
  protected VendorIdentification createDefault() {
    return VendorIdentification
        .builder()
        .value("test-vendor")
        .build();
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.TEXT_STRING;
  }
}