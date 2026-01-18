package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.NonceValue;

import java.nio.ByteBuffer;

public class NonceValueBenchmarkSubject extends KmipBenchmarkSubject<NonceValue> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public NonceValueBenchmarkSubject() throws Exception {
        NonceValue subject = NonceValue.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));  // TODO: Create a default instance
        initialize(subject, NonceValue.class);
    }

    @Override
    public String name() {
        return "NonceValue";
    }

}