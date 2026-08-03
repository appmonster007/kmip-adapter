package org.purpleBean.kmip.codec.ttlv.model.v3_0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.type.IterationCount;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.v3_0.structure.PasswordCredential;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("PasswordCredential Ttlv Serialization Tests")
class PasswordCredentialTtlvTest extends AbstractTtlvSerializationTestSuite<PasswordCredential> {

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