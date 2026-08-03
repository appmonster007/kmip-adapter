package org.purpleBean.kmip.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.test.suite.AbstractKmipDataTypeTestSuite;


@DisplayName("AttestationCapability Domain Tests")
class AttestationCapabilityTest extends AbstractKmipDataTypeTestSuite<AttestationCapability> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AttestationCapability> type() {
    return AttestationCapability.class;
  }

  @Override
  protected AttestationCapability createDefault() {
    return AttestationCapability.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}