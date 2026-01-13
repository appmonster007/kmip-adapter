package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationBenchmarkSubject extends KmipBenchmarkSubject<MACSignatureKeyInformation> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public MACSignatureKeyInformationBenchmarkSubject() throws Exception {
        MACSignatureKeyInformation mACSignatureKeyInformation = MACSignatureKeyInformation.builder().value("test-info").build();
        initialize(mACSignatureKeyInformation, MACSignatureKeyInformation.class);
    }

    @Override
    public String name() {
        return "MACSignatureKeyInformation";
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