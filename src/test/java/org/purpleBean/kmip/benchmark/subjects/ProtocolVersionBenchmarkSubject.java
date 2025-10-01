package org.purpleBean.kmip.benchmark.subjects;

import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.ProtocolVersion;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;

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
