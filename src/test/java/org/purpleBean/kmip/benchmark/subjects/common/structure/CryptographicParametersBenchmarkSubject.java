package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.CryptographicParameters;

public class CryptographicParametersBenchmarkSubject extends KmipBenchmarkSubject<CryptographicParameters> {

    public CryptographicParametersBenchmarkSubject() throws Exception {
        CryptographicParameters cryptographicParameters = CryptographicParameters.builder()
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
        initialize(cryptographicParameters, CryptographicParameters.class);
    }

    @Override
    public String name() {
        return "CryptographicParameters";
    }

    @Override
    public void setup() throws Exception {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}