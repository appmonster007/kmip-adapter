package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;

import java.nio.ByteBuffer;

public class AttestationAssertionBenchmarkSubject extends KmipBenchmarkSubject<AttestationAssertion> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public AttestationAssertionBenchmarkSubject() throws Exception {
        AttestationAssertion subject = AttestationAssertion.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));  // TODO: Create a default instance
        initialize(subject, AttestationAssertion.class);
    }

    @Override
    public String name() {
        return "AttestationAssertion";
    }

}