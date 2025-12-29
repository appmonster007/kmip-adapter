package org.purpleBean.kmip.benchmark.subjects.common;

import lombok.Getter;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;
import org.purpleBean.kmip.common.OpaqueDataValue;
import java.nio.ByteBuffer;

public class OpaqueDataValueBenchmarkSubject extends KmipBenchmarkSubject<OpaqueDataValue> {

    @Getter
    private KmipSpec spec = KmipSpec.V1_2;

    public OpaqueDataValueBenchmarkSubject() throws Exception {
        OpaqueDataValue opaqueDataValue = OpaqueDataValue.of(new byte[]{0x01, 0x02, 0x03});
        initialize(opaqueDataValue, OpaqueDataValue.class);
    }

    @Override
    public String name() {
        return "OpaqueDataValue";
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