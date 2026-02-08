package org.purpleBean.kmip.codec.xml.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("DerivationParameters Xml Serialization Tests")
class DerivationParametersXmlTest extends AbstractXmlSerializationTestSuite<DerivationParameters> {

    @Override
    protected void setupDefaultSpec() {
        defaultSpec = KmipSpec.UnknownVersion;
    }

    @Override
    public Class<DerivationParameters> type() {
        return DerivationParameters.class;
    }

    @Override
    public DerivationParameters createDefault() {
        return DerivationParameters.builder()
                .cryptographicParameters(CryptographicParameters.builder()
                        .blockCipherMode(BlockCipherMode.Standard.CBC.inst())
                        .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
                        .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
                        .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
                        .build())
                .initializationVector(InitializationVector.of(new byte[]{0x01, 0x02, 0x03}))
                .derivationData(DerivationData.of(new byte[]{0x04, 0x05, 0x06}))
                .build();
    }

    @Override
    public DerivationParameters createVariant() {
        return DerivationParameters.builder()
                .cryptographicParameters(CryptographicParameters.builder()
                        .blockCipherMode(BlockCipherMode.Standard.ECB.inst())
                        .paddingMethod(PaddingMethod.Standard.NONE.inst())
                        .hashingAlgorithm(HashingAlgorithm.Standard.SHA_512.inst())
                        .cryptographicAlgorithm(CryptographicAlgorithm.Standard.TRIPLE_DES.inst())
                        .build())
                .initializationVector(InitializationVector.of(new byte[]{0x07, 0x08, 0x09}))
                .build();
    }
}