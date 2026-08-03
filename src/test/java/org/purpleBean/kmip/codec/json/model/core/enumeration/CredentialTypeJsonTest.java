package org.purpleBean.kmip.codec.json.model.core.enumeration;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

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
