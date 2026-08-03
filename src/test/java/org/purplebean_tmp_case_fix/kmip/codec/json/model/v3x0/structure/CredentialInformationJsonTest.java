package org.purplebean.kmip.codec.json.model.v3x0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.enumeration.CredentialType;
import org.purplebean.kmip.model.v3x0.structure.CredentialInformation;
import org.purplebean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CredentialInformation Json Serialization Tests")
class CredentialInformationJsonTest
    extends AbstractJsonSerializationTestSuite<CredentialInformation> {

  @Override
  public Class<CredentialInformation> type() {
    return CredentialInformation.class;
  }

  @Override
  public CredentialInformation createDefault() {
    return CredentialInformation
        .builder()
        .credentialType(CredentialType.of(CredentialType.Standard.USERNAME_AND_PASSWORD))
        .build();
  }

  @Override
  public CredentialInformation createVariant() {
    return CredentialInformation
        .builder()
        .credentialType(CredentialType.of(CredentialType.Standard.ONE_TIME_PASSWORD))
        .credentialType(CredentialType.of(CredentialType.Standard.HASHED_PASSWORD))
        .build();
  }
}