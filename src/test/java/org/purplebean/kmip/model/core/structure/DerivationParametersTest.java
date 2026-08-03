package org.purplebean.kmip.model.core.structure;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purplebean.kmip.api.EncodingType;
import org.purplebean.kmip.api.KmipDataType;
import org.purplebean.kmip.api.KmipSpec;
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.model.core.type.DerivationData;
import org.purplebean.kmip.model.core.type.InitializationVector;
import org.purplebean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DerivationParameters Domain Tests")
class DerivationParametersTest extends AbstractKmipStructureTestSuite<DerivationParameters> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.UnknownVersion;
  }

  @Override
  protected Class<DerivationParameters> type() {
    return DerivationParameters.class;
  }

  @Override
  protected DerivationParameters createDefault() {
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
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 1;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSize(3);
    assertThat(values.get(0)).isInstanceOf(CryptographicParameters.class);
    assertThat(values.get(1)).isInstanceOf(InitializationVector.class);
    assertThat(values.get(2)).isInstanceOf(DerivationData.class);
  }
}