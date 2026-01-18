package org.purpleBean.kmip.benchmark.subjects.model.core.structure;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;

public class ProtocolVersionBenchmarkSubject extends KmipBenchmarkSubject<ProtocolVersion> {

    public ProtocolVersionBenchmarkSubject() throws Exception {
        ProtocolVersion protocolVersion = ProtocolVersion.of(1, 0);
        initialize(protocolVersion, ProtocolVersion.class);
    }

    @Override
    public String name() {
        return "ProtocolVersion";
    }

}
