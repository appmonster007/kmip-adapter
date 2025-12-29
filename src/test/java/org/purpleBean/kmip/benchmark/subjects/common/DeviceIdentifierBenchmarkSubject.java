package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.DeviceIdentifier;

public class DeviceIdentifierBenchmarkSubject extends KmipBenchmarkSubject<DeviceIdentifier> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

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