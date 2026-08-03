package org.purplebean.kmip.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.test.suite.AbstractKmipDataTypeTestSuite;

@DisplayName("AttestationCapableIndicator Domain Tests")
class AttestationCapableIndicatorTest
    extends AbstractKmipDataTypeTestSuite<AttestationCapableIndicator> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<AttestationCapableIndicator> type() {
    return AttestationCapableIndicator.class;
  }

  @Override
  protected AttestationCapableIndicator createDefault() {
    return AttestationCapableIndicator.of(true);
  }

  @Override
  protected EncodingType expectedEncodingType() {
    return EncodingType.BOOLEAN;
  }
}