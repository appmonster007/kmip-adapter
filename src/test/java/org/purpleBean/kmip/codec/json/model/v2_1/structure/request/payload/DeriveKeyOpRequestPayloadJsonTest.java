package org.purpleBean.kmip.codec.json.model.v2_1.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.DeriveKeyOpRequestPayload;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("DeriveKeyOpRequestPayload Json Serialization Tests")
class DeriveKeyOpRequestPayloadJsonTest
    extends AbstractJsonSerializationTestSuite<DeriveKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = org.purpleBean.kmip.api.KmipSpec.V2_1;
  }

  @Override
  public Class<DeriveKeyOpRequestPayload> type() {
    return DeriveKeyOpRequestPayload.class;
  }

  @Override
  public DeriveKeyOpRequestPayload createDefault() {
    return DeriveKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid")
            .build())
        .derivationMethod(DerivationMethod.Standard.HASH.inst())
        .derivationParameters(DerivationParameters
            .builder()
            .cryptographicParameters(CryptographicParameters
                .builder()
                .blockCipherMode(BlockCipherMode.Standard.CBC.inst())
                .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
                .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
                .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
                .build())
            .initializationVector(InitializationVector.of(new byte[] {0x01, 0x02}))
            .derivationData(DerivationData.of(new byte[] {0x03, 0x04}))
            .build())
        .attributes(Attributes
            .builder()
            .attribute(CryptographicAlgorithm.Standard.AES.inst())
            .build())
        .build();
  }

  @Override
  public DeriveKeyOpRequestPayload createVariant() {
    return DeriveKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.PRIVATE_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid-2")
            .build())
        .derivationMethod(DerivationMethod.Standard.PBKDF2.inst())
        .derivationParameters(DerivationParameters
            .builder()
            .cryptographicParameters(CryptographicParameters
                .builder()
                .blockCipherMode(BlockCipherMode.Standard.ECB.inst())
                .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
                .hashingAlgorithm(HashingAlgorithm.Standard.SHA_384.inst())
                .cryptographicAlgorithm(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
                .build())
            .initializationVector(InitializationVector.of(new byte[] {0x05, 0x06}))
            .derivationData(DerivationData.of(new byte[] {0x07, 0x08}))
            .build())
        .attributes(Attributes
            .builder()
            .attribute(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
            .build())
        .build();
  }
}