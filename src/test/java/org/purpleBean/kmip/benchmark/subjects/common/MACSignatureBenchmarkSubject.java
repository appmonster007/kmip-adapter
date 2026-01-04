package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.MACSignature;

public class MACSignatureBenchmarkSubject extends KmipBenchmarkSubject<MACSignature> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public MACSignatureBenchmarkSubject() throws Exception {
        MACSignature mACSignature = MACSignature.of(new byte[]{0x01, 0x02, 0x03});
        initialize(mACSignature, MACSignature.class);
    }

    @Override
    public String name() {
        return "MACSignature";
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