package org.purpleBean.kmip.benchmark.subjects.model.core.structure.response;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponseHeader;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMajor;
import org.purpleBean.kmip.model.core.type.ProtocolVersionMinor;

public class SimpleResponseHeaderBenchmarkSubject extends KmipBenchmarkSubject<SimpleResponseHeader> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public SimpleResponseHeaderBenchmarkSubject() throws Exception {
        SimpleResponseHeader subject = SimpleResponseHeader.builder()
                .protocolVersion(ProtocolVersion.of(ProtocolVersionMajor.of(1), ProtocolVersionMinor.of(2)))
                .build();
        initialize(subject, SimpleResponseHeader.class);
    }

    @Override
    public String name() {
        return "SimpleResponseHeader";
    }
}
