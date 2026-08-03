package org.purpleBean.kmip.codec.xml.model.v3x0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IterationCount;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.v3x0.structure.PasswordCredential;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("PasswordCredential Xml Serialization Tests")
class PasswordCredentialXmlTest extends AbstractXmlSerializationTestSuite<PasswordCredential> {

  @Override
  public Class<PasswordCredential> type() {
    return PasswordCredential.class;
  }

  @Override
  public PasswordCredential createDefault() {
    return PasswordCredential
        .builder()
        .password(Password.of("s3cr3t"))
        .build();
  }

  @Override
  public PasswordCredential createVariant() {
    return PasswordCredential
        .builder()
        .password(Password.of("passw0rd"))
        .iterationCount(IterationCount.of(1000))
        .build();
  }
}