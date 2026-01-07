package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.MacData;

import java.nio.ByteBuffer;

public class MacDataBenchmarkSubject extends KmipBenchmarkSubject<MacData> {

    @Getter
    private final KmipSpec spec = KmipSpec.V1_2;

    public MacDataBenchmarkSubject() throws Exception {
        byte[] data = "test mac data".getBytes();
        MacData macData = MacData.of(ByteBuffer.wrap(data));
        initialize(macData, MacData.class);
    }

    @Override
    public String name() {
        return "MacData";
    }

    @Override
    public void setup() {
        KmipContext.setSpec(spec);
    }

    @Override
    public void tearDown() {
        KmipContext.clear();
    }
}
