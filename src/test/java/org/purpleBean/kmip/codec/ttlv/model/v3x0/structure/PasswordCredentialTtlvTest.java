package org.purplebean.kmip.codec.ttlv.model.v3x0.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.model.core.type.IterationCount;
import org.purplebean.kmip.model.core.type.Password;
import org.purplebean.kmip.model.v3x0.structure.PasswordCredential;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

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