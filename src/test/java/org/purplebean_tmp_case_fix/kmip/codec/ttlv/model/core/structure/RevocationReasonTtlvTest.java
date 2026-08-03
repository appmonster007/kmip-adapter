package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.RevocationMessage;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("RevocationReason Ttlv Serialization Tests")
class RevocationReasonTtlvTest extends AbstractTtlvSerializationTestSuite<RevocationReason> {

  @Override
  public Class<RevocationReason> type() {
    return RevocationReason.class;
  }

  @Override
  public RevocationReason createDefault() {
    return RevocationReason
        .builder()
        .revocationReasonCode(RevocationReasonCode.Standard.KEY_COMPROMISE.inst())
        .revocationMessage(RevocationMessage.of("test-message"))
        .build();
  }
}
