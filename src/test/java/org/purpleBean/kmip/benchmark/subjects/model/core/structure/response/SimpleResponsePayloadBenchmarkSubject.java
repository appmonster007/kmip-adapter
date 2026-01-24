package org.purpleBean.kmip.benchmark.subjects.model.core.structure.response;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.structure.response.SimpleResponsePayload;

public class SimpleResponsePayloadBenchmarkSubject extends KmipBenchmarkSubject<SimpleResponsePayload> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public SimpleResponsePayloadBenchmarkSubject() throws Exception {
        SimpleResponsePayload subject = SimpleResponsePayload.builder().build();
        initialize(subject, SimpleResponsePayload.class);
    }

    @Override
    public String name() {
        return "SimpleResponsePayload";
    }
}
