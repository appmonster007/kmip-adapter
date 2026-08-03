package org.purplebean.kmip.codec.ttlv.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.type.AttestationCapableIndicator;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("AttestationCapableIndicator Ttlv Serialization Tests")
class AttestationCapableIndicatorTtlvTest
    extends AbstractTtlvSerializationTestSuite<AttestationCapableIndicator> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  public Class<AttestationCapableIndicator> type() {
    return AttestationCapableIndicator.class;
  }

  @Override
  public AttestationCapableIndicator createDefault() {
    return AttestationCapableIndicator.of(true);
  }

  @Override
  public AttestationCapableIndicator createVariant() {
    return AttestationCapableIndicator.of(false);
  }
}