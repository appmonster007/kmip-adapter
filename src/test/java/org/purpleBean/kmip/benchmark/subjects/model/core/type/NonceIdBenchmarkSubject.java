package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.NonceId;

import java.nio.ByteBuffer;

public class NonceIdBenchmarkSubject extends KmipBenchmarkSubject<NonceId> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion;

    public NonceIdBenchmarkSubject() throws Exception {
        NonceId subject = NonceId.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));
        initialize(subject, NonceId.class);
    }

    @Override
    public String name() {
        return "NonceId";
    }

}