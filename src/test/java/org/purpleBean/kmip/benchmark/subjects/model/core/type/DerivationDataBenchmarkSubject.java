package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DerivationData;

public class DerivationDataBenchmarkSubject extends KmipBenchmarkSubject<DerivationData> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public DerivationDataBenchmarkSubject() throws Exception {
        DerivationData derivationData = DerivationData.of(new byte[]{0x01, 0x02, 0x03});
        initialize(derivationData, DerivationData.class);
    }

    @Override
    public String name() {
        return "DerivationData";
    }

}