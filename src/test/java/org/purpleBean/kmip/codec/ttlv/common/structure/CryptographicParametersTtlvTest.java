package org.purpleBean.kmip.codec.ttlv.common.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.CryptographicParameters;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("CryptographicParameters Ttlv Serialization Tests")
class CryptographicParametersTtlvTest extends AbstractTtlvSerializationTestSuite<CryptographicParameters> {

    @Override
    protected Class<CryptographicParameters> type() {
        return CryptographicParameters.class;
    }

    @Override
    protected CryptographicParameters createDefault() {
        return CryptographicParameters.builder()
                .blockCipherMode(new BlockCipherMode(BlockCipherMode.Standard.CBC))
                .paddingMethod(new PaddingMethod(PaddingMethod.Standard.PKCS5))
                .hashingAlgorithm(new HashingAlgorithm(HashingAlgorithm.Standard.SHA_256))
                .keyRoleType(new KeyRoleType(KeyRoleType.Standard.KEK))
                .digitalSignatureAlgorithm(new DigitalSignatureAlgorithm(DigitalSignatureAlgorithm.Standard.SHA_1_WITH_RSA_ENCRYPTION))
                .cryptographicAlgorithm(new CryptographicAlgorithm(CryptographicAlgorithm.Standard.AES))
                .randomIv(RandomIv.of(true))
                .ivLength(IvLength.of(128))
                .tagLength(TagLength.of(128))
                .fixedFieldLength(FixedFieldLength.of(128))
                .invocationFieldLength(InvocationFieldLength.of(128))
                .counterLength(CounterLength.of(128))
                .initialCounterValue(InitialCounterValue.of(1))
                .build();
    }
}