package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.nio.ByteBuffer;

public class AttestationMeasurementBenchmarkSubject extends KmipBenchmarkSubject<AttestationMeasurement> {

    @Getter
    private final KmipSpec spec = KmipSpec.UnknownVersion; // TODO: Adjust if needed

    public AttestationMeasurementBenchmarkSubject() throws Exception {
        AttestationMeasurement subject = AttestationMeasurement.of(ByteBuffer.wrap(new byte[]{0x01, 0x02, 0x03}));  // TODO: Create a default instance
        initialize(subject, AttestationMeasurement.class);
    }

    @Override
    public String name() {
        return "AttestationMeasurement";
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