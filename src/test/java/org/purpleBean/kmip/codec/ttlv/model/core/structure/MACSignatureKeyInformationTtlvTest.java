package org.purpleBean.kmip.codec.ttlv.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("MACSignatureKeyInformation Ttlv Serialization Tests")
class MACSignatureKeyInformationTtlvTest extends AbstractTtlvSerializationTestSuite<MACSignatureKeyInformation> {
    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.V1_2;
    }

    @Override
    public Class<MACSignatureKeyInformation> type() {
        return MACSignatureKeyInformation.class;
    }

    @Override
    public MACSignatureKeyInformation createDefault() {
        return MACSignatureKeyInformation.of(
                UniqueIdentifier.of("fb44abe3-9721-43e0-a7d1-2568afe77d27"),
                CryptographicParameters.builder().cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst()).build()
        );
    }

    @Override
    public MACSignatureKeyInformation createVariant() {
        return MACSignatureKeyInformation.of(
                UniqueIdentifier.of("95275924-233f-42a5-a883-49cb51a3a6a3"),
                null
        );
    }
}