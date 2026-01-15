package org.purpleBean.kmip.codec.json.model.core.structure;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.*;
import org.purpleBean.kmip.test.suite.AbstractJsonSerializationTestSuite;

@DisplayName("CryptographicParameters Json Serialization Tests")
class CryptographicParametersJsonTest extends AbstractJsonSerializationTestSuite<CryptographicParameters> {

    @Override
    protected Class<CryptographicParameters> type() {
        return CryptographicParameters.class;
    }

    @Override
    protected CryptographicParameters createDefault() {
        return CryptographicParameters.builder()
                .blockCipherMode(BlockCipherMode.Standard.CBC.inst())
                .paddingMethod(PaddingMethod.Standard.PKCS5.inst())
                .hashingAlgorithm(HashingAlgorithm.Standard.SHA_256.inst())
                .keyRoleType(KeyRoleType.Standard.KEK.inst())
                .digitalSignatureAlgorithm(DigitalSignatureAlgorithm.Standard.SHA_1_WITH_RSA_ENCRYPTION.inst())
                .cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst())
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