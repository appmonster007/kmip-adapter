package org.purpleBean.kmip.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipDataType;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractKmipStructureTestSuite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MACSignatureKeyInformation Domain Tests")
class MACSignatureKeyInformationTest extends AbstractKmipStructureTestSuite<MACSignatureKeyInformation> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    protected Class<MACSignatureKeyInformation> type() {
        return MACSignatureKeyInformation.class;
    }

    @Override
    protected MACSignatureKeyInformation createDefault() {
        return MACSignatureKeyInformation.of(
                UniqueIdentifier.of("fb44abe3-9721-43e0-a7d1-2568afe77d27"),
                CryptographicParameters.builder().cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst()).build()
        );
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
        assertThat(values).hasSize(2);
        assertThat(values.get(0)).isInstanceOf(UniqueIdentifier.class);
        assertThat(values.get(1)).isInstanceOf(CryptographicParameters.class);
    }
}