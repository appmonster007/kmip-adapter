package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.VendorIdentification;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("VendorIdentification TTLV Serialization Tests")
class VendorIdentificationTtlvTest
    extends AbstractTtlvSerializationTestSuite<VendorIdentification> {

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