package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.DerivationData;
import java.nio.ByteBuffer;

public class DerivationDataBenchmarkSubject extends KmipBenchmarkSubject<DerivationData> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public DerivationDataBenchmarkSubject() throws Exception {
        DerivationData derivationData = DerivationData.of(new byte[]{0x01, 0x02, 0x03});
        initialize(derivationData, DerivationData.class);
    }

    @Override
    public String name() {
        return "DerivationData";
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