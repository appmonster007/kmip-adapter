package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.v2_1.type.AttestationCapability;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("AttestationCapability Xml Serialization Tests")
class AttestationCapabilityXmlTest
    extends AbstractXmlSerializationTestSuite<AttestationCapability> {

  @Override
  public Class<AttestationCapability> type() {
    return AttestationCapability.class;
  }

  @Override
  public AttestationCapability createDefault() {
    return AttestationCapability.of(true);
  }

  @Override
  public AttestationCapability createVariant() {
    return AttestationCapability.of(false);
  }
}