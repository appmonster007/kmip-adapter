package org.purpleBean.kmip.codec.json.model.core.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.type.AttestationCapableIndicator;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("AttestationCapableIndicator Json Serialization Tests")
class AttestationCapableIndicatorJsonTest
    extends AbstractJsonSerializationTestSuite<AttestationCapableIndicator> {

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