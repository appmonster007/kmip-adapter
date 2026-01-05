package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.structure.AlternativeName;

public class AlternativeNameBenchmarkSubject extends KmipBenchmarkSubject<AlternativeName> {

    public AlternativeNameBenchmarkSubject() throws Exception {
        // TODO: Create a new instance of the structure attribute.
        // For example:
        // AlternativeName alternativename = AlternativeName.builder()
        //         .hashingAlgorithm(new HashingAlgorithm(HashingAlgorithm.Standard.SHA_256))
        //         .digestValue(DigestValue.of(new byte[0]))
        //         .keyFormatType(new KeyFormatType(KeyFormatType.Standard.PKCS_1))
        //         .build();
        // initialize(alternativename, AlternativeName.class);
    }

    @Override
    public String name() {
        return "AlternativeName";
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