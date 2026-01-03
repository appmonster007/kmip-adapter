package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.structure.Digest;

public class DigestBenchmarkSubject extends KmipBenchmarkSubject<Digest> {

    public DigestBenchmarkSubject() throws Exception {
        Digest digest = Digest.builder()
                .hashingAlgorithm(new HashingAlgorithm(HashingAlgorithm.Standard.SHA_256))
                .digestValue(DigestValue.of(new byte[0]))
                .keyFormatType(new KeyFormatType(KeyFormatType.Standard.PKCS_1))
                .build();
        initialize(digest, Digest.class);
    }

    @Override
    public String name() {
        return "Digest";
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
