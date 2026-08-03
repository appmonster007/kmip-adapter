package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.structure.CredentialValueGenericStructure;
import org.purplebean.kmip.model.core.type.Username;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("CredentialValueGenericStructure Xml Serialization Tests")
class CredentialValueGenericStructureXmlTest
    extends AbstractXmlSerializationTestSuite<CredentialValueGenericStructure> {

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