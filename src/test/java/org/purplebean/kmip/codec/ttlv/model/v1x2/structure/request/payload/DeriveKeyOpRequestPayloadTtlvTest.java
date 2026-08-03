package org.purplebean.kmip.codec.ttlv.model.v1x2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.DerivationMethod;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.DerivationParameters;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.DerivationData;
import org.purplebean.kmip.model.core.type.InitializationVector;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DeriveKeyOpRequestPayload;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DeriveKeyOpRequestPayload Ttlv Serialization Tests")
class DeriveKeyOpRequestPayloadTtlvTest
    extends AbstractTtlvSerializationTestSuite<DeriveKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
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
            .initializationVector(InitializationVector.of(new byte[] {0x01, 0x02, 0x03}))
            .derivationData(DerivationData.of(new byte[] {0x04, 0x05, 0x06}))
            .build())
        .templateAttribute(TemplateAttribute
            .builder()
            .build())
        .build();
  }

  @Override
  public DeriveKeyOpRequestPayload createVariant() {
    return DeriveKeyOpRequestPayload
        .builder()
        .objectType(ObjectType.Standard.PUBLIC_KEY.inst())
        .uniqueIdentifier(UniqueIdentifier
            .builder()
            .value("uid2")
            .build())
        .derivationMethod(DerivationMethod.Standard.HASH.inst())
        .derivationParameters(DerivationParameters
            .builder()
            .cryptographicParameters(CryptographicParameters
                .builder()
                .blockCipherMode(BlockCipherMode.Standard.ECB.inst())
                .paddingMethod(PaddingMethod.Standard.NONE.inst())
                .hashingAlgorithm(HashingAlgorithm.Standard.SHA_512.inst())
                .cryptographicAlgorithm(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
                .build())
            .initializationVector(InitializationVector.of(new byte[] {0x07, 0x08, 0x09}))
            .build())
        .templateAttribute(TemplateAttribute
            .builder()
            .build())
        .build();
  }
}