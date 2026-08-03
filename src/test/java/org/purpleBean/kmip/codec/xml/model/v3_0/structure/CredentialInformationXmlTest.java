package org.purpleBean.kmip.codec.xml.model.v3_0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;
import org.purpleBean.kmip.model.v3_0.structure.CredentialInformation;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CredentialInformation Xml Serialization Tests")
class CredentialInformationXmlTest
    extends AbstractXmlSerializationTestSuite<CredentialInformation> {

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