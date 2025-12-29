package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.DeviceSerialNumber;

public class DeviceSerialNumberBenchmarkSubject extends KmipBenchmarkSubject<DeviceSerialNumber> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public DeviceSerialNumberBenchmarkSubject() throws Exception {
        DeviceSerialNumber deviceSerialNumber = DeviceSerialNumber.builder().value("12345").build();
        initialize(deviceSerialNumber, DeviceSerialNumber.class);
    }

    @Override
    public String name() {
        return "DeviceSerialNumber";
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