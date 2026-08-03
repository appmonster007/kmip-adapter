package org.purplebean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.structure.DerivationParameters;
import org.purplebean.kmip.model.core.type.DerivationData;
import org.purplebean.kmip.model.core.type.InitializationVector;
import org.purplebean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("DerivationParameters Ttlv Serialization Tests")
class DerivationParametersTtlvTest
    extends AbstractTtlvSerializationTestSuite<DerivationParameters> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V1_2;
  }

  @Override
  public Class<DerivationParameters> type() {
    return DerivationParameters.class;
  }

  @Override
  public DerivationParameters createDefault() {
    return DerivationParameters
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
        .build();
  }

  @Override
  public DerivationParameters createVariant() {
    return DerivationParameters
        .builder()
        .cryptographicParameters(CryptographicParameters
            .builder()
            .blockCipherMode(BlockCipherMode.Standard.ECB.inst())
            .paddingMethod(PaddingMethod.Standard.NONE.inst())
            .hashingAlgorithm(HashingAlgorithm.Standard.SHA_512.inst())
            .cryptographicAlgorithm(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
            .build())
        .initializationVector(InitializationVector.of(new byte[] {0x07, 0x08, 0x09}))
        .build();
  }
}