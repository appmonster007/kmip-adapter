package org.purplebean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("EncryptionKeyInformation Xml Serialization Tests")
class EncryptionKeyInformationXmlTest
    extends AbstractXmlSerializationTestSuite<EncryptionKeyInformation> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<EncryptionKeyInformation> type() {
    return EncryptionKeyInformation.class;
  }

  @Override
  public EncryptionKeyInformation createDefault() {
    return EncryptionKeyInformation.of(
        UniqueIdentifier.of("fb44abe3-9721-43e0-a7d1-2568afe77d27"),
        CryptographicParameters
            .builder()
            .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
            .build()
    );
  }

  @Override
  public EncryptionKeyInformation createVariant() {
    return EncryptionKeyInformation.of(
        UniqueIdentifier.of("95275924-233f-42a5-a883-49cb51a3a6a3"),
        null
    );
  }
}