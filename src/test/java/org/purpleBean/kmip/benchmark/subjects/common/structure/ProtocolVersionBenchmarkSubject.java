package org.purpleBean.kmip.benchmark.subjects.common.structure;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.structure.ProtocolVersion;

public class ProtocolVersionBenchmarkSubject extends KmipBenchmarkSubject<ProtocolVersion> {

    public ProtocolVersionBenchmarkSubject() throws Exception {
        ProtocolVersion protocolVersion = ProtocolVersion.of(1, 0);
        initialize(protocolVersion, ProtocolVersion.class);
    }

    @Override
    public String name() {
        return "ProtocolVersion";
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
