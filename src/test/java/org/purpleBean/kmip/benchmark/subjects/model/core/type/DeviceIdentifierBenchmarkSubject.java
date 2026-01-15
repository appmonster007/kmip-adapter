package org.purpleBean.kmip.benchmark.subjects.model.core.type;

import lombok.Getter;
import org.purpleBean.kmip.api.KmipContext;
import org.purpleBean.kmip.api.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.model.core.type.DeviceIdentifier;

public class DeviceIdentifierBenchmarkSubject extends KmipBenchmarkSubject<DeviceIdentifier> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public DeviceIdentifierBenchmarkSubject() throws Exception {
        DeviceIdentifier deviceIdentifier = DeviceIdentifier.builder().value("test-device-id").build();
        initialize(deviceIdentifier, DeviceIdentifier.class);
    }

    @Override
    public String name() {
        return "DeviceIdentifier";
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