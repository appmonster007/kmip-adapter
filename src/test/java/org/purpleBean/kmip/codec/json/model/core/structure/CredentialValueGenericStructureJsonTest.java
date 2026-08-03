package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.structure.CredentialValueGenericStructure;
import org.purpleBean.kmip.model.core.type.Username;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CredentialValueGenericStructure Json Serialization Tests")
class CredentialValueGenericStructureJsonTest
    extends AbstractJsonSerializationTestSuite<CredentialValueGenericStructure> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<CredentialValueGenericStructure> type() {
    return CredentialValueGenericStructure.class;
  }

  @Override
  public CredentialValueGenericStructure createDefault() {
    return CredentialValueGenericStructure
        .builder()
        .value(Username.of("test-value"))
        .build();
  }

  @Override
  public CredentialValueGenericStructure createVariant() {
    return CredentialValueGenericStructure
        .builder()
        .value(Username.of("test-value-variant"))
        .build();
  }
}