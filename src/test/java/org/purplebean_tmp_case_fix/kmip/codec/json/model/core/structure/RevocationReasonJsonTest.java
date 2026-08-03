package org.purplebean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.RevocationMessage;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("RevocationReason Json Serialization Tests")
class RevocationReasonJsonTest extends AbstractJsonSerializationTestSuite<RevocationReason> {

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
