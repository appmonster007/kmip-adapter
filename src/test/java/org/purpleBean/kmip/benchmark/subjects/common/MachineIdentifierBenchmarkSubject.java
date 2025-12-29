package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.MachineIdentifier;

public class MachineIdentifierBenchmarkSubject extends KmipBenchmarkSubject<MachineIdentifier> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public MachineIdentifierBenchmarkSubject() throws Exception {
        MachineIdentifier machineIdentifier = MachineIdentifier.builder().value("test-machine-id").build();
        initialize(machineIdentifier, MachineIdentifier.class);
    }

    @Override
    public String name() {
        return "MachineIdentifier";
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