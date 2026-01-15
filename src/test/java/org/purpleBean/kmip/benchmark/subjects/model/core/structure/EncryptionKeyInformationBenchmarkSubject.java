package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

public class EncryptionKeyInformationBenchmarkSubject extends KmipBenchmarkSubject<EncryptionKeyInformation> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public EncryptionKeyInformationBenchmarkSubject() throws Exception {
        EncryptionKeyInformation subject = EncryptionKeyInformation.of(
                UniqueIdentifier.of("fb44abe3-9721-43e0-a7d1-2568afe77d27"),
                CryptographicParameters.builder().cryptographicAlgorithm(CryptographicAlgorithm.Standard.AES.inst()).build()
        );
        initialize(subject, EncryptionKeyInformation.class);
    }

    @Override
    public String name() {
        return "EncryptionKeyInformation";
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