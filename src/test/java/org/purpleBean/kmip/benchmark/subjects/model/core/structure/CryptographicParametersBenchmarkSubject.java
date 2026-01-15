package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.*;

public class CryptographicParametersBenchmarkSubject extends KmipBenchmarkSubject<CryptographicParameters> {

    public CryptographicParametersBenchmarkSubject() throws Exception {
        CryptographicParameters cryptographicParameters = CryptographicParameters.builder()
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