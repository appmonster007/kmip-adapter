package org.purpleBean.kmip.model.v2x1.structure.request.payload;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
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
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

@DisplayName("DeriveKeyOpRequestPayload Domain Tests")
class DeriveKeyOpRequestPayloadTest
    extends AbstractKmipStructureTestSuite<DeriveKeyOpRequestPayload> {

  @Override
  protected void setupDefaultSpec() {
    defaultSpec = KmipSpec.V2_1;
  }

  @Override
  protected Class<DeriveKeyOpRequestPayload> type() {
    return DeriveKeyOpRequestPayload.class;
  }

  @Override
  protected DeriveKeyOpRequestPayload createDefault() {
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
  protected EncodingType expectedEncodingType() {
    return EncodingType.STRUCTURE;
  }

  @Override
  protected int expectedMinComponentCount() {
    return 5;
  }

  @Override
  protected void validateComponents(List<KmipDataType> values) {
    assertThat(values).hasSizeGreaterThanOrEqualTo(5);
    assertThat(values.get(0)).isInstanceOf(ObjectType.class);
    assertThat(values.get(1)).isInstanceOf(UniqueIdentifier.class);
    assertThat(values.get(2)).isInstanceOf(DerivationMethod.class);
    assertThat(values.get(3)).isInstanceOf(DerivationParameters.class);
  }
}