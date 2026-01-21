package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("DeriveKeyOpRequestPayload Domain Tests")
class DeriveKeyOpRequestPayloadTest extends AbstractKmipStructureTestSuite<DeriveKeyOpRequestPayload> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    protected Class<DeriveKeyOpRequestPayload> type() {
        return DeriveKeyOpRequestPayload.class;
    }

    @Override
    protected DeriveKeyOpRequestPayload createDefault() {
        return DeriveKeyOpRequestPayload.builder()
                .objectType(ObjectType.Standard.SYMMETRIC_KEY.inst())
                .uniqueIdentifier(UniqueIdentifier.builder().value("uid").build())
                .derivationMethod(DerivationMethod.Standard.HASH.inst())
                .derivationParameters(DerivationParameters.builder()
                        .cryptographicParameters(CryptographicParameters.builder()
                                .blockCipherMode(BlockCipherMode.Standard.CBC.inst())
                                .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
                                .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
                                .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
                                .build())
                        .initializationVector(InitializationVector.of(new byte[]{0x01, 0x02, 0x03}))
                        .derivationData(DerivationData.of(new byte[]{0x04, 0x05, 0x06}))
                        .build())
                .templateAttribute(TemplateAttribute.builder().build())
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
        assertThat(values).hasSize(5);
        assertThat(values.get(0)).isInstanceOf(ObjectType.class);
        assertThat(values.get(1)).isInstanceOf(UniqueIdentifier.class);
        assertThat(values.get(2)).isInstanceOf(DerivationMethod.class);
        assertThat(values.get(3)).isInstanceOf(DerivationParameters.class);
        assertThat(values.get(4)).isInstanceOf(TemplateAttribute.class);
    }
}