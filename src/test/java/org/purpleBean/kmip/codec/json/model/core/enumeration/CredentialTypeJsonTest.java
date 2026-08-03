package org.purplebean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CredentialType JSON Serialization")
class CredentialTypeJsonTest extends AbstractJsonSerializationTestSuite<CredentialType> {
  @Override
  public Class<CredentialType> type() {
    return CredentialType.class;
  }

  @Override
  public CredentialType createDefault() {
    return CredentialType.Standard.USERNAME_AND_PASSWORD.inst();
  }

  @Override
  public CredentialType createVariant() {
    return CredentialType.Standard.DEVICE.inst();
  }
}
